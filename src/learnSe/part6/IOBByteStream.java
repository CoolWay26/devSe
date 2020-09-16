package learnSe.part6;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//6.1IO流
//
//    记忆    FileInputStream read()
//
//2.字节流
//    1.FileInputStream     是抽象类InputSteam的子类
//        1.构造
//            可以直接给路径，也可以给File对象（本质上都是路径）
//          2.方法
//            int read()  读取一个字节，返回值为int，如果是-1则说明读到了文件末尾
//                为什么读取字节返回值不为byte而为int:  根本原因是为了更好的判断读到了末尾-1
//                    由于文件是以二进制形式存储，一旦返回为字节，很可能中间会读到11111111，即为-1
//                    如果返回值为int，那么会在前面补足24个0凑成32位，就变成了int类型的255，而不是-1
//                    read()这样处理，write()会进行逆向操作
//            void close()  关流
//            int available()   获得被读入流的文件的字节数量
//    2.FileOutputStream
//        1.构造
//            给路径或者File，另外还可以限制是否为追加写出到目标文件
//        2.方法
//            void write()  写出一个字节
//                路径必须正确，如果目标文件不存在，会自动创建一个；如果存在，看构造是否追加写入，不是追加则清空文件再写入内容
//            void close()
//    3.标准的小数组操作
//        1.byte[] arr = new byte[1024*8]   定义成1024的整数倍，字节数组byte[]
//        2.fis.read(arr) 返回值是读到的字节个数，读不到时返回-1
//        3.fos.write(arr,0,len)
//        4.用数组操作有一个小注意点：
//              每次向数组中读入数组是从index0向后依次覆盖原有数据，所以假设进行了多次操作
//              如果最后一次len<数组length，那么数组最后的数据不会被覆盖，因此fos.write(arr,0,len)是必要的，不能简单的写成fos.write()
public class IOBByteStream {

    //FileInputStream
    @Test
    public void fileInputSteamTest() throws IOException {
        FileInputStream fis = new FileInputStream("src\\learnSe\\part6\\InputTest.txt");
        int b;
        while ((b = fis.read()) != -1) {
            System.out.println(b);
        }
        fis.close();
    }

    //FileOutputStream
    @Test
    public void fileOutputStreamTest() throws IOException {
        FileOutputStream fos = new FileOutputStream("src\\learnSe\\part6\\OutputTest.txt", true);
        fos.write(97);
        fos.close();
    }

    //拷贝文件
    @Test
    public void copyTest() throws IOException {
        //逐个字节操作，效率低
        FileInputStream fis = new FileInputStream("src\\learnSe\\part6\\InputTest.txt");
        FileOutputStream fos = new FileOutputStream("src\\learnSe\\part6\\OutputTest.txt", true);

//        int b;
//        while ((b = fis.read()) != -1) {
//            fos.write(b);
//        }
//        fis.close();
//        fos.close();

        //用字节数组操作，效率高，但对于大文件存在内存溢出的可能，所以要定义成小数组，而不是定义成fis.available()大小
        //标准格式
        int len;
        byte[] arr = new byte[1024 * 8];
        while ((len = fis.read(arr)) != -1) {     //len是读到的字节个数
//            fos.write(arr);
            //必须要这样写，否则会将整个arr写出，包括那些尚未被覆盖的老数据
            fos.write(arr, 0, len);
        }
        fis.close();
        fos.close();
    }

}
