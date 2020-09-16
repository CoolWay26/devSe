package learnSe.part6;
//6.IO流
//
//练习
//1.统计文件夹大小
//  不存在直接获取文件夹的总长度，dir.length = 0
//  思想：判断是文件夹就递归，是文件就统计到总长度中
//2.删除文件夹
//  思想：是文件删除，是文件夹递归；最后把没有文件的文件夹删除
//3.复制文件夹           重要
//  思想：判断是文件复制，是文件夹递归
//4.层级打印
//  思想：文件则打印，文件夹递归
//      最重要的是解决制表符的问题：每一级多一个"\t"，第一个为0个"\t"
//      由于要采用递归，显然不能在递归的方法中定义该决定"\t"个数的变量
//5.1000阶乘
//6.约瑟夫环
import org.junit.Test;

import java.io.*;

public class IOFExercise {


    //统计文件夹大小
    @Test
    public void  statistics() {
        File file = new File("");
        if (file.exists()) {
            System.out.println(getFileLength(file));
        }
    }
    private long getFileLength(File file) {
        long len = 0;
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length>0) {
                for (File fileTemp : files) {
                    if (fileTemp.isFile()) {
                        len = len + fileTemp.length();
                    }else if (fileTemp.isDirectory()) {
                        len = len + getFileLength(fileTemp);
                    }
                }
            }
        }

        return len;
    }

    //删除文件夹
    @Test
    public void deleteFileTest() {
        File file = new File("");
        deleteFile(file);
    }
    private void deleteFile(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File fileTemp : files) {
                if (fileTemp.isFile()) {
                    fileTemp.delete();
                } else if (fileTemp.isDirectory()) {
                    deleteFile(fileTemp);
                }
            }
        }

        //删掉已经没有内容的文件夹
        file.delete();
    }

    //复制文件夹
    @Test
    public void copyTest() throws IOException {
        File inFile = new File("");
        File outFile = new File("");
        if (!inFile.equals(outFile)) {          //File类重写了equals()
            copy(inFile,outFile);
        }
    }
    private void copy(File inFile,File outFile) throws IOException {
        //创建要复制的文件夹，然后才能复制里面的文件
        File newFile = new File(outFile, inFile.getName());
        newFile.mkdirs();

        if (inFile.exists()) {
            File[] files = inFile.listFiles();

            if (files.length >0) {
                for (File fileTemp:files) {
                    if (fileTemp.isFile()) {
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileTemp));
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(newFile, fileTemp.getName())));
                        int b;
                        while ((b=bis.read()) !=-1) {
                            bos.write(b);
                        }
                        bis.close();
                        bos.close();
                    } else if (fileTemp.isDirectory()){
                        copy(fileTemp,newFile); //此时，变成了要把文件夹fileTemp复制到newFile中，递归
                    }
                }
            }
        }
    }

    //层级打印
    @Test
    public void printTest() {
        File file = new File("src");
        if (file.isDirectory()) {
            print(file, 0);
        } else {
            System.out.println(file.getName());
        }
    }
    private void print(File file,int numT) {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length > 0) {
                for (File fileTemp : files) {
                    //无论是文件还是文件夹，只要是同一级，那么"\t"就要一样多
                    for (int i = 0; i < numT; i++) {
                        System.out.print("\t");
                    }
                    //无论是文件还是文件夹，都要打印出来
                    System.out.println(fileTemp.getName());
                    //如果是文件夹，递归
                    if (fileTemp.isDirectory()) {
                        //这里的numT+1，已经变成下次调用的numT,这样就保证每个文件夹往下一级遍历时都会多一个"\t"，形成逐层打印的效果，这也是最关键的一点
                        print(fileTemp, numT + 1);
                    }
                }
            }
        }
    }
}
