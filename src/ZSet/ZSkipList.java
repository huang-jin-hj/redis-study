package ZSet;

public class ZSkipList {

    public static int MAX_LEVEL = 32;

    public static final double Z_SKIP_LIST_P = 0.25;

    ZSkipListNode head, tail;
    int len = 0;
    int level = 1;

    public ZSkipList() {
        this.head = createNode(null, 0, MAX_LEVEL);
    }


    @SuppressWarnings("all")
    public boolean insertNode(String member, int score){
        ZSkipListNode zSkipListNode = createNode(member, score, randomLevel());
        return insertNode(zSkipListNode);
    }

    public void foreachScore(){
        ZSkipListNode cur = this.head.zSkipListLevels[0].forward;
        while (cur != null){
            System.out.println(cur.score);
            cur = cur.zSkipListLevels[0].forward;
        }
    }

    public void foreachMem(){
        ZSkipListNode cur = this.head.zSkipListLevels[0].forward;
        while (cur != null){
            System.out.println(cur.member);
            cur = cur.zSkipListLevels[0].forward;
        }
    }

    public void reverseForeachScore(){
        ZSkipListNode cur = this.tail;
        while (cur != null){
            System.out.println(cur.score);
            cur = cur.backWord;
        }
    }

    public void reverseForeachMem(){
        ZSkipListNode cur = this.tail;
        while (cur != null){
            System.out.println(cur.member);
            cur = cur.backWord;
        }
    }

    public int maxScore(){
        if (this.len == 0){
            throw new RuntimeException("无数据");
        }
        return this.tail.score;
    }

    public int minScore(){
        if (this.len == 0){
            throw new RuntimeException("无数据");
        }
        return this.head.zSkipListLevels[0].forward.score;
    }


// ===========// ===========// =========== 内部调用 ===========// ===========// ===========// ===========
    private boolean insertNode(ZSkipListNode zSkipListNode) {
        ZSkipListNode[] backWordNode = new ZSkipListNode[MAX_LEVEL];
        int[] backWordLen = new int[MAX_LEVEL];
        ZSkipListNode currentNode = this.head;
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            backWordLen[i] = i == MAX_LEVEL - 1 ? 0 : backWordLen[i + 1];
            while (currentNode.zSkipListLevels[i].forward != null
                    && ((currentNode.zSkipListLevels[i].forward.score < zSkipListNode.score)
                    || (currentNode.zSkipListLevels[i].forward.score == zSkipListNode.score && currentNode.zSkipListLevels[i].forward.member.compareTo(zSkipListNode.member) < 0))) {
                backWordLen[i] += currentNode.zSkipListLevels[i].span;
                currentNode = currentNode.zSkipListLevels[i].forward;
            }
            backWordNode[i] = currentNode;
        }


        for (int i = this.level - 1; i > zSkipListNode.zSkipListLevels.length - 1; i--) {
            backWordNode[i].zSkipListLevels[i].span += 1;
        }


        for (int i = zSkipListNode.zSkipListLevels.length - 1; i >= 0; i--) {
            zSkipListNode.zSkipListLevels[i].forward = backWordNode[i].zSkipListLevels[i].forward;
            backWordNode[i].zSkipListLevels[i].forward = zSkipListNode;

            zSkipListNode.zSkipListLevels[i].span = backWordNode[i].zSkipListLevels[i].span - (backWordLen[0] - backWordLen[i]);
            backWordNode[i].zSkipListLevels[i].span = backWordLen[0] - backWordLen[i] + 1;
        }


        for (int i = zSkipListNode.zSkipListLevels.length - 1; i > this.level - 1; i--) {
            this.head.zSkipListLevels[i].forward = zSkipListNode;
            this.head.zSkipListLevels[i].span = backWordLen[0] + 1;
            zSkipListNode.zSkipListLevels[i].span = this.len - backWordLen[0];
        }

        if (zSkipListNode.zSkipListLevels[0].forward == null) {
            this.tail = zSkipListNode;
        } else {
            zSkipListNode.zSkipListLevels[0].forward.backWord = zSkipListNode;
        }

        if (backWordNode[0] != this.head) {
            zSkipListNode.backWord = backWordNode[0];
        }

        this.len += 1;
        this.level = Math.max(this.level, zSkipListNode.zSkipListLevels.length);

        return true;
    }


    private ZSkipListNode createNode(String member, int score, int level) {
        ZSkipListNode zSkipListNode = new ZSkipListNode(member, score);
        ZSkipListNode.ZSkipListLevel[] zSkipListLevels = new ZSkipListNode.ZSkipListLevel[level];
        for (int i = 0; i < level; i++) {
            zSkipListLevels[i] = new ZSkipListNode.ZSkipListLevel();
        }
        zSkipListNode.zSkipListLevels = zSkipListLevels;
        return zSkipListNode;
    }

    private static int randomLevel(){
        int level = 1;
        int threshold = (int) (Integer.MAX_VALUE * Z_SKIP_LIST_P);
        while ((Math.random() * Integer.MAX_VALUE) < threshold){
            level++;
        }
        return Math.min(ZSkipList.MAX_LEVEL, level);
    }

}
