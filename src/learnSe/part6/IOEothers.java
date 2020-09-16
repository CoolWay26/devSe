package learnSe.part6;
//6.IO流
//记忆
//
//
//了解
//  1.序列流
//
//
//7.序列流SequenceInputStream
//  1.作用：整合多个输入流，读的时候从第一个依次读
//8.内存输出流ByteArrayOutputStream
//  1.作用：该输出流可以向内存中写数据, 把内存当作一个缓冲区（字节数组）, 写出之后可以一次性获取出所有数据
//9.对象操作流ObjecOutputStream&&ObjectInputStream
//  1.作用：该流可以将一个对象写出, 或者读取一个对象到程序中. 也就是执行了序列化和反序列化的操作（操作的对象必须能被序列化和反序列化---实现Serializable）
//10.打印流PrintStream
//  1.作用：该流可以很方便的将对象的toString()结果输出, 并且自动加上换行, 而且可以使用自动刷出的模式
//11.标准输入输出流
//12.随机访问流RandomAccessFile（不属于流）
//13.数据输入输出流DataInputStream&&DataOutputStream
//14.Properties通过流读取配置文件
//  1.Properties是Hashtable子类
//        表示一个持久化的属性集，可以通过流加载，键和值都是String
//  2.方法    具备Hashtable的方法，即可以看作Map集合去操作，下面只说特有方法
//      public Object setProperty(String key,String value)
//      public String getProperty(String key)
//      public Set<String> stringPropertyNames()    获取键的集合
//      public Enumeration<?> propertyNames()   获取键的枚举，枚举不推荐用，麻烦
//
//      void load(流对象)  将配置文件键值对载入Properties对象
//      void store(流对象)   将Properties对象的键值对写入目标中
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class IOEothers {


    //properties特有方法
    @Test
    public void propertiesTest() throws IOException {
//        Properties properties = new Properties();
//        properties.setProperty("name", "ZhangSan");
//        properties.setProperty("age", "22");
//
//        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
//
//        Set<String> stringSet = properties.stringPropertyNames();
//        for (String str : stringSet) {
//            System.out.println(str + " : " + properties.getProperty(str));
//        }

        Properties proConfig = new Properties();
        proConfig.load(new FileReader("src\\learnSe\\part6\\config.properties"));

        Set<String> keySet = proConfig.stringPropertyNames();
        for (String key : keySet) {
            System.out.println(key + " : " + proConfig.getProperty(key));
            proConfig.setProperty(key,"我改了它！");
        }
        proConfig.store(new FileWriter("src\\learnSe\\part6\\config.properties"), "today i revise it!");
    }
}
