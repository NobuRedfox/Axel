import java.io.*;   // lesen und schreiben
import java.net.*;  // Netzwerk, Socket, ServerSocket
import java.util.Scanner;

public class GameServer {

    public void start() {

        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

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
            out.println("YOUR_TURN");
            out.println();

            while (true) {


                // Server wartet auf eine Zeile vom Client
                String message = in.readLine();
                System.out.println("Client says: " + message);

                String[] parts = message.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (!board.isValid(row - 1, col - 1)) {
                    out.println(board.getBoardAsString());
                    out.println("Invalid position!");
                    out.println("YOUR_TURN");
                    out.println();
                    continue;
                }

                if (!board.setMove(row - 1, col - 1, 'O')) {
                    out.println(board.getBoardAsString());
                    out.println("Field is already taken!");
                    out.println("YOUR_TURN");
                    out.println();
                    continue;
                }

                out.println(board.getBoardAsString());
                out.println("Waiting for server...");
                out.println();

                if (board.hasWinner('O')) {
                    out.println(board.getBoardAsString());
                    out.println("Player O wins!");
                    out.println();
                    board.printBoard();
                    System.out.println("Player O wins!");
                    break;
                }

                if (board.isFull()) {
                    out.println(board.getBoardAsString());
                    out.println("Draw!");
                    out.println();
                    board.printBoard();
                    System.out.println("Draw!");
                    break;
                }

                board.printBoard();

                boolean serverMoveSet = false;

                while (!serverMoveSet) {

                    System.out.print("Server Row (1-3): ");
                    int serverRow = scanner.nextInt();

                    System.out.print("Server Col (1-3): ");
                    int serverCol = scanner.nextInt();

                    if (!board.isValid(serverRow - 1, serverCol - 1)) {
                        System.out.println("Invalid position!");
                        continue;
                    }

                    if (!board.setMove(serverRow - 1, serverCol - 1, 'X')) {
                        System.out.println("Field is already taken!");
                        continue;
                    }

                    serverMoveSet = true;
                }

                board.printBoard();

                if (board.hasWinner('X')) {
                    out.println(board.getBoardAsString());
                    out.println("Player X wins!");
                    out.println();
                    board.printBoard();
                    System.out.println("Player X wins!");
                    break;
                }

                if (board.isFull()) {
                    out.println(board.getBoardAsString());
                    out.println("Draw!");
                    out.println();
                    board.printBoard();
                    System.out.println("Draw!");
                    break;
                }

                out.println(board.getBoardAsString());
                out.println("YOUR_TURN");
                out.println();
            }

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
