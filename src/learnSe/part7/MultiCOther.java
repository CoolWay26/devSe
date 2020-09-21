package learnSe.part7;
//7.多线程
//记忆
//  1.了解一下常见方法即可
//  2.Timer定时器类
//3.针对进程的常见操作
//  静态方法：
//  1.获取当前正在执行的线程
//      Thread.currentThread()  主线程也可以获取，获取线程后可以对线程进行操作，如
//          Thread.currentThread().getName()    Thread.currentThread().setName()
//  2.休眠线程
//      Thread.sleep(毫秒,纳秒), 控制当前线程休眠若干毫秒（注意单位是毫秒），必须给定一个时长
//      注意：sleep()方法在同步代码中并不释放锁，这很重要！
//          如果在run()中由sleep()产生的异常只能trycatch,不能throw,因为父类Thread没有做抛出异常的动作,其子类就也不能做
//  3.礼让线程
//      public static void yield()
//      暂停当前正在执行的线程对象，并执行其他线程
//
//  一般方法
//  1.获取和设置线程名
//      创建时通过Thread构造设置，或者进程执行体中通过this.setName("...")
//      getName()
//  2.守护线程
//      public final void setDaemon(boolean on) 设置一个线程（对象）为守护线程
//      该线程不会单独执行, 当其他非守护线程都执行结束后, 守护线程会在稍后（几乎是瞬间）自动退出
//      当其他线程没有退出时，守护线程正常执行
//  3.加入线程
//      public final void join(long millisec)
//      当前线程暂停, 等待join()的线程执行结束后, 当前线程再继续，可以给定参数为最长等待时间
//  4.设置线程优先级
//      public final void setPriority(int priority)
//      1-10    10最大	1最小
//      优先级高的更容易被系统执行
//4.Runtime类    Runtime类是一个单例类，使程序与运行环境相连接
//  1.  Runtime r = Runtime.getRuntime();
//      r.exec("shutdown -s -t 300");	//300秒后关机，exec()中可以执行字符串命令（dos）
//      r.exec("shutdown -a");			//取消关机
//5.Timer类  计时器类，指定时间安排指定任务，且可以固定间隔时间重复执行
//  1.方法
//      public void schedule(TimerTask task, Date firstTime, long period)   从firstTime开始，每隔period时间执行一次task任务
//          new Date(year,month,date,hrs,min,sec)
//              year是距离1900年的偏移量，2020年为120
//              month是0-11，九月为8
//  2.TimerTask 任务类     run()中写的就是要执行的任务
//

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MultiCOther {
    public static void main(String[] args) {

    }

    //获取、设置线程名
    @Test
    public void other1() {
        //设置
        Thread t1 = new Thread("线程1");
        //获取
        System.out.println(t1.getName());

        //设置2
        new Thread() {
            public void run() {
                this.setName("xiancheng1");
                System.out.println(this.getName());
            }
        }.start();
    }

    //休眠线程
    @Test
    public void sleepTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<10;i++) {
                    try {
                        System.out.println(i);
                        Thread.sleep(1000);     //这里的异常不能向上抛出,因为父类Thread没有进行throws操作,子类就也不能抛出异常
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //守护线程
    @Test
    public void daemonTest() {
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(getName() + "用户线程" + " " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println(getName() + "守护线程");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.setDaemon(true);        //将t2设置为守护线程，会发现只要t1线程结束，那么t2会在稍后就结束
        t1.start();
        t2.start();
    }

    //加入线程（插队）
    @Test
    public void joinTest() {
        final Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println(getName() + "线程1 " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if (i == 2) {
                        try {
                            //t1.join();		//插队,加入
                            t1.join(30);    //加入,有固定的时间,过了固定时间,继续交替执行
                            Thread.sleep(10);
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    }
                    System.out.println(getName() + "线程2 " + i);
                }
            }
        };

        t1.start();
        t2.start();
    }

    //Timer和TimerTask
    @Test
    public void timeTest() throws InterruptedException {
        Timer t = new Timer();
//指定时间开始执行，每隔3000ms重复执行一次
        t.schedule(new MyTimerTask(), new Date(120,8,20,22,22,35), 3000);

        while(true) {
            System.out.println(new Date());
            Thread.sleep(1000);
        }
    }
}

class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("this is TimerTask");
    }
}