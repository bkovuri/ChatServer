import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.PooledObjectState;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import sun.jvm.hotspot.utilities.WorkerThread;
import sun.security.ntlm.Server;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.Deque;

public class SocketObjectFactory implements PooledObjectFactory<Socket> {

    int port = 4000;   // Default value assigned to port.  We should assign default value as bean property.
    String host = "localhost";

    SocketObjectFactory(String host, int port)
    {
        this.host = host;
        this.port = port;

    }
    public PooledObject<Socket> makeObject() throws IOException {

        InetAddress address = InetAddress.getByName(host);
        Socket socket = new Socket(address, port);
        return new DefaultPooledObject<Socket>(socket);
    }

    public void destroyObject(PooledObject<Socket> pooledSocket) throws IOException {

        pooledSocket.getObject().close();
    }

    public boolean validateObject(PooledObject<Socket> pooledSocket) {
        Socket socket = pooledSocket.getObject();

        if (socket.isBound()) {
            return true;
        }
        return false;
    }

    public void activateObject(PooledObject<Socket> socket) {
        // System.out.println(" activateObject...");
    }

    public void passivateObject(PooledObject<Socket> obj) {
       //  System.out.println(" passivateObject..." + obj);
    }
}

