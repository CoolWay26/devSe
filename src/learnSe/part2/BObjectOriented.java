package learnSe.part2;
//2.2面向对象（Ⅱ）
//知识点
//记忆
//    1.抽象类（子类，abstract）
//    2.接口（成员特点，和抽象类区别）
//    3.权限修饰符（注意projected的说明）
//    4.内部类（成员内部类，局部内部类，匿名局部内部类本质是子类对象-重写方法并调用）
//了解
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
//        static	静态抽象方法没有意义（类名直接调用抽象方法没有意义，且static修饰的方法不能被子类重写）
//        final	    final修饰的方法不能重写（abstract强制子类重写）
//        private	private不让子类继承（abstract强制子类重写）
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
//            用 static 的原因，要注意的是，static修饰以后并非是不能改变值了，那是final
//                有两种解释，我觉得都站得住
//                1.因为接口是无状态的
//                正因为接口是无状态的，所以接口中不允许出现实例成员，这是一个原则。
//                带有static修饰的成员属于类成员，不属于实例成员（不是跟着对象走的），不属于接口的状态。
//                如果接口支持状态的话，那么抽象类还有其存在的价值么
//                2.是和Java语言的本身机制有关，Java不支持多继承，但是一个类可是现实多个接口。
//                假设有两个接口A和B，而类C实现了接口A和B。假设，此时，A和B中都有一个变量N，如果N不是static类型的，那么在C中该如何区分N到底是A的还是B的呢？
//                而如果是static类型的，我们可以通过A.N和B.N来区别调用A或者B中的成员变量N
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
//            抽象类	is	定义的是某些共性功能
//            接口		have定义的是某些拓展功能
//3.包package  其实就是文件夹
//    1.注意
//        1.package 包名;   包名一般为域名倒置（其余部分按功能和模块划分）
//        2.package语句必须是程序的第一条可执行的代码，package语句在一个java文件中只能有一个，如果没有package，默认表示无包名
//        3.命令行编译的时候，要使用javac -d xxx.java(会自动生成对应的包)     命令行运行要使用全类名 java x.x.x.x.xxx.java
//    2.import关键字 import 全类名;
//        1.作用：让有包的类对调用者可见,不再需要写全类名，而不是改变了访问权限
//        2.注意：import时要写到类名，可以用*通配符表示整个包（尽量只导入需要的类）
//    3.package,import,class的顺序关系
//        package必须是可执行语句的第一行，且仅一句
//        import放在中间，可以多句
//        class在最后
//4.四种权限修饰符
//                   本类	    同一个包下(子类和无关类)   不同包下(子类)	不同包下(子类和无关类)
//        private 	Y
//        默认		Y		          Y
//        protected	Y		          Y						Y
//        public	Y		          Y						Y				Y
//    private:本类中可以调用（访问，使用）
//    default:本包中可以调用（访问，使用）
//    projected:
//        本包中，不同包的子类中通过super或者（this）访问，其实super和this访问的是同一个对象
//        要明确的是，实际上并不是直接访问父类，而是访问继承而来的成员（其实还是访问子类对象）
//        并不能通过父类对象直接访问projected成员
//            比如：子父类不在同一包下，
//            package p1;
//            class Fu {
//                protected String name = "老父亲！";
//            }
//
//            package p2;
//            class Zi extends Fu{
//                String name = this.name;    //获取了继承来的父类projected成员
//                Zi zi = new Zi();
//                String name1 = zi.name;     //子类访问继承的父类projected成员（其实相当于访问自己的）
//                String name2 = super.name;   //super获取父类projected成员（其实也相当于访问自己的）
//                Fu fu = new Fu();
//                String name3 = fu.name;     //报错，因为不同包下子类其实并不能直接访问父类的projected成员（要么通过子类访问继承，要么super访问）
//
//            }
//        重写的时候声明权限只能更大-public不能更低-private，最好一致
//    public:完全公开访问权限
//5.其他修饰符
//    状态修饰符：static，final
//    抽象修饰符：abstract
//6.成员内部类（归根到底还是外部类的成员）
//    1.特点
//        1.内部类中可以直接访问外部类的成员，包括私有（因为内部类可以获得外部类的引用Outer.this）
//        2.外部类要访问内部类的成员，必须创建对象：Outer.Inner oi = new Outer().new Inner();
//        3.和其他的类一样，成员可以定义成private，提供public方法访问
//        4.内部类本身访问权限不能超过外部类（亲测），这也很好理解，因为访问内部类的前提是访问外部类
//    2.静态成员内部类   用static修饰
//        外部类名.内部类名 对象名 = 外部类名.内部类对象; Outer.Inner oi = new Outer.Inner();相当于外部类名调用内部静态成员，习惯把new提前
//    3.局部内部类（成员方法内的内部类）
//        jdk8以前，局部内部类访问其所在方法的局部变量时，该变量必须是final修饰的自定义常量
//            原因：
//                1.生命周期不同：方法执行结束出栈时，局部变量销毁，但是局部内部类对局部变量的引用依然存在，当局部内部类需要使用该局部变量时，就会出现非法引用
//                2.数据不同步：
//                    内部类并不是直接使用局部变量，而是通过自己的构造备份到内部，表面上看是一个变量，实际上并不是
//                    假如局部内部类对该变量做了修改，那么实际上该局部变量在局部内部类外的值不会改变，这就很尴尬，加上final就可以阻止这样的事发生
//                而final加载进常量池，就能解决以上问题，对于jdk8，会默认加上final，不需要显式处理
//    4.匿名局部内部类 内部类的简化写法
//        1.格式    跳过命名，直接继承一个类或是实现一个接口（可以是具体类也可以是抽象类）
//            new 类名或者接口名(){
//                重写方法;
//            }.方法名();
//            后面可以直接跟.方法名调用对象的成员方法（这也是我理解的目的）
//            所以其实，本质上是子类（实现类）匿名对象
//            class Outer {
//                //内部类实现接口重写方法，这样可以通过该内部类调用重写的方法
//                class Inner implements ForInner {
//                    @Override
//                    public void showName() {
//                        System.out.println(name);
//                    }
//                }
//
//                public void showInner() {
//                    //匿名局部内部类直接重写和调用
//                    new ForInner() {
//                        @Override
//                        public void showName() {
//                            System.out.println(name);
//                        }
//                    }.showName();
//                }
//                    //调用toShow()方法时，匿名内部类作为参数传递（重写showName()方法），也可以实现功能
//                    public void toShow(ForInner forInner) {
//                        forInner.showName();
//                    }
//            }
//
//            interface ForInner {
//                String name = "InnerClass";
//                void showName();
//            }

