package learnSe.part8;
//8.设计模式
//设计模式是软件开发行业经过长期总结下来的针对一些问题的解决方案
//
//1.装饰设计模式
//  1.定义
//      通过一个装饰类，包装原有的对象，使一个对象实现额外的功能，但不改变其原有结构
//  2.使用场景
//      动态的增加某个类的对象的功能
//  3.好处
//      相对于继承体系，装饰的耦合性不强
import org.junit.Test;
public class DesignAZhuangShi {

    //装饰设计模式
    @Test
    public void zhuangShi() {
        NewStudent nstu = new NewStudent(new Student());
        nstu.doSomething();
    }
}

//被装饰类
class Student {
    String name;
    int age;

    public void doSomething() {
        System.out.println("上课");
        System.out.println("作业");
    }
}

//装饰类
class NewStudent {
    //接收被装饰类的引用
    Student stu;
    public NewStudent(Student stu) {
        this.stu = stu;
    }

    //对原有功能进行增加
    public void doSomething() {
        stu.doSomething();
        System.out.println("做实验");
        System.out.println("歌舞");
    }
}
