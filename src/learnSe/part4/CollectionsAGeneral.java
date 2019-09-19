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
//    1.基本功能
//        boolean add(E e)			//增加
//        boolean remove(Object o)	//删除
//        void clear()				//清空
//        boolean contains(Object o)	//判断是否包含
//        boolean isEmpty()			//判断是否为空
//        int size()					//获取元素个数
//        注意：集合都重写了自己的toString()方法
//        boolean addAll(Collection c)		//添加所有元素
//        boolean removeAll(Collection c)		//删除的是交集
//        boolean containsAll(Collection c)	//判断是否包含c中的每个元素（重复的也算包含）
//        boolean retainAll(Collection c)		//判断C是否包含调用者集合
//            retain是保留的意思，调用者list1与list2做交集，结果集赋值给list1，如果list1被改变返回true，否则返回false（其实就是看交集是不是和调用者一样，交集肯定=小的那个）

import java.util.ArrayList;
import java.util.Collection;
public class CollectionsAGeneral {
    public static void main(String[] args) {
        Collection c = new ArrayList();
    }
}
