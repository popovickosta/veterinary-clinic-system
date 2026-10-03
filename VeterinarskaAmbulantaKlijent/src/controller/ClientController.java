package controller;

import domain.Mesto;
import domain.Racun;
import domain.StrucnaSprema;
import domain.Usluga;
import domain.VlasnikZivotinje;
import domain.Zaposleni;
import java.util.ArrayList;
import java.io.IOException;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Kosta
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static synchronized ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public synchronized Object sendRequest(Operation operation, Object data) throws Exception {
        try {
            Session.getInstance().connect();
            Session.getInstance().getOut().writeObject(new Request(operation, data));
            Session.getInstance().getOut().flush();
            Session.getInstance().getOut().reset();
            Response response = (Response) Session.getInstance().getIn().readObject();
            if (response.getStatus() == ResponseStatus.ERROR) {
                throw response.getException() == null ? new Exception("Greška na serveru.") : response.getException();
            }
            return response.getData();
        } catch (IOException e) {
            Session.getInstance().disconnect();
            throw new Exception("Veza sa serverom je prekinuta. Proverite da li su server i baza pokrenuti.");
        }
    }

    public Zaposleni login(String korisnickoIme, String lozinka) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setKorisnickoIme(korisnickoIme);
        zaposleni.setLozinka(lozinka);
        Zaposleni ulogovani = (Zaposleni) sendRequest(Operation.LOGIN, zaposleni);
        Session.getInstance().setUlogovani(ulogovani);
        return ulogovani;
    }

    public void logout() {
        try {
            if (Session.getInstance().isConnected()) {
                sendRequest(Operation.LOGOUT, Session.getInstance().getUlogovani());
            }
        } catch (Exception ignored) {
        } finally {
            Session.getInstance().disconnect();
        }
    }

    public ArrayList<Mesto> getAllMesto() throws Exception {
        return (ArrayList<Mesto>) sendRequest(Operation.GET_ALL_MESTO, null);
    }

    public ArrayList<Usluga> getAllUsluga() throws Exception {
        return (ArrayList<Usluga>) sendRequest(Operation.GET_ALL_USLUGA, null);
    }

    public Usluga addUsluga(Usluga usluga) throws Exception {
        return (Usluga) sendRequest(Operation.ADD_USLUGA, usluga);
    }

    public void updateUsluga(Usluga usluga) throws Exception {
        sendRequest(Operation.UPDATE_USLUGA, usluga);
    }

    public void deleteUsluga(Usluga usluga) throws Exception {
        sendRequest(Operation.DELETE_USLUGA, usluga);
    }

    public ArrayList<Usluga> searchUsluga(String tekst) throws Exception {
        return (ArrayList<Usluga>) sendRequest(Operation.SEARCH_USLUGA, tekst);
    }

    public ArrayList<StrucnaSprema> getAllStrucnaSprema() throws Exception {
        return (ArrayList<StrucnaSprema>) sendRequest(Operation.GET_ALL_STRUCNA_SPREMA, null);
    }

    public StrucnaSprema addStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        return (StrucnaSprema) sendRequest(Operation.ADD_STRUCNA_SPREMA, strucnaSprema);
    }

    public ArrayList<VlasnikZivotinje> getAllVlasnik() throws Exception {
        return (ArrayList<VlasnikZivotinje>) sendRequest(Operation.GET_ALL_VLASNIK, null);
    }

    public VlasnikZivotinje addVlasnik(VlasnikZivotinje vlasnik) throws Exception {
        return (VlasnikZivotinje) sendRequest(Operation.ADD_VLASNIK, vlasnik);
    }

    public void updateVlasnik(VlasnikZivotinje vlasnik) throws Exception {
        sendRequest(Operation.UPDATE_VLASNIK, vlasnik);
    }

    public void deleteVlasnik(VlasnikZivotinje vlasnik) throws Exception {
        sendRequest(Operation.DELETE_VLASNIK, vlasnik);
    }

    public ArrayList<VlasnikZivotinje> searchVlasnik(String tekst, Long idMesto) throws Exception {
        return (ArrayList<VlasnikZivotinje>) sendRequest(Operation.SEARCH_VLASNIK, new Object[]{tekst, idMesto});
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        return (ArrayList<Zaposleni>) sendRequest(Operation.GET_ALL_ZAPOSLENI, null);
    }

    public ArrayList<Racun> getAllRacun() throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.GET_ALL_RACUN, null);
    }

    public void addRacun(Racun racun) throws Exception {
        sendRequest(Operation.ADD_RACUN, racun);
    }

    public void updateRacun(Racun racun) throws Exception {
        sendRequest(Operation.UPDATE_RACUN, racun);
    }

    public ArrayList<Racun> searchRacun(Long idRacun, Long idZaposleni, Long idVlasnik, Long idUsluga) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.SEARCH_RACUN, new Object[]{idRacun, idZaposleni, idVlasnik, idUsluga});
    }
}
