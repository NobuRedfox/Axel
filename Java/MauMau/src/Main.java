import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {

    private Deck deck;
    private List<Card> playerHand;
    private List<Card> botHand;
    private Card topCard;
    private boolean playerTurn = true;

    public void start() {
        deck = new Deck();
        deck.shuffle();

        playerHand = new ArrayList<>();
        botHand = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.draw());
            botHand.add(deck.draw());
        }

        topCard = deck.draw();

        System.out.println("=== Mau Mau ===");

        while (true) {
            System.out.println("\nAktuelle Karte: " + topCard);

            if (playerTurn) {
                playerMove();

                if (playerHand.isEmpty()) {
                    System.out.println("\nDu hast gewonnen!");
                    break;
                }
            } else {
                botMove();

                if (botHand.isEmpty()) {
                    System.out.println("\nDer Bot hat gewonnen!");
                    break;
                }
            }

            playerTurn = !playerTurn;
        }
    }

    private void playerMove() {
        System.out.println("\nDeine Karten:");

        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println((i + 1) + ": " + playerHand.get(i));
        }

        System.out.println("0: Karte ziehen");

        while (true) {
            System.out.print("Wähle eine Karte: ");
            int choice;

            try {
                choice = Main.scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Bitte eine Zahl eingeben.");
                Main.scanner.nextLine();
                continue;
            }

            if (choice == 0) {
                Card drawn = deck.draw();
                playerHand.add(drawn);
                System.out.println("Du ziehst: " + drawn);
                return;
            }

            if (choice < 1 || choice > playerHand.size()) {
                System.out.println("Ungültige Auswahl.");
                continue;
            }

            Card selectedCard = playerHand.get(choice - 1);

            if (canPlay(selectedCard)) {
                topCard = selectedCard;
                playerHand.remove(selectedCard);
                System.out.println("Du spielst: " + selectedCard);
                return;
            } else {
                System.out.println("Diese Karte kannst du nicht legen.");
            }
        }
    }

    private void botMove() {
        System.out.println("\nBot ist dran...");

        for (Card card : botHand) {
            if (canPlay(card)) {
                topCard = card;
                botHand.remove(card);
                System.out.println("Bot spielt: " + card);
                System.out.println("Bot hat noch " + botHand.size() + " Karten.");
                return;
            }
        }

        Card drawn = deck.draw();
        botHand.add(drawn);
        System.out.println("Bot zieht eine Karte.");
        System.out.println("Bot hat noch " + botHand.size() + " Karten.");
    }

    private boolean canPlay(Card card) {
        return card.getSuit().equals(topCard.getSuit())
                || card.getValue().equals(topCard.getValue());
    }
}

class Deck {

    private List<Card> cards = new ArrayList<>();

    public Deck() {
        String[] suits = {"Herz", "Karo", "Pik", "Kreuz"};
        String[] values = {"7", "8", "9", "10", "Bube", "Dame", "König", "Ass"};

        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(suit, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            System.out.println("Der Stapel ist leer. Neues Deck wird erstellt.");
            Deck newDeck = new Deck();
            newDeck.shuffle();
            cards = newDeck.cards;
        }

        return cards.remove(0);
    }
}

class Card {

    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return suit + " " + value;
    }
}