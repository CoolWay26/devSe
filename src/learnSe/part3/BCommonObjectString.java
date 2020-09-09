package learnSe.part3;
import org.junit.Test;

import java.util.HashMap;
//3.1常见对象
//知识点
//记忆
//        1.String类
//          直接赋值和new String区别
//          常量池和常量优化机制
//          常用方法
//          转换，注意点
//          几个练习很重要（遍历，统计，去空格，按要求输出）,注意手写trim()时处理到全空格的String的特殊情况
//了解
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
//                每个字符串常量一旦创建，就不会改变，对str重新赋值是使其重新指向常量池中的另一个字符串常量
//                而new的时候，会拷贝常量池中常量的副本到堆中
//    2.构造方法  可以通过构造将转为字符串对象
//        1.字节数组（byte[] arr）转字符串对象  public String(byte[] bytes) public String(byte[] bytes, int index, int length)从index开始转length个
//        2.字符数组（char[] arr）转字符串对象  public String(char[] value) public String(char[] value, int index, int count)从index开始转count个
//        3.字符串常量转为字符串对象  public String(String str)
//            char[] chArr = {'a','b','c','d'};
//            String str = new String(chArr,0,2);
//            System.out.println(str);  //ab
//            byte[] byArr = {97,98,99};
//            String str2 = new String(byArr,0,2);
//            System.out.println(str2); //ab
//            System.out.println(str==str2);        //false，两个副本的地址不一致
//            System.out.println(str.equals(str2)); //true，String的equals方法重写过
//    3.成员方法
//        1.判断
//            equals()	    判断值是否相等，String类重写了equals()方法，由地址比较变为值的比较
//            contains()	判断大字符串是否包含小字符串
//            isEmpty()		判断是否为空（通过length判断）
//            如果是字符串常量和字符串变量比较，通常为了防止NullPointException会用常量调用方法，如"abc".equals(str);
//            equalsIgnoreCase()  忽略大小写判断值是否相等
//            startsWith()	判断是否以某字符串开头
//            endsWith()	判断是否以某字符串结尾
//                String str1 = "String";
//                String str11 = "String";
//                String str12 = "Strin" + "g";
//                String str13 = "Strin";
//                String str14 = str13 + "g";
//                String str2 = new String("String");
//                System.out.println(str1==str11);  //输出true    常量池机制，指向同一个常量池中的字符串常量
//                System.out.println(str1==str12);  //输出true    常量优化机制，常量之间的运算存在常量优化机制，虚拟机可以判断结果是什么样的常量
//                System.out.println(str1==str14);  //输出false   变量参与运算，会在堆中创建一个StringBuffer或者StringBuilder对象，通过append()方法拼接，结果返回一个String对象-对象在堆中存储
//                System.out.println(str1==str2);   //输出false   new的String对象是拷贝了常量池中常量的副本到堆中
//        2.获取
//            int length()	获取长度
//            char charAt(int index)	获取index处的字符
//            int indexOf(int ch)	    ch第一次出现的索引，找不到返回-1  注意：参数是int类型，传入char会自动转为int，所以可以传char
//            int indexOf(String str)	str第一次出现的索引
//            int indexOf(int ch, int fromIndex)	    ch从指定位置开始第一次出现的索引
//            int indexOf(String str, int fromIndex)	strch从指定位置开始第一次出现的索引
//            lastIndexOf()类似，从后往前检索（最后一次出现的索引）
//            String subString(int start)
//            String subString(int start, int end)	//左闭右开区间
//        3.转换
//            byte[] getBytes()	//字符串转为字节数组
//              根据平台的编码集	GBK码表1个中文2个字节，特点：第一个字节肯定是负数
//            char[] toCharArray()	//字符串转字符数组
//            static String valueOf(char[] chs)	//字符数组转为字符串
//            注意：String类的静态方法valueOf()可以将任意类型数据转为字符串
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
//            String str4 = str3 + "c";   //str1==str4    false
//            // 字符串+串联底层是通过StringBuilder，StringBuffer完成的，返回的str4实际上已经是sb.toString()（和new类似）,sb的地址和常量池中的地址肯定不一致
//    5.基本操作
//        1.遍历    length()  charAt()
//            基于遍历的任意数组转字符串（纯粹的遍历拼接），字符串反转（先转为数组再逆序拼接）
//        2.统计    HashMap   toCharArray()
//        3.手写trim()    统计前后空格的个数，再使用subString()
public class BCommonObjectString {
    public static void main(String[] args) {
    }

