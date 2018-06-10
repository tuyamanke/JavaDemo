package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wen on 2018/6/7
 */
public class MyServer {

    //定义保存所有Socket的ArrayList，并将其包装为线程安全的
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(30000);
        while(true){
            Socket s = ss.accept();//此行代码会阻塞，将一直等待别人的连接
            socketList.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }

}













