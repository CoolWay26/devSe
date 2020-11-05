package learnSe.part2;
//2.1面向对象（Ⅰ）
//知识点
//记忆
//        1.类与对象（内存情况，成员变量和局部变量本质区别，匿名对象）
//        2.构造方法（作用，形式-public无返回值，重载，创建对象的完整步骤）
//        3.static关键字（特点,注意）
//        4.继承（好处，特点，super，重写与重载区别）
//        5.final（类不能被继承，方法不能被重写，值只能初始化一次-初始化时机）
//        6.多态（前提，子父类关系，好处弊端）
//了解
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
//        1.区别  抓住本质，跟随对象同跟随方法之间的区别
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
//3.匿名对象  没有名字的对象   new Student().study();
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
//    1.作用    对象初始化（属性），但通常我们用get,set方法设置对象的属性
//    2.格式
//        1.方法名与类名相同(大小也要与类名一致)
//        2.没有返回值类型，连void都没有    通常以public修饰，单例情况为private
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
//            1.main方法加载进内存方法区
//            2.Demo.class加载进内存方法区
//            3.声明一个Demo类型引用变量de，入栈，默认值为null
//            4.在堆内存创建对象
//            5.给对象中属性默认初始化值（0，0.0，false，null等），对象属性的默认初始化是在创建之后立即进行的，优先于一切其他操作（如果有父类会先一步进行默认初始化）
//                如果有父类需要初始化，那么默认初始化之后，会先执行父类成员初始化（显式初始化-构造方法初始化）
//            5.属性进行显示初始化（类成员变量定义时赋值-定义初始化）
//            6.构造方法进栈,对对象中的属性赋值,构造方法弹栈（构造方法初始化）
//            7.将创建好的对象的地址值赋值给变量de
//6.static关键字 class加载到内存方法区以后会开辟属于自己的静态区
//    1.特点
//        优先于对象存在：跟随class文件加载进内存，进入该class的静态区
//        可以通过类名调用，被所有对象共享：其实也可以通过对象名调用，但对于静态成员，使用对象名调用也是去静态区找，而不是在堆中的对象中找
//    2.注意    static的特点决定了以下情况
//        1.静态方法中没有this（因为this是当前对象的引用）
//        2.静态方法只能访问静态成员
//        3.子类不能重写父类的static方法（因为子类有自己的静态区，哪怕是和父类完全同样声明的static方法，也是在自己的静态区，和父类的static完全没有关系）
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
//            此处还需注意一点：
//                static块之前若有其他static修饰的常量初始化，是要按声明顺序先执行常量初始化，而不是执行static块
//                如果static块声明在前，那么就先执行static块的内容
//8.继承    类之间的关系（所以千万不要为了功能去继承）
//    1.好处、弊端
//        好：多态的前提，复用性，维护性
//        弊：耦合性增强（开发的原则是高内聚，低耦合）
//    2.特点
//        1.单继承，多层继承
//        2.子类只能继承父类的非私有成员（方法和变量）
//        3.子类不能继承父类的构造方法，但是可以用super()调用，子类构造方法中默认会调用父类空参构造方法
//            1.每一个构造方法的第一条语句默认都是：super()--这样意味着子类初始化之前要进行父类初始化   Object类是最顶层的父类
//                实际上，执行顺序为：
//                    父类static,子类static（class文件加载进内存时执行）
//                    父类显式初始化，父类构造代码块，父类构造方法（父类初始化）
//                    子类显式初始化，子类构造代码块，子类构造方法（子类初始化）
//            2.子类会继承父类中的数据，可能还会使用父类的数据，所以必须要进行父类数据的初始化
//            3.父类没有无参构造方法,子类怎么办--显式加上super(...)
//                注意：super()必须要写在构造方法第一行
//        4.继承体现类之间的关系（学生 是 人），不要为了部分功能去继承
//    3.成员（变量和方法）就近原则，以子类为准，子类没有就去父类找
//    4.super关键字
//        可以使用super调用父类的非私有成员
//        但实际上，super并不是指向父类对象，实际上，super和this是指向同一个对象，但是对应不同的class，每个class有自己的属性
//        通过下面的例子理解super和子父类成员初始化的顺序
//                class Father {
//                    public int age = 40;
//                    public Father() {
//                        getAge();
//                    }
//                    public void getAge() {
//                        System.out.println("Father:" + this.age);
//                    }
//                }
//                class Son extends Father {
//                    public int age = 20;
//                    public Son() {
//                        super();
//                        super.getAge();
//                        getAge();
//                    }
//                    public void getAge() {
//                        System.out.println("Son:" + this.age);
//                    }
//                }
//
//                Son a = new Son();
//                输出：
//                        Son:0
//                        Father:40
//                        Son:20
//        子类Son a = new Son();
//        还应该明白一点，new才会开辟空间创建对象，子类创建对象并不会创建父类的对象，只是子类的对象由继承+特有成员组成
//        子类要创建对象，那么父类class和子类class先后加载进方法区，new的时候会进行默认初始化（包括继承父类和特有的属性）
//        然后要先进行父类属性的初始化（这是必然的，因为子类可能要用到父类的成员），不要用super()的调用顺序去理解父类初始化的时机
//            显式初始化 Father.age = 40;（先显式初始化也是必然的，因为本类的变量很可能要被本类的其他成员调用，调用以后再显式初始化没有意义）
//            构造方法初始化 getAge;//此时程序认为getAge已经被子类重写了，调用的其实是Son.getAge
//                Son.getAge()      Son.age此时由于还没有执行到显式初始化和构造方法初始化，所以是默认初始化的0 ，输出 Son:0
//                至此父类对象初始化完毕
//        接着进行子类的初始化
//            显式初始化 Son.age = 20;
//            构造方法初始化调用super.getAge()//这里指定调用父类getAge，输出Father:40
//            顺序执行getAge//调用子类重写后的getAge输出Son:20
//        总结就是：
//            会先进行父类子类的默认初始化，然后进行父类初始化，再进行子类初始化
//            （不要生硬的认为一定是子类先进行显式初始化才会执行构造方法初始化才会调用super(),但对于某个类的对象，一定是默认-显式-构造方法这样的初始化顺序）
//9.重写    子父类出现了一模一样的方法
//    1.作用
//        子类继承父类，又可以改写原本功能，实现自己的想法
//    2.注意
//        1.父类中私有方法不能被重写（因为私有方法不能被继承）
//        2.重写时访问权限不能更低（最好一致）
//        3.静态方法不能被重写（因为静态方法存在各自的静态方法区）
//        4.重写时，返回值类型可以不一样，但是重写后的返回值类型必须是原类型的子类型，比如原类型是Object，那么重写后可以是任意的Object的子类
//    3.重写与重载的比较
//        1.场景不同，重写是子父类之间，重载是同一个类中
//        2.重写方法声明一致（返回值可以是子父类关系），重载是方法的参数列表不同（与返回值无关）
//10.final关键字
//    1.final修饰作用
//        1.修饰类，类不能被继承
//        2.修饰变量，变量不能称为自定义常量，只能被赋值一次，加载进常量池，不随方法弹栈
//            1.修饰基本数据类型，值不能改变
//            2.修饰引用数据类型，地址不能改变（其实就是引用型变量的值不能改变），但是地址所指的存储内容可以被改变
//            3.必须在对象初始化完毕之前对自定义常量自行显式初始化（构造方法执行完毕之前--可以在构造方法中初始化）
//        3.修饰方法，方法不能被重写
//11.多态   同一个行为具有多个不同表现形式或形态的能力（动物吃饭--猫吃鱼，狗吃肉）
//    1.前提
//        继承，并且重写（不重写就没有多种形态的可能），父类引用指向子类对象（使用父类引用接收子类对象，才会有调用共有方法产生不同形态的现象）
//    2.编译和运行
//        class Father2A {
//            String name = "Father!";
//            int age = 40;
//            public void show() {
//                System.out.println("Father:show!");
//            }
//        }
//        class SonA extends Father2A {
//            String name = "SonA!";
//            int age = 20;
//            String sonStr = "sonA!";
//            public void show() {
//                System.out.println("SonA:show!");
//            }
//            public void showA() {
//                System.out.println("SonA:showA!");
//            }
//        }
//        使用多态时，编译检查会以父类为准，调用的成员变量和方法，都必须是父类有的
//        因为使用的是父类的引用指向对象，只能使用父类拥有的属性和功能，这个变量也只知道父类有什么属性和功能，你问他要子类的东西，他肯定一脸懵比
//        或者说，你可以看成一个父类对象，但是方法可能被子类重写了
//        1.成员变量
//            对于成员变量，编译看父类，运行也看父类，因为子类的成员变量已经被父类引用隐藏了（这很容易理解，因为使用引用型）
//                Father2A sonA = new SonA();     //向上转型
//                System.out.print(sonA.age);     //输出40
//                System.out.print(sonA.sonStr);  //编译报错
//            但是可以进行强转，转换为子类对象，就可以使用子类的属性（向下转型）
//                Father2A sonA = new SonA();             //向上转型
//                System.out.print(((SonA)sonA).age);     //输出20
//                System.out.print(((SonA)sonA).sonStr);  //正常输出
//            注意(SonA)sonA.age这样写是不对的，编译器会认为是把sonA.age这个值强转成SonA,将一个int转为SonA显然是不对的
//        2.成员方法
//            对于成员方法，编译还是看父类，调用的方法必须是父类有的，但运行是优先看子类重写后的方法（这是多态的体现）
//                Father2A sonA = new SonA();
//                sonA.show();    //输出SonA:show!
//                sonA.showA();   //编译报错
//        3.static成员方法
//            编译看父类，运行看父类
//                静态属于类成员方法，调用的时候是运行对应的类成员方法
//                （其实静态成员方法，自己是自己的，不是重写，所以我们可以说，子类无法重写父类的静态方法）
//    3.好处和弊端
//        好处：拓展性强，利于维护
//        弊端：不能直接使用子类特有的属性（要向下转型后才能使用）