    //基础知识点
    @Test
    public void jiBen() {
        String str = "abc";
        String str0 = "abc";
        String str1 = "ab";
        String str2 = str1 + "c";
        String str3 = "a" + "b" + "c";
        String str4 = new String("abc");
        //常量池机制
        System.out.println(str==str0);//true
        //常量优化机制，只针对常量
        System.out.println(str==str3);//true
        //String变量的拼接与常量不同，是通过StringBuilder或StringBuffer实现，str2获取的是sb.toString()
        System.out.println(str==str2);//false
        //String类重写了equals()，比较的是具体值
        System.out.println(str.equals(str2));//true
        //区别String 和 new String()，new创建的String对象在堆中，不在常量池中
        System.out.println(str==str4);//false
    }

    //构造方法
    @Test
    public void conTest() {
        //字节数组
        byte[] arrBytes = {97, 98, 99};
        String str210 = new String(arrBytes);
        String str211 = new String(arrBytes, 0, 2);
        System.out.println(str210);//abc
        System.out.println(str211);//ab
        //字符数组
        char[] arrChars = {'a', 'b', 'c'};
        String str220 = new String(arrChars);
        String str221 = new String(arrChars, 0, 2);
        System.out.println(str220);//abc
        System.out.println(str221);//ab
        //字符串
        String str23 = new String("abc");
        System.out.println(str23);
    }

    //判断
    @Test
    public void panDuan() {
        //equals()
        String str0 = "abc";
        String str1 = new String("abc");
        System.out.println(str0.equals(str1));
        //contains
        String str2 = "ab";
        System.out.println(str0.contains(str2));
        //startsWith
        System.out.println(str0.startsWith("a"));
        //endsWith
        System.out.println(str0.endsWith("a"));
    }

    //获取
    @Test
    public void huoQu() {
        String str = "abc";
        System.out.println(str.length());
        System.out.println(str.charAt(1));
        System.out.println(str.indexOf("a"));
        System.out.println(str.lastIndexOf("a"));
        System.out.println(str.substring(1));
        System.out.println(str.substring(0,1));
    }

    //转换
    @Test
    public void change() {
        String str = "abc";
        byte[] bs = str.getBytes();
        char[] cs = str.toCharArray();
        String[] ss = {"111","222"};
        //String.valueOf()能将任意类型转为字符串，但对于非char[]和Object而言会是toString()
        String str1 = String.valueOf(cs);
        System.out.println(str1);       //abc
        System.out.println(String.valueOf(bs));     //[B@520a3426
        System.out.println(String.valueOf(cs));     //abc
        System.out.println(String.valueOf(ss));     //[Ljava.lang.String;@18eed359
        System.out.println(String.valueOf(new HashMap<>()));    //{}
    }

    //其他
    @Test
    public void other() {
        String str = "Hello";
        String str1 = "World!";
        System.out.println(str.concat(" " + str1));
        System.out.println(str.replace("H", "HHH"));
        System.out.println(str.trim());
        System.out.println(str.compareTo("hello")); //当前编码的字典顺序挨个比较每个char，输出差值，如果两个字符串是子串的关系，那么输出length的差值，总之若相同结果为0
        System.out.println(str.compareToIgnoreCase("hello"));
    }

    //常见操作
    @Test
    public void doSomething() {
        String str = "1234567";
        String trimStr = " trim  ";
        String upStr = "upperCase";
        String bigStr = "123 68876 123 98987987 123 98797 123";
        int[] intArr = {1,2,3,4,5};
        //遍历输出
        StringTools.getAll(str);
        //统计每个字符分别出现的次数
        StringTools.countChar(str);
        //手写trim()
        StringTools.myTrim(trimStr);
        //String首字母变大写
        StringTools.changeFirstLetter(upStr);
        //数组转为一个{...}字符串
        StringTools.changeArrToString(intArr);
        //反转字符串 StringBuffer的reverse()
        StringTools.reverseString(str);
        //大串中字串的个数  StringBuffer
        StringTools.searchString(bigStr,"123");
    }
}


