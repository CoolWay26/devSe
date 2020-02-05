package learnSe.part1;
//1.3基本概念（Ⅱ）
//知识点
//记忆
//    1.算术运算符（整数相除，负数取模，自增自减-逻辑|转换，拓展赋值运算符-转换）
//    2.关系运算符（结果一定是boolean型）
//    3.逻辑运算符（&&||短路）
//    4.位运算符（相当于乘除，^异或加密，移位补零）
//    5.三目运算符（?:;和if_else区别，运算符一定要有结果）
//    6.流程控制语句（switch注意点--break穿透，打印形状-九九乘法表|三角|菱形，控制跳转语句--break整个|continue当次|return结束方法，标号--标记循环）
//了解
//1.运算符   操作常量和变量的符号
//    1.分类
//        1.算术运算符 +,-,*,/,%,++,--
//            1.+ 正号,加法运算,字符串连接符
//            2./ 整数相除只能得到整数,浮点数运算得到小数，所以想得到小数结果，参与运算的值就要有小数，如10/3.0
//            3.% 负数模运算先忽略符号计算，结果符号再与被模数保持一致      (-10)%3==-1;
//                结果与被模数保持一致
//            4.++和--
//                单独使用：放在操作数的前面和后面效果一样。(这种用法是我们比较常见的)
//                参与运算使用：
//                    放在操作数的前面，先自增或者自减，然后再参与运算。   ++a + b;
//                    放在操作数的后面，先参与运算，再自增或者自减。       a++ + b;
//                    加上括号不会改变这样的逻辑，如(a++)，a会先参加括号外的运算再进行++
//                        int x = 4;
//                        int y = (x++)+(++x)+(x*10); //加不加括号是一样的，加了括号，也是要先参与下一步的运算，再进行++
//                        System.out.println(y);      //4 + 6 + 6*10 = 70
//                ++--运算符会做自动强转，不会出错
//                    问哪句会报错,为什么
//                    byte b = 10;
//                    b++;            //如果按b = b + 1运算，b+1的结果默认转换为int，再赋值给byte类型的b会报错，但++运算符将自增结果自动强转成了原本的byte类型
//                    b = b + 1;	  //这里报错
//        2.赋值运算符 =,+=,-=,*=,/=,%=
//            1.拓展赋值运算符   +=,-=,*=,/=,%= 左右两边运算后赋值    a += b;即 a = a + b;
//                同自增自减运算符一样，会做自动强转
//                    short s=1;
//                    s=s+1;    //报错，short运算自动转为int
//                    short s=1;s+=1;   //自动强转s=(short)(s+1)
//        3.关系运算符 ==,!=,>,>=,<,<=
//            1.注意：
//                1.无论你的操作是简单还是复杂，结果一定是boolean类型
//                2."=="不能写成"="
//        4.逻辑运算符 &,|,^,!,&&,||
//            1.逻辑运算符一般用于连接boolean类型的表达式或者值
//            2.规则：
//                &逻辑与:有false则false。
//                |逻辑或:有true则true。
//                ^逻辑异或:相同为false，不同为true。
//                !逻辑非:非false则true，非true则false。
//            3.&& || 具有短路效果
//        5.位运算符  &,|,^,~ ,>>,>>>,<<  0视为false,1视为true
//            1.位异或运算符^   一个数据对另一个数据位异或两次，该数本身不变（用来加密解密）
//            2.左右移位相当于乘除2	位运算比乘除运算更高效，对于加减差别不明显
//                最有效率的算出2 * 8的结果（2<<3）
//                <<:左移		低位补0
//                >>:右移		高位补原最高位
//                >>>:无符号右移 	高位补0
//        6.三元运算符 (关系表达式) ? 表达式1 : 表达式2;
//            对于if_else和三目运算符，针对java编译器来说，三目运算符效率要更高，因为jvm解析class文件时少一个指令
//            但针对汇编的编译器而言，if_else的指令要少两个，效率反而更高
//2.流程控制语句
//    1.顺序结构
//    2.选择结构
//        1.if语句
//            if () {
//                if () {
//
//                } else {
//
//                }
//            } else if () {
//
//            } else {
//
//            }
//            1.可以跟多个else if ,最后的else可以省略
//            2.可以嵌套
//            3.语句体只有1句时，大括号可以省略,，但建议不省略，因为会有陷阱，如
//                int x = 10;实际上是2句
//                int x;
//                x = 10;
//            4.if语句和三目运算符
//                1.三目运算符实现的，都可以采用if语句实现，反之不成立
//                    因为三目运算符是一个运算符，运算符操作完是一定会有一个结果
//                    而很多语句的执行并没有结果，比如输出语句，赋值语句等
//                        (3>2)?System.out.println(3):System.out.println(3);    //报错
//                        (3>2)?int a = 3: int b = 2;   //报错，原因都是没有结果
//        2.switch语句
//            1.格式
//                switch(表达式) {
//                case 值1：
//                    语句体1;
//                    break;
//                case 值2：
//                    语句体2;
//                    break;
//                …
//                default：
//                    语句体n+1;
//                    break;
//                }
//            2.注意
//                1.表达式可以是 byte short char int String enum，1.7以前不支持String类型
//                2.default可以省略，但建议不省，可以对错误情况做出说明
//                    default不放在最后也是最后不匹配才去执行，但建议放在最后
//                    如果default不放在最后，假设default没有break，那么会接着执行后面的case语句块，直到最后或者遇到break
//                    如下情况会输出null,2
//        switch(3){
//            case 1:
//                System.out.println(1);
//                break;
//            default:
//                System.out.println("null");
////                break;
//            case 2:
//                System.out.println(2);
//                break;
////            case 3:
////                System.out.println(3);
////                break;
//        }
//                3.最后一个break可以省略，但建议不省
//                4.结束条件是break或者末尾的大括号，而不是执行完default		尤其注意break穿透
//                5.switch在判断固定值时使用，if往往应用于区间
//                6.注意多个case对应同一处理的情况，如下
//                    switch (x) {
//                    case 1:
//                    case 2:
//                    case 3:
//                        System.out.println("123！");
//                        break;
//                    default:
//                        break;
//                    }
//    3.循环结构  for,while,do...while
//        1.for
//            1.格式
//                for(初始化表达式;条件表达式;循环后的操作表达式) {
//                循环体;
//                }
//                for(接收数组元素的变量:循环对象-数组) {
//                ...//语句
//                }
//
//                例子：九九乘法表
//                for (int i = 1; i <= 9; i++) {
//                    for (int j = 1; j <= i; j++) {
//                        System.out.print(j + " * " + i + " = " + (i*j) +"\t");
//                    }
//                System.out.println();
//                }
//                注意：
//                    外循环控制行数，内循环控制列数
//                    转义字符 "\t"制表符
//                //打印菱形，正三角拼倒三角
//                //正三角
//                for (int m = 0; m <= 4; m++) {
//                      for (int s=m;s<4;s++){
//                          System.out.print(" ");
//                      }
//                  for (int i = 0; i <= m; i++) {
//                      System.out.print("* ");
//                  }
//                  System.out.println();
//                }
//                //倒三角
//                for (int n = 4; n > 0; n--) {
//                  for (int s= n;s<=4;s++){
//                      System.out.print(" ");
//                  }
//                  for (int i = 0; i < n; i++) {
//                      System.out.print("* ");
//                  }
//                  System.out.println();
//                }
//                统计水仙花数的个数
//                for(i=100;i<1000;i++) {
//                    int x = i/100;
//                    int y = (i-100*x)/10
//                    int z = i-100*x-10*y
//                    int count = 0;
//                    if(i == x*x*x + y*y*y + z*z*z) {
//                        count++;
//                        System.out.println(i);
//                    }
//                }
//            2.注意
//                1.判断条件语句无论简单还是复杂结果是boolean类型
//                2.循环体语句如果是一条语句，大括号可以省略，建议不省
//        2.while
//            1.格式
//                初始化语句;
//                while(判断条件语句) {
//                    循环体语句;
//                    控制条件语句;
//                }
//                不满足条件跳出循环
//            2.例子
//                求1-100之和
//                int sum = 0;
//                int i = 0;
//                while(i<=100) {
//                    sum = sum + i;
//                    i++;
//                }
//        3.do while
//            1.格式
//                初始化语句;
//                do {
//                    循环体语句;
//                    控制条件语句;
//                }while(判断条件语句);
//            2.例子
//                1-100之和
//                int sum = 0;
//                int i = 0;
//                do {
//                    sum += i;
//                    i++;
//                }while (i>100);
//        4.几种循环的区别
//            1.do...while循环至少执行一次循环体
//            2.for循环结束后，循环控制变量从内存中弹出，while循环的控制变量在while外声明，不随循环弹出，但这样的情况在开发中很少，几乎不用while和do while
//        5.控制跳转语句
//            1.break 在循环和switch中，终止整个循环
//            2.continue	只在循环中，跳出当次循环
//            3.另外，可以用return借由结束方法的方式结束循环
//        6.死循环
//            1.原因：循环控制变量出现问题，比如忘记处理循环控制变量了
//            2.例子：while(true){...}
//        7.控制跳转语句标号
//            1.定义：用合法的标识符标记某个循环从而对其控制
//            2.例子：
//                outer:
//                for (int i = 0; i < 5; i++) {
//                    inner:
//                    for (int j = 0; j <= i; j++) {
//                        System.out.println(" *");
//                        //一般来讲，都是用于跳出外循环，因为跳出内循环直接用break就行
//                        break outer;
//                    }
//                }
public class CBasicConcept {
    public static void main(String[] args) {
//++
//        int x = 4;
//        int y = x++ + ++x + (x*10);
//        System.out.println(y);      //4 + 6 + 6*10 = 70
//移位运算乘除效率高
//        int a = 2<<3;
//        System.out.println(a);
//if标签最好不要省略大括号，有陷阱
//        if (true)
//            int i = 10;   //报错，int i = 10;实际上是两条语句
//三目运算符操作后必须要有结果
//        (3>2)?System.out.println(3):System.out.println(3);    //报错
//        (3>2)?int a = 3: int b = 2;   //报错，原因都是没有结果
//if,++,||
//        int x = 1,y = 1;
//        if(x++==1 || ++y==1)	//2 1
//        {
//            x =7;			//7 1
//        }
//        System.out.println("x="+x+",y="+y); //        x=7,y=1
//九九乘法表
//        for (int i = 1; i <= 9; i++) {
//            for (int j = 1; j <= i; j++) {
//                System.out.print(j + " * " + i + " = " + (i*j) +"\t");
//            }
//            System.out.println();
//        }
// do{...}while
//        int sum = 0;
//        int i = 0;
//        do {
//            sum += i;
//            i++;
//        }while (i>100);
//标号
//        outer:
//        for (int i = 0; i < 5; i++) {
//            inner:
//            for (int j = 0; j <= i; j++) {
//                System.out.println(" *");
//                break outer;
//            }
//        }
//        switch (3) {
//            case 1:
//                System.out.println("matching");
//                break;
//            default:
//                System.out.println("not matching");
//            case 3:
//                System.out.println("matching");
//        }
    }

//    public void test() {
//        //下面的语句执行，因为3匹配不上，所以会执行default输出not matching，但因为漏了break，导致后续代码会重复执行，不论case是否匹配上，即会接着输出matching
//        //not matching
//        //matching
//        switch (3) {
//            case 1:
//                System.out.println("matching");
//                break;
//            default:
//                System.out.println("not matching");
//
//            case 2:
//                System.out.println("matching");
//                break;
//        }
//    }
}
