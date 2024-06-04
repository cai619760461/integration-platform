package com.incaier.integration.platform.controller.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 服务器 demo
 *
 * @author caiweijie
 * @date 2024/05/15
 */
public class NioSelectorServer {

    public static void main(String[] args) {
        try {
            // 创建服务端链接通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 绑定服务端通道端口
            serverSocketChannel.socket().bind(new InetSocketAddress(9000));
            //设置serverSocketChannel为非阻塞
            serverSocketChannel.configureBlocking(false);
            // 打开 Selector 多路复用器 处理 Channel，即创建 epoll
            Selector selector = Selector.open();
            // 把 serverSocketChannel 注册到 selector 上，并且 selector 对客户端 accept 连接操作进行监听处理
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务启动成功");

            while (true) {
                // 阻塞等待需要处理的事件发生
                selector.select();

                // 获取 selector 中注册的全部事件的 SelectionKey 实例
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                // 遍历 SelectionKey 对不同事件进行处理
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // 如果是 OP_ACCPET 事件，则进行连接获取和事件注册
                    if (key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = server.accept();
                        socketChannel.configureBlocking(false);
                        // 这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端连接成功");
                    }else if (key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        //缓冲区从数组长度
                        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
                        int len = socketChannel.read(byteBuffer);
                        if (len > 0) {
                            System.out.println("接收到数据：" + new String(byteBuffer.array()));
                        }else if (len == -1){
                            System.out.println("客户端断开连接");
                            socketChannel.close();
                        }
                    }
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
