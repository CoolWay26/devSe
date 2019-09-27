package learnSe.part4;
//4.1集合框架
//  Collections工具类
//1.方法
//    public static <T> void sort(List<T> list)   字典顺序
//    public static <T> int binarySearch(List<?> list,T key)  二分查找
//        调用此方法前，必须已经升序排列，否则返回值不确定
//        如果集合中有多个目标元素，不确定会找到哪一个
//        找到，返回索引，找不到，返回-（目标元素应该所在索引-1）
//    public static <T> T max(Collection<?> coll) 字典顺序最大值
//    public static void reverse(List<?> list)    反转
//    public static void shuffle(List<?> list)    随机置换（类似洗牌）
//2.练习    模拟扑克洗牌发牌
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
public class CollectionsFUtility {
    public void poker() {
        //生成一副扑克
        String[] num = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] color = {"方片", "梅花", "红桃", "黑桃"};
        ArrayList<String> poker = new ArrayList<>();
        for (String s1 : color) {
            for (String s2 : num) {
                poker.add(s1.concat(s2));
            }
        }
        poker.add("小王");
        poker.add("大王");
        //洗牌
        Collections.shuffle(poker);
        //发牌
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();
        if (!poker.isEmpty()) {
            for (int i = 0; i < poker.size(); i++) {
                if (i == 0 || i == 1 || i == 2) {
                    dipai.add(poker.get(i));
                } else if (i % 3 == 0) {
                    player1.add(poker.get(i));
                } else if (i % 3 == 1) {
                    player2.add(poker.get(i));
                } else {
                    player3.add(poker.get(i));
                }
            }
        }
        //看牌
        System.out.println(dipai);
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
    }

    @Test
    public void pokerTest() {
        poker();
    }
}
