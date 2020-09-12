package learnSe.part4;
//4.1集合框架
//      List集合、三个子类
//知识点
//记忆
//    1.List特有成员方法  指定索引处add remove get set
//    2.并发修改异常    通常不在迭代过程中增删元素 ListIterator
//    3.ArrayList和LinkedList区别  数组查询快，链表增删快
//    4.LinkedList特有  addFirst addLast removeFirst removeLast getFirst getLast
//    5.练习  List集合遍历（）    对ArraysList去重   用LinkedList模拟栈结构
//了解
//    1.ListIterator  hasPrevious() previous() 当前索引处set add remove
//6.List  围绕索引拓展功能  特点：有索引、有序、可重复
//    1.List特有功能
//        void add(int index,E element)    指定索引位置添加元素
//        boolean remove(Object o)         删除某元素
//        E remove(int index)              删除指定索引位置元素，并返回该元素
//          add()会自动装箱，但是remove() 由于要存在重载函数，输入int会被当成索引，所以无法自动装箱
//        E get(int index)                 获取
//            显然可以通过get(int index)    方法遍历
//        E set(int index,E element)       修改
//    2.并发修改异常    ConcurrentModificationException并发修改异常
//        原因：在迭代的时候进行了集合的增删改操作，但是迭代器并不知道（每次迭代前会进行结构性变化的检查，modCount和expectedModCount是否相等）
//        解决：迭代器修改元素    ListIterator具有add()等方法，可以双向迭代list集合（因为有索引）
//            ListIterator lit = list.listiterator()
//            boolean hasNext()       是否有下一个
//            boolean hasPrevious()   是否有前一个
//            Object next()           返回下一个元素
//            Object previous()       返回上一个元素
//            void set(E e)           修改，当前索引处
//            void add(E e)           增加，在当前索引后
//            void remove()           删除，当前索引处，此方法在单线程的前提下，一般的迭代器也不会报错！
//                                    （因为此方法做了expectedModCount = modCount的操作）但remove(E e)方法是不行的
//    3.Vector类   vector实现了list接口，但已被ArrayList取代
//        1.特有功能
//            1.public void addElement(E obj) 增
//            2.public E elementAt(int index) 查
//            3.public Enumeration elements() 获取枚举
//        2.Vector的迭代（通过枚举进行迭代）
//            Enumeration en = v.elements();			    //获取枚举，这不是迭代，是枚举
//            while(en.hasMoreElements()) {			    //判断集合中是否有元素
//                System.out.println(en.nextElement());   //获取集合中的元素
//            }
//    4.ArrayList类
//        1.利用迭代器遍历和去重
//    5.LinkedList
//        1.特有功能  （链表的融入） 首尾增删
//            1.public void addFirst(E e)及addLast(E e)
//            2.public E getFirst()及getLast()
//            3.public E removeFirst()及public E removeLast()
//    6.List三个子类的特点
//        1.List特点    有序，可重复，有索引
//        2.三个子类特点
//            1.Vector    （不用了）
//                底层数据结构是数组，查询快，增删慢
//                线程安全，效率低
//                    Vector相对ArrayList查询慢(线程安全的)
//                    Vector相对LinkedList增删慢(数组结构)
//            2.ArrayList
//                底层数据结构是数组，查询快，增删慢
//                线程不安全，效率高
//            3.LinkedList
//                底层数据结构是链表，查询慢，增删快
//                线程不安全，效率高
//    7.栈和队列
//        1.栈：先进后出    队列：先进先出
//        2.LinkedList模拟栈


import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class CollectionsBList {
    public static void main(String[] args) {

    }

    //crud
    @Test
    public void crud() {
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        //有序,crud时关注一下返回值
        System.out.println(list);
        list.add(0,111);
        System.out.println(list);
        list.remove(0);     //删除某索引下的元素，返回该元素
        list.remove(new Integer(444));  //删除某元素，返回是否存在这个元素
        System.out.println(list);
        list.set(0,222);        //修改某索引下元素，返回oldElement
        System.out.println(list);
        System.out.println(list.get(0));
    }

    //List集合如何规避并发修改异常
    @Test
    public void modException() {
        ArrayList<String> list = new ArrayList();
        list.add("i");
        list.add("like");
        list.add("study");

        //如下操作会产生并发修改异常
        //迭代器要写在一般的集合操作之后，因为获取的是当前list的迭代器，不会跟随list的操作刷新
//        Iterator<String> it = list.iterator();
//        while (it.hasNext()) {
//            String strTemp = it.next();   //这里会抛出ConcurrentModificationException并发修改异常
//            if (strTemp.equals("study")) {
//                list.add(1,"dont");
//            }
//        }

        //应该如下操作，使用List特有的迭代器操作list，而不是直接使用list操作，这样迭代器就会知道list产生的变化
        ListIterator<String> listIt = list.listIterator();
        while (listIt.hasNext()) {
            String strTemp = listIt.next();
            if (strTemp.equals("like")) {
                listIt.add("!!!");      //注意这里的add()是在当前index后面add,结果是[i, like, !!!, study]
            }
        }
        System.out.println(list.toString());
    }

    //ArrayList去重
    @Test
    public void getSingle() {
        ArrayList<String> list = new ArrayList();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        list.add("c");
        System.out.println(list);

        ArrayList<String> newList = new ArrayList();
        ListIterator<String> listIt = list.listIterator();
        while (listIt.hasNext()) {
            String strTemp = listIt.next();
            if (!newList.contains(strTemp)) {       //由于contains底层依赖着equals方法，假如List集合中存的是自定义的对象，那么要重写该对象类的equals()方法
                newList.add(strTemp);
            }
        }
        if (newList.size() > 0) {
            System.out.println(newList.toString());
        }
    }

    //LinkedList特有方法模拟堆栈
    @Test
    public void linkedListStack() {
        Stack stack = new Stack();
        stack.in(1);
        stack.in(2);
        stack.in(3);
        stack.show();
        stack.out();
        stack.show();
        stack.out();
        stack.show();
        stack.out();
        stack.show();
    }
}

//LinkedList模拟栈结构
class Stack {
    //创建一个LinkedList对象
    private LinkedList linkedList = new LinkedList();

    //封装方法
    public void in(Object obj) {
        linkedList.addLast(obj);                            //封装addLast()方法
    }
    public Object out() {
        return linkedList.removeLast();                    //封装removeLast()方法
    }
    public boolean isEmpty() {
        return linkedList.isEmpty();                        //封装isEmpty()方法
    }
    public void show() {
        System.out.println(linkedList.toString());
    }
}
