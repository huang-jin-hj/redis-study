package ZSet;

public class ZSkipListNode {
    ZSkipListNode backWord;
    String member;
    int score;

    ZSkipListLevel[] zSkipListLevels;

    public ZSkipListNode getBackWord() {
        return backWord;
    }

    public void setBackWord(ZSkipListNode backWord) {
        this.backWord = backWord;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ZSkipListLevel[] getzSkipListLevels() {
        return zSkipListLevels;
    }

    public void setzSkipListLevels(ZSkipListLevel[] zSkipListLevels) {
        this.zSkipListLevels = zSkipListLevels;
    }

    private static class ZSkipListLevel{
        ZSkipListNode forward;
        int span;

        public ZSkipListNode getForward() {
            return forward;
        }

        public void setForward(ZSkipListNode forward) {
            this.forward = forward;
        }

        public int getSpan() {
            return span;
        }

        public void setSpan(int span) {
            this.span = span;
        }
    }
}
