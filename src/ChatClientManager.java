import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ChatClientManager {

    ExecutorService clientService;
    SocketPool socketPool;
    SocketObjectFactory socketObjectFactory;

    private static ChatClientManager chatClientManager = new ChatClientManager();

    //Its Singleton Class
    private ChatClientManager() {

        socketObjectFactory = new SocketObjectFactory("localhost",4000);
        socketPool = new SocketPool(socketObjectFactory);

        clientService = Executors.newCachedThreadPool();
    }

    public static ChatClientManager getInstance() {
        return chatClientManager;
    }

    public void clientRequest(String numbers) {
        try
        {
            clientService.execute(new ClientService(numbers, socketPool.borrowObject()));
        }
        catch(Exception e)
        {
            System.err.println("ChatClientManager::clientRequest: " +
                    "Exception raised while getting socket object from pool and executing its service");
            e.printStackTrace();
        }
    }
}

