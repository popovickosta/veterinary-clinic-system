package so.mesto;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Mesto;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOGetAllMesto extends AbstractSO {
    private ArrayList<Mesto> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        lista = (ArrayList<Mesto>) (ArrayList<?>) DBBroker.getInstance().select(new Mesto());
    }

    public ArrayList<Mesto> getLista() {
        return lista;
    }
}
