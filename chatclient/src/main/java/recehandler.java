import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
public class recehandler implements Runnable
{
    public void run()
    {
        while (true)
        {
            try
            {
                String s = in.readLine();
                if (!s.equals(""))
                {
                    log.info(s);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    BufferedReader in;

    public recehandler(BufferedReader in)
    {
        this.in = in;
    }
}
