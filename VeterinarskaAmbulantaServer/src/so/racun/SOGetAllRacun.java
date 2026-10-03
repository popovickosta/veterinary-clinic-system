package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOGetAllRacun extends AbstractSO {
    private ArrayList<Racun> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        lista = (ArrayList<Racun>) (ArrayList<?>) DBBroker.getInstance().select(new Racun());
        for (Racun racun : lista) {
            StavkaRacuna filter = new StavkaRacuna();
            filter.setRacun(racun);
            ArrayList<StavkaRacuna> stavke = (ArrayList<StavkaRacuna>) (ArrayList<?>) DBBroker.getInstance().select(filter);
            for (StavkaRacuna stavka : stavke) {
                stavka.setRacun(racun);
            }
            racun.setStavkeRacuna(stavke);
        }
    }

    public ArrayList<Racun> getLista() {
        return lista;
    }
}
