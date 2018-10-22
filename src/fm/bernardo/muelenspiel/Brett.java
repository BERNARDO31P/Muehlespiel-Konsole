package fm.bernardo.muelenspiel;

import java.util.*;

final class Brett {

    // Array Liste zum speichern von den Feldern
    private final ArrayList<Feld> spielfelder = new ArrayList<>();

    // Kreation von zwei Spielern
    private final ArrayList<Spieler> spieler = new ArrayList<Spieler>() {{
        add(new Spieler(0, "x"));
        add(new Spieler(1, "y"));
    }};

    // Definition von allen Symbolen je nach IDs
    private final HashMap<String, List<Integer>> symbole = new HashMap<String, List<Integer>>() {
        {
            put(" ", Collections.singletonList(24));
            put("-", Arrays.asList(1, 2, 4, 5, 9, 11, 37, 39, 43, 44, 46, 47));
            put("|", Arrays.asList(7, 13, 14, 15, 19, 20, 28, 29, 33, 34, 35, 41));
            put("┏", Arrays.asList(0, 8, 16));
            put("┓", Arrays.asList(6, 12, 18));
            put("┗", Arrays.asList(30, 36, 42));
            put("┛", Arrays.asList(32, 40, 48));
            put("╋", Arrays.asList(10, 22, 26, 38));
            put("┻", Arrays.asList(17, 45));
            put("┫", Arrays.asList(23, 27));
            put("┣", Arrays.asList(21, 25));
            put("┳", Arrays.asList(3, 31));
        }
    };

    // Funktion für die Suche vom Symbol mit der ID vom Feld
    private String findeSymbol(final int feldID) {

        for (Map.Entry<String, List<Integer>> eintrag : symbole.entrySet()) {
            for (int id : eintrag.getValue()) {
                if (feldID == id)
                    return eintrag.getKey();
            }
        }
        return null;

    }

    // Konstrukteur der Klasse Brett
    Brett(final int startBelegung) {

        final String[] spielbar = {"┏", "┓", "┗", "┛", "╋", "┻", "┫", "┣", "┳"};
        final Random r = new Random();

        // Schleife um dynamisch die Felder generieren
        for (int laenge = 0; laenge < 7; laenge++) {
            for (int breite = 0; breite < 7; breite++) {

                final Feld feld = new Feld();

                // Dynamische Berrechnung der Feld ID
                feld.id = 7 * laenge + breite;

                feld.symbol = findeSymbol(feld.id);

                // Überprüfung ob das Spielfeld spielbar ist
                feld.spielbar = Arrays.asList(spielbar).contains(feld.symbol);

                spielfelder.add(feld);

            }
        }

        // Schleife zum Setzen von fünf Felder pro Spieler
        for (final Spieler anDerReihe : spieler) {
            for (int i = 0; i < startBelegung; i++) {

                // Diese Schleife wird erst unterbrochen, wenn ein Feld gefunden wurde, welches Spielbar ist und keinen Besitzer hat
                while (true) {
                    int id = r.nextInt(49);
                    final Feld feld = spielfelder.get(id);
                    if (feld.spielbar && feld.besitzer == null) {

                        // Übernehmen von allen Informationen
                        feld.besitzer = Integer.toString(anDerReihe.id);
                        feld.symbol = anDerReihe.symbol;
                        anDerReihe.besetzteFelder.add(feld);
                        spieler.set(anDerReihe.id, anDerReihe);
                        spielfelder.set(id, feld);

                        break;
                    }
                }

            }
        }

    }

    final void feldInformationen (final int x, final int y) {

        final int feldID = 7 * y + x;
        final Feld feld;

        try {
            feld = spielfelder.get(feldID);
        } catch (IndexOutOfBoundsException e) {
            System.out.println((char) 27 + "[31m[FEHLER] Dieses Feld existiert nicht." + (char) 27 + "[39m");
            return;
        }

        if (!feld.spielbar) {
            System.out.println((char) 27 + "[33m[INFO] Dieses Feld ist nicht spielbar." + (char) 27 + "[39m");
        } else if (feld.besitzer != null) {
            for (final Spieler einzelnerSpieler : spieler) {
                if (feld.symbol.equals(einzelnerSpieler.symbol))
                    System.out.println((char) 27 + "[33m[INFO] Auf diesem Feld ist eine Figur vom Spieler" + einzelnerSpieler.id + "." + (char) 27 + "[39m");
            }
        } else {
            System.out.println((char) 27 + "[33m[INFO] Dieses Feld ist frei." + (char) 27 + "[39m");
        }

    }

    // Funktion zum Ausgeben vom Spielfeld
    final void spielfeldAusgeben() {

        int i = 0;
        for (Feld feld : spielfelder) {
            if (i == 7) {
                System.out.println("\n");
                i = 0;
            }
            System.out.print(feld.symbol + "   ");
            i++;
        }

        // Ausgabe von der Anzahl besetze Felder pro Spieler
        System.out.println("\nSpieler1: " + spieler.get(0).besetzteFelder.size() + " // Spieler2: " + spieler.get(1).besetzteFelder.size() + "\n");

    }

}
