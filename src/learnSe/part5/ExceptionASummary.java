package learnSe.part5;
//5.异常
//      概述
//知识点
//记忆
//    1.异常分类  RuntimeException最重要，通常是因为程序员考虑不周
//    2.异常处理
//        何时需要异常处理：不能通过修改代码人为避免的异常
//        异常处理机制  优先在方法内处理，否则向调用者寻求解决方法   JVM默认的处理方式printStackTrace
//            方法内处理的两种方式
//                try catch finally
//                throw throws   向上抛出，交给上一级调用者处理
//    3.编译时和运行时异常的区别 编译时异常必须处理才能通过编译；运行时异常可以不处理，但可能导致程序运行出现问题
//了解
//    1.异常父类Throwable的常用方法 getMessage toString printStackTrace
//1.概述
//    异常就是Java程序在运行过程中出现的错误
//2.分类
//    Throwable
//        Error
//        Exception
//          IOException
//          RuntimeException    运行时异常通常都是程序员编程考虑不周
//              IndexOutOfBoundsException等等
//3.异常处理
//    1.何时需要进行异常处理
//        对于可以通过修改代码而避免的异常，不应考虑异常处理机制，而是应该通过严谨的代码逻辑去避免
//        异常处理机制针对的是人为无法避免的异常，但实际上对于此类异常，也可以通过异常处理机制进行处理
//    2.异常处理机制
//        方法内的异常，优先在方法内处理，否则会向上寻求处理方法，最后找不到交由JVM处理
//        只要方法内异常被处理，后续代码仍然可以执行
//    3.JVM默认的处理方法
//        如果程序发生异常，向上直到main函数都没有设置处理方法，则交由JVM处理
//        JVM默认将抛出一个异常对象，输出异常的名称和信息和异常出现的位置，然后终止运行程序
//    4.方法内处理
//        1.try catch finally
//        2.throws
//3.编译时和运行时异常
//    1.编译时异常
//        必需处理，否则无法通过编译
//    2.运行时异常   所有的RuntimeException类及其子类的实例被称为运行时异常
//        一般因为程序考虑不周导致，可以和编译一样处理
//        如果不处理，可能会导致程序后续运行出现问题
//4.Throwable
//    Throwable是Exception的父类（继承体系）
//    1.Throwable的几个常见方法
//        1.getMessage()      获取异常信息，返回字符串
//        2.toString()        获取异常类名和异常信息，返回字符串
//        3.printStackTrace() 获取异常类名和异常信息，以及异常出现在程序中的位置，返回值void(jvm默认使用该种处理方式)
public class ExceptionASummary {

}
