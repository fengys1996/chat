import java.io.PrintWriter;
import java.util.Scanner;

public class sendhandler implements Runnable
{
    PrintWriter out;
    public sendhandler(PrintWriter out)
    {
        this.out = out;
    }

    public void run()
    {
        Scanner in = new Scanner(System.in);
        while (true)
        {
            String s = in.nextLine();
            out.println(s);
            if ("Bye".equals(in.nextLine()))
            {
                in.close();
                out.close();
            }
        }
    }
}
