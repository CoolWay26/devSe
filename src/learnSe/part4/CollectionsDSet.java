package learnSe.part4;
//4.集合框架
//  Set集合
//知识点
//记忆
//    1.无序的含义，如何保证元素的唯一性，为什么重写equals()一定要重写hashCode()
//    2.练习
//      遍历Set   迭代器，增强for
//      利用Set集合进行去重
//        对于非集合的目标，可以遍历，存进set，再输出
//        对于集合，可以直接addAll()
//      生成10个1-20不重复的随机数
//        Random()  nextInt(20)+1
//      字符串中的字符去重
//        toCharArray() LinkedHashSet StringBuilder
//    3.TreeSet 元素排序
//      Comparable  add()方法自动提升为Comparable类型，调用该对象的compareTo()方法进行比较，怎么存，取决于compareTo()方法的返回值
//      Comparator
//      练习：自定义对象通过TreeSet按指定方式排序
//了解
//    1.LinkedHashSet 通过引入链表来保证存储顺序不变
//
//1.Set集合特点
//    1.无序，唯一，无索引（Set集合没有什么特殊的成员方法，主要需要掌握的就是元素唯一的特点）
//    2.存储自定义对象的时候，要想使用Set去重或者保证唯一就要重写该类的hashCode()和equals()，通常用开发工具自动生成
//    3.无序的含义
//        所谓无序，是指数据存进set时各个元素的位置可能发生改变，而不是按先后顺序依次存储，并不是指每次取出来的时候顺序都会改变
//            对于没有增减元素的同一个set，取多少次，元素的顺序都是一样的
//2.HashSet
//    1.HashSet如何保证元素唯一性
//        重写hashCode()和equals()，两个都要重写
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
//                3.重写要达到的效果
//                    HashCode()    属性相同的对象-true   属性不同的要尽量-false
//3.LinkedHashSet
//    融入链表，可以保证怎么存就怎么取存   进去是a,b,c,d,取出来还是a,b,c,d
//4.TreeSet   元素排序，唯一
//    1.排序和保证唯一的原理：
//        底层是二叉树：小的存左边（return 负数），大的存右边（return 正数），不存（return 0）怎么存，取决于compareTo()方法的返回值
//        用二叉树的结构去理解，不要用数组的想法去理解：从根元素开始一直比较下去，直到自己成为最后的子元素
//    2.实现排序
//        自然顺序(Comparable)    通过Comparable接口的compareTo()方法
//            TreeSet类的add()方法中会把存入的对象提升为Comparable类型，然后调用对象的compareTo()方法和集合中的对象比较，根据compareTo()方法返回的结果进行存储
//            对于一般类的对象，已经进行了默认的comparaTo()重写，默认是字典顺序
//            对于自定义对象，可以实现Comparable接口并重写compareTo()，依次比较元素的一个或多个属性
//            属性一般都是基本数据类型，可以调用基本数据类型包装类的comparaTo()进行比较，因为ComparaTo()方法返回值是个int，不需进一步处理就符合要求
//              注意：关于comparaTo()方法的this.xxx - obj.xxx  这里的this指的是新存入的对象，obj指的是被比较的节点对象
//                    即，在重写时，是用新存入对象的属性 - 节点对象的属性，结果负数存入左子节点，正数存入右子节点
//        比较器顺序(Comparator)--自定义排序
//            需要创建一个比较器Comparator的子类对象
//            实现Comparator接口，重写compare()方法方法，将Comparator的实现类对象传给TreeeSet()对象构造方法
//            创建TreeSet的时候可以制定一个Comparator(传入一个Comparator的子类对象，重写compare()方法)
//            如果传入了Comparator的子类对象, 那么TreeSet就会按照比较器中的顺序排序
//            add()方法内部会自动调用Comparator接口中compare()方法排序
//            新存入的对象是compare方法的第一个参数,集合中的对象是compare方法的第二个参数
//              即obj1.comparaTo(obj2)，或者 obj1.xxx - obj2.xxx，这个顺序很重要，如果反过来，就会变成逆序
//        注意：在implements时要通过泛型给定比较对象的类型，否则重写的时候必须时默认的Object，因为重写要求声明一致
//              比较器的compara()方法有两个参数，而自然顺序的comparaTo()方法只有一个参数
//    3.练习
//         1.对一个List<String>集合排序，保留重复       通过比较器
//         2.对一个List<String>集合排序，保留重复，倒序输出      通过比较器
import org.junit.Test;

