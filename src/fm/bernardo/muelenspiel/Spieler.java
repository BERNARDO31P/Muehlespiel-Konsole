package fm.bernardo.muelenspiel;

import java.util.ArrayList;

final class Spieler {

    // Eigenschaften von einem Spieler
    String symbol;
    final ArrayList<Feld> besetzteFelder = new ArrayList<>();
    int id;

    // Konstukteur der Klasse Spieler
    Spieler(final int id, final String symbol) {
        this.symbol = symbol;
        this.id = id;
    }

}
