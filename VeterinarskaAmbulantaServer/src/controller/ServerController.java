package controller;

import domain.Mesto;
import domain.Racun;
import domain.StrucnaSprema;
import domain.Usluga;
import domain.VlasnikZivotinje;
import domain.Zaposleni;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import so.login.SOLogin;
import so.mesto.SOGetAllMesto;
import so.racun.SOAddRacun;
import so.racun.SOGetAllRacun;
import so.racun.SOSearchRacun;
import so.racun.SOUpdateRacun;
import so.strucna_sprema.SOAddStrucnaSprema;
import so.strucna_sprema.SOGetAllStrucnaSprema;
import so.usluga.SOAddUsluga;
import so.usluga.SODeleteUsluga;
import so.usluga.SOGetAllUsluga;
import so.usluga.SOSearchUsluga;
import so.usluga.SOUpdateUsluga;
import so.vlasnik.SOAddVlasnik;
import so.vlasnik.SODeleteVlasnik;
import so.vlasnik.SOGetAllVlasnik;
import so.vlasnik.SOSearchVlasnik;
import so.vlasnik.SOUpdateVlasnik;
import so.zaposleni.SOGetAllZaposleni;

/**
 *
 * @author Kosta
 */
public class ServerController {

    private static ServerController instance;
    private final List<Zaposleni> ulogovani = Collections.synchronizedList(new ArrayList<Zaposleni>());

    private ServerController() {
    }

    public static synchronized ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(zaposleni);
        Zaposleni ulogovan = so.getUlogovani();
        synchronized (ulogovani) {
            if (ulogovani.contains(ulogovan)) {
                throw new Exception("Korisnik je već prijavljen na sistem.");
            }
            ulogovani.add(ulogovan);
        }
        return ulogovan;
    }

    public void logout(Zaposleni zaposleni) {
        if (zaposleni != null) {
            ulogovani.remove(zaposleni);
        }
    }

    public List<Zaposleni> getUlogovani() {
        synchronized (ulogovani) {
            return new ArrayList<>(ulogovani);
        }
    }

    public ArrayList<Mesto> getAllMesto() throws Exception {
        SOGetAllMesto so = new SOGetAllMesto();
        so.templateExecute(new Mesto());
        return so.getLista();
    }

    public ArrayList<Usluga> getAllUsluga() throws Exception {
        SOGetAllUsluga so = new SOGetAllUsluga();
        so.templateExecute(new Usluga());
        return so.getLista();
    }

    public void addUsluga(Usluga usluga) throws Exception {
        (new SOAddUsluga()).templateExecute(usluga);
    }

    public void updateUsluga(Usluga usluga) throws Exception {
        (new SOUpdateUsluga()).templateExecute(usluga);
    }

    public void deleteUsluga(Usluga usluga) throws Exception {
        (new SODeleteUsluga()).templateExecute(usluga);
    }

    public ArrayList<Usluga> searchUsluga(String tekst) throws Exception {
        SOSearchUsluga so = new SOSearchUsluga(tekst);
        so.templateExecute(new Usluga());
        return so.getLista();
    }

    public ArrayList<StrucnaSprema> getAllStrucnaSprema() throws Exception {
        SOGetAllStrucnaSprema so = new SOGetAllStrucnaSprema();
        so.templateExecute(new StrucnaSprema());
        return so.getLista();
    }

    public void addStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        (new SOAddStrucnaSprema()).templateExecute(strucnaSprema);
    }

    public ArrayList<VlasnikZivotinje> getAllVlasnik() throws Exception {
        SOGetAllVlasnik so = new SOGetAllVlasnik();
        so.templateExecute(new VlasnikZivotinje());
        return so.getLista();
    }

    public void addVlasnik(VlasnikZivotinje vlasnik) throws Exception {
        (new SOAddVlasnik()).templateExecute(vlasnik);
    }

    public void updateVlasnik(VlasnikZivotinje vlasnik) throws Exception {
        (new SOUpdateVlasnik()).templateExecute(vlasnik);
    }

    public void deleteVlasnik(VlasnikZivotinje vlasnik) throws Exception {
        (new SODeleteVlasnik()).templateExecute(vlasnik);
    }

    public ArrayList<VlasnikZivotinje> searchVlasnik(String tekst, Long idMesto) throws Exception {
        SOSearchVlasnik so = new SOSearchVlasnik(tekst, idMesto);
        so.templateExecute(new VlasnikZivotinje());
        return so.getLista();
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        SOGetAllZaposleni so = new SOGetAllZaposleni();
        so.templateExecute(new Zaposleni());
        return so.getLista();
    }

    public ArrayList<Racun> getAllRacun() throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();
        so.templateExecute(new Racun());
        return so.getLista();
    }

    public void addRacun(Racun racun) throws Exception {
        (new SOAddRacun()).templateExecute(racun);
    }

    public void updateRacun(Racun racun) throws Exception {
        (new SOUpdateRacun()).templateExecute(racun);
    }

    public ArrayList<Racun> searchRacun(Long idRacun, Long idZaposleni, Long idVlasnik, Long idUsluga) throws Exception {
        SOSearchRacun so = new SOSearchRacun(idRacun, idZaposleni, idVlasnik, idUsluga);
        so.templateExecute(new Racun());
        return so.getLista();
    }
}
