package learnSe.part3;
//3.1常见对象
//知识点
//记忆
//    1.Arrays类   成员方法toString,sort,binarySearch
//    2.包装类以Integer为例
//        形式Integer
//        构造方法：将String或者int型转为包装类
//        成员方法：转换Integer.parseInt("1")    String.valueOf(1)   包装类和String的转换用的是static方法
//        自动装箱拆箱：byte范围自动装箱不会新建对象，手动装箱（new）仍然会新建对象
//    3.System类
//        gc
//        垃圾回收    特点，垃圾标准，触发主GC的条件，编码要注意的4点
//    4.SimpleDateFormat类 格式化Date()对象为String，或者把String转为Date  pattern规则   format(Date date)  parse(String str)
//            判断人活了多少天
//    5.Calendar类 日历类 Calendar.getInstance() set(int year,int month,int date) add(Calendar.DATE, 10) get(int field)
//            判断闰年（如果不用Calendar：不是100的倍数是4的倍数，是100的倍数是400的倍数）
////了解
//    1.Math类 成员方法max min random[0.0, 1.0)
//    2.Random类   给定种子的效果，nextInt可以给定上限
//    3.BigInteger类   大数运算add()...
//    4.BigDecimal类   浮点运算add()...
//    5.Date类 时刻  getTime()   setTime(long time)
//
//
//6.Arrays类   针对数组操作的工具类，成员方法都是static
//    1.成员方法
//        public static String toString(int[] a)          转字符串  [aa, ab, ac]
//        public static void sort(int[] a)                排序      字典顺序
//        public static int binarySearch(int[] a,int key) 二分查找，找到则返回索引，找不到则返回-插入点-1
//7.基本类型包装类
//    1.目的
//        将基本类型封装成包装类，可以定义更多的属性和方法操作数据
//        byte 			Byte                char			Character
//        short			Short
//        int			Integer
//        long			Long
//        float			Float
//        double		Double
//        boolean		Boolean
//    2.以Integer为例
//        1.构造方法
//            public Integer(int value)
//            public Integer(String s)    只能是可以转为Integer的字符串，如"100"
//        2.成员方法
//            1.String和int转换
//                String转int
//                    Integer.parseInt(str)   直接转换
//                    Integer.parseInt(String s, int radix)   将s视为radix进制数，最终解析成十进制有符号数（这里的有符号指的是直接加-负号，而不是0，1表示的正负）
//                    new Integer(str).intValue()     通过Integer再转为int
//                int转String
//                    Integer.toString(int i) Integer静态方法
//                    integer.toString()      Integer静态方法非静态方法，非静态方法要保证调用者不为null
//                    String.valueOf(int i)   String类静态方法
//                    ""+xxx  拼接
//                注意：基本数据类型包装类中，只有char类型没有parseInt(String str)方法，char类型显然不能有这样的方法
//                      只能有通过str.toCharArray()将字符串转为字符数组
//        3.自动装箱拆箱    jdk5
//            自动装箱：把基本类型转换为包装类类型  Integer inte1 = 100;    底层实际上还是使用了构造方法
//            自动拆箱：把包装类类型转换为基本类型  int i = inte1;          底层使用了integer.intValue()
//            注意：
//                自动装箱，Integer -128至127之间，同样大小不会新建对象，会从常量池获取（这是byte的范围，原码这么操作的）
//                手动装箱，一定会新建对象
//8.Math类     执行数学运算
//    1.成员方法      static      关注返回值类型
//        int abs(int a)  绝对值
//        double ceil(double a)   向大取整
//        double floor(double a)  向小取整
//        int max(int a,int b)    取大
//        int min(int a,int b)    取小
//        double pow(double a,double b)   a的b次幂
//        double random() 0.0-1.0之间的随机小数（左闭右开）  double意味着16位有效数字（不算个位），而不是一位小数！本质是调用了Random实例的nextDouble()方法
//9.Random类   生成伪随机数    稳定算法所得出的稳定结果序列
//    1.构造方法
//        public Random()     随机种子
//        public Random(long seed)    给定种子，每次重启程序，会生成同之前一致的数；如果两个Random实例的种子相同，相同调用后生成的数也相同（通常种子会是根据当前时间计算出的值DateTime.Now.Millisecend()，保证种子不会相同，这样看起来更像是真的随机）
//                      种子决定的是起始位置，其实，如果对同一个实例一直不停的调用，N次以后，总归是会出现重复循环的情况
//    2.成员方法  生成指定类型的随机数
//        public int nextInt()        生成一个int范围内的随机数，正负都可以
//        public int nextInt(int n)   给定上限，左闭右开，即生成[0,n)
//        nextBoolean()               true false
//        nextDouble()                [0.0,1.0)  其他范围要依据这个范围进行运算操作来生成
//10.System类  不能实例化
//    1.成员方法
//        public static void gc() 建议执行垃圾回收器进行回收，当垃圾回收器确定了垃圾对象，会调用Object类的finalize()方法回收垃圾
//            System.gc();
//        public static void exit(int status) 终止运行java虚拟机
//            System.exit();
//        public static long currentTimeMillis()  获取当前时间与1970.1.1之间的时间差（毫秒值）
//            可以通过首尾获取两次currentTimeMillis()做差得到程序运行的时间
//        public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
//        将src数组中srcPos开始的length长度复制到dest数组的destPos起始位置
//        集合的底层是数组，集合长度可变就是使用了System.arraycopy()，集合长度可变是一个新建和复制的过程
//        StringBuffer和StringBuilder也是如此
//    2.垃圾回收机制
//            参考：https://blog.csdn.net/bao19901210/article/details/39318665   http://blog.sina.com.cn/s/blog_66a6172c01018jda.html
//        目的：跟踪正在使用的对象和发现并回收不再使用(引用)的对象，防止因内存垃圾过多而引发的内存耗尽，以及不恰当的内存释放所造成的内存非法引用
//        优点：无需人为的进行垃圾回收，垃圾回收器有自己的一套固有方案判断哪个内存块需要被回收，哪些暂不回收，并自行决定何时回收
//        缺点：
//            垃圾回收器在一个java程序中的执行是自动的，无法被强制执行，即使程序员能肯定哪一块是垃圾，也只能通过System.gc()来建议执行垃圾收集器进行回收
//            至于垃圾回收器是否会执行回收操作，什么时候执行，都是不可知的，这也是垃圾回收器最主要的缺点
//        垃圾回收器的特点：
//            1.垃圾回收器的工作目标是回收无用对象的内存空间，节省内存资源，避免内存溢出，减小因此导致程序崩溃的可能
//            2.垃圾回收器判断某个对象是否无用的标准：该对象不被任何 活动的部分（尚未执行完毕的部分） 引用
//            3.垃圾回收线程虽然优先级较低，但在可用内存极低的时候，它可能会突发执行来解放内存资源（这样的行为无法预知）
//            4.垃圾回收器不可人为的强制执行，只能通过System.gc()建议垃圾收集器进行回收
//            5.甚至，垃圾回收器在某段程序中完全没有运行，在程序执行过程中被分配出去的空间一直保留到程序执行完毕
//            6.当然，对于一批符合垃圾标准的对象中，垃圾回收器会按什么顺序回收，也无法预测
//            7.处于循环中的垃圾也是垃圾，也可能被回收
//            8.保证已经去除监听器+引用变量赋null可以让垃圾回收器视之为垃圾
//            9.finalize()：
//                垃圾回收器在收集垃圾空间时，会调用对象的finalize()方法（但是并不是这个方法收集垃圾，这个方法是回收内存以外的系统资源）
//                每个对象只能执行一次finalize()，用来回收内存以外的系统资源，在调用finalize()时，意味着本来已经死了的对象会"复苏"一次
//                即使finalize()方法执行时产生Exception，垃圾（占用的内存）仍然会被回收，系统也不会报告这个异常
//                finalize()方法可以重载，但是必须具备初始finalize()方法特点才会被垃圾回收器调用
//                finalize()方法可以明确地被调用，该方法会在垃圾收集器交换回收对象之前被调用，但不建议显式的调用，因为该方法调用后的执行结果是不可预知的
//                当finalize( )方法尚未被调用时，System. runFinalization( )方法可以用来调用finalize( )方法，并实现相同的效果，对无用对象进行垃圾收集
//        判断垃圾的依据：简单的说，就是不再被引用的对象就是垃圾对象
//            引用计数法，统计引用数量
//            可达性分析算法（对象引用是否可达），当一个对象到 GC Roots 没有任何引用链相连（就是从 GC Roots 到这个对象不可达）时，则证明此对象是不可用的
//        触发主GC的条件
//            JVM进行次GC的频率很高，但这种GC都是瞬间完成的，对系统影响不大，而主GC影响更明显，有2种情况会触发主GC：
//            1.应用程序空闲时，因为GC在优先级最低的线程中运行，忙时不会执行
//            2.内存不足时，主GC会被调用（应用程序需要创建新的对象，但内存不够，主GC会运行）
//        减少主GC开销的措施
//            为了不反复触发主GC，要针对主GC的特点进行编码
//            1.不要显式调用System.gc() gc()会建议主GC执行，虽然只是建议，但很多情况都会触发主GC
//            2.尽量减少临时对象的使用（临时变量往往短暂调用后就是垃圾）
//            3.对象不用时最好显式置为Null（有利于GC判断垃圾）
//            4.尽量使用StringBuffer,而不用String来累加字符串（每累加一次就是一个新的Stirng对象存到常量池）
//            5.能用基本类型如Int,Long,就不用Integer,Long对象（占用内存资源少的多）
//            6.尽量少用静态对象变量（静态变量不会被判为垃圾）
//            7.分散对象创建或删除的时间（集中处理可能会忽然占用很多内存，增大内存不足的可能）
//11.BigInteger类  大数运算    不可变的任意精度的整数
//    1.概述
//        1.BigInteger不是基本数据类型之一，它其实更像String，这个类的取值范围原则上是没有上限的，取决于你的计算机的内存
//    2.构造方法
//        1.public BigInteger(String val)//注意参数是String类型
//    3.成员方法  大数加减乘除不能使用+、-、*、/这些运算符号     注意，运算的参数是BigInteger，不是String
//        1.public BigInteger add(BigInteger val)		+
//        2.public BigInteger subtract(BigInteger val)	-
//        3.public BigInteger multiply(BigInteger val)	*
//        4.public BigInteger divide(BigInteger val)		/
//        5.public BigInteger[] divideAndRemainder(BigInteger val)    //数组中存商和模（余数）
//12.BigDecimal类
//    1.概述
//        float类型和double很容易丢失精度（7位，16位，由尾数决定）
//        为了能精确的表示、计算浮点数，Java提供了BigDecimal
//    2.构造方法
//        public BigDecimal(String val)
//          直接以double作为源是可以的，但是会出现不可预测的情况，这是因为double数据结构的缘故（double并不绝对的准），所以建议先转为String再使用该构造
//          更多的是使用BigDecimal bi = BigDecimal.valueOf(1.2);
//    3.成员方法
//        1.public BigDecimal add(BigDecimal augend)
//        2.public BigDecimal subtract(BigDecimal subtrahend)
//        3.public BigDecimal multiply(BigDecimal multiplicand)
//        4.public BigDecimal divide(BigDecimal divisor)
//13.Date类    表示特定的瞬间，精确到毫秒
//    1.构造方法
//        public Date()   当前时刻（不是毫秒值）  Tue Sep 10 08:51:25 CST 2019，可以通过SimpleDateFormat格式化
//        public Date(long date)  参数date意味着从标准时间（1970.1.1）以来指定date毫秒数的时刻，但要注意电脑本机设置的区时，比如传参0（格林威治时间为0），对于东八区，显示的是CST 8点（中英两国差8小时，中国快）
//    2.成员方法
//        1.public long getTime() 获取该时间对象对应的毫秒值（System.currentTimeMillis()）
//        2.public void setTime(long time)    给该时间对象设置毫秒值
//        3.public String toString( ) 把此 Date 对象转换为以下形式的 String： dow mon dd hh:mm:ss zzz yyyy 其中： dow 是一周中的某一天 (Sun, Mon, Tue, Wed, Thu, Fri, Sat)    ZZZ是时间标准
//14.SimpleDateFormat类
//    1.概述    日期格式化     date和String的转化
//        是DateFormat抽象类的实现类
//    2.构造方法
//        public SimpleDateFormat()
//        public SimpleDateFormat(String pattern)
//    3.成员方法
//        public final String format(Date date)   格式化日期
//        public Date parse(String source)    String转为Date（符合格式的字符串）
//            关于自定义格式的说明，只记录常用的几个：
//            yyyy;yy     年       1970;70
//            MM;M        月       09;9（即使pattern是M，10还是10，位数不足则前面补0）
//            dd;d        天/月    09;9(同上，不足补0)
//            DDD;DD;D    天/年    001;01;1(同上，不足补0)
//            HH;H        小时     0-23
//            kk;k        小时     1-24
//            KK;K        小时     0-11（AM,PM）
//            hh;h        小时     1-12（AM,PM）
//            ss;s        秒
//            SS;S        毫秒
//15.Calendar类    抽象类，无法自己创建对象
//参考  https://www.runoob.com/java/java-date-time.html
//    1.概述    日历类     操作特定的瞬间，通过Calendar类，可以获取特定瞬间的某一部分，比如此时的小时，分钟等等
//    2.成员方法
//        public static Calendar getInstance()	//通过getInstance()获取默认的Calendar子类对象，默认是当前时刻
//        public final void set(int year,int month,int date)    //设置年月日
//        public void set(int field,int val   ue)  //设置指定的字段值
//            类似的还有add(Calendar.DATE, 10);  //给指定字段增加，没有减的方法，但是加负的就是减
//        public int get(int field)	//传入对应字段，获取对应字段值
//        注意：Calendar.DAY_OF_WEEK是从周日开始算的（1）    Calendar.MONTH是0~11
//        可以利用Calendar类巧妙的判断闰年（月份天数-1）

