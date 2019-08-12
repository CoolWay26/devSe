package learnSe.part1;
//1.1java语言概述

//1.java语言发展
//    詹姆斯·高斯林（James Gosling）
//    在Sun公司时--"绿色计划"--因为觉得使用C++有很多弊端，开发了一套语言--Oak（橡树）后改名为Java
//2.Java语言版本
//    1.5版本（Tiger）实现了质变（飞跃）
//3.Java语言平台
//    J2SE(Java 2 Platform Standard Edition)标准版
//        针对桌面应用程序开发,该技术体系是其他两者的基础
//    J2ME(Java 2 Platform Micro Edition)小型版
//        针对电子消费产品和嵌入式设备开发
//    J2EE(Java 2 Platform Enterprise Edition)企业版
//        针对Web应用程序开发,该技术体系中包含的技术如 Servlet、Jsp等
//****
//4.语言特点
//    开源、跨平台
//    跨平台原理（可移植性）
//        什么是跨平台
//            java语言编写的程序在不同的平台上都可以运行（一次编译write once，到处运行run anywhere）
//        原理：
//            java程序需要先被编译工具（javac.exe）编译成class文件，再由JVM(JVM Java Virtual Machine)对class文件解释运行
//            不同的平台（操作系统）使用不同的虚拟机即可运行同一个java程序
//5.JRE和JDK
//    JRE：java运行环境 java Runtime environment
//        JVM（Java虚拟机）+Java程序核心类库等，安装了JRE即可运行开发好的java程序
//    JDK：java开发工具 java development kit
//        JDK:JRE+JAVA的开发工具，其中的开发工具：编译工具(javac.exe)  打包工具(jar.exe)等
//6.JDK的下载和安装
//    下载：
//        官网http://www.oracle.com
//        1.7：https://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html
//        1.8：https://www.oracle.com/technetwork/java/javase/downloads/index.html
//        jdk-7u72-windows-i586.exe   jdk-版本update版本-平台-位数    1.7update	86是32位
//****
//    安装的细节：
//        安装路径不要有中文或者特殊符号如空格等
//            举例：我的JDK安装路径	* D:\develop\Java\jdk1.7.0_72
//        安装jdk的坑：
//            提示安装JRE，建议安装上，且不能和JDK安装目录相同，否则配置jdk后使用时可能会出现plesae ensure JAVA_HOME point to JDK rather JRE
//            在同一台机器上安装多个版本jdk，修改环境变量不生效，因为在安装JDK时，自动将java.exe、javaw.exe、javaws.exe三个可执行文件复制到了C:\Windows\System32目录，由于这个目录在WINDOWS环境变量中的优先级高于JAVA_HOME设置的环境变量优先级
//    验证安装是否成功：
//        在bin目录下打开命令行测试java	javac
//7.JDK安装路径下的目录解释
//    bin目录：该目录用于存放一些可执行程序
//    lib目录：lib是library的缩写，Java类库
//    include目录：由于JDK是通过C和C++实现的，因此在启动时需要引入一些C语言的头文件，该目录就是用于存放这些头文件的
//    jre目录：JVM+核心类库（bin+lib）
//    src.zip文件：src.zip为src文件夹的压缩文件，src中放置的是JDK核心类的源代码，通过该文件可以查看Java基础类的源代码
//****
//8.path环境变量的作用及配置方式
//    作用：配置后可以在不同的盘符下访问path路径下的可执行文件（bin），如果不配置path则要在bin目录下开发，这显然不合适
//        配置方式1：
//            右击计算机→属性→高级系统设置→高级选项卡→环境变量→系统变量中查找Path→双击	Path→将jdk安装目录下的bin目录添加到最左边并注意用分号隔开
//        配置方式2：
//            使用JAVA_HOME	配置JDK安装目录
//            Path配置%JAVA_HOME%\bin
//            推荐使用这种，这样修改JDK路径时不需要修改Path,防止一些误操作
//9.classpath环境变量的作用及其配置
//    作用：配置访问的类文件的路径，1.5以后默认是当前路径，即编译后生成.class文件的路径，你在哪生成，我就去哪里找
//        配置后，无论在哪里执行目标程序，都会在配置路径下找目标的.class文件
//            弊端：想要执行就必须放到配置路径下，不符合实际需求
//        配置方式：
//            系统变量中配置classpath		当前路径指的是   .
public class Agaishu {
}
