package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Kosta
 */
public class Racun extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;

    private long idRacun;
    private Date datumIzdavanja;
    private double ukupanIznos;
    private Zaposleni zaposleni;
    private VlasnikZivotinje vlasnikZivotinje;
    private ArrayList<StavkaRacuna> stavkeRacuna = new ArrayList<>();

    public Racun() {
    }

    public Racun(long idRacun, Date datumIzdavanja, double ukupanIznos, Zaposleni zaposleni, VlasnikZivotinje vlasnikZivotinje, ArrayList<StavkaRacuna> stavkeRacuna) {
        this.idRacun = idRacun;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupanIznos = ukupanIznos;
        this.zaposleni = zaposleni;
        this.vlasnikZivotinje = vlasnikZivotinje;
        setStavkeRacuna(stavkeRacuna);
    }

    @Override
    public String nazivTabele() {
        return "Racun";
    }

    @Override
    public String alijas() {
        return "r";
    }

    @Override
    public String join() {
        return " JOIN Zaposleni z ON z.idZaposleni=r.idZaposleni JOIN VlasnikZivotinje v ON v.idVlasnik=r.idVlasnik JOIN Mesto m ON m.idMesto=v.idMesto ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getLong(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            Mesto m = new Mesto(rs.getLong(15), rs.getString(16));
            VlasnikZivotinje v = new VlasnikZivotinje(rs.getLong(11), rs.getString(12), rs.getString(13), m);
            lista.add(new Racun(rs.getLong(1), rs.getTimestamp(2), rs.getDouble(3), z, v, new ArrayList<StavkaRacuna>()));
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(datumIzdavanja,ukupanIznos,idZaposleni,idVlasnik)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Timestamp(datumIzdavanja.getTime()) + "'," + ukupanIznos + "," + zaposleni.getIdZaposleni() + "," + vlasnikZivotinje.getIdVlasnik();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "datumIzdavanja='" + new java.sql.Timestamp(datumIzdavanja.getTime()) + "',ukupanIznos=" + ukupanIznos + ",idZaposleni=" + zaposleni.getIdZaposleni() + ",idVlasnik=" + vlasnikZivotinje.getIdVlasnik();
    }

    @Override
    public String uslov() {
        return "idRacun=" + idRacun;
    }

    @Override
    public String uslovZaSelect() {
        return " ORDER BY r.datumIzdavanja DESC,r.idRacun DESC";
    }

    public void preracunajUkupanIznos() {
        double total = 0;
        for (StavkaRacuna s : stavkeRacuna) {
            s.preracunajIznos();
            total += s.getIznosStavke();
        }
        this.ukupanIznos = Math.round(total * 100.0) / 100.0;
    }

    public long getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(long idRacun) {
        this.idRacun = idRacun;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public VlasnikZivotinje getVlasnikZivotinje() {
        return vlasnikZivotinje;
    }

    public void setVlasnikZivotinje(VlasnikZivotinje vlasnikZivotinje) {
        this.vlasnikZivotinje = vlasnikZivotinje;
    }

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna == null ? new ArrayList<StavkaRacuna>() : stavkeRacuna;
    }

    @Override
    public String toString() {
        return "Račun #" + idRacun;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Racun && ((Racun) o).idRacun == idRacun;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRacun);
    }
}