//操作
class StringTools {
    //遍历    思路：length()和charAt()循环遍历
    public static void getAll(String str) {
        if (!str.isEmpty()) {
            for (int i = 0; i < str.length() - 1; i++) {
                System.out.print(str.charAt(i) + " ");
            }
            System.out.println(str.charAt(str.length() - 1));
        }
    }
    //统计    思路：转为char[]，用HashMap存储统计结果；注意点：计数map泛型，对象判空
    public static void countChar(String str) {
        if (!str.isEmpty()) {
            HashMap<Character, Integer> countMap = new HashMap();
            char[] chars = str.toCharArray();
            for (char ch: chars) {
                if (countMap.containsKey(ch)) {
                    countMap.put(ch, countMap.get(ch) +1);
                } else {
                    countMap.put(ch,1);
                }
            }
            if (countMap.size()!=0) {
                System.out.println(countMap.toString());
            }
        }
    }

    //手写trim()：统计目标字符串首尾第一个不为空格的index，然后subString()
    //trim()是String类已有的方法， "  sabc  ".trim()
    //注意：while循环过程中，为了防止全为空格的字符串，要保证start<=end，可以相等，原因如下
    //举例：对于'   '三个空格的字符串，0-1-2，start循环找到2以后仍然为空格，start变为3发现start>end,中止，end循环此时也不满足条件，则最终取subString(3,3)不越界
    //API决定substring方法是可以写成"sss".substring(3,3)，这样并不越界，但是"sss".substring(4,4)就越界了
    //注意：subString()左闭右开;最终的start,end是首尾不为空格的index,都要被subString()取到，所以右标要取到end+1处
    public static void myTrim(String str) {
        if (!str.isEmpty()) {
            int start = 0;
            int end = str.length() -1;
            while (start <= end && str.charAt(start) == ' ') {
                start ++;
            }
            while (start <= end && str.charAt(end) == ' ') {
                end--;
            }

            System.out.println(str.substring(start,++end));
        }
    }

    //将一个字符串首字符变为大写，其余变为小写 concat(),subString()
    //取出首字母变为大写，同后续字串变为小写拼接
    public static void changeFirstLetter(String str) {
        if (!str.isEmpty()) {
            String strTemp = str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());
            System.out.println(strTemp);
        }
    }

    //数组转为字符串
    //循环数组按要求存入StringBuffer
    public static void changeArrToString(int[] arr) {
        if (arr.length>0) {
            StringBuffer sb = new StringBuffer();
            sb.append("{");
            for (int i : arr) {
                sb.append(i + ",");
            }
            sb.deleteCharAt(sb.length()-1).append("}");
            System.out.println(sb.toString());
        }
    }

    //反转字符串
    //传入StringBuffer直接调用stringBuffer()的reverse()方法；变为数组，反向遍历存入StringBuffer()
    public static void reverseString(String str) {
        if (!str.isEmpty()) {
            StringBuffer sb = new StringBuffer(str);
            System.out.println(sb.reverse().toString());

            //手写，数组反向遍历存入StringBuffer()
//            char[] chars = str.toCharArray();
//            StringBuffer sb = new StringBuffer();
//            for (int i = chars.length - 1; i >= 0; i--) {
//                sb.append(chars[i]);
//            }
//            System.out.println(sb.toString());
        }
    }

    //大串中小串出现的次数
    public static void searchString(String bigStr, String smallStr) {
        //循环中要用StringBuffer操作字符串，否则会产生很多无用的String常量
        if (bigStr.length() > smallStr.length()) {
            int count = 0;
            StringBuffer sb = new StringBuffer(bigStr);
            while (sb.indexOf(smallStr) != -1) {
                count++;
                sb.delete(0,sb.indexOf(smallStr) + smallStr.length());
            }
            System.out.println(count);
        }
    }

}
