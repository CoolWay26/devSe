package learnSe.part3;
//知识点
//记忆
//    1.Objejct类（超类，方法hashCode(),equals(),toString(),getClass()）
//了解
//    1.Scanner类
//
//3.1常见对象
//
//1.API
//    1.广义的API指的是Application Programming Interface应用程序编程接口
//      Java API，就是Java提供给我们使用的类，这些类将底层的实现封装了起来（无需关心如何实现，只要学习如何使用）
//2.Objejct
//    1.概述
//        类层次结构的根类（所有类都直接或者间接的继承自该类）所以该类的非私有成员，所有的类都有
//    2.构造方法  public Object()
//    3.hashCode()
//        public native int hashCode()    返回该对象的哈希码值，是一个数值
//        默认情况下，会根据对象的地址值计算（因此不同对象的hashCode()一般来说不会相同；但是相同对象的hashCode()肯定相同）
//    4.getClass()
//        public final Class getClass()   返回此对象的运行时类
//        Class类是用来描述类的属性
//    5.toString()
//        对于引用型变量，默认返回    类名@16进制哈希值，即getClass().getName() + "@" + Integer.toHexString(hashCode())
//        这显然没什么应用的意义，所以一般都会进行重写
//    6.equals()
//        比较两个对象是否相等，obj.equal()默认比较的地址值，与直接用==比较是一样的
//        ==号和equals方法的区别
//            1.本质不同： ==是一个比较运算符，equals()是一个方法
//            2.使用场景不同：==可以比较基本和引用数据类型，equals()只能比较引用型
//        这显然没什么意义，所以要进行重写（改成比较对象的属性值），重写equals()就一定要重写hashCode()，在Set集合会讲到


//3.Scanner类
//    1.概述
//        1.jdk5出现，用来获取用户的输入
//    2.构造方法  接收InputStream，File等
//    3.成员方法
//        1.hasNext() hasNextInt() hasNextLine() next() nextInt() nextLine()
//            注意：
//                1.每次调用nextXXX()，索引会后移一位
//                2.nextLine()和next()区别
//                    next():
//                        1.读取到有效字符以后，将第一个非有效字符视为结束
//                        2.对输入有效字符之前的遇到的空白，next()方法会自动将其去掉
//                        3.接收有效字符后才将其后面输入的空白作为分隔或者结束符
//                        4.综上，next()不能得到带有空格的字符串
//                    nextLine():
//                        1.以Enter为结束符
//                        2.即nextLine()方法返回回车之前的字符，可以获得空白
//                3.使用时，如果使用混合nextLine()的next方法进行接收，则在输入nextLine()的上一个字符时，不能用回车结束，因为这样会导致nextLine()遇到回车马上结束
//                    这样显然不符合正常思维，所以这种情况要么用两个Scanner分别去录入，这样互不影响（但浪费资源）
//                    要么全部都使用nextLine()接收，然后对字符串做类型转换

public class ACommonObject {
    public static void main(String[] args) {
//Object类
//        ObjejctDemo objDemo = new ObjejctDemo();
//        objDemo.showInf();
//Scanner类
//        Scanner sc = new Scanner(System.in);
        //成员方法
//        if (sc.hasNext()) {
//            String str1 = sc.next();
//            System.out.println(str1);
//        }
//        if (sc.hasNextInt()) {
//            int i1 = sc.nextInt();
//            System.out.println(i1);
//        }
//        if (sc.hasNextLine()) {
//            String str2 = sc.nextLine();
//            System.out.println(str2);
//        }
//        String str1 = sc.next();
//        int i1 = sc.nextInt();
//        //假如输入i1结束时使用了回车作为结束，那么i1读到回车视为结束，接着下一个nextLine()去读回车也就结束了,str2为空
//        String str2 = sc.nextLine();
//        //可以使用新的Scanner对象接收，这样互不影响，但是浪费资源
//        Scanner sc2 = new Scanner(System.in);
//        String str3 = sc2.nextLine();
//
//        System.out.println(str1);
//        System.out.println(i1);
//        System.out.println(str2);
//        System.out.println(str3);
    }
}

class ObjejctDemo {
    String name = "ObjDemo";
    int age = 10;

    public void showInf() {
        System.out.println(this.hashCode());
        System.out.println(this.toString());
    }
    //重写
    @Override
    public int hashCode() {
        return age;
    }
    @Override
    public String toString() {
        return name;
    }
}

class Demo {
    String name = "";
    int age = 10;
}
