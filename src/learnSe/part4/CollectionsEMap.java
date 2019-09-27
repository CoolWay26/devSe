package learnSe.part4;
//4.1集合框架
//  Map集合
//1.概述
//    1.键值对
//        1.不能包含重复的键，每个键最多只能映射一个值
//        2.如果使用自定义对象作为键，需要重写hashCode()和equals()来保证键的唯一性
//    2.集合子类命名是通过 数据结构+父类，HashMap,TreeMap数据结构都是针对键而言；Collection集合的数据结构是针对元素
//    3.单列和双列集合的关系
//        单列集合的底层是双列集合（单列集合隐藏了值列，只展示了键列），这样的好处是，用一种算法就能生成两种集合
//2.Map集合基本功能
//    1.增
//        V put(K key,V value):添加元素，返回值是被覆盖的value
//            注意：
//                HashMap是通过key的hashCode来计算索引的，与元素放入的先后顺序没有什么关系（hash是环形结构）
//                如果要保证压入的顺序一致，可以使用LinkedHashMap对象
//    2.删
//        void clear():移除所有的键值对元素
//        V remove(Object key)：根据键删除键值对元素，并把值返回
//    3.判断
//        boolean containsKey(Object key)：判断集合是否包含指定的键
//        boolean containsValue(Object value):判断集合是否包含指定的值
//        boolean isEmpty()：判断集合是否为空
//    4.获取
//        int size()：返回集合中的键值对的个数
//        Set<Map.Entry<K,V>> entrySet():获取所有键值对
//        Collection<V> values():获取集合中所有值的集合
//        Set<K> keySet():获取集合中所有键的集合
//        V get(Object key):根据键获取值
//3.遍历
//    1.键找值
//        Set<String> keySet = hashMap.keySet();
//        遍历key集合，get(key)取值
//    2.键值对
//        Set<Map.Entry<String,Integer>> entrySet = hashMap.entrySet();
//        遍历entrySet集合，getKey(),getValue()取值
//4.LinkedHashMap 底层是链表实现的可以保证怎么存就怎么取 Linked有序    Hash唯一  Map双列
//5.TreeMap
//6.练习
//    1.统计字符串中每个字符出现的频率
//7.HashTable
//    HashMap和Hashtable的区别
//        1.
//            Hashtable是JDK1.0版本出现的,是线程安全的,效率低
//            HashMap是JDK1.2版本出现的,是线程不安全的,效率高
//        2.
//            Hashtable不可以存储null键和null值
//            HashMap可以存储null键和null值
import org.junit.Test;
import java.util.*;

public class CollectionsEMap {

    public void mapDemo() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String str = (String) hashMap.put("name", "java");
        System.out.println(str);
    }

    public void traverseMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("一",1);
        hashMap.put("二",2);
        hashMap.put("三",3);
        hashMap.put("四",4);

        //键找值
        Set<String> keySet = hashMap.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String keyTemp = it.next();
            System.out.println(keyTemp + ":" + hashMap.get(keyTemp));
        }
        for (String keyTemp : keySet ) {
            System.out.println(keyTemp + ":" + hashMap.get(keyTemp));
        }

        //键值对
        Set<Map.Entry<String,Integer>> entrySet = hashMap.entrySet();
        //Map.Entry指的是Map的内部接口Entry，将键值对封装成Entry对象，存储在Set集合中，HashMap.Entry是子类
        Iterator<Map.Entry<String,Integer>> itForEntrySet = entrySet.iterator();
        //迭代器一定要记得加泛型，否则取值要强转
        while (itForEntrySet.hasNext()) {
            Map.Entry<String,Integer> entryTemp = itForEntrySet.next();
            //通过entry的getKey()和getValue()获取键值
            String keyTemp = entryTemp.getKey();
            Integer valueTemp = entryTemp.getValue();
            System.out.println(keyTemp + ":" + valueTemp);
        }
        for (Map.Entry<String,Integer> entryTemp : entrySet) {
            String keyTemp = entryTemp.getKey();
            Integer valueTemp = entryTemp.getValue();
            System.out.println(keyTemp + ":" + valueTemp);
        }
    }

    //统计字符串中每个字符的出现频率
    public void countChar() {
        String str = "aaaabbbcccccccccc";
        char[] chars = str.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (Character ch : chars) {
            if (hashMap.containsKey(ch)) {
                if (hashMap.get(ch) != null) {
                    int count = hashMap.get(ch) + 1;
                    hashMap.put(ch,count);
                }else {
                    hashMap.put(ch,1);
                }
            }else {
                hashMap.put(ch,1);
            }
        }
        System.out.println(hashMap);
    }

    @Test
    public void mapDemoTest() {
//        mapDemo();
//        traverseMap();
        countChar();
    }

}
