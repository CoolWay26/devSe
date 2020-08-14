package learnSe.part1;
//1.2基本概念
//知识点
//记忆
//    1.注释嵌套（单行嵌套'多行无法嵌套）
//    2.标识符命名规则，编码约定
//    3.数据类型范围（浮点有效数字），转换（自动转换，强制转换，运算例子）
//    4.char类型能存中文，ASCII码表
//了解
//    1.进制（转换）
//    2.原码反码补码
//
//1.注释
//    1.作用
//        提高阅读性
//        排错
//    2.分类
//        1.单行注释
//            可以嵌套
//            //注释1//注释2
//        2.多行注释
//            不可以嵌套，会把第一个结束标志视为注释结束
//            /*注释*/
//        3.文档注释
//        说明书的制作过程
//            通过javadoc命令生成说明书，以下三个是编写中必用的文档注释
//            @author(提取作者内容)
//            @version(提取版本内容)
//            @param 参数名称//形式参数的变量名称@return 函数运行完返回的数据
//            javadoc -d 指定的文件目录 -author -version ArrayTool.java 出现编码问题可以指定编码
//2.关键字   被Java语言赋予特定含义的单词
//    1.特点
//        组成关键字的字母全部小写,如public static void class等
//    2.注意事项
//        goto和const作为保留字存在,目前并不使用
//****
//3.标志符   给类,接口,方法,变量等命名时使用的字符序列
//    1.组成规则
//        英文大小写字母 数字 $和_
//    2.注意事项
//        1.不能使用关键字
//        2.不能数字开头    _123	$123都是可以的
//    3.约定的命名规则
//        1.原则：         见名知意
//        2.包：           域名倒置,字母要小写 com.daddy
//        3.类或者接口：   每个单词的首字母大写
//        4.方法或者变量： 如果是一个单词全部小写，从第二个单词首字母大写
//        5.常量：         所有的单词大写,用下划线区分每个单词--阅读清晰
//    4.其他书写约定
//        1.方法和程序块之间加空行--让程序看起来清晰
//        2.大括号要对齐，成对，注意缩进
//        3.左大括号前面有空格
//        4.并排语句之间加空格,例如for语句           for (int i; s; s++) {}
//        5.运算符两侧加空格
//4.常量    程序执行过程中值不可以改变
//    1.分类
//        字面常量（字符串，整数，小数，字符--单引号，布尔--true&&false，空常量--null(数组部分)）true,false,null都只能是小写
//        自定义常量（final修饰）
//5.变量    程序执行过程中，在某个范围内其值可以发生改变的量
//    1.定义格式
//        数据类型 变量名 = 变量值;
//    2.目的
//        存放同一类型的常量，重复使用
//    3.注意事项
//        作用域：同一个作用域不能使用相同的变量名
//        初始化值：局部变量在使用之前必须赋值
//        其他：一条语句可以定义多个变量（int a,b,c...; ）
//****
//6.数据类型
//    1.区分数据类型的意义：java是强类型语言，不同数据定义不同类型，分配不同的内存空间，不同类型存储结构不同，严格意义上说，一定要尽可能的确定好较准确的数据类型
//    2.分类：
//        1.基本数据类型（4类8种）
//            整数型（一个字节8位，有一位符号位）
//                byte 占一个字节  -2^7到2^7-1    -128~127
//                short 占两个字节  -2^15~2^15-1  Short.MAX_VALUE=32767   MIN_VALUE = -32768
//                int 占四个字节 -2^31~2^31-1   Integer.MAX_VALUE=2147483647
//                long 占八个字节 -2^63~2^63-1   Long.MAX_VALUE=9223372036854775807
//        解释为何负数看起来比正数表示的范围多一个
//        以byte为例，1位符号位，符号位为0原码范围为00000000~01111111即0到127，0不算正数
//        符号位为1，原码范围为10000000~11111111，即-0~-127，显然-0没有什么实际上的计算意义
//        在计算机中，为了负数参与运算，用补码表示正负数，-0一旦换成补码，排除符号位，按位取反末尾加一那么10000000-11111111-110000000就变成了110000000，而这个补码是-128的补码110000000-101111111-110000000
//        所以实际上，-0和多一位的那个数在补码上是一致的，所以负数就比正数多了一个值的范围
//            浮点型
//                float 占四个字节 -3.403E38~3.403E38  单精度
//                double 占八个字节-1.798E308~1.798E308 双精度
//                1位符号位	8位指数位	23位尾数位
//                    1			23			52
//                IEE754标准计算： 真值指的是硬件存储的值
//                        阶码真值=阶码-127（存储的阶码为阶码真值+127的偏移量是为了便于机器处理，浮点符号位和阶码符号位这样双符号位会影响机器大小比较）
//                        尾数真值=尾数+1（因为实际上隐藏了个位的1，只保存了小数点以后的位数）
//                        对于规格化浮点数阶码，要排除全0（0），全1（无穷）的情况，阶码范围即为1--254
//                        以float为例：
//                        最大值：
//                        (-1)^0 * (1 + 1-2^(-23)) 2^(254 - 127) 约为 2^128
//                        还可以通过查看Float.MAX_VALUE注释来理解{@code float}, (2-2<sup>-23</sup>)&middot;2<sup>127</sup>.
//
//                取值范围主要看指数部分：
//
//                精度(有效数字)主要看尾数位：
//                        float:2^23=8388608  共7位有效数字（不算默认的个位1），但第七位可能受到第八位的影响，所以绝对准的只有6位，第七位可能不准
//                        double:同理有16位有效数字，15位绝对准
//                注意：比较两个数字0和0.0是相等的
//            字符型
//                char 占两个字节 0~65535
//                    char能否存中文字符：java中采用Unicode编码，一个汉字也是2个字节，所以char可以存一个汉字字符；
//                        汉字在GBK占2字节，在UTF8占3字节
//                    ASCII码：给char数据赋值对应的数值，会根据ASCII码表输出对应的符号，如
//                        char a = 3;
//                        sout(a);//输出红桃
//            布尔型
//                boolean理论上占八分之一个字节,因为一个bit就可以决定true和false,但是java中boolean类型没有明确指定他的大小
//                关于boolean的封装类Boolean，在jdk1.5以后，Boolean在"赋值"和"判断"上和boolean一样，即
//                boolean b1 = true ; 或者 Boolean b2 = true ;
//                但是从map中取boolean时，只能用Boolean接收,因为map中存的是对象（对基本数据类型会自动装箱）
//                在jdk1.7以后才可以进行强制转换，如下：
//                    boolean b = true;
//                    Map map = new HashMap();
//                    map.put("b",b);
//                    boolean bm = (boolean)(map.get("b"));
//        2.引用数据类型      数组，类，接口，枚举
//****
//7.数据类型转换
//    1.默认转换  运算的时候byte,short,char不会相互转换,都会自动类型提升为int类型，即使是两个byte类型的数运算，也会提升为int
//    2.强制转换
//        格式 b = (byte)(a + b); 	目标类型加括号
//        注意事项    如果超出了被赋值的数据类型的取值范围得到的结果会与你期望的结果不同，会截断
//    3.字符和字符串运算
//        System.out.println('a');			a字符
//        System.out.println('a'+1);			98	        char和int运算，提升为int
//        System.out.println("hello"+'a'+1);	helloa1
//        System.out.println('a'+1+"hello");	98hello     任何数据类型用+与字符串连接，都会产生新的字符串
//        System.out.println("5+5="+5+5);	    5+5=55
//        System.out.println(5+5+"=5+5");	    10=5+5
//    4.常量和常量相加，变量和变量相加的区别
//        byte b1 = 1;
//        byte b2 = 2;
//        byte b3 = b1 + b2;  //出错，因为编译器无法判断变量的值，不能确定是否会超出范围，会自动转换为int类型
//        byte b4 = 1 + 2;    //不出错，因为编译器可以确定常量的值--java的常量优化机制
//8.二,八,十六进制
//    1.表现形式
//        二进制的数据表现形式
//            由0,1组成。以0b(b可以大写也可以小写)开头(JDK1.7版本可以表示二进制字面量)
//        八进制的数据表现形式
//            由0,1,…7组成。以0开头
//        十六进制的数据表现形式
//            由0,1,…9,a,b,c,d,e,f(大小写均可)。以0x开头（x大小写均可）
//    2.转换
//        任意进制转十进制
//            系数*基数权次幂 求和
//        十进制转任意进制
//            除基倒取余
//        进制间相互转换
//            以二进制为媒介，八进制一位==二进制三位，十六进制一位==八进制四位
//9.原码反码补码
//    1.原码
//        二进制定点表示法，即最高位为符号位，“0”表示正，“1”表示负，其余位表示数值的大小。
//            通过一个字节,也就是8个二进制位表示+7和-7
//            0(符号位)	0000111
//            1(符号位)	0000111
//    2.反码
//        正数的反码与其原码相同；负数的反码是对其原码逐位取反，但符号位除外。
//    3.补码
//        计算机中数值的运算以补码形式进行
//        正数的补码与其原码相同；负数的补码是在其反码的末位加1。
//        补码运算时，如果符号位发生变化，是溢出

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
public class BBasicConcept {
    public static void main(String[] args) {
        Types ty = new Types();
        ty.show();

        boolean b = true;
        Map map = new HashMap();
        map.put("b",b);
        boolean bm = (boolean)(map.get("b"));
    }

