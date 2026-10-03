package so.vlasnik;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.VlasnikZivotinje;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOSearchVlasnik extends AbstractSO {
    private final String tekst;
    private final Long idMesto;
    private ArrayList<VlasnikZivotinje> lista;

    public SOSearchVlasnik(String tekst, Long idMesto) {
        this.tekst = tekst;
        this.idMesto = idMesto;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        String t = tekst == null ? "" : tekst.trim().replace("'", "''");
        StringBuilder query = new StringBuilder("SELECT * FROM VlasnikZivotinje v JOIN Mesto m ON m.idMesto=v.idMesto WHERE 1=1");
        if (!t.isEmpty()) {
            query.append(" AND (LOWER(v.ime) LIKE LOWER('%").append(t).append("%') OR LOWER(v.prezime) LIKE LOWER('%").append(t).append("%'))");
        }
        if (idMesto != null) {
            query.append(" AND m.idMesto=").append(idMesto);
        }
        query.append(" ORDER BY v.prezime,v.ime");
        lista = (ArrayList<VlasnikZivotinje>) (ArrayList<?>) DBBroker.getInstance().selectCustom(query.toString(), ado);
    }

    public ArrayList<VlasnikZivotinje> getLista() {
        return lista;
    }
}
