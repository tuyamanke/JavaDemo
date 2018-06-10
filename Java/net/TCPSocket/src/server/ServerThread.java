package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by wen on 2018/6/7
 */
public class ServerThread implements Runnable {

    Socket s = null;//定义当前线程所处理的Socket
    BufferedReader br = null;//该线程所处理的Socket对应的输入流
    public ServerThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));//初始化该Socket对应的输入流
    }
    @Override
    public void run() {
        String content = null;
        //采用循环不断地从Socket中读取客户端发送过来的数据
        while ((content = readFromClient()) != null){
            //将读到的内容向socketList中的每个Socket发送一次
            for(Socket s : MyServer.socketList){
                try {
                    PrintStream ps = new PrintStream(s.getOutputStream());
                    ps.println(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //定义读取客户端数据的方法
    private String readFromClient(){
        try {
            return br.readLine();
        } catch (IOException e) {
            //如果捕获到异常，表明该Socket对应的客户端已经关闭，从socketList中移除
            MyServer.socketList.remove(s);
        }
        return null;
    }
}

















