
public class ClientApplication {

    public static void main(String[] args)  throws Exception
    {

        ChatClientManager chatClientManager = ChatClientManager.getInstance();

        String numbers1 = "123456789" + "\n" + "122456789" + "\n" + "123456799" + "\n" + "123456789" + "\n";

        chatClientManager.clientRequest(numbers1);

        String numbers2 = "223456789" + "\n" + "223456789" + "\n" + "223456799" + "\n" + "222456789" + "\n";

        chatClientManager.clientRequest(numbers2);

        String numbers3 = "323456789" + "\n" + "33323456789" + "\n" + "323456799" + "\n" + "322456789" + "\n";

        chatClientManager.clientRequest(numbers3);
    }
}
