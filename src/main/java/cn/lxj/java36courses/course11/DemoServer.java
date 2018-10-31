package cn.lxj.java36courses.course11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DemoServer   简单的Socket服务器
 * description 阻塞式IO服务
 * create by lxj 2018/7/25
 **/
public class DemoServer extends Thread {
    private ServerSocket serverSocket;

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(8080);

            // 修改
            ExecutorService executor = Executors.newFixedThreadPool(8);

            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                // 修改 requestHandler.start();
                executor.execute(requestHandler);// 并发服务的常用方式，使用线程池来解决
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DemoServer server = new DemoServer();
        server.start();
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), server.getPort());
            BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bfr.lines().forEach(s -> System.out.println(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//简化实现，不做读取，直接发送字符串
class RequestHandler extends Thread {
    private Socket socket;

    RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}