package so.vlasnik;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.VlasnikZivotinje;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SODeleteVlasnik extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof VlasnikZivotinje) || ((VlasnikZivotinje) ado).getIdVlasnik() <= 0) {
            throw new Exception("Vlasnik nije izabran.");
        }
        VlasnikZivotinje vlasnik = (VlasnikZivotinje) ado;
        if (DBBroker.getInstance().postojiPovezanZapis("Racun", "idVlasnik", vlasnik.getIdVlasnik())) {
            throw new Exception("Vlasnika nije moguće obrisati jer postoje povezani računi.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
