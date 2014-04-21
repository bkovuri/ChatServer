import java.util.concurrent.atomic.AtomicInteger;

public class CountServer implements Runnable {

    ChatServer chatServer;

    CountServer(ChatServer chatServer)
    {
        this.chatServer = chatServer;
    }

    public void run()
    {
        while(true)
        {
            System.out.println("Count of numbers received in last 10 seconds: " + chatServer.getIncomingCount().get());
            System.out.println("Count of duplicate numbers received in last 10 seconds: " + chatServer.getDuplicateCount().get());
            try
            {
                chatServer.setDuplicateCount(new AtomicInteger(0));
                chatServer.setIncomingCount(new AtomicInteger(0));
                Thread.sleep(10000);
            }
            catch(InterruptedException e)
            {
               e.printStackTrace();
            }
        }
    }
}
