package learnSe.part3;
//3.1常见对象
//
//1.正则表达式
//    参考：https://www.runoob.com/java/java-regular-expressions.html  https://www.cnblogs.com/dirtyman/articles/2689367.html
//    1.概述
//        按一定规则匹配字符串，这个规则就是正则表达式
//    2.规则
//        1.字符类   直接用字符表示，通俗易懂
//            [abc] a、b 或 c（简单类）
//            [^abc] 任何字符，除了 a、b 或 c（否定）
//            [a-zA-Z] a到 z 或 A到 Z，两头的字母包括在内（范围）
//            [a-z[A-Z]] a到z 或A到Z（并集）
//            [a-z&&[abc]]a到z且a,b,c（交集）
//            [a-z&&[^abc]]a到z且除去abc（减去）
//            [0-9] 0到9的字符都包括
//        2.预定义字符类  没有数量词的字符类只能匹配单个字符
//            . 任意字符
//            \d 数字：[0-9]   digit(数字)
//            \D 非数字 ：[^0-9]
//            \s 空白字符 ：[\t\n\r\f]   space(空白)
//            \S 非空白字符 ：[^\s]
//            \w 单词字符：[a-zA-Z_0-9]      注意这个包括_和0-9     word（单词）
//            \W 非单词字符 ：[^\w]
//        3.数量词   没有数量词的字符类只能匹配单个字符
//            X? X，一次或一次也没有
//            X* X，零次或多次
//            X+ X，一次或多次
//            X{n} X，恰好 n 次
//            X{n,} X，至少 n 次
//            X{n,m} X，至少 n 次，但是不超过 m 次
//        4.举例说明转义的问题
//            转义字符有两层用法：
//                1. 本来没有特殊含义的字符，加上转义字符后，就变成了特殊字符，不能当做普通字符处理
//                2. 本来是特殊含义的字符，加上转义字符后，取消特殊含义表示为普通字符
//            正则中的转义问题：（根本原因是某些字符在字符串中或者正则表达式中有特殊含义，每一层有特殊含义，就要转义一次）
//                由于字符串中\表示转义，所以编写正则字符串时，直接使用\明显不合法，想要获取一个普通的\就要写成\\
//                而对于正则规则，\也有其特殊含义，所以需要再次转义，就需要写成\\\\
//            对于\\.表示的是字符.（会进行两步解释）（要匹配字符.直接写.是不行的，因为对于正则表达式.有特殊含义）
//                第一步：\\被编译器解释为\  （转义）
//                第二步：\.被正则表达式引擎解释为.（转义，否则会根据正则规则解释）
//                思考：如果匹配一个\字符，要怎么写？
//                    \\\\
//                        第一步转义：得到\\（将转义标志变成了两个普通字符）
//                        第二步正则解释：想要获得一般的字符需要再次转义，否则会按正则规则解释（\对于正则有特殊含义）
//        5.边界匹配器 使用()定义
//            ^   行的开头，请在正则表达式的开始处使用^。例如：^(abc)表示以abc开头的字符串
//                    注意编译的时候要设置参数MULTILINE，如 Pattern p = Pattern.compile(regex,Pattern.MULTILINE);
//            $   行的结尾，请在正则表达式的结束处使用。例如：(^bca).*(abc$)表示以bca开头以abc结尾的行。
//            \b  单词边界。例如\b(abc)表示单词的开始或结束包含有abc,（abcjj、jjabc 都可以匹配）
//            \B  非单词边界。例如\B(abc)表示单词的中间包含有abc,(jjabcjj匹配而jjabc、abcjj不匹配)
//            \A  输入的开头
//            \G  上一个匹配的结尾(个人感觉这个参数没什么用)。例如\\Gdog表示在上一个匹配结尾处查找dog如果没有的话则从开头查找,注意如果开头不是dog则不能匹配。
//            \Z  输入的结尾，仅用于最后的结束符（如果有的话）
//    3.利用正则表达式进行字符串操作
//            String类的分割功能：public String[] split(String regex)
//            String类的替换功能：public String replaceAll(String regex,String replacement)
//    4.正则表达式的分组
//        1.捕获组可以通过从左到右计算其左括号的顺序来编号   组零始终代表整个表达式
//            例如，在表达式 ((A)(B(C))) 中，存在四个这样的组：
//            1     ((A)(B(C)))
//            2     (A)
//            3     (B(C))
//            4     (C)
//        2.
//            在定义正则表达式时，\1指的是第一组，仍要注意需要转义
//            而在匹配字符串时，$1指的是匹配上的字符串中第一组的部分
//    5.Pattern和Matcher的概述
//        典型的调用顺序：
//        //获取一个Pattern对象，是正则表达式的一个编译表示
//        Pattern p = Pattern.compile("a*b");     //regex
//        //获取匹配器Matcher
//        Matcher m = p.matcher("aaaaab");        //String
//        //看是否匹配
//        boolean b = m.matches();
//            Pattern 类：
//                pattern 对象是一个正则表达式的编译表示，Pattern 类没有公共构造方法
//                要创建一个 Pattern 对象，你必须首先调用其公共静态编译方法compile()，它返回一个 Pattern 对象
//                该方法接受一个正则表达式作为它的第一个参数
//            Matcher 类：
//                Matcher 对象是对输入字符串进行解释和匹配操作的引擎（匹配器）
//                与Pattern 类一样，Matcher 也没有公共构造方法，需要调用 Pattern 对象的 matcher 方法（对象的成员方法）来获得一个 Matcher 对象
//            matcher 对象有很多方法可以进行操作
//                索引方法  index从0开始，左闭右开（或者理解成匹配索引在匹配到不符合的字符才会结束）
//                    public int start()  返回上一次匹配的初始索引
//                    public int start(int group) 返回上次匹配操作期间，由给定组所捕获的子序列的初始索引
//                    public int end()    返回最后匹配字符之后的偏移量
//                    public int end(int group)   返回在以前的匹配操作期间，由给定组所捕获子序列的最后字符之后的偏移量
//                研究方法
//                    public boolean lookingAt()  尝试从第一个字符开始匹配，虽不要求整个字符串都符合正则，但必须从第一个字符开始符合正则才返回true
//                    public boolean matches()  尝试将整个区域与模式匹配，和lookingAt()对比
//                    public boolean find()   尝试查找下一个匹配的子序列，匹配器指向的索引会发生改变
//                    public boolean find(int start）  重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列
//                    public int groupCount() 查看表达式有多少个分组。groupCount 方法返回一个 int 值，表示matcher对象当前有多少个捕获组
//                                        特殊的组（group(0)）总是代表整个表达式，该组不包括在 groupCount 的返回值中
//                    public String group(i)  在字符串符合正则时，可以调用group(i)捕获具体的组对应的子串
//                替换方法
//                    public Matcher appendReplacement(StringBuffer sb, String replacement)   实现非终端添加和替换步骤（匹配到的子序列替换成replacement），然后添加到一个StringBuffer对象中
//                    public StringBuffer appendTail(StringBuffer sb)     实现终端添加和替换步骤，只要存在正则子序列，就将整个字符串追加到参数StringBuffer对象中
//                    public String replaceAll(String replacement)        替换模式与给定替换字符串相匹配的输入序列的每个子序列
//                    public String replaceFirst(String replacement)      替换模式与给定替换字符串匹配的输入序列的第一个子序列
//                    public static String quoteReplacement(String s)     返回指定字符串的字面替换字符串。这个方法返回一个字符串，就像传递给Matcher类的appendReplacement 方法一个字面字符串一样工作
//        PatternSyntaxException  PatternSyntaxException 是一个非强制异常类，它表示一个正则表达式模式中的语法错误
//                PatternSyntaxException 类的方法     PatternSyntaxException 类提供了下面的方法来帮助我们查看发生了什么错误（catch到异常以后使用这些方法输出异常信息）
//                    public String getDescription()  获取错误的描述
//                    public int getIndex()       获取错误的索引
//                    public String getPattern()  获取错误的正则表达式模式
//                    public String getMessage()  返回多行字符串，包含语法错误及其索引的描述、错误的正则表达式模式和模式中错误索引的可视化指示

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FCommonObjectRegex {
    public static void main(String[] args) {
        //字符类
//        String regex = "[abc]";
//        boolean des = "a".matches(regex);
//        System.out.println(des);
        //预定义字符类
//        String regex = "\\d";
//        boolean des = "0".matches(regex);
//        System.out.println(des);
        //关于转义
//        String regex = "[\\\\]\\d";
//        boolean des = ("\\"+"0").matches(regex);
//        System.out.println(des);
        //边界匹配
//        String regex = ".+(abc$)";
//        boolean des = ("aaaabc").matches(regex);
//        System.out.println(des);
        //分割字符串
//        String regex = "\\\\";
//        String des = "a\\a a abc";
//        String[] res = des.split(regex);
//        System.out.println(Arrays.toString(res));
        //分组（按照叠词切割）
//        String regex = "(.)\\1+";   //第一组的词重复至少一次
//        String des = "sdqqfgkkkhjppppkll";
//        String[] res = des.split(regex);
//        System.out.println(Arrays.toString(res));   //[sd, fg, hj, k]
        //matcher
//        String regex = "(.)\\1+";   //第一组的词重复至少一次
//        String des = "sdqqfgkkkhjppppkll";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(des);
//        int count = 0;
//        while (matcher.find()) {
//            //索引
//            count ++;
//            System.out.println("count:" + count);
//            System.out.println("start:" + matcher.start());
//            System.out.println("end:" + matcher.end());
//            System.out.println();
//        }
        //研究
//        String des2 = "qq";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(des);
//        Matcher matcher1 = pattern.matcher(des2);
//        System.out.println("matches:" + matcher.matches());
//        System.out.println("matches:" + matcher1.matches());
//        System.out.println("lookingAt():" + matcher.lookingAt());
//        System.out.println("lookingAt():" + matcher1.lookingAt());
        //替换
//        String regex = "(.)\\1+";   //第一组的词重复至少一次
//        String des = "sdqqfgkkkhjppppkasa";
//        StringBuffer sb = new StringBuffer();
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(des);
//        while (matcher.find()) {
//            matcher.appendTail(sb);
//            System.out.println(sb.toString());
//        }
    }


}
