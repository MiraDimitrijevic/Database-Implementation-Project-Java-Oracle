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
public class Smena extends AbstractDomainObject{
private int sifraSmene;
private String nazivSmene;

    public Smena() {
    }

    public Smena(int sifraSmene, String nazivSmene) {
        this.sifraSmene = sifraSmene;
        this.nazivSmene = nazivSmene;
    }



    @Override
    public String nazivTabele() {
        return "Smena";
    }

    @Override
    public String alijas() {
        return "s";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {            
            Smena s = new Smena(rs.getInt("sifraSmene"), rs.getString("nazivSmene"));
            lista.add(s);
        }
        return lista;    }

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

    public int getSifraSmene() {
        return sifraSmene;
    }

    public void setSifraSmene(int sifraSmene) {
        this.sifraSmene = sifraSmene;
    }

    public String getNazivSmene() {
        return nazivSmene;
    }

    public void setNazivSmene(String nazivSmene) {
        this.nazivSmene = nazivSmene;
    }

    @Override
    public String toString() {
        return nazivSmene;
    }
    
    
}
