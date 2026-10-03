package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.HashMap;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOUpdateRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Pogrešan tip objekta.");
        }
        Racun racun = (Racun) ado;
        if (racun.getIdRacun() <= 0) {
            throw new Exception("Račun nije izabran.");
        }
        if (racun.getDatumIzdavanja() == null) {
            throw new Exception("Datum izdavanja računa je obavezan.");
        }
        if (racun.getDatumIzdavanja().after(new java.util.Date(System.currentTimeMillis() + 60000))) {
            throw new Exception("Datum izdavanja ne može biti u budućnosti.");
        }
        if (racun.getZaposleni() == null || racun.getZaposleni().getIdZaposleni() <= 0) {
            throw new Exception("Zaposleni mora biti izabran.");
        }
        if (racun.getVlasnikZivotinje() == null || racun.getVlasnikZivotinje().getIdVlasnik() <= 0) {
            throw new Exception("Vlasnik životinje mora biti izabran.");
        }
        if (racun.getStavkeRacuna() == null || racun.getStavkeRacuna().isEmpty()) {
            throw new Exception("Račun mora imati najmanje jednu stavku.");
        }
        for (StavkaRacuna stavka : racun.getStavkeRacuna()) {
            if (stavka.getUsluga() == null || stavka.getUsluga().getIdUsluga() <= 0) {
                throw new Exception("Svaka stavka mora imati uslugu.");
            }
            if (stavka.getKolicina() <= 0) {
                throw new Exception("Količina mora biti veća od nule.");
            }
            if (stavka.getJedinicnaCena() <= 0) {
                throw new Exception("Jedinična cena mora biti veća od nule.");
            }
            if (Math.abs(stavka.getJedinicnaCena() - stavka.getUsluga().getCenaUsluge()) > 0.001) {
                throw new Exception("Jedinična cena stavke mora odgovarati ceni izabrane usluge.");
            }
            if (stavka.getRb() <= 0) {
                throw new Exception("Redni broj stavke nije ispravno postavljen.");
            }
            stavka.preracunajIznos();
        }
        racun.preracunajUkupanIznos();

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Racun racun = (Racun) ado;

        DBBroker.getInstance().update(racun);

        StavkaRacuna filter = new StavkaRacuna();
        filter.setRacun(racun);
        ArrayList<StavkaRacuna> stareStavke
                = (ArrayList<StavkaRacuna>) (ArrayList<?>) DBBroker.getInstance().select(filter);

        HashMap<Integer, StavkaRacuna> mapaStarih = new HashMap<>();
        for (StavkaRacuna stara : stareStavke) {
            mapaStarih.put(stara.getRb(), stara);
        }

        HashMap<Integer, StavkaRacuna> mapaNovih = new HashMap<>();
        for (StavkaRacuna nova : racun.getStavkeRacuna()) {
            nova.setRacun(racun);
            nova.preracunajIznos();
            mapaNovih.put(nova.getRb(), nova);
        }

        for (StavkaRacuna stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) {
                DBBroker.getInstance().delete(stara);
            }
        }

        for (StavkaRacuna nova : racun.getStavkeRacuna()) {
            if (mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().update(nova);
            }
        }

        for (StavkaRacuna nova : racun.getStavkeRacuna()) {
            if (!mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().insert(nova).close();
            }
        }
    }
}
