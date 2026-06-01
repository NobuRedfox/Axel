import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {

    public void start(String serverIp) {

        Scanner scanner = new Scanner(System.in);

        try {
            Socket socket = new Socket(serverIp, 5000);

            // Client kann zum Server schreiben
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Client liest vom Server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            boolean yourTurn = false;

            while (true) {

                String message;

                while ((message = in.readLine()) != null) {

                    if (message.isEmpty()) {
                        break;
                    }

                    if (message.equals("YOUR_TURN")) {
                        yourTurn = true;
                        continue;
                    }

                    System.out.println(message);

                    if (message.contains("wins") || message.contains("Draw")) {
                        socket.close();
                        return;
                    }
                }

                if (message == null) {
                    break;
                }

                if (!yourTurn) {
                    continue;
                }

                yourTurn = false;

                // Eingabe
                System.out.print("Row (1-3): ");
                int row = scanner.nextInt();

                System.out.print("Col (1-3): ");
                int col = scanner.nextInt();

                out.println(row + "," + col);
                System.out.println("Move sent. Waiting for Server...");
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