import org.junit.Test;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
public class  DCommonObjectOther {
    public static void main(String[] args) {
    }

    //Arrays工具类
    @Test
    public void arraysTest() {
        int[] arr = {3,2,1,4};
        Arrays.sort(arr);
        System.out.println(Arrays.binarySearch(arr, 1));
        System.out.println(Arrays.toString(arr));
    }

    //基本类型包装类
    @Test
    public void baoZhuangInteger() {
        //构造方法
        Integer in1 = new Integer("1");
        Integer in11 = new Integer(1);
        //成员方法，String和int类型转化
        //int-->String
        String str1 = String.valueOf(1);
        String str2 = new Integer(1).toString();
        String str3 = Integer.toString(1);
        //String转为int
        int int1 = new Integer("1").intValue();
        int int12 = Integer.parseInt("1");
        //自动装箱拆箱
        Integer in21 = 127;
        int int3 = in21;
            //byte范围自动装箱同样大小不会新建对象，从常量池直接获取
        Integer in22 = 127;
        Integer in23 = 128;
        Integer in24 = 128;
        System.out.println(in21==in22); //true
        System.out.println(in23==in24); //false
        System.out.println(in23.equals(in24));  //true
    }

    //Math
    @Test
    public void math() {
        //绝对值
        System.out.println(Math.abs(-11));
        //幂，返回值为double
        System.out.println(Math.pow(2,3));
        //开方，返回值为double
        System.out.println(Math.sqrt(4));
        //随机数，返回值为double
        System.out.println(Math.random());
    }

