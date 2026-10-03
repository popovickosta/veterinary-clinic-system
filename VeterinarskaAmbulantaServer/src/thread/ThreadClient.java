package thread;

import controller.ServerController;
import domain.Mesto;
import domain.Racun;
import domain.StrucnaSprema;
import domain.Usluga;
import domain.VlasnikZivotinje;
import domain.Zaposleni;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;

/**
 *
 * @author Kosta
 */
public class ThreadClient extends Thread {

    private final Socket socket;
    private final ThreadServer server;
    private volatile boolean running = true;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Zaposleni prijavljeni;

    public ThreadClient(Socket socket, ThreadServer server) {
        this.socket = socket;
        this.server = server;
        setName("Klijent-" + socket.getRemoteSocketAddress());
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            while (running && !socket.isClosed()) {
                Object obj = in.readObject();
                if (!(obj instanceof Request)) {
                    continue;
                }
                Response response = handle((Request) obj);
                out.writeObject(response);
                out.flush();
                out.reset();
            }
        } catch (EOFException ignored) {
        } catch (Exception e) {
            if (running) {
                e.printStackTrace();
            }
        } finally {
            closeClient();
            server.removeClient(this);
        }
    }

    private Response handle(Request request) {
        Response response = new Response();
        try {
            if (request.getOperation() != transfer.util.Operation.LOGIN && prijavljeni == null) {
                throw new Exception("Korisnik mora biti prijavljen na sistem.");
            }
            switch (request.getOperation()) {
                case LOGIN:
                    prijavljeni = ServerController.getInstance().login((Zaposleni) request.getData());
                    response.setData(prijavljeni);
                    break;
                case LOGOUT:
                    ServerController.getInstance().logout((Zaposleni) request.getData());
                    prijavljeni = null;
                    break;
                case GET_ALL_MESTO:
                    response.setData(ServerController.getInstance().getAllMesto());
                    break;
                case GET_ALL_USLUGA:
                    response.setData(ServerController.getInstance().getAllUsluga());
                    break;
                case ADD_USLUGA:
                    ServerController.getInstance().addUsluga((Usluga) request.getData());
                    response.setData(request.getData());
                    break;
                case SEARCH_USLUGA:
                    response.setData(ServerController.getInstance().searchUsluga((String) request.getData()));
                    break;
                case UPDATE_USLUGA:
                    ServerController.getInstance().updateUsluga((Usluga) request.getData());
                    break;
                case DELETE_USLUGA:
                    ServerController.getInstance().deleteUsluga((Usluga) request.getData());
                    break;
                case GET_ALL_STRUCNA_SPREMA:
                    response.setData(ServerController.getInstance().getAllStrucnaSprema());
                    break;
                case ADD_STRUCNA_SPREMA:
                    ServerController.getInstance().addStrucnaSprema((StrucnaSprema) request.getData());
                    response.setData(request.getData());
                    break;
                case GET_ALL_VLASNIK:
                    response.setData(ServerController.getInstance().getAllVlasnik());
                    break;
                case ADD_VLASNIK:
                    ServerController.getInstance().addVlasnik((VlasnikZivotinje) request.getData());
                    response.setData(request.getData());
                    break;
                case SEARCH_VLASNIK: {
                    Object[] parametri = (Object[]) request.getData();
                    response.setData(ServerController.getInstance().searchVlasnik((String) parametri[0], (Long) parametri[1]));
                    break;
                }
                case UPDATE_VLASNIK:
                    ServerController.getInstance().updateVlasnik((VlasnikZivotinje) request.getData());
                    break;
                case DELETE_VLASNIK:
                    ServerController.getInstance().deleteVlasnik((VlasnikZivotinje) request.getData());
                    break;
                case GET_ALL_ZAPOSLENI:
                    response.setData(ServerController.getInstance().getAllZaposleni());
                    break;
                case GET_ALL_RACUN:
                    response.setData(ServerController.getInstance().getAllRacun());
                    break;
                case ADD_RACUN:
                    ServerController.getInstance().addRacun((Racun) request.getData());
                    break;
                case SEARCH_RACUN: {
                    Object[] parametri = (Object[]) request.getData();
                    response.setData(ServerController.getInstance().searchRacun((Long) parametri[0], (Long) parametri[1], (Long) parametri[2], (Long) parametri[3]));
                    break;
                }
                case UPDATE_RACUN:
                    ServerController.getInstance().updateRacun((Racun) request.getData());
                    break;
                default:
                    throw new Exception("Nepoznata operacija: " + request.getOperation());
            }
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(e);
        }
        return response;
    }

    public void closeClient() {
        running = false;
        if (prijavljeni != null) {
            ServerController.getInstance().logout(prijavljeni);
        }
        try {
            socket.close();
        } catch (Exception ignored) {
        }
        interrupt();
    }
}
