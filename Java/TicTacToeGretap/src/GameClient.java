import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {

    public void start(String serverIp) {

        try {
            Socket socket = new Socket(serverIp, 5000);

            // Client kann zum Server schreiben
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Client liest vom Server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }

            System.out.print("Row (1-3)");
            int row = scanner.nextInt();

            System.out.print("Col (1-3)");
            int col = scanner.nextInt();

            out.println(row + "," + col);

            socket.close();

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
