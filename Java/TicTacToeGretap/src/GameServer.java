import java.io.*;   // lesen und schreiben
import java.net.*;  // Netzwerk, Socket, ServerSocket

public class GameServer {

    public void start() {

        Board board = new Board();

        try {
            // Server wird geöffnet mit Port 5000
            // Merksatz: ServerSocket = Tür, Port 5000 = Hausnummer
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            // Programm bleibt stehen und wartet, bis ein Client kommt.
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected!");

            // Server kann zum Client schreiben. true = sofort abschicken
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Server kánn vom Client lesen.
            // clientSocket = rohe Bytes vom Netzwerk
            // InputStreamReader = macht daraus lesbare Zeichen
            // BufferedReader = kann ganze Zeilen mit readLine lesen
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Spielfeld wird zum Client geschickt und gibt das Board als String zurück
            out.println(board.getBoardAsString());

            // Server wartet auf eine Zeile vom Client
            String message = in.readLine();
            System.out.println("Client says: " + message);

            String[] parts = message.split(",");

            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            board.setMove(row - 1, col - 1, 'O');

            board.printBoard();

            out.println(board.getBoardAsString());

            // Verbindung zum Clienten
            clientSocket.close();
            // öffnet Server-Tür
            serverSocket.close();

            // Falls Netzwerkfehler passiert, landet Java hier
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
