package so.strucna_sprema;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StrucnaSprema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOAddStrucnaSprema extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StrucnaSprema)) {
            throw new Exception("Pogrešan tip objekta.");
        }
        StrucnaSprema ss = (StrucnaSprema) ado;
        if (ss.getNazivSS() == null || ss.getNazivSS().trim().length() < 2 || ss.getNazivSS().trim().length() > 100) {
            throw new Exception("Naziv stručne spreme mora imati između 2 i 100 karaktera.");
        }
        if (ss.getStepenSS() <= 0 || ss.getStepenSS() > 8) {
            throw new Exception("Stepen stručne spreme mora biti između 1 i 8.");
        }
        if (DBBroker.getInstance().postojiStrucnaSpremaSaNazivom(ss.getNazivSS(), 0)) {
            throw new Exception("Stručna sprema sa unetim nazivom već postoji.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        StrucnaSprema ss = (StrucnaSprema) ado;
        PreparedStatement ps = DBBroker.getInstance().insert(ss);
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            ss.setIdStrucnaSprema(rs.getLong(1));
        }
        rs.close();
        ps.close();
    }
}
