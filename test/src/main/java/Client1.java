import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Client1
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
        try
        {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            //new Thread(new SreceHandler(socket)).start();
            //new Thread(new SsendHandler(socket)).start();
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                String s = scanner.nextLine();
                out.println("123");
                if (s.equals("Bye"))
                {
                    log.info("对话已结束！");
                    socket.close();
                    out.close();
                }
            }
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
        send();
    }
}
