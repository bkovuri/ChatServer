import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Bhanu on 4/19/14.
 */
public class SocketPool extends GenericObjectPool<Socket> {
    //First constructor.
    public SocketPool(SocketObjectFactory objFactory) {
        super(objFactory);
    }

    public Socket borrowObject() throws Exception {
        //System.out.println(" borrowing object..");
        return super.borrowObject();
    }
    public void returnObject(Socket socket)  {
        //System.out.println(" returning object.." + socket);
        super.returnObject(socket);
    }
}