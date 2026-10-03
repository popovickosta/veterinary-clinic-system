package so.usluga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Usluga;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SODeleteUsluga extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Usluga) || ((Usluga) ado).getIdUsluga() <= 0) {
            throw new Exception("Usluga nije izabrana.");
        }
        Usluga usluga = (Usluga) ado;
        if (DBBroker.getInstance().postojiPovezanZapis("StavkaRacuna", "idUsluga", usluga.getIdUsluga())) {
            throw new Exception("Uslugu nije moguće obrisati jer se nalazi na računu.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
