package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kosta
 */
public class StavkaRacuna extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;

    private Racun racun;
    private int rb;
    private double jedinicnaCena;
    private int kolicina;
    private double iznosStavke;
    private Usluga usluga;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int rb, double jedinicnaCena, int kolicina, double iznosStavke, Usluga usluga) {
        this.racun = racun;
        this.rb = rb;
        this.jedinicnaCena = jedinicnaCena;
        this.kolicina = kolicina;
        this.iznosStavke = iznosStavke;
        this.usluga = usluga;
    }

    @Override
    public String nazivTabele() {
        return "StavkaRacuna";
    }

    @Override
    public String alijas() {
        return "sr";
    }

    @Override
    public String join() {
        return " JOIN Usluga u ON u.idUsluga=sr.idUsluga ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Usluga u = new Usluga(rs.getLong(7), rs.getString(8), rs.getDouble(9), rs.getInt(10));
            Racun r = new Racun();
            r.setIdRacun(rs.getLong(1));
            lista.add(new StavkaRacuna(r, rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getDouble(5), u));
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(idRacun,rb,jedinicnaCena,kolicina,iznosStavke,idUsluga)";
    }

    @Override
    public String vrednostiZaInsert() {
        return racun.getIdRacun() + "," + rb + "," + jedinicnaCena + "," + kolicina + "," + iznosStavke + "," + usluga.getIdUsluga();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "jedinicnaCena=" + jedinicnaCena + ",kolicina=" + kolicina + ",iznosStavke=" + iznosStavke + ",idUsluga=" + usluga.getIdUsluga();
    }

    @Override
    public String uslov() {
        return "idRacun=" + racun.getIdRacun() + " AND rb=" + rb;
    }

    @Override
    public String uslovZaSelect() {
        return racun == null ? " ORDER BY sr.idRacun,sr.rb" : " WHERE sr.idRacun=" + racun.getIdRacun() + " ORDER BY sr.rb";
    }

    public void preracunajIznos() {
        this.iznosStavke = Math.round(jedinicnaCena * kolicina * 100.0) / 100.0;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getJedinicnaCena() {
        return jedinicnaCena;
    }

    public void setJedinicnaCena(double jedinicnaCena) {
        this.jedinicnaCena = jedinicnaCena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(double iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }
}
