package so.vlasnik;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.VlasnikZivotinje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOAddVlasnik extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof VlasnikZivotinje)) {
            throw new Exception("Pogrešan tip objekta.");
        }
        VlasnikZivotinje vlasnik = (VlasnikZivotinje) ado;
        if (vlasnik.getIme() == null || vlasnik.getIme().trim().length() < 2 || vlasnik.getIme().trim().length() > 30) {
            throw new Exception("Ime mora imati između 2 i 30 karaktera.");
        }
        if (vlasnik.getPrezime() == null || vlasnik.getPrezime().trim().length() < 2 || vlasnik.getPrezime().trim().length() > 30) {
            throw new Exception("Prezime mora imati između 2 i 30 karaktera.");
        }
        if (vlasnik.getMesto() == null || vlasnik.getMesto().getIdMesto() <= 0) {
            throw new Exception("Mesto mora biti izabrano.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        VlasnikZivotinje vlasnik = (VlasnikZivotinje) ado;
        PreparedStatement ps = DBBroker.getInstance().insert(vlasnik);
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            vlasnik.setIdVlasnik(rs.getLong(1));
        }
        rs.close();
        ps.close();
    }
}
