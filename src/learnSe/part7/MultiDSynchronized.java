package learnSe.part7;
//7.多线程
//
//记忆
//
//4.同步
//  1.应用场景
//      多线程并发，需要某段代码执行时CPU不会切换到其他线程，用到同步
//  2.同步代码块
//      synchronized关键字加上一个任意的锁对象来定义一段代码，多个同步代码块如果使用相同的锁对象, 那么他们就是同步的
//          执行时不能切换到同一把锁的其他代码，不是同一把锁的仍然可以切换到
//          拿到锁->执行->释放锁，所以锁一样就会同步
//  3.同步方法
//      使用synchronized关键字修饰一个方法, 该方法中所有的代码都是同步的
//      非静态同步函数的锁是:this，这个this指的是当前对象(new Printer())
//      静态的同步函数的锁是:字节码对象(class)
//5.线程安全问题
//  1.场景    多线程并发操作同一数据时, 就有可能出现线程安全问题
//  2.如何保证安全    使用同步技术可以解决这种问题, 把操作数据的代码进行同步, 不让多个线程同时操作一个数据
//      针对static变量，要用static锁，即.class文件对象
//      尽量减少同步的代码量，因为同步效率低
//6.死锁
//  1.场景：同步代码嵌套
//  2.要避免出现同步代码嵌套
//7.哪些常用类是线程安全的     方法上用synchronized修饰
//  1.StringBuffer
//  2.Collections
//      原本，同步的集合是HashTable,Vector，但通过Collections的synchronizedList等方法可获取对应的同步的集合
//8.线程通信
//  1.场景
//      多个线程并发，CPU是随机切换执行，当需要根据一定规则切换时，就需要线程通信
//  2.实现方法
//      等待唤醒机制：     写在同步代码块中，因为是通过锁对象调用，不写在同步代码块中就失去了通信的意义
//          如果希望线程等待, 就调用wait()，传入参数则指参数时间后再wait()
//              注意是指定时间后wait()不是wait()指定时间
//          如果希望唤醒等待的线程, 就调用notify();
//              notify()方法是随机唤醒一个线程
//              notifyAll()方法是唤醒所有线程
//          这等待、唤醒方法必须在同步代码中执行, 并且使用同步锁对象来调用
//              同步代码块中，sleep()方法不释放锁
//              同步代码块中，wait()方法释放锁
//  3.if是从哪暂停被唤醒后就从哪开始，使用while才会循环判断（在循环体暂停时，下次唤醒会再次判断条件）
//      简单粗暴的使用notifyAll()
//  4.ReentrantLock互斥锁
//      使用ReentrantLock类的lock()和unlock()方法进行同步，可以替换synchronized，看清楚是可以替换synchronized的
//          使用ReentrantLock对象的newCondition()方法可以获取多个Condition对象
//          需要等待的时候使用Condition的await()方法, 唤醒的时候用signal()方法
//          不同的线程使用不同的Condition, 这样就能区分唤醒的时候找哪个线程了
//9.线程组
//  1.概述
//      Java中使用ThreadGroup来表示线程组，它可以对一批线程进行分类管理，Java允许程序直接对线程组进行控制，默认情况下，所有的线程都属于主线程组
//  2.常见操作
//      给线程设置分组
//          1.ThreadGroup(String name) 创建线程组对象并给其赋值名字
//          2.创建线程对象,Thread(ThreadGroup group, Runnable target, String name)
//          3.设置整组的优先级或者守护线程tg.setDaemon(),tg.setPriority
//      public final ThreadGroup getThreadGroup()//通过线程对象获取他所属于的线程组对象
//      public final String getName()//通过线程组对象获取其组的名字
//
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiDSynchronized {
    public static void main(String[] args) {
        TongXin tongXin = new TongXin();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    tongXin.sout1();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    tongXin.sout2();
                }
            }
        }.start();
    }

    //多线程应用场景例子
    @Test
    public void soutTest() {
        SoutTest soutTest = new SoutTest();
        Sout1 sout1 = new Sout1(soutTest);
        Sout2 sout2 = new Sout2(soutTest);

        sout1.start();
        sout2.start();
    }

    //演示一个线程不同步会不安全的例子
    @Test
    public void xianChengAnQuan() {

        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();

        ticket1.start();
        ticket2.start();

    }

    //通过Runable实现卖票的多线程，注意特点
    @Test
    public void runableTicketsTest() {
        TicketsRunable tickets = new TicketsRunable();
        Thread t1 = new Thread(tickets);
        Thread t2 = new Thread(tickets);
        Thread t3 = new Thread(tickets);
        Thread t4 = new Thread(tickets);
    }

    //死锁
    @Test
    public void siSuo() {
        //存在一个时刻，线程1拿到锁s1,线程2拿到锁s2；僵住了，都没法拿到下一个锁，也都不释放第一个锁
        final String s1 = "11";
        final String s2 = "22";
        new Thread() {
            public void run() {
                while(true) {
                    synchronized(s1) {
                        System.out.println(getName() + "...拿到" + s1 + "等待" + s2);
                        synchronized(s2) {
                            System.out.println(getName() + "...拿到" + s2 + "开吃");
                        }
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                while(true) {
                    synchronized(s2) {
                        System.out.println(getName() + "...拿到" + s2 + "等待" + s1);
                        synchronized(s1) {
                            System.out.println(getName() + "...拿到" + s1 + "开吃");
                        }
                    }
                }
            }
        }.start();
    }

    //线程通信
    @Test
    public void xianChengTongXin() {
        TongXin tongXin = new TongXin();
        new Thread() {
            @Override
            public void run() {
                tongXin.sout1();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                tongXin.sout2();
            }
        }.start();
    }

    //线程分组
    @Test
    public void threadGroupTest() {
        ThreadGroup tg = new ThreadGroup("线程组1");
        Thread thread1 = new Thread(tg,new TicketsRunable(),"线程1");
        Thread thread2 = new Thread(tg,new TicketsRunable(),"线程2");

        //
        System.out.println(tg.getName());       //线程组1
        System.out.println(thread1.getThreadGroup() == tg);     //true

    }
}

class Sout1 extends Thread{
    private SoutTest soutTest;

    public Sout1(SoutTest soutTest) {
        this.soutTest = soutTest;
    }
    @Override
    public void run() {
        for (int i =0;i<100;i++) {
            soutTest.sout1();
        }
    }
}

class Sout2 extends Thread {
    private SoutTest soutTest;

    public Sout2(SoutTest soutTest) {
        this.soutTest = soutTest;
    }

    @Override
    public void run() {
        for (int i =0;i<100;i++) {
            soutTest.sout2();
        }
    }


}

class SoutTest {

    //同步代码块
    public void sout1() {
        //为了连续输出，要同步，该段执行时不允许切换到同一把锁的其他线程
        synchronized (this) {
            System.out.print("我是");
            System.out.print("线程");
            System.out.println("一号");
        }
    }

    public void sout2() {
//        synchronized (this) {     //同一把锁的代码是同步的
        synchronized (new SoutTest()) { //不是同一把锁的代码不同步
            System.out.print("ta是");
            System.out.print("线程");
            System.out.println("er号");
        }
    }

    //同步方法，锁是this
    public synchronized void sout3() {
        System.out.print("ta是");
        System.out.print("线程");
        System.out.println("er号");
    }
    //静态同步方法，锁是xxx.class（类文件）
    public static synchronized void sout4() {
        System.out.print("ta是");
        System.out.print("线程");
        System.out.println("er号");
    }
}


//卖票
class Ticket extends Thread{
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(tickets--);
            
            //举例：假设有十个线程并发，此时tickets已经是1，十个线程都卡在判断tickets>0后，此时
            //十个线程都符合了tickets>0的条件，到下一步后都进行了sout(tickets--)，就会有多个线程输出0以下的数据，与程序的初衷不符
            //就需要如下的同步操作，将操作一个数据的代码变为同步代码块
            synchronized (Ticket.class) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);      //这里是模拟有大量代码在此处执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(tickets--);
            }

        }
    }
}

