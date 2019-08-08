package server;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SreceHandler implements Runnable
{
    Socket socket;

    public SreceHandler(Socket socket)
    {
        System.out.println(socket);
        this.socket = socket;
    }

    public void run()
    {
        BufferedReader in = null;
        PrintWriter pw = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true)
            {
                if (!socket.isClosed())
                {
                    String s = in.readLine();
                    log.info("服务器端收到消息:" + s);
                    for (Socket socket1 : Server.sockets)
                    {
                        if (socket1 != socket)
                        {
                            pw = new PrintWriter(socket1.getOutputStream(),true);
                            log.info("服务端发送消息" + s);
                            pw.println(s);
                        }
                        else
                        {
                            if (s.equals("bye"))
                            {
                                pw = new PrintWriter(socket1.getOutputStream(),true);
                                log.info("发送关闭确认");
                                pw.println("asdfg");
                            }
                        }
                    }
                    if (s==null || s.equals("bye"))
                    {
                        log.info("服务端socket关闭!");
                        socket.shutdownOutput();
                        socket.close();
                        break;
                    }
                }
                else
                {
                    break;
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            Server.sockets.remove(socket);
            try
            {
                TimeUnit.MICROSECONDS.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (socket != null)
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
