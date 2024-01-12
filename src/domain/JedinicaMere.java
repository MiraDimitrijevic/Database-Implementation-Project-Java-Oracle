/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 38169
 */
public class JedinicaMere extends AbstractDomainObject {

    private int sifrajm;
    private String jedinica;

    public JedinicaMere() {

    }

    public JedinicaMere(int sifrajm, String jedinica) {
        this.sifrajm = sifrajm;
        this.jedinica = jedinica;
    }

    @Override
    public String nazivTabele() {
        return "JedinicaMere";
    }

    @Override
    public String alijas() {
        return "jm";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            JedinicaMere jm = new JedinicaMere(rs.getInt("sifraJM"), rs.getString("jedinica"));
            lista.add(jm);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    public int getSifrajm() {
        return sifrajm;
    }

    public void setSifrajm(int sifrajm) {
        this.sifrajm = sifrajm;
    }

    public String getJedinica() {
        return jedinica;
    }

    public void setJedinica(String jedinica) {
        this.jedinica = jedinica;
    }

    @Override
    public String toString() {
        return jedinica;
    }

    @Override
    public String atributPK() {
        return "";
    }

    @Override

    public String vrednostZaDelete() {
        return "";
    }

    @Override
    public String koloneZaSelect() {
        return "*";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof JedinicaMere)) {
            return false;
        }
        JedinicaMere jm = (JedinicaMere) obj;
        if (jm.getSifrajm() != this.getSifrajm()) {
            return false;
        }
        return true;
    }

}
