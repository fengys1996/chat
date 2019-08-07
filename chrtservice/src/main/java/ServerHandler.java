import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ServerHandler implements Runnable
{
    private Socket socket;

    public ServerHandler(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        BufferedReader in = null;
        PrintWriter out = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true)
            {
                String strChat = in.readLine();
                log.info(strChat);
                if (!strChat.equals(""))
                {
                    if ("Bye".equals(strChat))
                    {
                        socket.close();
                    }
                    for (Socket element : Server.sockets)
                    {
                        if (element != socket)
                        {
                            out = new PrintWriter(element.getOutputStream());
                            out.println(strChat);
                        }
                    }
                }

            }
        }
        catch (Exception e)
        {

        }
    }

    public static void main(String[] args)
    {
        log.info("123");
    }
}
