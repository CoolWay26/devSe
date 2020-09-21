package learnSe.part7;

//7.多线程
//记忆
//  1.并行并发区别
//
//1.概述
//  1.什么是线程 程序执行的路径，一个进程中可以包含多个线程
//  2.多线程存在的意义  提高程序效率，可以同时完成多项工作
//  3.应用场景例子    QQ同时和多人聊天，同时下载多个文件
//  4.多线程并行和并发的区别
//      并行：多个任务同时进行，需要多核CPU
//      并发：多个任务轮流进行，但时间间隔短，感觉上就像在同时运行
//  5.JVM运行原理：JVM启动后会启动一个主线程，该线程会去调用main()；JVM至少启动了主线程和垃圾回收线程，肯定是多线程运行方式
//2.线程的五种状态
//  1.新建状态（new）
//      使用 new 创建一个线程，仅仅只是在堆中分配了内存空间
//      新建状态下，线程还没有调用 start()方法启动，只是存在一个线程对象而已
//  2.就绪（runnable）
//      新建状态调用 start() 方法，线程已经被启动，正在等待被分配给CPU时间片，也就是说此时线程正在就绪队列中排队等候得到CPU资源
//      注意：线程对象只能调用一次 start() 方法，否则报错：illegaThreadStateExecptiong
//  3.运行状态（running）
//      线程获得CPU资源正在执行任务（run()方法）
//  4.阻塞状态（blocked）
//      正在运行的线程因为某种原因放弃 CPU，暂时停止运行，就会进入阻塞状态
//      此时 JVM 不会给线程分配 CPU，直到线程重新进入就绪状态，才有机会转到运行状态
//      阻塞状态分为多种情况，举例：
//          同步锁
//          计时等待timed waiting:  sleep(long t)
//          等待waiting:  wait()
//          被其他线程加入：    join()
//  5.终止状态（terminated）
//      线程终止，常见原因：
//          ①、正常终止，执行完 run()，正常结束
//          ②、强制终止，如调用 stop()或destory()（暴力行为不好），stop()已经被淘汰
//          ③、异常终止，执行过程中发生异常
//3.相关方法的简单说明
//    1.sleep(long millis)
//      线程阻塞，放弃CPU资源，在指定时间内不会获得执行机会，但不会释放同步锁
//      该方法更多的是用于模拟代码执行，让多线程并发的效果更明显
//    2.wait()
//      线程阻塞，放弃CPU资源，在指定时间内不会获得执行机会
//      此方法必须在同步代码块或同步方法中，会释放当前的锁
//    3.notify()/notifyAll()
//      唤醒线程，使线程进入就绪状态
//      此方法需和wait()成对使用，必须在同步代码块或同步方法中
//    4.join()
//      线程阻塞，执行调用该方法的线程
//    5. yield()
//      线程就绪，当前线程对象通知调度器自己愿意让出CPU资源
//      所以完全有可能：某个线程调用了yield()方法，但是线程调度器又把它调度出来重新执行，会考虑线程的优先级
public class MultiABasics {


}
