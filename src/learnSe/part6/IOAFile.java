package learnSe.part6;
//6.1 IO流
//知识点
//    记忆
//      1.File类 构造方法
//    了解
//1.File类
//    1.概述
//        File是一个路径，而不是一个文件：文件或者文件夹的路径，相对或者绝对路径
//          绝对路径是从盘符开始，相对路径是相对某个位置（项目根目录）
//    2.构造方法
//        File(String pathname)：根据一个路径得到File对象
//          绝对路径对于文件而言不需要加格式后缀，加上也可以，加不加会影响后续获取路径方法的执行结果
//        File(String parent,String child):根据一个目录和一个子文件/目录得到File对象
//          通过这种方式可以分别得到某个父目录下不同的子目录文件（夹）
//        File(File parent,String child):根据一个父File对象和一个子文件/目录得到File对象（这里应该可以很好的理解File是一个路径而不是文件）
//          也可以将父目录封装成File对象，这样比String更强大（有很多属性和方法可以调用）
//    3.功能  
//        1.创建功能
//            public boolean createNewFile():创建文件 如果存在这样的文件，就不创建了
//            public boolean mkdir():创建单级文件夹
//              只能在已经存在的文件夹的基础上多创建一级的文件夹，如果父级文件夹并不完整存在，则不能创建成功
//              如果已经存在，则不创建
//            public boolean mkdirs():创建多级文件夹 此方法如果父文件夹不存在，会全部创建出来
//        2.重命名和删除功能
//            public boolean renameTo(File dest): 把文件重命名为指定的文件路径（改名+剪切）
//                如果父级路径相同，是改名；父级路径不同，是改名+剪切
//                实践中发现，不能通过这种方式删除父级文件夹
//            public boolean delete(): 删除文件或者文件夹
//                注意：Java中的删除不走回收站；要删除一个文件夹，则该文件夹内不能包含文件或者文件夹
//        3.判断功能
//            public boolean isDirectory():判断是否是目录
//            public boolean isFile():判断是否是文件
//            public boolean exists():判断是否存在
//            public boolean canRead():判断是否可读
//                setReadable(false);  Windows系统认为所有的文件都是可读的，设置不可读是无法成功的
//            public boolean canWrite():判断是否可写
//                setWriteable(false); Windows中可以设置为可写或者不可写
//            public boolean isHidden():判断是否隐藏
//        4.获取功能
//            public String getAbsolutePath()：获取绝对路径
//            public String getPath():获取路径（构造方法中传入的路径）
//            public String getName():获取名称
//            public long length():获取长度（字节数）
//            public long lastModified():获取最后一次的修改时间（距1970的毫秒值）
//                Date和SimpleDateFormat
//            public String[] list():获取指定目录下的所有文件或者文件夹的名称数组     给定目录肯定是文件夹，否则结果为null，没有意义
//            public File[] listFiles():获取指定目录下的所有文件或者文件夹的File对象数组
//                这两个获得数组的方法要注意，只会获得指定目录下一级的所有，而不会往更下层的子文件(夹)去获取
//                  比如 "src\\1\\2\\3"，如果file是"src\\1"，只会获取2这个文件夹
//         5.文件名称过滤器
//            练习：找出给定路径下所有的.jpg文件，输出名称（注意是给定路径下，所以针对的是文件夹）
//            1.概述
//                public String[] list(FilenameFilter filter)
//                public File[] listFiles(FileFilter filter)
//                    通过FileFilter的accept(File dir,String name)方法进行比较，返回true才会被加入数组
//                      重写accept(File dir,String name)，dir是当前文件夹，name是文件夹下的文件文件夹名

