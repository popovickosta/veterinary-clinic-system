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
public class SOGetAllVlasnik extends AbstractSO {
    private ArrayList<VlasnikZivotinje> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        lista = (ArrayList<VlasnikZivotinje>) (ArrayList<?>) DBBroker.getInstance().select(new VlasnikZivotinje());
    }

    public ArrayList<VlasnikZivotinje> getLista() {
        return lista;
    }
}
