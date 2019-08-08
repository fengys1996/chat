package client;

import lombok.extern.slf4j.Slf4j;

import java.net.Socket;

@Slf4j
public class Client3
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
            new Thread(new SreceHandler(socket)).start();
            new Thread(new SsendHandler(socket)).start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
