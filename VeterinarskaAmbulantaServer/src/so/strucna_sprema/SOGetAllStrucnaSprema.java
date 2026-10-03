package so.strucna_sprema;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StrucnaSprema;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOGetAllStrucnaSprema extends AbstractSO {
    private ArrayList<StrucnaSprema> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        lista = (ArrayList<StrucnaSprema>) (ArrayList<?>) DBBroker.getInstance().select(new StrucnaSprema());
    }

    public ArrayList<StrucnaSprema> getLista() {
        return lista;
    }
}
