package learnSe.part9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//9.网络编程
//
//4.UDP   面向无连接，数据不安全，速度快。不区分客户端与服务端
//    1.发送Send
//        创建DatagramSocket, 随机端口号
//        创建DatagramPacket, 指定数据, 长度, 地址, 端口（四个参数）
//        使用DatagramSocket发送DatagramPacket
//        关闭DatagramSocket
//    2.接收Receive
//        创建DatagramSocket, 指定端口号
//        创建DatagramPacket, 指定数组, 长度
//        使用DatagramSocket接收DatagramPacket
//        关闭DatagramSocket
//        从DatagramPacket中获取数据
//    3.接收方获取ip和端口号
//        String ip = packet.getAddress().getHostAddress();
//        int port = packet.getPort();
//    4.先启动接收方，否则可能导致数据丢失
//    5.注意：传输的是字节数组，IP要通过InetAddress.getByName("127.0.0.1)将字符串转为InetAddress
public class NetBUDP {

}

class Send {
    public static void main(String[] args) throws Exception {
        byte[] strByte = "去吧皮卡丘！".getBytes();

        //通过socket把packet发送出去，在packet中指定数据，数据字节数，IP，端口
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(strByte, strByte.length, InetAddress.getByName("127.0.0.1"), 46806);
        socket.send(packet);
        socket.close();
    }
}

class Receive {
    public static void main(String[] args) throws Exception {

        //接收的时候在Socket设置端口，在packet中设置缓冲区
        DatagramSocket socket = new DatagramSocket(46806);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        //以socket为载体将数据传输到dp中
        socket.receive(packet);

        //packet.getData()获取数据  字节数组
        byte[] str = packet.getData();
        String strResult = new String(str, 0, packet.getLength());
        System.out.println(strResult);
        socket.close();
    }
}

//UDP传输优化，键盘录入，直到输入quit
class Send2 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();
        while (true) {
            String str = sc.nextLine();
            byte[] strArr = str.getBytes();
            DatagramPacket packet = new DatagramPacket(strArr, strArr.length, InetAddress.getByName("127.0.0.1"), 46806);
            socket.send(packet);
            if (str.equals("quit")) {
                socket.close();
                break;
            }
        }
    }
}

class Receive2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(46806);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        while (true) {
            socket.receive(packet);
            byte[] str = packet.getData();
            String strResult = new String(str, 0, packet.getLength());
            System.out.println(strResult);
            if (strResult.equals("quit")) {
                socket.close();
            }
        }
    }
}


//UDP传输多线程
//        让Reveive和Send继承Thread，用run()封装传输的代码
//        然后new Receive().start()，new Send().start()；需要几个线程就new几个

class Demo3_MoreThread {

    public static void main(String[] args) {
        new Receive3().start();
        new Send3().start();
    }
}

class Receive3 extends Thread {
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(6666);
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

            while (true) {
                socket.receive(packet);
                byte[] arr = packet.getData();
                int len = packet.getLength();
                String ip = packet.getAddress().getHostAddress();
                System.out.println(ip + ":" + new String(arr, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Send3 extends Thread {
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);

            while (true) {
                String str = sc.nextLine();
                if ("quit".equals(str))
                    break;
                DatagramPacket packet = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.0.1"), 6666);
                socket.send(packet);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
