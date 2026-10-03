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
public class SOGetAllUsluga extends AbstractSO {
    private ArrayList<Usluga> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        lista = (ArrayList<Usluga>) (ArrayList<?>) DBBroker.getInstance().select(new Usluga());
    }

    public ArrayList<Usluga> getLista() {
        return lista;
    }
}
