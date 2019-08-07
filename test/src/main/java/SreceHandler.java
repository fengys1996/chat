import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

@Slf4j
public class SreceHandler implements Runnable
{
    Socket socket;
    public SreceHandler(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true)
            {
                String s = in.readLine();
                log.info(s);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