    @Test
    public void zhushi() {
//        //单行注释可以嵌套//嵌套
//        /*多行注释不能嵌套/*
//        因为识别到第一个结束标志后面的就失效了*/
//         */
    }
    @Test
    public void biao_Zhi$Fu() {
        //规则：大小写字母，数字，_和$，不能以数字开头
        System.out.println(2*2*2*2*2*2*2);
        for (int i = 0; i>1; i++) {

        }
    }

    @Test
    public void charZhongWen() {
//        java使用Unicode编码，一个中文字符占2字节，可以用char存储
        char ch = '中';
        System.out.println(ch);
    }

    @Test
    public void jinZhi() {
        int er = 0b100;
        int ba = 0100;
        int sl = 0x100;
        System.out.println(er + ";" + ba + ";" + sl);
    }

    @Test
    public void dataTypeChange() {
        //byte,short,char和int进行运算时，会自动转换为int
        byte bt = 2;
        short st = 4;
        char ch = '1';
        int bt2 = bt + st;
        int i = 8;
        System.out.println(bt+st+ch);
    }
}



class Types {
    public void show() {
        short maxShort = Short.MAX_VALUE;
        int maxInt = Integer.MAX_VALUE;
        long maxLong = Long.MAX_VALUE;
        float maxFloat = Float.MAX_VALUE;
        System.out.println(maxLong);
    }


}
