import ZSet.ZSkipList;
import ZSet.ZSkipListNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ZSkipList zSkipList = new ZSkipList();

        Set<String> sets = new HashSet<>();
        Map<String, String> treeMap = new TreeMap<>();


        long l = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            String s = UUID.randomUUID().toString();
            if (sets.add(s)){
                zSkipList.insertNode(s, (int) (Math.random() * Integer.MAX_VALUE));
                treeMap.put(s, s );
            }
        }

        zSkipList.foreachScore();
        System.out.println("==========");
        System.out.println(zSkipList.maxScore());
        System.out.println(zSkipList.minScore());


//        System.out.println((System.currentTimeMillis() - l) / 1000);

    }
}