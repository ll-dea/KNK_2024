package Service;

import Model.OrariKonsultimeve;
import java.util.ArrayList;
import java.util.List;

public class Sesioni {
    private List<OrariKonsultimeve> oraret;

    public Sesioni() {
        oraret = new ArrayList<>();
    }

    public void shtoOrar(OrariKonsultimeve orari) {
        oraret.add(orari);
    }

    public List<OrariKonsultimeve> getOraret() {
        return oraret;
    }
    public void fshijOrar(OrariKonsultimeve orari) {
        oraret.remove(orari);

    }


}
