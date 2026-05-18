public class Main {
    public static void main(String[] args) {
        String name = "Ben";
        int alter = 20;
        int note = 2;
        String hobby = "Programmieren";
        String sprache = "Java";
        double groesse = 1.80;
        boolean magJava = true;

        begruesse();
        begruessePerson(name);

        System.out.println("Hallo " + name);
        System.out.println("Du bist " + alter + " Jahre alt.");
        System.out.println("Mein Hobby ist " + hobby + ".");
        System.out.println("Meine Lieblingssprache ist " + sprache + ".");
        System.out.println("Meine Groesse ist " + groesse + ".");
        System.out.println("Ich mag Java: " + magJava + ".");

        if (alter >= 18) {
            System.out.println("Du bist volljaehrig.");
        } else {
            System.out.println("Du bist minderjaehrig.");
        }

        if (magJava) {
            System.out.println("Java macht Spaß.");
        } else {
            System.out.println("Vielleicht magst du Java spaeter.");
        }

        if (note == 1) {
            System.out.println("Sehr gut");
        } else if (note == 2) {
            System.out.println("Gut");
        } else if (note == 3) {
            System.out.println("Befriedigend");
        } else {
            System.out.println("Andere Note");
        }

        for (int i = 1; i <= 10; i++ ) {
            System.out.println(i * 2);
        }

        int summe = 0;
        for (int i = 1; i <= 10; i++) {
            summe += i;
        }
        System.out.println(summe);

        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        for (int i = 1; i <= 20; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }

        int zahl = 5;
        while (zahl >= 1) {
            System.out.println(zahl);
            zahl--;
        }
    }

    public static void begruesse() {
        System.out.println("Hallo aus der Methode!");
    }

    public static void begruessePerson(String name) {
        System.out.println("Hallo " + name + "!");
    }
}
