package so.usluga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Usluga;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOAddUsluga extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Usluga)) {
            throw new Exception("Pogrešan tip objekta.");
        }
        Usluga usluga = (Usluga) ado;
        if (usluga.getNazivUsluge() == null || usluga.getNazivUsluge().trim().length() < 2 || usluga.getNazivUsluge().trim().length() > 100) {
            throw new Exception("Naziv usluge mora imati između 2 i 100 karaktera.");
        }
        if (usluga.getCenaUsluge() <= 0 || usluga.getCenaUsluge() > 100000) {
            throw new Exception("Cena usluge mora biti veća od 0 i najviše 100000.");
        }
        if (usluga.getTrajanje() <= 0 || usluga.getTrajanje() > 1440) {
            throw new Exception("Trajanje usluge mora biti pozitivan broj minuta.");
        }
        if (DBBroker.getInstance().postojiUslugaSaNazivom(usluga.getNazivUsluge(), 0)) {
            throw new Exception("Usluga sa unetim nazivom već postoji.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Usluga usluga = (Usluga) ado;
        PreparedStatement ps = DBBroker.getInstance().insert(usluga);
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            usluga.setIdUsluga(rs.getLong(1));
        }
        rs.close();
        ps.close();
    }
}
