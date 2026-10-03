package thread;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kosta
 */
public class ThreadServer extends Thread {

    private final int port;
    private volatile boolean running;
    private ServerSocket serverSocket;
    private final List<ThreadClient> clients = Collections.synchronizedList(new ArrayList<ThreadClient>());
    private Runnable onChange;

    public ThreadServer(int port) throws Exception {
        if (port < 1 || port > 65535) {
            throw new Exception("Port mora biti broj između 1 i 65535.");
        }
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        setName("VeterinarskaAmbulanta-Server");
    }

    public void setOnChange(Runnable onChange) {
        this.onChange = onChange;
    }

    @Override
    public void run() {
        try {
            running = true;
            fire();
            while (running) {
                Socket socket = serverSocket.accept();
                ThreadClient client = new ThreadClient(socket, this);
                clients.add(client);
                fire();
                client.start();
            }
        } catch (SocketException e) {
            if (running) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }

    public synchronized void stopServer() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception ignored) {
        }
        synchronized (clients) {
            for (ThreadClient client : new ArrayList<>(clients)) {
                client.closeClient();
            }
            clients.clear();
        }
        fire();
    }

    public void removeClient(ThreadClient client) {
        clients.remove(client);
        fire();
    }

    public int getClientCount() {
        return clients.size();
    }

    public boolean isRunning() {
        return running;
    }

    private void fire() {
        if (onChange != null) {
            onChange.run();
        }
    }
}
