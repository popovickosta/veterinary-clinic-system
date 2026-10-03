package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Kosta
 */
public class SOLogin extends AbstractSO {
    private Zaposleni ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Pogrešan tip objekta.");
        }
        Zaposleni z = (Zaposleni) ado;
        if (z.getKorisnickoIme() == null || z.getKorisnickoIme().trim().isEmpty() || z.getLozinka() == null || z.getLozinka().isEmpty()) {
            throw new Exception("Unesite korisničko ime i šifru.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Zaposleni z = (Zaposleni) ado;
        String korisnickoIme = z.getKorisnickoIme().trim().replace("'", "''");
        String lozinka = z.getLozinka().replace("'", "''");
        String query = "SELECT * FROM Zaposleni z WHERE z.korisnickoIme='" + korisnickoIme + "' AND z.lozinka='" + lozinka + "'";
        ArrayList<Zaposleni> lista = (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().selectCustom(query, new Zaposleni());
        if (lista.isEmpty()) {
            throw new Exception("Korisničko ime i/ili šifra nisu ispravni.");
        }
        ulogovani = lista.get(0);
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }
}
