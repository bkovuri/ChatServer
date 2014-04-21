


    This application is divided into two modules. ChatServer (Server Side) and ClientService (Client Side)
    
    ChatServer:
    ============
    
        1) ChatServer has runnable service to accept incoming client connections on server socket. It can accept atmost
           5 client connections.
        2) ChatServerManager: Used to instatniate and configure ChatServer, Write logs to file, and Count service.
        3) CountService: Will report numbers received in last 10 seconds, and duplicates among them.
        
    ClientService:
    ==============
    
        1) ClientService: Runnable service to send number to chat server. 
           We can simultaneously run as many client services as we need.
        2) Using apache commons pool designed SocketObjectFactory, SocketPool where client service can borrow socket objects.
           This sockets are used to communicate with server.
        3) ChartClientManager to configure chart client.
        4) ClientApplication: To test.
           
    
