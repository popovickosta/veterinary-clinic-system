package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kosta
 */
public class StrucnaSprema extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;

    private long idStrucnaSprema;
    private String nazivSS;
    private int stepenSS;

    public StrucnaSprema() {
    }

    public StrucnaSprema(long idStrucnaSprema, String nazivSS, int stepenSS) {
        this.idStrucnaSprema = idStrucnaSprema;
        this.nazivSS = nazivSS;
        this.stepenSS = stepenSS;
    }

    @Override
    public String nazivTabele() {
        return "StrucnaSprema";
    }

    @Override
    public String alijas() {
        return "ss";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new StrucnaSprema(rs.getLong("idStrucnaSprema"), rs.getString("nazivSS"), rs.getInt("stepenSS")));
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(nazivSS,stepenSS)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivSS.replace("'", "''") + "'," + stepenSS;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "nazivSS='" + nazivSS.replace("'", "''") + "',stepenSS=" + stepenSS;
    }

    @Override
    public String uslov() {
        return "idStrucnaSprema=" + idStrucnaSprema;
    }

    @Override
    public String uslovZaSelect() {
        return " ORDER BY ss.stepenSS, ss.nazivSS";
    }

    public long getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    public void setIdStrucnaSprema(long idStrucnaSprema) {
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public String getNazivSS() {
        return nazivSS;
    }

    public void setNazivSS(String nazivSS) {
        this.nazivSS = nazivSS;
    }

    public int getStepenSS() {
        return stepenSS;
    }

    public void setStepenSS(int stepenSS) {
        this.stepenSS = stepenSS;
    }

    @Override
    public String toString() {
        return nazivSS == null ? "" : nazivSS + " (stepen " + stepenSS + ")";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof StrucnaSprema && ((StrucnaSprema) o).idStrucnaSprema == idStrucnaSprema;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStrucnaSprema);
    }
}
