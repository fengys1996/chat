import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SsendHandler implements Runnable
{
    Socket socket;
    public SsendHandler(Socket socket)
    {
        System.out.println(socket);
        this.socket = socket;
    }

    public void run()
    {
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                if (!socket.isClosed())
                {
                    String s = scanner.nextLine();
                    out.println(s);
                    if ("bye".equals(s))
                    {
                        break;
                    }
                }
                else {
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
            if (out != null)
            {
                out.close();
            }
        }
    }
}
