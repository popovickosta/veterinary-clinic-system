package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kosta
 */
public class Zaposleni extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;

    private long idZaposleni;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private ArrayList<ZaposleniSS> strucneSpreme = new ArrayList<>();

    public Zaposleni() {
    }

    public Zaposleni(long idZaposleni, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    @Override
    public String nazivTabele() {
        return "Zaposleni";
    }

    @Override
    public String alijas() {
        return "z";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Zaposleni(rs.getLong("idZaposleni"), rs.getString("ime"), rs.getString("prezime"), rs.getString("korisnickoIme"), rs.getString("lozinka")));
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ime,prezime,korisnickoIme,lozinka)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime.replace("'", "''") + "','" + prezime.replace("'", "''") + "','" + korisnickoIme.replace("'", "''") + "','" + lozinka.replace("'", "''") + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ime='" + ime.replace("'", "''") + "',prezime='" + prezime.replace("'", "''") + "',korisnickoIme='" + korisnickoIme.replace("'", "''") + "',lozinka='" + lozinka.replace("'", "''") + "'";
    }

    @Override
    public String uslov() {
        return "idZaposleni=" + idZaposleni;
    }

    @Override
    public String uslovZaSelect() {
        return " ORDER BY z.prezime,z.ime";
    }

    public long getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(long idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public ArrayList<ZaposleniSS> getStrucneSpreme() {
        return strucneSpreme;
    }

    public void setStrucneSpreme(ArrayList<ZaposleniSS> strucneSpreme) {
        this.strucneSpreme = strucneSpreme == null ? new ArrayList<ZaposleniSS>() : strucneSpreme;
    }

    @Override
    public String toString() {
        return ((ime == null ? "" : ime) + " " + (prezime == null ? "" : prezime)).trim();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Zaposleni && ((Zaposleni) o).idZaposleni == idZaposleni;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZaposleni);
    }
}
