package learnSe.part4;
//4.1集合框架
//
//1.集合概述  数组长度固定，超过长度需要重新定义数组（System.arraycopy()）
//    区别于数组：
//        数组可以存引用数据类型、基本数据类型；集合只能存引用数据类型
//        数组长度固定；集合长度可变
//2.集合继承体系
//    Collection（根接口）
//        List（有序，可重复，有索引）
//            ArrayList（数组实现）
//            LinkedList（链表实现）
//            Vector（数组实现）
//        Set（无序，不可重复，无索引）
//            HashSet（哈希算法）
//            TreeSet（二叉树算法）
//3.数据结构之数组和链表
//    1.数组集合
//        查询，修改快
//            通过索引招到目标
//        增删慢
//            数组一旦被初始化，长度就不会改变
//            add()时，会判断add()后是否超长，超长则会新建数组，size增加oldCapacity /2
//            某个索引位置增加元素时，其后的元素都要往后移动一位
//            某个索引位置删除元素时，其后的元素都要往前移动一位
//    2.链表集合
//        查询，修改慢
//            先判断是从前还是从后找（二分判断离头尾哪个近），然后依次挨个存储单元找
//        增删快
//            每个存储单元，会记住链中前后存储单元的地址，从而形成链
//            指定索引插入元素时，只需要插入元素记住该索引前后单元的地址，就插入成功
//            删除也是，拿出一个元素，前后索引修改记忆的前后单元的地址
//4.Collection集合
//    1.基本功能    所有集合都有的方法
//        boolean add(E e)			//增加
//        boolean remove(Object o)	//删除    boolean remove(int index)
//        void clear()				//清空
//        boolean contains(Object o)	//判断是否包含
//        boolean isEmpty()			//判断是否为空
//        int size()					//获取元素个数
//        注意：集合都重写了自己的toString()方法
//        boolean addAll(Collection c)		//添加所有元素
//        boolean removeAll(Collection c)		//删除的是交集
//        boolean containsAll(Collection c)	//判断是否包含c中的每个元素（重复的也算包含）
//        boolean retainAll(Collection c)		//判断C是否包含调用者集合（是否被包含）
//            retain是保留的意思，调用者list1与list2做交集，结果集赋值给list1，如果list1被改变返回true，否则返回false（其实就是看交集是不是和调用者一样，交集肯定=小的那个）
//5.迭代器
//    用来访问集合的元素
//        c1.iterator()   返回迭代器的引用
//        it.hasNext()    判断集合中是否仍有元素可以迭代
//        it.next()       返回迭代的下一个元素
//            it.next()会移动指针到下一个位置,因为指针的移动，所以显然要谨慎调用next()，每调用一次都会移动指针，可能会导致取到的数据和预想不一样
//            ArrayList c1 = new ArrayList();
//            Iterator it = c1.iterator();
//            while(it.hasNext()) {
//                System.out.println(it.next());
//            }

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionsAGeneral {
    public static void main(String[] args) {
        ArrayList<Integer> c1 = new ArrayList();
        ArrayList<Integer> c2 = new ArrayList();
        //Collection基本功能
        c1.add(1);
        c1.remove(new Integer(1));
        c1.add(1);
        c1.remove(0);
        c1.clear();
        boolean bo1 = c1.contains(new Integer(1));
        boolean bo2 = c1.isEmpty();
        int size = c1.size();

        c1.addAll(c2);
        c1.removeAll(c2);
        boolean bo3 = c1.retainAll(c2);

        //Collection遍历
        c1.add(1);
        c1.add(2);
        c1.add(3);
        //toArray()转为数组进行遍历
//        Object[] objArr = c1.toArray();
//        for (Object obj : objArr) {
//            System.out.println((Integer)obj);
//        }
        //迭代器
//        Iterator it = c1.iterator();
//        while(it.hasNext()) {
//            System.out.println(it.next());
//        }
        //增强for循环，需要判空
        if (!c1.isEmpty()) {
            for (Integer integer : c1) {
                System.out.println(integer);
            }
        }



    }
}
