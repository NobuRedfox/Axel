/* Aufgabe 1 - Variablen

a) Welche primitiven Datentypen kennst du noch? Vervollständige deine Liste nach
   einer kurzen Internetrecherche.
b) Deklariere eine Variable „count“ vom Typ Integer und initialisiere sie mit dem
   Wert „0“ (einmal in einer einzigen Operation (Zeile) und einmal in 2 Operationen)
c) Dann inkrementiere „count“. Gebe den Wert von „count“ auf der Konsole aus.
d) Deklariere eine Variable „countCopy“ vom Typ Integer und initialisiere sie mit
   dem Wert von „count“. Erhöhe den Wert von „countCopy“ um 2 und gebe die
   Werte von „count“ und „countCopy“ auf der Konsole aus.
e) Damit wir gleich auf der Konsole erkennen können, welcher ausgegebene Wert
   zu welcher Variabel gehört, gib vor den beiden Werten „count: „ bzw. „countCopy:
   „ aus.
f) Gib die Summe von „count“ und „countCopy“ auf der Konsole aus. */


// a) byte, short, int, long, float, double, char, boolean


public class Main {
    public static void main(String[] args) {

        // b) 1
        int count = 0;
        /* b) 2
        int count;
        count = 0;
         */

        // c)
        count++;
        System.out.println(count);

        // d)
        int countCopy = count;
        countCopy += 2;

        // e)
        System.out.println("count " + count);
        System.out.println("countCopy " + countCopy);

        // f)
        System.out.println("Summe: " + (count + countCopy));

        /* Aufgabe 2
           a) */
        printHello();

        // b)
        addPrint(3, 5);

        // c)
        int result = add(10, 20);
            System.out.println("Ergebnis " + result);

        // d)
        boolean check = startsWithAnA("Apfel");
        System.out.println("Starts with A:" + check);

        // e)
        System.out.println(isPalindrom("Anna"));
        System.out.println(isPalindrom("Baum"));
        System.out.println(isPalindrom("Lagerregal"));

        /* Aufgabe 3 - Arrays
        a) Initialisiere ein String-Array „animals“, das die Werte „cat“, „dog“ und „mouse“ (in
           genau dieser Reihenfolge) speichert.
        b) Gib den ersten Wert des String-Arrays auf der Konsole aus.
        c) Tausche im Array die Werte „dog“ und „mouse“. Prüfe, ob der Tausch geklappt
           hat, indem du alle Werte des Arrays auf der Konsole ausgibst (hier noch ohne
           Schleife ok).
        d) Gib die Größe des Arrays „animals“ auf der Konsole aus. */

        // a)
        String[] animals = {"cat", "dog", "mouse"};

        // b)
        System.out.println(animals[0]);

        // c)
        String temp = animals[1];
        animals[1]  = animals[2];
        animals[2]  = temp;

        System.out.println(animals[0]);
        System.out.println(animals[1]);
        System.out.println(animals[2]);

        // d)
        System.out.println(animals.length);
    }


    /* Aufgabe 2 - Methoden
    a) Schreibe eine Methode „printHello“, die auf der Konsole den String „Hello“ aus-
       gibt.
    b) Schreibe eine Methode „addPrint“, die zwei Integer Werte addiert und das Ergeb-
       nis auf der Konsole ausgibt.
    c) Schreibe eine Methode „add“, die zwei Integer Werte addiert und das Ergebnis
       zurückgibt.
    d) Schreibe eine Methode „startsWithAnA“, die prüft ob ein String mit „A“ startet und
       ein Boolean zurückgibt.
    e) Schwer: Schreibe eine Methode, „isPalindrom“, die prüft ob ein String ein Palind-
       rom ist. (Tipp: Nutze die Methode „charAt“ der Klasse String.) */

    // a)
    public static void printHello() {
        System.out.println("Hello");
    }

    // b)
    public static void addPrint(int a, int b) {
        int sum = a + b;
        System.out.println(sum);
    }

    // c)
    public static int add(int a, int b) {
        return a + b;
    }

    // d)
    public static boolean startsWithAnA(String text) {
        return text.startsWith("A");
    }

    // e)
    public static boolean isPalindrom(String palindrom) {

        palindrom = palindrom.toLowerCase();

        int length = palindrom.length();

        for (int i = 0; i < length / 2; i++) {
            if (palindrom.charAt(i) != palindrom.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /* Aufgabe 4 - Main-Klasse, die verschiedene Methoden nutzt
    a) Schreibe eine Methode „substract“, die zwei Integer Werte subtrahiert und das
       Ergebnis zurückgibt.
    b) Schreibe eine Methode „greaterThan10“, die einen einen Boolean zurückgibt.
    c) Initialisiere eine Integer Variable „a“ mit 7 und eine „b“ mit 16. Addiere die beiden
       Werte mithilfe deiner selbst geschriebenen „add“-Methode. Initialisiere eine Vari-
       able „c“ mit dem um 1 dekrementierten Wert von „a“. Berechne mittels deiner
       „substract“-Methode das Ergebnis von (b + c) – a. Rechne von Hand nach, stimmt
       dein Ergebnis? */

    // a)
    public static int substract(int c, int d) {
        return c - d;
    }

    // b)
    public static boolean greaterThan10(int value) {
        return value > 10;
    }
}
