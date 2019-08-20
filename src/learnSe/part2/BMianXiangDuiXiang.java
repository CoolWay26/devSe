package learnSe.part2;
//2.2面向对象（Ⅱ）
//
//1.抽象类
//    1.特点
//        1.abstract修饰（抽象类和抽象方法）
//        2.抽象类不一定要有抽象方法，但有抽象方法的一定是抽象类或者接口
//        3.抽象类不能被实例化，但是可以通过多态的形式用子类实例化（就算抽象类直接实例化也没有什么意义）
//        4.抽象类的子类要么是抽象类，要么就重写父类所有的抽象方法
//    2.成员
//        1.构造方法：有
//        2.成员变量：变量或者常量，不能用abstract修饰（抽象变量常量没有意义）
//        3.成员方法：抽象或者非抽象，抽象会强制子类重写
//            假如没有抽象方法，为什么还要定义成抽象类？
//                为了限制本类实例化，强制要求通过子类实例化
//    3.abstract不能和哪些关键字共存
//        static	    静态抽象方法没有意义（类名直接调用抽象方法没有意义）
//        final	    final修饰的方法不能重写（abstract强制子类重写）
//        private	    private不让子类继承（abstract强制子类重写）
//2.接口    狭义上接口就是interface    广义上指的是对外提供的规则
//    1.定义    某些共性的集合体，是一种"协议"，与父类的共性抽取不同，接口是可以多实现的，并不存在关系上的限制，需要接口的功能就可以实现该接口（应该看作一种功能拓展）
//        接口  interface 接口名{}
//        子类  class 类名 implements 接口名 {}
//                接口显然不能直接实例化，要通过多态的形式实例化
//                子类也可以是抽象类（没有什么意义，因为仍然不能直接实例化，还要继续生成子类）
//    2.成员特点
//        1.成员变量：只能是常量    自带修饰符public static final
//            用 public 的原因：接口的实现类都可以使用该成员
//                这是显然的，接口无法实例化，不public给子类的话该成员还有什么存在的意义？）
//            用 static 的原因：预防多实现变量重名的情况
//                接口是多实现的，如果不用static修饰，出现多个重名变量就很尴尬？用static修饰以后，可以用接口名调用区分
//            用 final 的原因：阻止实现类去修改
//                一旦进行修改，那么所有的实现类的该属性都会发生变化，而接口作为一种高级抽象，原则上关闭修改，开放拓展
//                换句话说，你有需要变化的东西，不能放在接口中，应该定义在自己的实现类中，如果是共性，你还可以抽取成父类、抽象类
//        2.构造方法
//            接口没有构造方法，因为接口不能实例化，变量定义时就会进行初始化，成员方法都是抽象的，没什么要初始化的，要构造方法干啥
//        3.成员方法  必须抽象，   自带修饰符public abstract
//            public：对实现类开放
//            abstract：强制实现类重写
//            JKD8以后可以有方法体（编写默认实现）
//
//            interface Father2B2 {
//                String name = "Father2B2:interface!";
//                abstract void show();
//            }
//            interface Father2B3 {
//                String name = "Father2B3:interface!";
//                abstract void show();
//            }
//
//            class Son2B2 implements Father2B2,Father2B3 {
//                @Override
//                public void show() {
//                    System.out.print(Father2B2.name + "\n"+ Father2B3.name);
//                }
//            }
//    3.抽象类和接口的区别
//        1.成员区别
//            抽象类
//                成员变量	可以是常量，可以是变量
//                构造方法	有构造方法，用于子类进行父类初始化
//                成员方法	可以是抽象或者非抽象方法
//            接口
//                成员变量	只能是常量 public static final
//                构造方法	没有
//                成员方法	只能是抽象方法 public abstract
//        2.关系区别
//            类与类	    继承，单继承，多层继承
//            类与接口	实现，单实现，多实现  class Demo implements InterA,InterB {}
//            接口与接口	继承，单继承，多继承  interface Demo extends InterA,InterB {}
//        3.设计理念区别
//            抽象类	    is	定义的是某些共性功能
//            接口		have定义的是某些拓展功能


public class BMianXiangDuiXiang {
    public static void main(String[] args) {
//        Father2B1 father2B1 = new Son2B1();
//        father2B1.show();
//
//        Son2B2 son2B2 = new Son2B2();
//        son2B2.show();
    }
}


//abstract class Father2B1 {
//    public abstract void show();
//    public void showFather() {
//        System.out.println("Father2B1!");
//    }
//}
//
//class Son2B1 extends Father2B1 {
//    @Override
//    public void show() {
//        System.out.println("Son2B1!");
//    }
//}

//接口
//interface Father2B2 {
//    String name = "Father2B2:interface!";
//    abstract void show();
//}
//interface Father2B3 {
//    String name = "Father2B3:interface!";
//    abstract void show();
//}
//
//class Son2B2 implements Father2B2,Father2B3 {
//
//    @Override
//    public void show() {
//        System.out.print(Father2B2.name + "\n"+ Father2B3.name);
//    }
//}
