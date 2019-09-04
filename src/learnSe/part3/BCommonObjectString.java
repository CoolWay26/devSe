package learnSe.part3;
import java.util.HashMap;
import java.util.Scanner;
//3.1常见对象
//
//4.String类
//    1.概述
//        1.首先要区别直接赋值String和new String
//            1.明确一点，最终都是返回String对象
//            2.区别
//                String str = "xxx";//这是直接指向常量池中的"xxx"字符串常量对象
//                String str = new String("xxx");//这是在堆中new一个String对象，堆中对象获取了常量池中字符串常量对象的副本
//                String str1 = "xxx";
//                String str2 = "xxx";    //str1和str2指向同一个对象（常量池中的）
//                String str3 = new String("xxx");
//                String str4 = new String("xxx");    //str3和str4指向堆中不同的两个对象，但这两个对象都复制了同一个对象（常量池中的）的副本
//    2.构造方法  可以通过构造将转为字符串对象
//        1.字节数组（byte[] arr）转字符串对象  public String(byte[] bytes) public String(byte[] bytes, int index, int length)
//        2.字符数组（char[] arr）转字符串对象  public String(char[] value) public String(char[] value, int index, int count)
//        3.字符串常量转为字符串对象  public String(String str)
//    3.成员方法
//        1.判断
//            equals()	    判断值是否相等，String类重写了equals()方法，由地址比较变为值的比较
//            如果是字符串常量和字符串变量比较，通常为了防止NullPointException会用常量调用方法，如"abc".equals(str);
//            equalsIgnoreCase()  忽略大小写判断值是否相等
//            contains()	    判断大字符串是否包含小字符串
//            startsWith()	判断是否以某字符串开头
//            endsWith()	    判断是否以某字符串结尾
//            isEmpty()		判断是否为空（通过length判断）
//        2.获取
//            int length()	获取长度
//            char charAt(int index)	获取index处的字符
//            int indexOf(int ch)	ch第一次出现的索引
//            int indexOf(String str)	str第一次出现的索引
//            int indexOf(int ch, int fromIndex)	ch从指定位置开始第一次出现的索引
//            int indexOf(String str, int fromIndex)	strch从指定位置开始第一次出现的索引
//            lastIndexOf()类似，从后往前检索（最后一次出现的索引）
//            String subString(int start)
//            String subString(int start, int end)	//左闭右开区间
//        3.转换
//            byte[] getBytes()	//字符串转为字节数组
//              根据平台的编码集	GBK码表1个中文2个字节，特点：第一个字节肯定是负数
//            char[] toCharArray()	//字符串转字符数组
//            static String valueOf(char[] chs)	//字符数组转为字符串
//            注意：String类的valueOf()可以将任意类型数据转为字符串
//            对象也可以，输出调用toString()方法的结果
//        4.拼接
//            concat()
//        5.其他
//            toUpperCase()//转大写
//            toLowerCase()//转小写
//            String replace(char/String old, new)	//替换，返回另一个字符串，当前的字符串并没有变化
//            trim()	//去除两端空格
//            int compareTo(String str) //按位字典顺序比较，输出按码表值作差的值，相同的是0，长短不一致的，空格算作0，如果比较到较短字符最后一位都相同，那么返回length的差
//            int compareToIgnoreCase(String str)	//忽略大小写
//    4.注意
//        1.字符串属于对象   引用型变量最终指向一个常量池中的字符串常量对象
//        2.String是不可变常量对象（存在常量池中）
//            String str = "abc";//常量池中没有"abc"，新生成一个"abc"
//            str = "def";    //常量池中没有"def"，新生成一个"def"
//            str = "abc";    //常量池中已有"abc"，直接将"abc"的地址给str
//            这样赋值，"abc","def"一旦生成，就不会改变，只是str指向的地址值发生改变
//        3.java常量优化机制
//            String str1 = "abc";
//            String str2 = "a" + "b" + "c";  //str1和str2是同一个字符串常量    str1==str2  true，这是java的常量优化机制，常量先拼接再去看常量池
//            但是这里也有一个坑
//            String str3 = "ab";
//            String str4 = str3 + "c";   //str3==str4    false
//            // 字符串+串联底层是通过StringBuilder，StringBuffer完成的，返回的str4实际上已经是sb.toString()（和new类似）,sb的地址和常量池中的地址肯定不一致
//    5.基本操作
//        1.遍历    length()  charAt()
//            基于遍历的任意数组转字符串（纯粹的遍历拼接），字符串反转（先转为数组再逆序拼接）
//        2.统计    HashMap   toCharArray()
//        3.手写trim()    统计前后空格的个数，再使用subString()
public class BCommonObjectString {
    public static void main(String[] args) {
//区别String 和 new String()，常量优化机制和 + 串联String的区别
        String str1 = "String";
        String str11 = "String";
        String str12 = "Strin" + "g";
        String str13 = "Strin";
        String str14 = str13 + "g";
        String str2 = new String("String");
//        System.out.println(str1==str11); //输出true
//        System.out.println(str1==str12); //输出true
//        System.out.println(str1==str14); //输出false
//        System.out.println(str1==str2); //输出false

//构造方法
        //字节数组
//        byte[] arrBytes = {97,98,99};
//        String str21 = new String(arrBytes);
//        System.out.println(str21);//输出abc
//        //字符数组
//        char[] arrChars = {'a','b','c'};
//        String str22 = new String(arrChars);
//        System.out.println(str22);//输出abc

//成员方法
//        equals()
//        boolean bool1 = str1.equals(str14);             //true
//        boolean bool2 = str1.equalsIgnoreCase(str14);   //true
//        boolean bool3 = str1.contains(str13);           //true
//        boolean bool4 = str1.isEmpty();
//
//        int len = str1.length();
//        char ch = str1.charAt(0);
//        int index = str1.indexOf("st", 0);
//        int index2 = str1.lastIndexOf("str", 2);
//        String strSub = str1.substring(0,2);
//
//        byte[] bt = str1.getBytes();
//        char[] cr = str1.toCharArray();
//        String strValue = String.valueOf(bt);
//
//        String con = str1.concat(str2);
//        String rep = str1.replace("str","str2");
//        int com = str1.compareTo(str13);
//        String strTrim = str1.trim();

        StringTools tools = new StringTools();
//        tools.traverse("traverse!");
//        tools.countChar();
        tools.myTrim();
    }
}


