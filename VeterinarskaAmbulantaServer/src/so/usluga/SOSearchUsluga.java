package so.usluga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Usluga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOSearchUsluga extends AbstractSO {
    private final String tekst;
    private ArrayList<Usluga> lista;

    public SOSearchUsluga(String tekst) {
        this.tekst = tekst;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        String t = tekst == null ? "" : tekst.trim().replace("'", "''");
        String query = "SELECT * FROM Usluga u";
        if (!t.isEmpty()) {
            query += " WHERE LOWER(u.nazivUsluge) LIKE LOWER('%" + t + "%')";
        }
        query += " ORDER BY u.nazivUsluge";
        lista = (ArrayList<Usluga>) (ArrayList<?>) DBBroker.getInstance().selectCustom(query, ado);
    }

    public ArrayList<Usluga> getLista() {
        return lista;
    }
}
