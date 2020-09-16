package learnSe.part6;

import org.junit.Test;

import java.io.*;

//6.IO流
//
//记忆
//    1.字符流基本操作，什么情况用字符流，不能用于非纯文本
//    2.缓冲字符流Buffered
//    3.转换流InputStreamReader OutputStreamWriter
//了解
//    1.LineNumberReader
//4.字符流 可以直接读写字符的IO流
//    1.原理
//        实际上，字符流是先读取字节数据再转为字符；写出时是把流中字符数据转为字节再写出
//        使用平台默认编码表读写
//    2.FileReader
//        1.方法
//          read()  读取的是char，但类型提升为int；末尾为-1;
//                  汉字由2个字节组成，第一个字节一定是负数，FileReader读到负数就会连读2个
//        2.
//    3.FileWriter      是字符流，不是字符串流，也不是汉字流
//        1.方法
//          writer()    自动把字符转为字节写出，不再需要.getBytes()
//    4.注意事项
//        1.什么情况使用字符流：只读和只写的时候才使用字符流，其他情况不用（字符流似乎用处不大）
//              读取的时候是按照字符的大小读取的,不会出现半个中文
//              写出的时候可以直接将字符串写出,不用转换为字节数组
//        2.字符流绝对不可以拷贝非纯文本的文件
//              因为字符流读取字节数据后会转为字符，非文本的内容转为字符很大可能是找不到对应的字符，就会用�占位，这样在写出的时候就是�
//5.缓冲字符流   缓冲思想
//    1.BufferedReader
//      1.read()    一次性读取多个字符到缓冲区
//      2.readLine()    读取一行字符到缓冲区（遇到换行符结束，但不包含换行符），返回值是String
//    2.BufferedWriter
//      1.writer()  向缓冲区写出字符
//      2.newLine() 写出一个跨平台的换行符
//    3.练习，反转字符串    理解读取数据后操作数据后再写出
//6.LineNumberReader    BufferedReader的子类，具有相同功能，还可以统计行号
//    1.方法
//      1.getLineNumber()   获取当前行号，默认从0开始，每调用一次readLine()只要读的不是null,那么LineNumber+1（所以看起来是从1开始）
//      2.setLineNumber()   设置当前行号，不会更改流中实际的位置，只是更改getLineNumber()的返回值
//7.使用不同码表读取数据
//    1.通过InputStreamReader，OutputStreamWriter      属于字符流
//    2.适用于BufferedReader和BufferedWriter    缓冲字符流
//8.练习：递归的思想
//
public class IODCharStream {

    //基础操作
    @Test
    public void readerTest() throws IOException {
        FileReader fr = new FileReader("src\\learnSe\\part6\\InputTest.txt");
        FileWriter fw = new FileWriter("src\\learnSe\\part6\\OutputTest.txt");
        int ch;
        while((ch = fr.read()) != -1) {				//将读到的字符赋值给ch
            System.out.println((char)ch);			//将读到的字符强转后打印
            fw.write("我的天哪！");
            fw.write("adsda");
            fw.write(ch);           //向fw的缓冲区写出
        }

        fr.close();	    //关流
        fw.close();
    }

    //缓冲字符流
    @Test
    public void bufferedTest() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(""));
        BufferedWriter bw = new BufferedWriter(new FileWriter(""));
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    //练习：反转字符串
    @Test
    public void reverseTest() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src\\learnSe\\part6\\InputTest.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\learnSe\\part6\\OutputTest.txt",true));

        String str = br.readLine();
        if (str != null) {
            bw.newLine();
            str = new StringBuilder(str).reverse().toString();
            char[] arr = str.toCharArray();
            if (arr.length > 0) {
                for (char ch : arr) {
                    bw.write(ch);
                }
            }
        }
        br.close();
        bw.close();
    }

    //LineNumberReader
    @Test
    public void lineTest() throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader("aaa.txt"));
        String line;
        lnr.setLineNumber(100);							//设置行号
        while((line = lnr.readLine()) != null) {
            System.out.println(lnr.getLineNumber() + ":" + line);//获取行号
        }

        lnr.close();
    }

    //使用不同码表读取数据
    @Test
    public void different() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(""), "UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(""), "GBK");

        int ch;
        while ((ch = isr.read()) != -1) {
            osw.write(ch);
        }

        isr.close();
        osw.close();
    }

    //练习：打印给定文件夹路径下所有.java文件
    @Test
    public void diGuiTest() {
        File file = new File("src\\learnSe\\part6");
        soutFile(file);
    }
    private void soutFile(File file) {
        File[] files = file.listFiles();
        for (File fileTemp : files) {
            if (fileTemp != null && fileTemp.isFile() && fileTemp.getName().endsWith(".txt")) {
                System.out.println(fileTemp.getName());
            } else if (fileTemp.isDirectory()) {
                soutFile(fileTemp);
            }
        }
    }
}
