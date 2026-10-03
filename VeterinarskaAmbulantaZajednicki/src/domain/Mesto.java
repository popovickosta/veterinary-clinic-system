package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kosta
 */
public class Mesto extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;

    private long idMesto;
    private String nazivMesta;

    public Mesto() {
    }

    public Mesto(long idMesto, String nazivMesta) {
        this.idMesto = idMesto;
        this.nazivMesta = nazivMesta;
    }

    @Override
    public String nazivTabele() {
        return "Mesto";
    }

    @Override
    public String alijas() {
        return "m";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Mesto(rs.getLong("idMesto"), rs.getString("nazivMesta")));
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(nazivMesta)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivMesta.replace("'", "''") + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "nazivMesta='" + nazivMesta.replace("'", "''") + "'";
    }

    @Override
    public String uslov() {
        return "idMesto=" + idMesto;
    }

    @Override
    public String uslovZaSelect() {
        return " ORDER BY m.nazivMesta";
    }

    public long getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(long idMesto) {
        this.idMesto = idMesto;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    @Override
    public String toString() {
        return nazivMesta == null ? "" : nazivMesta;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Mesto && ((Mesto) o).idMesto == idMesto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMesto);
    }
}
