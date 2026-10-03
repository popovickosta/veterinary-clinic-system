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
public class SOSearchRacun extends AbstractSO {
    private final Long idRacun;
    private final Long idZaposleni;
    private final Long idVlasnik;
    private final Long idUsluga;
    private ArrayList<Racun> lista;

    public SOSearchRacun(Long idRacun, Long idZaposleni, Long idVlasnik, Long idUsluga) {
        this.idRacun = idRacun;
        this.idZaposleni = idZaposleni;
        this.idVlasnik = idVlasnik;
        this.idUsluga = idUsluga;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        StringBuilder query = new StringBuilder("SELECT * FROM Racun r JOIN Zaposleni z ON z.idZaposleni=r.idZaposleni JOIN VlasnikZivotinje v ON v.idVlasnik=r.idVlasnik JOIN Mesto m ON m.idMesto=v.idMesto WHERE 1=1");
        if (idRacun != null) {
            query.append(" AND r.idRacun=").append(idRacun);
        }
        if (idZaposleni != null) {
            query.append(" AND z.idZaposleni=").append(idZaposleni);
        }
        if (idVlasnik != null) {
            query.append(" AND v.idVlasnik=").append(idVlasnik);
        }
        if (idUsluga != null) {
            query.append(" AND EXISTS (SELECT 1 FROM StavkaRacuna sx WHERE sx.idRacun=r.idRacun AND sx.idUsluga=").append(idUsluga).append(")");
        }
        query.append(" ORDER BY r.datumIzdavanja DESC,r.idRacun DESC");
        lista = (ArrayList<Racun>) (ArrayList<?>) DBBroker.getInstance().selectCustom(query.toString(), ado);

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
