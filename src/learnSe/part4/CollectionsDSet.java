package learnSe.part4;
//4.集合框架
//  Set集合
//1.Set集合特点   无序，唯一，无索引
//    存储自定义对象的时候，要保证唯一就要重写hashCode()和equals()
//    所谓无须，是指存进set时各个元素的位置可能发生改变，而不是按先后顺序依次存储，并不是指每次取出来的时候顺序都会改变，对于同一个set，取多少次，元素的顺序都是一样的
//        通过Set集合的特点进行去重操作
//        对于非集合的目标，可以遍历，存进set，再输出
//        对于集合，可以直接addAll()
//2.HashSet
//    1.HashSet如何保证元素唯一性
//        重写hashCode()和equals()
//            注意
//                1.判断两个元素是否相同，先进行hashCode()的比较，hashCode()相同则再进行equals()比较，如果hashCode()不同那么直接认为两个元素不同
//                    为什么采用这样的方式比较？
//                        原因
//                            1.hashCode()是根据算法以地址值为依据计算出一个返回值，算法并不是完全可靠的
//                                结果相同：不可靠，不同的对象可能返回值会相同
//                                结果不同：可靠，是不同对象
//                                这很好理解：对于一个算法而言，输入值相同，结果一定相同；输入值如果不同，结果可能不同也可能相同
//                                            反过来就是，结果相同，不一定是同样的输入值；结果不同，一定是不同的输入值
//                              equals()比较的更为全面详细，是可靠的，但这样就意味着equals()效率低
//                            所以采用先hashCode()，hashCode()出现不可靠的相同情况，再用equals()进一步确认
//
//                        equals()比较的更为全面详细，是可靠的，那么为什么不直接用equals()比较呢？因为更为全面详细，就意味着equals()相比hashCode()而言效率很低
//                        对于hashCode()可以可靠的判断出元素不相同的情况，所以只在hashCode()相同的情况下再用equals()确认
//                2.为什么重写equals()一定要重写hashCode()
//                    有三个约定
//                        1.在一个应用程序执行期间，如果一个对象的equals方法做比较所用到的信息没有被修改的话，那么对该对象调用hashCode方法多次，它必须始终如一地返回同一个整数。
//                          在同一个应用程序的多次执行过程中，这个整数可以不同，即这个应用程序这次执行返回的整数与下一次执行返回的整数可以不一致
//                        2.如果两个对象根据equals(Object)方法是相等的，那么调用hashCode方法比较必须产生同样的整数结果
//                        3.如果根据equals(java.lang.Object)方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不一定生成不同的整数结果。
//                          但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。
//                    如果只重写了equals方法而没有重写hashCode方法的话，则会违反约定的第二条（将equals()改为比较内容，但是hashCode()仍然是以地址值为依据，从而equals()相等hashCode()不等）
//                    此外，还有一种说法
//                        针对HashSet和HashMap这些基于散列值（hash）实现的类，HashMap的底层处理机制是以数组的方法保存放入的数据的(Node<K,V>[] table)，其中的关键是数组下标的处理。
//                        数组的下标是根据传入的元素hashCode方法的返回值再和特定的值异或决定的-Key，
//                        如果以对象作为Key，又没有重写hashCode()，这时equals()完全一样的对象，会hashCode()并不一样，导致传进去的下标并不能找到对应的值
//3.LinkedHashSet
//    融入链表，可以保证怎么存就怎么取存   进去是a,b,c,d,取出来还是a,b,c,d
//4.TreeSet   元素排序，唯一
//    1.排序和保证唯一的原理：
//        底层是二叉树：小的存左边（return 负数），大的存右边（return 正数），不存（return 0）怎么存，取决于compareTo()方法的返回值
//    2.实现排序
//        自然顺序(Comparable)    通过Comparable接口的compareTo()方法
//            TreeSet类的add()方法中会把存入的对象提升为Comparable类型，然后调用对象的compareTo()方法和集合中的对象比较，根据compareTo()方法返回的结果进行存储
//            对于自定义对象，可以实现Comparable接口并重写compareTo()
//        比较器顺序(Comparator)--自定义排序
//            实现Comparator接口，重写compare()方法方法，将Comparator的实现类对象传给TreeeSet()对象构造方法
//            创建TreeSet的时候可以制定一个Comparator(传入一个Comparator的子类对象，重写compare()方法)
//            如果传入了Comparator的子类对象, 那么TreeSet就会按照比较器中的顺序排序
//            add()方法内部会自动调用Comparator接口中compare()方法排序
//            调用的对象是compare方法的第一个参数,集合中的对象是compare方法的第二个参数
import org.junit.Test;

import java.util.*;

public class CollectionsDSet {
    //利用set去重
    public<Gen extends Collection> void getSingle(Gen list) {
        if (list.size() > 0) {
            HashSet hashSet = new HashSet();
            hashSet.addAll(list);
            System.out.println(hashSet);
        }
    }

    //LinkedHashSet有序
    public void linkedHashSetDemo () {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("a");
        linkedHashSet.add("b");
        linkedHashSet.add("c");
        HashSet hashSet = new HashSet();
        hashSet.add("a");
        System.out.println(hashSet);
        hashSet.add("b");
        System.out.println(hashSet);
        hashSet.add("c");
        System.out.println(hashSet);
        System.out.println(linkedHashSet);
        System.out.println(linkedHashSet);
        System.out.println(linkedHashSet);
        System.out.println(hashSet);
        System.out.println(hashSet);
        System.out.println(hashSet);
    }

    //TreeSet比较器
    public void treeSetDemo () {
        TreeSet<Integer> treeSet = new TreeSet<>(new ComparatorImpl());
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(1);
        System.out.println(treeSet);    //[1, 2, 3]
    }



//    @Test
//    public void getSingleTest() {
//        ArrayList list = new ArrayList();
//        list.add("a");
//        list.add("a");
//        getSingle(list);
//    }

//    @Test
//    public void linkedHashSetDemoTest () {
//        linkedHashSetDemo();
//    }

    @Test
    public void treeSetTest() {
        treeSetDemo();
    }
}

class ComparatorImpl implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        int sub = (int)o1 - (int)o2;
        return sub;
    }
}