public class BObjectOriented {
//    private String namePri = "Pri";
//    String name = "";
//    protected String namePro = "Pro";
//    public String namePub = "Pub";

    public static void main(String[] args) {
//        Father2B1 father2B1 = new Son2B1();
//        father2B1.show();
//
//        Son2B2 son2B2 = new Son2B2();
//        son2B2.show();

//        Qualifier qua1 = new Qualifier();
//        String name1 = qua1.name;       //default成员在同一包下可以访问
//        String name2 = qua1.namePro;    //projected成员在同一包下，或者不同包下的子类中可以访问
//        String name3 = qua1.namePub;    //public成员在不同包下仍可以访问
//        String name4 = qua1.namePri;    //报错，因为private成员的除了在本类中，都不能访问（但可以提供公共方法去访问）

//        OuterClass.InnerClass innerClass = new OuterClass().new InnerClass();
//        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
//        innerClass.showOuterName();
//        staticInnerClass.showOuterName();
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

//成员内部类
//class OuterClass {
//    private String name = "OuterName";
//    class InnerClass {
//        private String name = "InnerName";
//        public void showOuterName() {
//            String name = "name";
//            System.out.println(name);
//            System.out.println(this.name);
//            System.out.println(OuterClass.this.name);//通过外部类的引用直接访问外部类成员（包括私有的）
//        }
//    }
//    static class StaticInnerClass {
//        private String name = "InnerName";
//        public void showOuterName() {
//            String name = "name";
//            System.out.println(name);
//            System.out.println(this.name);
//        }
//    }
//    public void show() {
//        final int age = 10;
//        class inner {
//            public void showAge() {
//                System.out.println(age);
//            }
//        }
//    }
//}

//匿名局部内部类
//class Outer {
//    //内部类实现接口重写方法，这样可以通过该内部类调用重写的方法
//    class Inner implements ForInner {
//        @Override
//        public void showName() {
//            System.out.println(name);
//        }
//    }
//
//    public void showInner() {
//        //匿名局部内部类直接重写和调用
//        new ForInner() {
//            @Override
//            public void showName() {
//                System.out.println(name);
//            }
//        }.showName();
//    }
//
//    //调用toShow()方法时，匿名内部类作为参数传递（重写showName()方法），也可以实现功能
//    public void toShow(ForInner forInner) {
//        forInner.showName();
//    }
//}
//
//interface ForInner {
//    String name = "InnerClass";
//    void showName();
//}



