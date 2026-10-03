package so.zaposleni;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import domain.ZaposleniSS;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOGetAllZaposleni extends AbstractSO {
    private ArrayList<Zaposleni> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        lista = (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().select(new Zaposleni());
        for (Zaposleni z : lista) {
            ZaposleniSS filter = new ZaposleniSS();
            filter.setZaposleni(z);
            ArrayList<ZaposleniSS> veze = (ArrayList<ZaposleniSS>) (ArrayList<?>) DBBroker.getInstance().select(filter);
            for (ZaposleniSS veza : veze) {
                veza.setZaposleni(z);
            }
            z.setStrucneSpreme(veze);
        }
    }

    public ArrayList<Zaposleni> getLista() {
        return lista;
    }
}
