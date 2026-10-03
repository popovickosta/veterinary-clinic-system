package db;

import domain.AbstractDomainObject;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Kosta
 */
public class DBBroker {
    private static DBBroker instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    private DBBroker() throws Exception {
        loadConfiguration();
    }

    public static synchronized DBBroker getInstance() throws Exception {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public static synchronized void reset() {
        if (instance != null) {
            try {
                instance.close();
            } catch (Exception ignored) {}
        }
        instance = null;
    }

    private void loadConfiguration() throws Exception {
        Properties p = new Properties();
        FileInputStream in = new FileInputStream("dbconfig.properties");
        p.load(in);
        in.close();
        url = p.getProperty("url");
        username = p.getProperty("username", "root");
        password = p.getProperty("password", "");
    }

    public synchronized Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed() || !connection.isValid(2)) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ignored) {
                }
            }
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }

    public synchronized boolean testConnection() {
        try {
            return getConnection() != null && getConnection().isValid(2);
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = null;
    }

    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws Exception {
        String query = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas() + " " + ado.join() + " " + ado.uslovZaSelect();
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<AbstractDomainObject> lista = ado.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
    }

    public ArrayList<AbstractDomainObject> selectCustom(String query, AbstractDomainObject ado) throws Exception {
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<AbstractDomainObject> lista = ado.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
    }

    public PreparedStatement insert(AbstractDomainObject ado) throws Exception {
        String sql = "INSERT INTO " + ado.nazivTabele() + " " + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(AbstractDomainObject ado) throws Exception {
        String sql = "UPDATE " + ado.nazivTabele() + " SET " + ado.vrednostiZaUpdate() + " WHERE " + ado.uslov();
        Statement st = getConnection().createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public void delete(AbstractDomainObject ado) throws Exception {
        String sql = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.uslov();
        Statement st = getConnection().createStatement();
        st.executeUpdate(sql);
        st.close();
    }

    public boolean postojiStrucnaSpremaSaNazivom(String naziv, long idZaIzuzimanje) throws Exception {
        String sql = "SELECT COUNT(*) FROM StrucnaSprema "
                + "WHERE LOWER(TRIM(nazivSS)) = LOWER(TRIM(?)) "
                + "AND idStrucnaSprema <> ?";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setString(1, naziv);
        ps.setLong(2, idZaIzuzimanje);
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean postoji = rs.getInt(1) > 0;
        rs.close();
        ps.close();
        return postoji;
    }

    public boolean postojiMestoSaNazivom(String naziv, long idZaIzuzimanje) throws Exception {
        String sql = "SELECT COUNT(*) FROM Mesto "
                + "WHERE LOWER(TRIM(nazivMesta)) = LOWER(TRIM(?)) "
                + "AND idMesto <> ?";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setString(1, naziv);
        ps.setLong(2, idZaIzuzimanje);
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean postoji = rs.getInt(1) > 0;
        rs.close();
        ps.close();
        return postoji;
    }

    public boolean postojiUslugaSaNazivom(String naziv, long idZaIzuzimanje) throws Exception {
        String sql = "SELECT COUNT(*) FROM Usluga "
                + "WHERE LOWER(TRIM(nazivUsluge)) = LOWER(TRIM(?)) "
                + "AND idUsluga <> ?";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setString(1, naziv);
        ps.setLong(2, idZaIzuzimanje);
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean postoji = rs.getInt(1) > 0;
        rs.close();
        ps.close();
        return postoji;
    }

    public boolean postojiPovezanZapis(String tabela, String kolona, long id) throws Exception {
        String sql = "SELECT COUNT(*) FROM " + tabela + " WHERE " + kolona + " = ?";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean postoji = rs.getInt(1) > 0;
        rs.close();
        ps.close();
        return postoji;
    }
}
