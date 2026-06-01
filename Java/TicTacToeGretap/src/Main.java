// erst im Terminal im src Ordner -> "javac *.java"
// dann "java Main server"
// Client muss das eingeben "java Main client 127.0.0.1"

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Use:");
            System.out.println("java Main server");
            System.out.println("java Main client <server-ip>");
            return;
        }

        if (args[0].equals("server")) {
            GameServer server = new GameServer();
            server.start();
        }

        if (args[0].equals("client")) {
            GameClient client = new GameClient();
            client.start(args[1]);
        }
    }
}