import org.junit.Test;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class IOAFile {
    //File构造方法
    @Test
    public void conTest () {
        //方式一
        //绝对路径
        File file1= new File("D:\\Study\\developAll\\project\\devSe\\devSe\\src\\learnSe\\part6\\InputTest.txt");
        System.out.println(file1.exists());
        //相对路径
        File file2 = new File("src\\learnSe\\part6\\InputTest.txt");
        System.out.println(file2.exists());
        //方式二  通过方式二可以得到某个父级文件夹下的不同子文件（夹）
        String parent = "D:\\Study\\developAll\\project\\devSe\\devSe";
        String child = "src\\learnSe\\part6\\InputTest.txt";
        File file3 = new File(parent,child);
        System.out.println(file3.exists());
        //方式三  通过方式三可以对路径进行File类的操作
        File file4 = new File("D:\\Study\\developAll\\project\\devSe\\devSe");
        File file5 = new File(file4,child);
        System.out.println(file5.exists());
    }

    //crud
    @Test
    public void fileCrudTest() throws IOException {
        //创建    return是否创建成功
        File file = new File("src\\learnSe\\part6\\create");
//        file.mkdir();//创建单级文件夹
        File file1 = new File("src\\learnSe\\part6\\create\\1\\2\\3");
//        file1.mkdirs();//创建多级文件夹（包括父类文件夹）
        File file2 = new File("src\\learnSe\\part6\\create\\1\\2\\3\\test1.txt");
//        file2.createNewFile();//创建文件  针对不同类型把后缀加上来保证文件格式正确；文件夹路径要存在才能创建成功（否则IOException）

        //删除    如果目标是文件夹，那么必须是空文件夹才能删除成功
//        file1.delete();
//        file2.delete();
        //改名/剪切 文件或者文件夹都可以renameTo()     路径相同-改名     路径不同-剪切     只有原File的路径存在才能执行成功
//        file.renameTo(new File("src\\learnSe\\part6\\create1"));
        //不能通过这种方式删除父级文件夹，如下操作不会成功
//        File fileRename = new File("src\\learnSe\\part6\\create\\1\\2\\test1.txt");
//        file2.renameTo(fileRename);


        //判断
//        System.out.println(file.exists());
//        System.out.println(file.isDirectory());
//        System.out.println(file.isFile());
//        System.out.println(file.canRead());
//        file.setReadable(true);   //这里要注意：Windows中设置不可读不能成功
//        System.out.println(file.canWrite());
//        file.setWritable(true);
//        System.out.println(file.isHidden());

    }


    //获取
    @Test
    public void getTest() {
        File file = new File("src\\learnSe\\part6\\InputTest.txt");
        //获取绝对路径
        System.out.println(file.getAbsolutePath());
        //获取构造方法中传入的路径
        System.out.println(file.getPath());
        //获取名称
        System.out.println(file.getName());
        //获取字节长度
        System.out.println(file.length());
        //获取最后一次修改时间
        System.out.println(file.lastModified());
        //获取名称数组
        File file1 = new File("src\\learnSe\\part6");
        System.out.println(file1.list());
        //获取对象数组
        System.out.println(file1.listFiles());
    }

    //练习：找出给定路径下所有的.jpg文件，输出名称
    @Test
    public void fileFilterTest() {
        File file = new File("src\\learnSe\\part6");
        //如下做法不行，原因是文件夹名称也可能以.jpg结尾
//        String[] arr = file.list();
//        if (arr.length > 0) {
//            for (String str : arr) {
//                if (str.endsWith(".jpg")) {
//                    System.out.println(str);
//                }
//            }
//        }

        //如下
        File[] files = file.listFiles();
        if (files.length > 0) {
            for (File fileTemp : files) {
                if (fileTemp.isFile() && fileTemp.getName().endsWith(".jpg")) {
                    System.out.println(fileTemp.getName());
                }
            }
        }
    }

    //文件名称过滤器
    @Test
    public void filenameFilterTest() {
        File file = new File("src\\learnSe\\part6");
        String[] strs = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
//                System.out.println(dir.getName() + " " + name);
//                sout的输出结果为：
//                    part6 create1
//                    part6 IOAFile.java
//                    part6 test.txt
                if (name.endsWith(".txt")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        if (strs.length > 0) {
            for (String str : strs) {
                System.out.println(str);
            }
        }
    }
}
