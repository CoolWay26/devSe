package learnSe.part3;
//3.1常见对象
//知识点
//记忆
//    1.StringBuffer
//        线程安全，可变
//        构造方法
//        成员方法append,delete,deleteCharAt,insert,replace;substring
//          append任意类型数据追加到字符串缓冲区（自动转型）
//        StringBuffer和StringBuilder区别
//        StringBuffer作为形式参数传递时操作会改变原来的值
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

import org.junit.Test;

public class CCommonObjectStringBuffer {
    public static void main(String[] args) {
    }


    //构造方法
    @Test
    public void con() {
        //capacity
        StringBuffer sb1 = new StringBuffer();
        System.out.println(sb1.capacity()); //16
        StringBuffer sb2 = new StringBuffer("111");
        System.out.println(sb2.capacity()); //19
        StringBuffer sb3 = new StringBuffer(10);
        System.out.println(sb3.capacity());//10
    }

    //crud
    @Test
    public void crud() {
        //append
        StringBuffer sb = new StringBuffer();
        sb.append(1);
        sb.append('2');
        sb.append("str");
        sb.append(3.14);
        System.out.println(sb.toString());
        //delete
        sb.deleteCharAt(0);
        sb.delete(0,2);
        System.out.println(sb.toString());
        //replace
        sb.replace(0,2,"strReplace");
        System.out.println(sb);
        //reverse
        System.out.println(sb.reverse());
        //subString
        System.out.println(sb.substring(1));
        System.out.println(sb.substring(0,2));
    }

    //String和StringBuffer转换
    @Test
    public void change() {
        //String-->StringBuffer
        StringBuffer sb = new StringBuffer("str");
        sb.append("str");
        //StringBuffer-->String
        String str0 = new String(sb);
        String str1 = sb.toString();
        //强调subString()返回的是一个String
        String str2 = sb.substring(0);
    }

    //StringBuffer作为形式参数传递后进行操作会改变原本的值，String作为形参传递不会改变
    @Test
    public void xingCan() {
        StringBuffer sb = new StringBuffer("str111222");
        changeBuffer(sb);
        System.out.println(sb.toString());
    }
    public void changeBuffer(StringBuffer sb) {
        sb.append("333");
        sb.delete(0,3);
        sb.replace(0,3,"xxx");
        sb.reverse();
    }
}
