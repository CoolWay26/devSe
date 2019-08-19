package learnSe.part2;
//2.1面向对象（Ⅰ）
//
//1.面向对象思想
//    1.区别于面向过程
//        面向过程思想    第一步怎么做，第二步怎么做
//        面向对象思想    找对象(对象进行第一步,第二步)
//    2.举例    洗衣服
//        面向过程：买洗衣液，买盆，泡衣服，手洗
//        面向对象：找个家政服务人员，全交给这个人处理
//    3.面向对象特征
//        封装(encapsulation)继承(inheritance)多态(polymorphism)
//2.类与对象  （描述现实世界的某一类物体（学生），通过属性（姓名，年龄等等）和功能（看书，做题等等）来描述，这和类的定义是一致的）
//    1.定义
//        类：属性和功能的集合
//        对象：类的具体体现（实例）   比如学生和具体的某个学生
//    2.使用对象
//        类名 对象名 = new 类名()
//        对象名.变量名
//        对象名.方法名(...)
//****
//    3.存储情况  引用型变量存在栈中，对象new在堆中，对应的class文件加载在方法区中
//        步骤：
//            1.主方法class文件加载进内存方法区，主方法Demoe1_Car进栈
//            2.类class文件加载进内存方法区，new Car()进堆，进行默认初始化，地址赋给栈中引用型变量
//            3.调用对象，设置属性，使用功能
//            4.调用结束，方法弹栈，堆中的对象不会消失而是成为垃圾，交由垃圾回收机制处理
//    4.成员变量和局部变量
//        1.区别
//            1.在类中的位置不同
//                成员变量：在类中方法外
//                局部变量：在方法定义中或者方法声明上
//            2.在内存中的位置不同
//                成员变量：在堆内存(成员变量属于对象,对象进堆内存)
//                局部变量：在栈内存(局部变量属于方法,方法进栈内存)
//            3.生命周期不同
//                成员变量：随着对象的创建而存在，随着对象的消失而消失
//                局部变量：随着方法的调用而存在，随着方法的调用完毕而消失
//            4.初始化值不同
//                成员变量：有默认初始化值
//                局部变量：没有默认初始化值，必须定义，赋值，然后才能使用。
//        2.注意
//            局部变量名称可以和成员变量名称一样，在方法中使用的时候，采用的是就近原则
//3.匿名对象  没有名字的对象
//    1.作用：仅使用一次的时候（如调用一次方法），通常调用结束匿名对象就成为了垃圾
//    2.注意：匿名对象可以调用对象属性和赋值，但是没有什么意义，因为调用完就变成垃圾
//4.封装
//    1.原则
//        1.将不需要对外提供的内容都隐藏起来
//        2.属性隐藏，提供公共方法对其访问
//    2.好处
//        提高复用性，安全性
//    3.private关键字
//        1.权限修饰符，可以修饰成员变量和成员方法
//        2.被其修饰的成员只能在本类中被访问
//        3.把成员变量用private修饰，提供对应的public getXxx()和setXxx()方法
//        4.private仅仅是封装的一种体现形式,不能说封装就是私有
//    4.this关键字
//        1.代表当前对象的引用
//        2.用来区分成员变量和局部变量重名
//5.构造方法
//    1.作用    对象初始化（属性）
//    2.格式
//        1.方法名与类名相同(大小也要与类名一致)
//        2.没有返回值类型，连void都没有
//        3.没有具体的返回值return;
//    3.重载
//    4.注意
//        1.如果我们没有给出构造方法，系统将自动提供一个无参构造方法。
//        2.如果我们给出了构造方法，系统将不再提供默认的无参构造方法。
//            这个时候，如果我们还想使用无参构造方法，就必须自己给出,建议永远自己给出无参构造方法
//            class ConstructorDemo {
//                public ConstructorDemo() {
//                }
//                public ConstructorDemo(String name, int age) {
//                    this.name = name;
//                    this.age = age;
//                }
//                String name;
//                int age;
//                Insert.get.set...
//            }
//            所以可以通过构造方法给对象初始化，或者在new以后通过set方法设置对象的属性值
//        3.创建一个对象的步骤
//            Demo de = new Demo();
//            1.Demo.class加载进内存方法区
//            2.声明一个Demo类型引用变量de（栈中）值为null
//            3.在堆内存创建对象
//            4.给对象中属性默认初始化值（0，0.0，false，null等）这一步很重要，给对应的属性根据类型赋默认值
//            5.属性进行显示初始化（类成员变量定义时赋值-定义初始化）
//            6.构造方法进栈,对对象中的属性赋值,构造方法弹栈（构造方法初始化）
//            7.将对象的地址值赋值给变量de
//6.static关键字 class加载到内存方法区以后会开辟属于自己的静态区
//    1.特点
//        跟随class文件加载进内存--优先于对象存在
//        被所有对象共享--可以通过类名调用（其实也可以通过对象名调用，即对于静态成员，使用对象名调用也是去静态区找，而不是在堆中的对象中找）
//    2.注意    static的特点决定了以下情况
//        1.静态方法中没有this（因为this是当前对象的引用）
//        2.静态方法只能访问静态成员
//    3.关于main方法     程序的入口    public static void main(String[] args) {}
//        1.针对格式的解释
//            public  被jvm调用，访问权限足够大
//            static  被jvm调用，不用创建对象，直接类名访问
//            void    被jvm调用，不需要给jvm返回值
//            main     一个通用的名称，虽然不是关键字，但是被jvm识别
//            String[] args 以前用于接收键盘录入的
//    4.工具类中定义成员通常使用静态
//        比如获取[0.0,1.0)随机数    Math.random();
//7.代码块   {}括起来的代码被称为代码块
//    1.分类
//        1.局部代码块
//            在方法中出现；限定变量生命周期，及早释放，提高内存利用率
//        2.构造代码块 (初始化块)
//            在类中方法外出现；多个构造方法方法中相同的代码存放到一起
//            每次调用构造都执行，并且在构造方法前执行
//        3.静态代码块
//            在类中方法外出现，并加上static修饰；用于给类进行初始化
//            在加载class的时候就执行，并且只执行一次。
//            一般用于加载驱动
//            class Fu2A {
//                static {
//                    System.out.println("Fu:static代码块！");
//                }
//                {
//                    System.out.println("Fu:Construtor代码块！");
//                }
//                public Fu2A () {
//                    System.out.println("Fu:这是Construtor！");
//                }
//            }
//            class Zi2A extends Fu2A {
//                static {
//                    System.out.println("Zi:static代码块！");
//                }
//                {
//                    System.out.println("Zi:Construtor代码块！");
//                }
//                public Zi2A () {
//                    System.out.println("Zi:Construtor！");
//                }
//            }
//
//            Zi2A demo = new Zi2A();
//            输出结果：
//                Fu:static代码块！
//                Zi:static代码块！
//                Fu:Construtor代码块！
//                Fu:这是Construtor！
//                Zi:Construtor代码块！
//                Zi:Construtor！
//
//8.继承    类之间的关系（所以千万不要为了功能去继承）
//    1.好处、弊端
//        好：复用性，维护性，多态的前提
//        弊：耦合性增强（开发的原则是高内聚，低耦合）
//    2.特点
//        1.单继承，多层继承
//        2.子类只能继承父类的非私有成员（方法和变量）
//        3.子类不能继承父类的构造方法，但是可以用super()调用，子类构造方法中默认会调用父类空参构造方法
//            1.每一个构造方法的第一条语句默认都是：super()--这样意味着执行子类构造方法之前要先执行父类构造方法   Object类是最顶层的父类
//                实际上，执行顺序为：
//                    父类static,子类static（class文件加载进内存时执行）
//                    父类构造代码块，父类构造方法（执行子类构造方法之前要执行父类构造方法）
//                    子类构造代码块，子类构造方法
//            2.子类会继承父类中的数据，可能还会使用父类的数据，所以必须要进行父类数据的初始化--父类构造方法
//            3.父类没有无参构造方法,子类怎么办--显式加上super(...)
//                注意：super()必须要写在构造方法第一行
//        4.继承体现类之间的关系（学生 是 人）
//    3.成员（变量和方法）就近原则，以子类为准，子类没有就去父类找
//9.重写    子父类出现了一模一样的方法
//    1.作用
//        子类继承父类，又可以改写原本功能，实现自己的想法