    //Random
    @Test
    public void randomTest() {
        Random rd = new Random();
        //nextInt的种子，伪随机
        Random rdInt = new Random(100);
        for (int i=0;i<10;i++) {
            //nextInt给定参数为随机数上限（不包括）
            System.out.println(rdInt.nextInt(100));
        }
    }

    //System
    @Test
    public void sysTest() {
        //垃圾回收机制
        System.gc();
        //总结垃圾回收机制的几个特点
//            1.无法人为控制，随机事件对随机垃圾对象进行回收
//            2.判断对象为垃圾的标准，没有任何引用（关联）
//            3.垃圾对象在被回收前会运行finalize()来解放内存以外的系统资源，垃圾对象会复苏，产生异常，但是虚拟机会进行忽略
//            4.触发主GC的条件，如何避免
//                1.不要显式System.gc()，虽然是建议，但一般都会运行
//                2.循环中使用StringBuffer而不是直接操作String
//                3.能使用基本类型就不用包装类
    }

    //精确运算
    @Test
    public void big() {
        BigInteger bi1 = new BigInteger("2222222222222");
        BigInteger bi2 = new BigInteger("3333333333333");
        System.out.println(bi1.add(bi2));
    }

    //Date类
    @Test
    public void dateTest() {
        //构造
        Date date = new Date();
        Date date1 = new Date(10000);

        //设置距1970.1.1的毫秒值
        date1.setTime(10001);
        //获取
        System.out.println(date1.getTime());
        System.out.println(date1.toString());
    }

