package learnSe.part3;
//3.1常见对象
//知识点
//记忆
//    1.StringBuffer
//        线程安全，可变
//        构造方法
//        成员方法append,delete,deleteCharAt,insert,replace;substring
//了解
//5.StringBuffer，StringBuilder
//    1.概述
//        StringBuffer线程安全的可变字符序列（底层加锁-线程安全synchronized）
//        StringBuilder线程不安全
//            String是不可变的字符序列，sb是可变的字符序列
//    2.构造方法
//        1.默认生成16个字符的缓冲区
//            public StringBuffer(){
//                super(16);
//            }
//        2.可以指定容量
//            public StringBuffer(int capacity) {
//                super(capacity);
//            }
//        3.可以直接把String塞进StringBuffer，此时容量为str.length() + 16
//            public StringBuffer(String str) {
//                super(str.length() + 16);
//                append(str);
//            }
//            注意：不能塞char或者String[]，可以塞入其他StringBuffer,StringBuilder,String子类对象
//    3.成员方法
//        1.增
//            append(String str); 任意类型数据追加到字符串缓冲区（自动转型）
//            insert(int offset,String str)   把任意类型的数据插入到字符串缓冲区指定位置
//        2.删
//            deleteCharAt(int index) 删除指定位置字符
//            delete(int start,int end)   删除指定区间  左闭右开
//        3.其他
//            1.替换    replace(int start,int end,String str)       sb的replace()是区间替换，Strign是字符/字符串替换
//            2.截取    返回值是String，不再是SB
//                substring(int start)    start到末尾
//                substring(int start,int end)    左闭右开
//        4.转换
//            StringBuffer -- String
//                sb.substring()
//                sb.toString()
//                new String(StringBuffer sb)
//            String -- StringBuffer
//                new StringBuffer(String str)
//                append();

public class CCommonObjectStringBuffer {
    public static void main(String[] args) {
//        StringBuffer sbf = new StringBuffer();
        StringBuffer sbf = new StringBuffer("StringDemo");
//length和capacity
//        int len = sbf.length();
//        int cap = sbf.capacity();
//        System.out.println("length:" + len + "\ncapacity:" + cap);
//增
//        sbf.append("str");
//        sbf.insert(0,"str");
//删
//        sbf.deleteCharAt(0);
//        sbf.delete(10,10);
//替换
//        sbf.replace(0,1,"0-1");
//反转
//        sbf.reverse();
        char[] charArr = {'s','t','r','i','n','g'};
        System.out.println(new String(SbDemo.reverserCharArr(charArr)));
        System.out.println(sbf);
    }
}

class SbDemo {
    //手写数组的reverse()
    public static char[] reverserCharArr(char[] arr) {
        char[] result = {};
        if (arr.length != 0) {
            //这里注意，1.StirngBuffer构造并不能直接接收数组    2.数组没有进行toString()的重写
            StringBuffer sb = new StringBuffer(new String(arr));
            result = sb.reverse().toString().toCharArray();
        }
        return result;
    }

}
