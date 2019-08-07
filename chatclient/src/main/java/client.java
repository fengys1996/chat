import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class client
{
    private static int DEFAULT_SERVER_PORT = 7777;

    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send()
    {
        send(DEFAULT_SERVER_PORT);
    }

    private static void send(int port)
    {
        Socket socket = null;

        BufferedReader in = null;
        PrintWriter out = null;

        try
        {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            new Thread(new sendhandler(out)).start();
            new Thread(new recehandler(in)).start();


        }
        catch (Exception e)
        {

        }
        finally
        {

        }
    }

    public static void main(String[] args)
    {
        send(7778);
    }
}
