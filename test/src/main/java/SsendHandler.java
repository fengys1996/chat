import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class SsendHandler implements Runnable
{
    Socket socket;
    public SsendHandler(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        try
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                String s = scanner.nextLine();
                out.println(s);
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
            e.printStackTrace();
        }
    }
}
