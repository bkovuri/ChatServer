import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatServer implements Runnable {

    ServerSocket socket;
    private AtomicInteger incomingCount;
    private AtomicInteger duplicateCount;
    Set<String> numberSet;


    ChatServer(ServerSocket socket)
    {
        this.socket = socket;
        setIncomingCount(new AtomicInteger(0));
        setDuplicateCount(new AtomicInteger(0));
        numberSet = new HashSet<String>();
    }


    public void run()
    {
        Socket connection = null;
        try
        {
            while (true) {
                connection = socket.accept();

                BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
                InputStreamReader isr = new InputStreamReader(is);
                StringBuilder numberString = new StringBuilder();
                int character;
                while((character = isr.read()) != -1)
                {
                    if(character >= 48 && character <= 57)
                    {
                        character = character - 48;
                        numberString.append(character);
                    }
                    else if((character == 13 || character == 10) && numberString.length() == 9) {
                        String number = numberString.toString();
                        if(numberSet.contains(number))
                        {
                            getDuplicateCount().getAndIncrement();
                            numberString.delete(0, numberString.length());
                        }
                        else
                        {
                            numberSet.add(number);
                            ChatServerManager.getInstance().writeToLog(number + "\n");
                            numberString.delete(0, numberString.length());
                        }
                        getIncomingCount().getAndIncrement();
                    }
                    else
                    {
                        break;
                    }
                }
                try {
                    Thread.sleep(10000);
                }
                catch (Exception e){}
            }

        }
        catch(Exception e)
        {
            System.err.println("Failed to get socket");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch(IOException io)
            {
                System.err.println(" IOException raised while closing socket connection");
            }
        }
    }

    public AtomicInteger getIncomingCount() {
        return incomingCount;
    }

    public void setIncomingCount(AtomicInteger incomingCount) {
        this.incomingCount = incomingCount;
    }

    public AtomicInteger getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(AtomicInteger duplicateCount) {
        this.duplicateCount = duplicateCount;
    }
}
