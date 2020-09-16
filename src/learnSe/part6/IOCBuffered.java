package learnSe.part6;
import org.junit.Test;
import java.io.*;
//6.IO流
//记忆
//    1.缓冲思想（原理）
//    2.注意事项  和小数组比较哪个效率高
//    3.基本操作  java7特有的处理流异常的方式
//
//
//3.缓冲字节流     减少对硬盘的读写，转为对内存的读写，效率高很多
//    1.缓冲思想
//        用小数组操作比逐个读写效率高，java考虑到这样的思想，通过装饰设计模式提供了字节缓冲流，减少了IO的资源消耗
//    2.BufferedInputStream
//        BufferedInputStream内置了一个缓冲区（数组），BufferedInputStream会一次性从文件读取1024*8=8192个字节存在缓冲区中
//        当程序需要时就返回给程序，直到8192个字节都被读走以后，再从文件中重新获取至多8192个字节
//    3.BufferedOutputStream
//        BufferedOutStream内置了一个缓冲区(数组)，程序从流中写出数据，不会直接写入文件，而是存在缓冲区中，然后一次性写入到文件
//    4.注意事项：
//        1.关流时只需要关缓冲字节流
//        2.小数组和Buffered哪个效率更高
//          如果把小数组定义为length=8192，那么小数组效率更高，因为小数组操作，只需要操作一个数组，而缓冲字节流是操作两个缓冲数组
//        3.flush()和close()
//          flush()刷新缓冲区，会将缓冲区中的内容写出，保证实时性，刷新后仍然可以继续操作缓冲区；
//          close()是关流，实际上每次关流之前都会刷新缓冲区，但关流以后显然不能继续操作缓冲区了；此外，每次缓冲区存满，也会自动进行flush()
//    5.字节流读写中文
//        1.字节流读取中文时很可能会读到半个中文导致乱码（这是无法避免的，因为一句话中有标点，有字母的可能性特别大），缓冲区不可能无限大的读整句
//        2.字节流写出中文时，必须转为字节数组--getBytes()
//    6.java7处理流异常的标准代码
//        try()小括号中创建流对象
//        后跟{}大括号进行读写操作
//        自动关流，原因是  在try()中创建的流对象必须实现了AutoCloseable这个接口,如果实现了,在try后面的{}(读写代码)执行后就会自动调用流对象的close方法将流关掉
public class IOCBuffered {

    //基本操作
    @Test
    public void bufferedInOutTest() throws IOException {
        FileInputStream fis = new FileInputStream("src\\learnSe\\part6\\InputTest.txt");
        BufferedInputStream bis = new BufferedInputStream(fis); //装饰者
        FileOutputStream fos = new FileOutputStream("src\\learnSe\\part6\\OutputTest.txt",true);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int b;
        while ((b=bis.read()) != -1) {
            bos.write(b);   //缓冲字节流是向缓冲区写出，不意味着一次性向缓冲区写出，缓冲区满了才一次性从缓冲区向目标写出
        }

        bis.close();
        bos.close();
    }

    //操作中文
    @Test
    public void inputOutputCN() throws IOException {
        FileInputStream fis = new FileInputStream("src\\learnSe\\part6\\InputTest.txt");
        FileOutputStream fos = new FileOutputStream("src\\learnSe\\part6\\OutputTest.txt", true);
        byte[] arr = new byte[2];
        int len;
        while ((len = fis.read(arr)) != -1) {
            System.out.println(new String(arr));    //String构造方法能将byte[],char[]转为String，能看到由于数组太小导致读取了半个汉字--乱码
            fos.write(arr, 0, len);     //虽然可能读写了半个汉字，但只要完整读写，不影响结果
        }
//        fos.write("你好吗？".getBytes());
    }

    //异常处理java7
    public void exceptionTest() throws IOException {
        try (
                FileInputStream fis = new FileInputStream("src\\learnSe\\part6\\InputTest.txt");
                FileOutputStream fos = new FileOutputStream("src\\learnSe\\part6\\OutputTest.txt", true);
        ) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        }
    }

    //练习
    //加密    异或
    public void jiaMi() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src\\learnSe\\part6\\InputTest.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src\\learnSe\\part6\\OutputTest.txt", true));
        int b;
        while((b = bis.read()) != -1) {
            bos.write(b ^ 123);     //每一个字节都^一个数（密钥）
        }

        bis.close();
        bos.close();
    }
}
