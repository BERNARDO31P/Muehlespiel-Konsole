package fm.bernardo.muelenspiel;

import java.util.Scanner;

final class BrettTest {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        Brett spielfeld = null;
        System.out.print("© 2018 BERNARDO.FM - Alle Rechte vorbehalten.\nSie finden dieses Projekt auch auf GitLab: https://gitlab.com/bernardo31p/muehlenspiel\n\nWillkommen zum Mühlenspiel von Bernardo.\n\n1. Spielfeld generieren\n2. Spielfeld ausgeben\n3. Feld Informationen abfragen\n4. Spiel beenden\nZum Beginn, bitte einen Menüpunkt auswählen: ");

        while (true) {

            final String auswahl = scanner.nextLine();
            for (int i = 0; i < 100; ++i) System.out.println();

            // Alternative zu if / else / else if
            switch (auswahl) {
                case "1":
                    spielfeld = new Brett(5);
                    System.out.println((char) 27 + "[32m[ERFOLG] Spielfeld wurde erfolgreich generiert." + (char) 27 + "[39m");
                    break;
                case "2":
                    try {
                        spielfeld.spielfeldAusgeben();
                    } catch (NullPointerException e) {
                        System.out.println((char) 27 + "[31m[FEHLER] Sie haben noch kein Spielfeld generiert." + (char) 27 + "[39m");
                    }
                    break;
                case "3":
                    System.out.print("Bitte geben Sie die Koordinaten, per Komma getrennt, vom Feld ein: ");
                    final String[] koordinaten = scanner.nextLine().split(",");

                    try {
                        spielfeld.feldInformationen(Integer.parseInt(koordinaten[0]), Integer.parseInt(koordinaten[1]));
                    } catch (NullPointerException e) {
                        System.out.println((char) 27 + "[31m[FEHLER] Sie haben noch kein Spielfeld generiert." + (char) 27 + "[39m");
                    } catch (NumberFormatException e) {
                        System.out.println((char) 27 + "[31m[FEHLER] Die Eingabe stimmt mit der Vorgabe nicht überein." + (char) 27 + "[39m");
                    }
                    break;
                case "4":
                    System.out.println((char) 27 + "[33m[INFO] Beendungsvorgang wird ausgeführt." + (char) 27 + "[39m");
                    System.exit(1);
                    break;
                default:
                    System.out.println((char) 27 + "[31m[FEHLER] Dieser Menüpunkt existiert nicht." + (char) 27 + "[39m");
                    break;
            }

            System.out.print("\n1. Spielfeld generieren\n2. Spielfeld ausgeben\n3. Feld Informationen abfragen\n4. Spiel beenden\nBitte wählen Sie einen Menüpunkt aus: ");

        }

    }

}
