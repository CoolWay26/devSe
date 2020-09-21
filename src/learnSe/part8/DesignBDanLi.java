package learnSe.part8;

import org.junit.Test;

//8.设计模式
//
//2.单例设计模式
//  1.保证类在内存中仅有一个实例，并提供一个它的全局访问方式
//  2.实现思路
//      1.私有构造方法：这样其他类中就无法创建该类对象，这也是单例模式的关键所在
//      2.私有本类对象：本类中创建一个本类对象
//              私有，这样其他类中无法直接访问该对象去做修改
//              其他类无法创建对象，那么就要通过static方法直接获取，那么该对象也要定义为static，否则不能用于static方法
//      3.提供公共方法获取该类对象  static
//  3.实现方式
//      1.懒汉式   对象被需要时才创建
//          线程不安全   最简单的单例
//              这样针对多线程不安全，可能会创建多个实例（判断为null，进入方法后暂停，其他线程进来仍然判断为null，就会发生这样的情况）
//          线程安全     通过给public static方法加锁实现同步-线程安全，但加锁就会降低效率
//      2.饿汉式   对象在声明时就创建，比较常用，但随之而来的是产生垃圾对象
//          类加载时就初始化，浪费内存；但不需要判断是否为null，减少了运行时的时间
//          最重要的是，在多线程调用getSingle()获取该唯一实例时，不会存在懒汉式那种不安全的情况，不要用synchronized保证安全，也是节约运行时间
//          在程序运行时肯定是时间更重要
//

public class DesignBDanLi {

    @Test
    public void singleTest() {

    }

}

//懒汉式
class SingleLanHan {
    //private static本类对象，懒汉不创建对象
    private static SingleLanHan singleLanHan;

    //私有构造方法
    private SingleLanHan() {
    }

    //public static方法返回该对象
//    public static SingleLanHan getSingle() {
//        if (singleLanHan == null) {
//            singleLanHan = new SingleLanHan();
//        }
//        return singleLanHan;
//    }

    //线程安全
    public static synchronized SingleLanHan getSingle() {
        if (singleLanHan == null) {
            singleLanHan = new SingleLanHan();
        }
        return singleLanHan;
    }

}

class SingleEHan {
    //私有本类对象
    private static SingleEHan singleEHan = new SingleEHan();

    //私有构造方法
    private SingleEHan() {

    }

    //public static方法返回该对象
    public static SingleEHan getSingle() {
        return singleEHan;
    }
}