import java.util.*;
public class CollectionsDSet {
    //利用Set去重
    //如下是针对一般集合的去重，如果是存储自定义对象的集合去重，该对象的类一定要重写hashCode()和equals()方法（通常是由开发工具自动生成--右键Generate选项）
    @Test
    public void getSingleTest() {
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("a");
        getSingle(list);
    }
    private <T extends Collection> void getSingle(T list) {    //通过泛型extends限制给定目标必须是集合
        if (list.size() > 0) {
            HashSet hashSet = new HashSet();
            hashSet.addAll(list);
            System.out.println(hashSet);
        }
    }
    //对给定字符串中的字符去重
    @Test
    public void getSingleChar() {
        String str = "aaabbbcccdd";
        LinkedHashSet<Character> set = new LinkedHashSet();
        char[] arr = str.toCharArray();
        for (char ch : arr) {
            set.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : set) {
            sb.append(ch);
        }
        System.out.println(sb);
    }

    //生成1-20的10个随机数，int类型，不重复
    @Test
    public void randomSetTest() {
        Random rd = new Random();
        HashSet<Integer> set = new HashSet();
        while (set.size() < 10) {
            set.add(rd.nextInt(20) + 1);
        }
        System.out.println(set);
    }

    //LinkedHashSet有序
    @Test
    public void linkedHashSetTest() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("a");
        linkedHashSet.add("b");
        linkedHashSet.add("c");
        System.out.println(linkedHashSet);
    }

    //TreeSet   比较器
    @Test
    public void treeSetTest() {
        //方式一   implements Comparable，重写compareTo()，常见类都已经做了这个事情，只需要注意自定义类要自行编写
        TreeSet<Integer> integerTreeSet = new TreeSet<>();  //Integer已经implements Comparable，并重写了compareTo()
        integerTreeSet.add(3);
        integerTreeSet.add(1);
        integerTreeSet.add(2);
        System.out.println(integerTreeSet);

        //方式二   创建ComparatorImpl implements comparator，重写compare()；在创建TreeSet集合时给定comparatorImpl
        TreeSet<Integer> integerTreeSet1 = new TreeSet<>(new ComparatorImplForInteger());
        integerTreeSet1.add(3);
        integerTreeSet1.add(1);
        integerTreeSet1.add(2);
        System.out.println(integerTreeSet1);
    }

    //排序但不去重
    @Test
    public void sortStringTest() {
        ArrayList<String> list = new ArrayList<>();
        list.add("333");
        list.add("222");
        list.add("111");
        list.add("222");
        list.add("111");
        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {  //需要重复使用应当抽取为一个新的实现类而不是用匿名内部类
            @Override
            public int compare(String o1, String o2) {
                if (o1.compareTo(o2) > 0) {
                    return o1.compareTo(o2);
                } else {
                    return -1;
                }
            }
        });
        treeSet.addAll(list);
        System.out.println(treeSet);
    }

    //倒序排序
    @Test
    public void sortIntegerTest() {
        Integer[] arr = {1, 2, 2, 3, 4, 5, 5, 6, 6};
        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer int1, Integer int2) {
                int num = int2.compareTo(int1); //注意这里是用第二个参数调用方法compareTo()，结果为[6, 6, 5, 5, 4, 3, 2, 2, 1]，即为倒序
                if (num == 0) {
                    return 1;
                } else {
                    return num;
                }
            }
        });
        treeSet.addAll(Arrays.asList(arr));
        System.out.println(treeSet);
    }
}

class ComparatorImplForInteger implements Comparator<Integer> { //可以给定泛型，否则重写compare()时参数为Object
    @Override
    public int compare(Integer integer1, Integer integer2) {
        return integer1-integer2;
    }
}


