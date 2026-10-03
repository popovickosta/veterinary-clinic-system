package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kosta
 */
public class VlasnikZivotinje extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;

    private long idVlasnik;
    private String ime;
    private String prezime;
    private Mesto mesto;

    public VlasnikZivotinje() {
    }

    public VlasnikZivotinje(long idVlasnik, String ime, String prezime, Mesto mesto) {
        this.idVlasnik = idVlasnik;
        this.ime = ime;
        this.prezime = prezime;
        this.mesto = mesto;
    }

    @Override
    public String nazivTabele() {
        return "VlasnikZivotinje";
    }

    @Override
    public String alijas() {
        return "v";
    }

    @Override
    public String join() {
        return " JOIN Mesto m ON m.idMesto=v.idMesto ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Mesto m = new Mesto(rs.getLong(5), rs.getString(6));
            lista.add(new VlasnikZivotinje(rs.getLong(1), rs.getString(2), rs.getString(3), m));
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ime,prezime,idMesto)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime.replace("'", "''") + "','" + prezime.replace("'", "''") + "'," + mesto.getIdMesto();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ime='" + ime.replace("'", "''") + "',prezime='" + prezime.replace("'", "''") + "',idMesto=" + mesto.getIdMesto();
    }

    @Override
    public String uslov() {
        return "idVlasnik=" + idVlasnik;
    }

    @Override
    public String uslovZaSelect() {
        return " ORDER BY v.prezime,v.ime";
    }

    public long getIdVlasnik() {
        return idVlasnik;
    }

    public void setIdVlasnik(long idVlasnik) {
        this.idVlasnik = idVlasnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return ((ime == null ? "" : ime) + " " + (prezime == null ? "" : prezime)).trim();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof VlasnikZivotinje && ((VlasnikZivotinje) o).idVlasnik == idVlasnik;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVlasnik);
    }
}
