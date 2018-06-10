package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by wen on 2018/6/7
 */
public class ClientThread implements Runnable {

    private Socket s;//该线程负责处理的Socket
    BufferedReader br = null;//该线程所处理的Socket对应的输入流

    public ClientThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String content = null;
            while((content = br.readLine()) != null){
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
