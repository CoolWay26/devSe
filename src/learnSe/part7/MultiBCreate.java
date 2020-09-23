package learnSe.part7;

import org.junit.Test;

import java.util.concurrent.*;

//7.多线程
//记忆
//  1.多线程实现方式
//
//
//2.多线程实现方式
//  强调：不能使用Junit测试多线程   参考：  https://segmentfault.com/a/1190000003762719
//      因为Junit测试的本质上是将待测方法test()作为参数传给TestRunner的main方法，在main中执行，test()执行完，main会调用System.exit(0)，停止JVM
//  1.实现Runnable()接口     原理是Thread的run()方法中调用了Runnable的run()方法
//      1.创建Runnable接口的实现类，重写run()
//      2.run()是多线程的执行体，线程要做的事情都写在run()中；可以重写构造方法接收需要的数据来给run()使用
//      3.启动线程：用Thread类对象包装该实现类对象，thread.start()启动线程
//          也可以在Runnable实现类中定义一个Thread对象，然后定义对应的方法，如start()，在start()中调用thread.start()，相当于手动包装
//      缺陷：
//          无法获取执行结果（run()没有返回值）
//      注意：创建Runnable实现类对象并不是创建线程，new Thread()才是创建了新的线程，其他情况实现多线程同理
//  2.继承Thread类     本质上还是实现了Runable接口，因为class Thread implements Runnable{...}
//      1.创建Thread类的子类，重写run()，线程要做的事写在run()中
//      2.创建该类对象，调用start()启动线程
//      缺陷：
//          有父类的类无法使用这种方式，因为java是单继承
//          无法获取执行结果（run()没有返回值）
//  3.实现Callable接口  因为代码复杂，所以一般不用，好处是可以有返回值，可以抛出异常
//      1.创建Callable接口的实现类，重写call()，该方法是线程执行体
//      2.使用FutureTask对象包装Callable对象，futureTask对象封装了callable.call()执行的返回值
//      3.启动线程：用Thread对象包装FutureTask对象，thread.start()
//      4.如何获取线程执行返回值：futureTask.get();注意父类返回值是泛型，可以自行指定，但必须是引用型
//  4.匿名内部类实现（这只是换一种形式，并不是第四种实现线程的方式）
//  5.线程池的方式实现多线程
//      1.场景：启动一个新线程成本较高，使用线程池可以从此方面提高性能，尤其是程序需要大量生存周期很短的线程时
//              线程池中的线程运行结束后不会终止线程，会再次回到线程池中呈现空闲状态，等待对象来调用
//      2.java1.5以后提供了内置的线程池
//          JDK5新增了一个Executors工厂类来产生线程池
//              public static ExecutorService newFixedThreadPool(int nThreads)  参数是线程池的容量
//              public static ExecutorService newSingleThreadExecutor()
//          上面这些方法的返回值是ExecutorService对象
//              该对象表示一个线程池，可以执行Runnable对象或者Callable对象代表的线程，它提供了如下方法
//                  Future<?> submit(Runnable task)
//                  <T> Future<T> submit(Callable<T> task)
public class MultiBCreate {
    public static void main(String[] args) {
    }


    //实现多线程： 实现Runnable接口
    @Test
    public void runnableTest() {
        RunnableDemo runnableDemo1 = new RunnableDemo("线程1");
        Thread thread1 = new Thread(runnableDemo1);

        RunnableDemo runnableDemo2 = new RunnableDemo("线程2");
        Thread thread2 = new Thread(runnableDemo2);

        thread1.start();
        thread2.start();
    }

    //实现多线程：继承Thread类
    @Test
    public void threadTest() {
        NewThread thread1 = new NewThread("线程1");
        NewThread thread2 = new NewThread("线程2");

        thread1.start();
        thread2.start();
    }

    //实现多线程：Callable+Future
    @Test
    public void callableTest() throws ExecutionException, InterruptedException {
        CallableTest callableTest1 = new CallableTest("线程1");
        FutureTask futureTask1 = new FutureTask(callableTest1);
        Thread thread1 = new Thread(futureTask1);

        CallableTest callableTest2 = new CallableTest("线程2");
        FutureTask futureTask2 = new FutureTask(callableTest2);
        Thread thread2 = new Thread(futureTask2);

        thread1.start();
        thread2.start();

        //获取线程返回值
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
    }

    //匿名内部类
    @Test
    public void niMing() {
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("1111");
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("2222");
                }
            }
        }).start();

        new Thread(new FutureTask<>(new Callable() {

            @Override
            public Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    System.out.println("3333");
                }
                return null;
            }
        })).start();
    }

    //线程池
    public void executorsTest() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(new RunnableDemo("线程1"));
        pool.submit(new RunnableDemo("线程2"));

        //如果是Callable实现的多线程，可以获取call()返回值
        Future ft = pool.submit(new CallableTest("线程1"));
        Future ft2 = pool.submit(new CallableTest("线程2"));

        System.out.println(ft.get());
        System.out.println(ft2.get());
        pool.shutdown();

    }

}


//实现多线程：Runnable接口
class RunnableDemo implements Runnable {
    String threadName;

    public RunnableDemo(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Runable: " + threadName + "  " + i);
        }
    }
}

//继承Thread类
class NewThread extends Thread{
    String threadName;
    public NewThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i=0;i<200;i++) {
            System.out.println("Thread: " + threadName + " " + i);
        }
    }
}

//实现Callable+future
class CallableTest implements Callable {
    String name;

    public CallableTest(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println("我执行了" + name + " " + i);
        }
        return "成功了！" + name;
    }
}