//操作
class StringTools {
    //遍历
    public void traverse(String str) {
        if (!str.isEmpty()) {
            for (int i = 0; i < str.length() - 1; i++) {
                System.out.print(str.charAt(i) + " ");
            }
            System.out.println(str.charAt(str.length() - 1));
        }
    }
    //统计
    public void countChar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入任意字符串，按回车结束");
        String str = sc.nextLine();
        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();

        //直接遍历str
//        if (!str.isEmpty()) {
//            for (int i = 0; i < str.length(); i++) {
//                if (countMap.containsKey(str.charAt(i))) {
//                    int countTemp = countMap.get(str.charAt(i)) + 1;
//                    countMap.put(str.charAt(i), countTemp);
//                } else {
//                    countMap.put(str.charAt(i), 1);
//                }
//            }
//        }
        //转为数组进行遍历，代码更清晰
        char[] chars = str.toCharArray();
        if (chars.length != 0) {
            for (char charTemp : chars) {
                if (countMap.containsKey(charTemp)) {
                    int countTemp = countMap.get(charTemp) + 1;
                    countMap.put(charTemp, countTemp);
                } else {
                    countMap.put(charTemp, 1);
                }
            }
        }
        if (countMap.size() != 0) {
            System.out.println(countMap);//hashMap重写了toString()方法，输出{key=value...}
        }
    }

    //手写trim()
    public void myTrim() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入任意字符串，按回车结束");
        String str = sc.nextLine();

        if (!str.isEmpty()) {
            int start = 0;
            int end = str.length() - 1;
            while (start <= end && str.charAt(start) == ' ') {
                start++;//为空格则继续看下一个字符
            }
            while (start <= end && str.charAt(end) == ' ') {
                end--;//为空则继续看前一个字符
            }
            //API决定substring方法是可以写成"sss".substring(3,3)，这样并不越界，但是"sss".substring(4,4)就越界了
            //左闭右开，end + 1
            System.out.println(str.substring(start, end + 1));
        }
    }
}
