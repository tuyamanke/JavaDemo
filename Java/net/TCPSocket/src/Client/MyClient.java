package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by wen on 2018/6/7
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 30000);
        new Thread(new ClientThread(s)).start();//客户端启动ClientThread线程不断地读取来自服务器的数据
        PrintStream ps = new PrintStream(s.getOutputStream());//获取Socket对应的输出流
        String line = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//不断地读取键盘输入
        while((line = br.readLine()) != null){
            ps.println(line);//将用户的键盘输入内容写入Socket对应的输入流
        }
    }
}
