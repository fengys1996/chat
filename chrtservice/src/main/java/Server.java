import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Server
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
        while (true)
        {
            Socket socket = serverSocket.accept();
            sockets.add(socket);
            new Thread(new ServerHandler(socket)).start();
        }
    }

    public static void main(String[] args) throws IOException
    {
        new Server().start();
    }
}
