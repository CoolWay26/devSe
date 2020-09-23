package learnSe.part9;
//9.网络编程
//
//5.TCP
//1.三次握手
//    1.建立连接：客户端发送syn包（syn=j）到服务器，进入syn_sent状态，等待服务器确认
//    2.服务器确认：服务器收到syn包，必须确认客户的SYN（ack=j+1），同时自己也发送一个SYN包（seq=k），即SYN+ACK包，此时服务器进入SYN_RECV状态
//    3.连接成功：客户端收到服务器的SYN+ACK包，向服务器发送确认包ACK(ack=k+1），此包发送完毕，客户端和服务器进入ESTABLISHED（TCP连接成功）状态，完成三次握手
//2.客户端
//    创建Socket连接服务端(指定ip地址,端口号)通过ip地址找对应的服务器
//    调用Socket的getInputStream()和getOutputStream()方法获取和服务端相连的IO流
//    输入流可以读取服务端输出流写出的数据
//    输出流可以写出数据到服务端的输入流
//3.服务端
//    创建ServerSocket(需要指定端口号)
//    调用ServerSocket的accept()方法接收一个客户端请求，得到一个Socket     serverSocket.accept()
//    调用Socket的getInputStream()和getOutputStream()方法获取和客户端相连的IO流
//    输入流可以读取客户端输出流写出的数据
//    输出流可以写出数据到客户端的输入流

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class NetCTCP {
}


class TcpSend {
    public static void main(String[] args) throws IOException {
        //绑定目标ip和端口号
        Socket socket = new Socket("127.0.0.1",40456);

        //第一次握手，获取客户端socket的输入输出流，向服务端发送一个请求
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //第二次握手：接收服务端发来的包（明白服务端已经知道自己被请求了）
        byte[] sendArr = new byte[1024];
        int len = is.read(sendArr);
        System.out.println(new String(sendArr,0,len));

        //第三次握手：向服务端发送包，此包发送完毕即三次握手结束（告诉服务端好的我们开始吧），之后可以开始传送数据
        os.write("皮卡丘我们一起玩！".getBytes());
        socket.close();
    }
}


class TcpReceive {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(40456);

        //第一次握手：调用ServerSocket的accept()方法接收一个客户端请求，得到一个Socket
        Socket socket = serverSocket.accept();

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        //第二次握手：服务器接收到客户端请求后，也向客户端发送一个包（相当于告诉客户端我知道你在请求我了）
        os.write("皮卡丘，我知道你在找我了".getBytes());

        //第三次握手：接收到客户端发来的确认包
        byte[] receArr = new byte[1024];
        int len = is.read(receArr);
        System.out.println(new String(receArr,0,len));

        //一般来说serverSocket不要关，要关的是socket
        socket.close();
    }
}

//TCP协议代码优化

class TCPSend2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);        //创建Socket指定ip地址和端口号
        InputStream is = socket.getInputStream();            //获取输入流
        OutputStream os = socket.getOutputStream();        //获取输出流
        BufferedReader br = new BufferedReader(new InputStreamReader(is));    //通过转换流，将字节流转为字符流
        PrintStream ps = new PrintStream(os);    //通过打印流可以方便的整行输出并换行（println()）

        System.out.println(br.readLine());    //要注意readLine()是以/r/n为结束标记
        ps.println("我想报名就业班");
        System.out.println(br.readLine());
        ps.println("爷不学了");
        socket.close();
    }
}

class TCPRecevice {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);    //创建服务器
        Socket socket = server.accept();                //接受客户端的请求
        InputStream is = socket.getInputStream();        //获取输入流
        OutputStream os = socket.getOutputStream();    //获取输出流

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PrintStream ps = new PrintStream(os);

        ps.println("欢迎咨询传智播客");    //发送消息要加换行/r/n否则接收端不好处理
        System.out.println(br.readLine());
        ps.println("报满了,请报下一期吧");
        System.out.println(br.readLine());
        server.close();
        socket.close();
    }
}


//客户端多线程
class TCPRecevice3 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);    //创建服务器
        while (true) {
            //接受客户端的请求，因为用匿名内部类实现，
            final Socket socket = server.accept();
            new Thread() {    //接受一个请求就开一个线程
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                        ps.println("欢迎咨询传智播客");
                        System.out.println(br.readLine());
                        ps.println("报满了,请报下一期吧");
                        System.out.println(br.readLine());
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
