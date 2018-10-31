package cn.lxj.java36courses.course11;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NIOServer    NIO服务器，多路复用技术
 * description
 * create by lxj 2018/7/26
 **/
public class NIOServer extends Thread {
    @Override
    public void run() {
        try {
            // selector是调度员的角色
            Selector selector = Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();// 创建 selector 和 channel
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            // 明确配置非阻塞模式，因为阻塞模式下，注册操作是不允许的，会抛出IllegalBlockingModeException
            serverSocket.configureBlocking(false);
            // 注册到 Selector，并说明关注点（OP_ACCEPT 说明关注的是新的连接请求）
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();// 阻塞等待就绪的channel,这是关键点之一
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // 生产系统一般会进行额外就绪状态检查
                    sayHelloWorld((ServerSocketChannel) key.channel());
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHelloWorld(ServerSocketChannel server) throws IOException {
        try (SocketChannel client = server.accept();){
            client.write(Charset.defaultCharset().encode("Hello World!"));
        }
    }

    public static void main(String[] args) {
        NIOServer server = new NIOServer();
        server.start();
        // 类似
    }
}