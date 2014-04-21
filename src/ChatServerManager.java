import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ChatServerManager {

    ExecutorService serverService, countService;
    String fileName = "numbers.log";
    FileWriter fw;
    BufferedWriter bw;


    private static ChatServerManager chatServerManager = new ChatServerManager();

    //Its Singleton Class
    private ChatServerManager() {
        serverService = Executors.newSingleThreadExecutor();
        countService = Executors.newSingleThreadExecutor();
        serverRequest();
    }

    public static ChatServerManager getInstance() {
        return chatServerManager;
    }

    public void serverRequest() {
        try
        {
            File file = new File(fileName);
            if(file.exists())
            {
                file.createNewFile();
            }
            file.createNewFile();
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            // Set backlog to 5, to limit maximum client connections to 5
            ChatServer chatServer = new ChatServer(new ServerSocket(4000, 5));
            CountServer countServer = new CountServer(chatServer);
            serverService.execute(chatServer);
            countService.execute(countServer);
        }
        catch(Exception e)
        {
            System.err.println("ChatServerManager::serverRequest: " +
                    "Exception raised while getting socket object from pool and executing its service");
            e.printStackTrace();
        }
    }

    void writeToLog(String number)
    {
        try
        {
            bw.write(number);
            bw.flush();
        }
        catch(IOException io)
        {
            System.err.println("ChatServerManager::writeToLog: " +
                    "Exception raised while writng data to log file");
            io.printStackTrace();
        }

    }
}
