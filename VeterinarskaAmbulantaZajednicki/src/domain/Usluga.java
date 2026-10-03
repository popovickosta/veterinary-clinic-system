package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kosta
 */
public class Usluga extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;

    private long idUsluga;
    private String nazivUsluge;
    private double cenaUsluge;
    private int trajanje;

    public Usluga() {
    }

    public Usluga(long idUsluga, String nazivUsluge, double cenaUsluge, int trajanje) {
        this.idUsluga = idUsluga;
        this.nazivUsluge = nazivUsluge;
        this.cenaUsluge = cenaUsluge;
        this.trajanje = trajanje;
    }

    @Override
    public String nazivTabele() {
        return "Usluga";
    }

    @Override
    public String alijas() {
        return "u";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Usluga(rs.getLong("idUsluga"), rs.getString("nazivUsluge"), rs.getDouble("cenaUsluge"), rs.getInt("trajanje")));
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(nazivUsluge,cenaUsluge,trajanje)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivUsluge.replace("'", "''") + "'," + cenaUsluge + "," + trajanje;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "nazivUsluge='" + nazivUsluge.replace("'", "''") + "',cenaUsluge=" + cenaUsluge + ",trajanje=" + trajanje;
    }

    @Override
    public String uslov() {
        return "idUsluga=" + idUsluga;
    }

    @Override
    public String uslovZaSelect() {
        return " ORDER BY u.nazivUsluge";
    }

    public long getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(long idUsluga) {
        this.idUsluga = idUsluga;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public double getCenaUsluge() {
        return cenaUsluge;
    }

    public void setCenaUsluge(double cenaUsluge) {
        this.cenaUsluge = cenaUsluge;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    @Override
    public String toString() {
        return nazivUsluge == null ? "" : nazivUsluge + " (" + String.format("%.2f", cenaUsluge) + " RSD)";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Usluga && ((Usluga) o).idUsluga == idUsluga;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsluga);
    }
}
