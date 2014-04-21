import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Bhanu on 4/20/14.
 */
public class ClientService implements Runnable {

    String numbers;
    Socket socket;

    ClientService(String numbers, Socket socket)
    {
        this.numbers = numbers;
        this.socket = socket;
    }

    public  void run()
    {
        try {

            BufferedOutputStream bos = new BufferedOutputStream(socket.
                    getOutputStream());

            OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");

            /** Write across the socket connection and flush the buffer */
            osw.write(numbers);
            osw.flush();
            osw.close();
        }
        catch(IOException io)
        {
            System.err.println("ClientService::run - IO Exception raised while sending data to server");
            io.printStackTrace();
        }
    }
}
