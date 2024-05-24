package Model;

import java.sql.Date;

public class Lendet {

    private int id;
    private String lenda;
    private String prof;
    private Date data;
    private String ora;
    private String salla;

    public Lendet(int id, String lenda, String prof, Date data, String ora, String salla) {
        this.id = id;
        this.lenda = lenda;
        this.prof = prof;
        this.data = data;
        this.ora = ora;
        this.salla = salla;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getLenda() {
        return lenda;
    }

    public String getProf() {
        return prof;
    }

    public Date getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }

    public String getSalla() {
        return salla;
    }
}
