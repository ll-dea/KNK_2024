package Model;

import java.sql.Date;

public class OrariKonsultimeve {

    private int Id;
    private String Lenda;
    private Date Data;
    private String Ora;
    private String Salla;

    public OrariKonsultimeve(int Id, String Lenda, Date Data, String Ora, String Salla) {
        this.Id = Id;
        this.Lenda = Lenda;
        this.Data = Data;
        this.Ora = Ora;
        this.Salla = Salla;
    }

    public int getId() {
        return Id;
    }

    public String getLenda() {
        return Lenda;
    }


    public Date getData() {
        return Data;
    }

    public String getOra() {
        return Ora;
    }

    public String getSalla() {
        return Salla;
    }
}
