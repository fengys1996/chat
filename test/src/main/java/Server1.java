import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Server1
{
    private static int DEFAULT_PORT = 7777;

    private static ServerSocket serverSocket;

    public void start () throws IOException
    {
        start(DEFAULT_PORT);
    }

    public static List<Socket> sockets = new ArrayList<Socket>();

    private void start(int port) throws IOException
    {
        if (serverSocket != null)
        {
            return;
        }
        serverSocket = new ServerSocket(port);
        log.info("服务端已启动,端口号:" + port);
        Socket socket = serverSocket.accept();
        //new Thread(new SreceHandler(socket)).start();
        //new Thread(new SsendHandler(socket)).start();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while(true)
        {
            String s = in.readLine();
            if(s==null||"".equals(s))
            {
                break;
            }
            log.info(s);
        }
        socket.close();
        in.close();
    }

    public static void main(String[] args) throws IOException
    {
        new Server1().start();
    }
}
