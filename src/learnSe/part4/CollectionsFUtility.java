package learnSe.part4;
//4.1集合框架
//  Collections工具类
//知识点
//记忆
//    1.工具类，意味着方法全静态，这样的类没有创建对象的必要，通过私有构造方法限制其他类中创建该类对象
//    2.sort(List<T> list)  binarySearch(List<?> list,T key)  reverse(List<?> list)  max(Collection<?> coll)  shuffle(List<?> list)
//    3.练习    模拟斗地主
//了解
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
import java.util.HashMap;

public class CollectionsFUtility {

    //1.生成一副扑克
    //    定义一个List存索引，从0开始，定义一个HashMap存索引对应的牌，这样做是为了方便发牌后的手牌排序
    //    color和num数组，color.concat(num)对应牌面，最后单独加上大小王，每生成一张，index++，存入hashMap
    //2.洗牌
    //    Collections.shuffle(list)洗牌
    //3.发牌
    //    留三张底牌，剩下的牌索引从3开始
    //    根据索引，%3==0，1，2发给三个人手牌的集合
    //4.发牌后排序
    //    Collections.sort(shoupai);
    //5.看牌
    //    通过手牌的index，去map中读取对应牌面
    @Test
    public void poker() {
        //生成一副扑克
        String[] num = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K","A", "2" };
        String[] color = {"方片", "梅花", "红桃", "黑桃"};
        ArrayList<Integer> poker = new ArrayList<>();
        HashMap<Integer, String> hm = new HashMap<>();
        Integer index = 0;
        for (String s1 : num) {
            for (String s2 : color) {
                poker.add(index);
                hm.put(index, s2.concat(s1));
                index++;
            }
        }
        poker.add(index);
        hm.put(index, "小王");
        index++;
        poker.add(index);
        hm.put(index, "大王");
        //洗牌
        Collections.shuffle(poker);
        //发牌
        ArrayList<Integer> player1 = new ArrayList<>();
        ArrayList<Integer> player2 = new ArrayList<>();
        ArrayList<Integer> player3 = new ArrayList<>();
        ArrayList<Integer> dipai = new ArrayList<>();
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
        lookPoker(player1, hm);
        System.out.println("\n");
        lookPoker(player2, hm);
        System.out.println("\n");
        lookPoker(player3, hm);
        System.out.println("\n");
        lookPoker(dipai, hm);
    }

    private void lookPoker(ArrayList<Integer> player, HashMap<Integer, String> hm) {
        Collections.sort(player);
        for (Integer i : player) {
            System.out.println(hm.get(i));
        }
    }
}
