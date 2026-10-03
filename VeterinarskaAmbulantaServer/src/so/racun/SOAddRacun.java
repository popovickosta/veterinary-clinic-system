package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOAddRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Pogrešan tip objekta.");
        }
        Racun racun = (Racun) ado;
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
        int rb = 1;
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
            stavka.setRb(rb++);
            stavka.preracunajIznos();
        }
        racun.preracunajUkupanIznos();
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Racun racun = (Racun) ado;
        PreparedStatement ps = DBBroker.getInstance().insert(racun);
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            racun.setIdRacun(rs.getLong(1));
        }
        rs.close();
        ps.close();

        int rb = 1;
        for (StavkaRacuna stavka : racun.getStavkeRacuna()) {
            stavka.setRacun(racun);
            stavka.setRb(rb++);
            stavka.preracunajIznos();
            DBBroker.getInstance().insert(stavka).close();
        }
    }
}