    //Date和String转换，格式化
    @Test
    public void simpleDateTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        //格式化date生成String
        String dateStr = sdf.format(date);
        try {
            //String转为date
            Date date1 = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Calendar日历类
    @Test
    public void calendarTest() {
        Calendar caTest = Calendar.getInstance();
        caTest.set(2020,2,28);
        caTest.add(Calendar.DAY_OF_MONTH,1);
        System.out.println(caTest.get(Calendar.MONTH)+1 + "月" + caTest.get(Calendar.DAY_OF_MONTH) + "日");
    }

    public static void getLeapYear(int year) {
        if (year > 0 ) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,1,28);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            if (calendar.get(Calendar.MONTH) == 1) {
                System.out.println("闰年");
            } else {
                System.out.println("平年");
            }
        } else {
            System.out.println("年份有误");
        }
    }

}

class CalendarDemo {
//
//    public String getLeapYear(int year) {
//        Calendar calDemo = Calendar.getInstance();
//        calDemo.set(year, 2, 1);
//        calDemo.add(Calendar.DAY_OF_MONTH, -1);
//        int res = calDemo.get(Calendar.DAY_OF_MONTH);
//        if (res == 29) {
//            return "leap year";
//        } else {
//            return "not leap year";
//        }
//    }
//
//    public void getDaysFromBirthday(String str) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
//        Date dateBir = sdf.parse(str);
//        Date dateNow = new Date();
//        long time = dateNow.getTime()-dateBir.getTime();
//        long day = time/1000/60/60/24;
//        System.out.println(day);
//    }


}
