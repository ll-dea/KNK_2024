package Model;


import java.time.LocalDate;

public class OrariKonsultimeve {
    private String lenda;
    private LocalDate data;
    private String ora;
    private String salla;



    public OrariKonsultimeve( String lenda, LocalDate data, String ora, String salla) {
        this.lenda = lenda;
        this.data = data;
        this.ora = ora;
        this.salla = salla;
    }

    public String getLenda() {
        return lenda;
    }

    public void setLenda(String lenda) {
        this.lenda = lenda;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getSalla() {
        return salla;
    }

    public void setSalla(String salla) {
        this.salla = salla;
    }

}

