/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbb;

import domain.AbstractDomainObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDatabaseException;

/**
 *
 * @author 38169
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;

    private DBBroker() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521/Connection2";
            String username = "system";
            String password = "kupki";
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws OracleDatabaseException, SQLException {
        String upit = "SELECT " + ado.koloneZaSelect() + " FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join() + " " + ado.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.vratiListu(rs);
    }

    public void insert(AbstractDomainObject ado) throws Exception {
        String upit = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        System.out.println(upit);
        if(ado.nazivTabele().equals("Prijemnica_view")) {
            System.out.println(true);
            Statement s= connection.createStatement();
            s.executeUpdate(upit);
        }else {
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();}
    }

    public void update(AbstractDomainObject ado) throws Exception {
        String upit = "UPDATE " + ado.nazivTabele() + " SET "
                + ado.vrednostiZaUpdate() + " WHERE " + ado.vrednostZaPrimarniKljuc();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    public void delete(AbstractDomainObject ado) throws OracleDatabaseException, SQLException {
        String upit = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.vrednostZaDelete();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    public int getID(AbstractDomainObject ado) throws OracleDatabaseException, SQLException {
        int ID = 0;
        String upit = "SELECT max(" + ado.atributPK()
                + ") FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.uslov();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            ID = rs.getInt(1);
        }
        return ++ID;
    }

    public ArrayList<AbstractDomainObject> selectParticion(AbstractDomainObject ado, String kriterijum) throws OracleDatabaseException, SQLException {
        String upit = "SELECT " + ado.koloneZaSelect() + " FROM " + ado.nazivTabele() + " partition("+kriterijum+") " + ado.alijas()
                + " " + ado.join() + " ";
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.vratiListu(rs);
    }

}