import org.junit.Test;

public class AObjectOriented {
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
//多态
//        Father2A sonA = new SonA();
//        System.out.print(((SonA)sonA).age);
//        System.out.print(((SonA)sonA).sonStr);
//        sonA.show();
//理解super和初始化顺序
//        Son a = new Son();
    }

    @Test
    public void chuShiHua() {
        Son son1 = new Son();
//        Son:0
//        Father:40
//        Son:20
    }

    @Test
    public void duoTai() {
        Father2A fa = new SonA();
        System.out.println(fa.age);
        fa.show();

        SonA sonA = (SonA)fa;
        System.out.println(sonA.age);
        sonA.show();
//        40
//        SonA:show!
//        20
//        SonA:show!
//        要理解这里就一定要理解继承，
//        子类并不是把父类的非私有成员直接复制为自己的成员，字符类应当看作两个有交集的集合，
//        父类的引用只能指向自己的成员变量，子类引用也只能指向自己的成员变量
    }
}

class Father {
    public int age = 40;

    public Father() {
        getAge();
    }

    public void getAge() {
        System.out.println("Father:" + this.age);
    }
}

class Son extends Father {
    public int age = 20;

    public Son() {
        super();
        super.getAge();
        getAge();
    }

    public void getAge() {
        System.out.println("Son:" + this.age);
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
//多态
class Father2A {
    String name = "Father!";
    int age = 40;
    public void show() {
        System.out.println("Father:show!");
    }
}

class SonA extends Father2A {
    public static String test ;
    String name = "SonA!";
    int age = 20;
    String sonStr = "sonA!";

    public void show() {
        System.out.println("SonA:show!");
    }

    public void showA() {
        System.out.println("SonA:showA!");
    }
}


