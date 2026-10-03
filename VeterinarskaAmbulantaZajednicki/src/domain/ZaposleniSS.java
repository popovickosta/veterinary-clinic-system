package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Kosta
 */
public class ZaposleniSS extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;

    private Zaposleni zaposleni;
    private StrucnaSprema strucnaSprema;
    private Date datumSticanja;
    private Date datumZaposlenja;

    public ZaposleniSS() {
    }

    public ZaposleniSS(Zaposleni zaposleni, StrucnaSprema strucnaSprema, Date datumSticanja, Date datumZaposlenja) {
        this.zaposleni = zaposleni;
        this.strucnaSprema = strucnaSprema;
        this.datumSticanja = datumSticanja;
        this.datumZaposlenja = datumZaposlenja;
    }

    @Override
    public String nazivTabele() {
        return "ZaposleniSS";
    }

    @Override
    public String alijas() {
        return "zss";
    }

    @Override
    public String join() {
        return " JOIN Zaposleni z ON z.idZaposleni=zss.idZaposleni JOIN StrucnaSprema ss ON ss.idStrucnaSprema=zss.idStrucnaSprema ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            StrucnaSprema ss = new StrucnaSprema(rs.getLong(10), rs.getString(11), rs.getInt(12));
            lista.add(new ZaposleniSS(z, ss, rs.getDate(3), rs.getDate(4)));
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(idZaposleni,idStrucnaSprema,datumSticanja,datumZaposlenja)";
    }

    @Override
    public String vrednostiZaInsert() {
        return zaposleni.getIdZaposleni() + "," + strucnaSprema.getIdStrucnaSprema() + ",'" + new java.sql.Date(datumSticanja.getTime()) + "','" + new java.sql.Date(datumZaposlenja.getTime()) + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "datumSticanja='" + new java.sql.Date(datumSticanja.getTime()) + "',datumZaposlenja='" + new java.sql.Date(datumZaposlenja.getTime()) + "'";
    }

    @Override
    public String uslov() {
        return "idZaposleni=" + zaposleni.getIdZaposleni() + " AND idStrucnaSprema=" + strucnaSprema.getIdStrucnaSprema();
    }

    @Override
    public String uslovZaSelect() {
        return zaposleni == null ? " ORDER BY z.prezime,z.ime" : " WHERE zss.idZaposleni=" + zaposleni.getIdZaposleni() + " ORDER BY ss.stepenSS";
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public StrucnaSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }
}