class TicketsRunable implements Runnable {
    private static int tickets = 100;
    @Override
    public void run() {
        while(true) {
            //由于针对卖票的操作我只需要new依次TicketsRunnable对象，即开多个线程使用一个TicketsRunnable对象，所以可以用this，用别的也没毛病
            //但对于extends Thread的方式，绝不能用this作为锁
            synchronized (this) {
                if (tickets>0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + tickets--);
                }
            }
        }
    }
}

//两个多线程通信
class TongXin {
    private int flag = 1;

    public synchronized void sout1() {
        if (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("111");
        flag = 2;
        this.notify();
    }

    public synchronized void sout2() {
        if (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("222");
        flag = 1;
        this.notify();
    }
}

//三个以上多线程通信
class TongXin2 {
    private int flag = 1;

    public synchronized void sout1() {
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("111");
        flag = 2;
        this.notifyAll();
    }

    public synchronized void sout2() {
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("222");
        flag = 3;
        this.notifyAll();
    }

    public synchronized void sout3() {
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("333");
        flag = 1;
        this.notifyAll();
    }
}

//ReentrantLock
class Reetrant {
    private ReentrantLock r = new ReentrantLock();
    private Condition c1 = r.newCondition();
    private Condition c2 = r.newCondition();
    private Condition c3 = r.newCondition();

    private int flag = 1;
    //替换synchronized
    public void sout1() throws InterruptedException {
        r.lock();
        while (flag != 1) {
            c1.await();     //锁c1线程在此等待
        }

        System.out.println("111");
        flag = 2;
        c2.signal();        //唤醒锁c2线程
        r.unlock();
    }

    public void sout2() throws InterruptedException {
        r.lock();
        while (flag != 2) {
            c2.await();     //锁c2线程在此等待
        }

        System.out.println("111");
        flag = 3;
        c3.signal();        //唤醒锁c3线程
        r.unlock();
    }

    public void sout3() throws InterruptedException {
        r.lock();
        while (flag != 3) {
            c3.await();     //锁c3线程在此等待
        }

        System.out.println("111");
        flag = 1;
        c1.signal();        //唤醒锁c1线程
        r.unlock();
    }
}