public class AMianXiangDuiXiangGaiShu {
    public static void main(String[] args) {
//匿名对象
//        showString(new NoNameClass());
//        showString(new NoNameClass());  //两个输出结果不一样
//
//        new NoNameClass().show();   //调用一次show方法，如果需要对一个对象多次调用，仍然还是要使用非匿名对象
//
//        NoNameClass noName = new NoNameClass();
//        noName.show();
//        noName.show();
//        ConstructorDemo de = new ConstructorDemo();
//        System.out.print(de.getAge());
//    }
//    public static void showString(Object obj) {
//        System.out.print(obj.toString());
//    }
//理解三类代码块
//        Zi2A demo = new Zi2A();
    }
}



//定义学生类
//class Student {
//    String name;
//    String age;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }
//
//    public void study() {
//        System.out.println("学习中！");
//    }
//    public void doQuestions() {
//        System.out.println("做题中！");
//    }
//}
//匿名对象类
//class NoNameClass {
//    public void show() {
//        System.out.println("show！");
//    }
//}

//构造方法
//class ConstructorDemo {
//    public ConstructorDemo() {
//
//    }
//    public ConstructorDemo(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    String name;
//    int age;
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public int getAge() {
//        return age;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }
//}
//代码块和继承
//class Fu2A {
//    static {
//        System.out.println("Fu:static代码块！");
//    }
//    {
//        System.out.println("Fu:Construtor代码块！");
//    }
//    public Fu2A () {
//        System.out.println("Fu:这是Construtor！");
//    }
//}
//class Zi2A extends Fu2A {
//    static {
//        System.out.println("Zi:static代码块！");
//    }
//    {
//        System.out.println("Zi:Construtor代码块！");
//    }
//    public Zi2A () {
//        System.out.println("Zi:Construtor！");
//    }
//}