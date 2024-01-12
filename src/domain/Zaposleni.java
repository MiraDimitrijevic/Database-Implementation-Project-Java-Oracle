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
public class Zaposleni extends AbstractDomainObject{

    private int sifraZapolenog;
    private String imePrezime;

    public Zaposleni() {
    }

    public Zaposleni(int sifraZapolenog, String imePrezime) {
        this.sifraZapolenog = sifraZapolenog;
        this.imePrezime = imePrezime;
    }
    
    @Override
    public String nazivTabele() {
        return "Zaposleni";
    }

    @Override
    public String alijas() {
        return "z";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {            
            Zaposleni z = new Zaposleni(rs.getInt("sifraZaposlenog"), rs.getString("imePrezime"));
            lista.add(z);
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

    public int getSifraZapolenog() {
        return sifraZapolenog;
    }

    public void setSifraZapolenog(int sifraZapolenog) {
        this.sifraZapolenog = sifraZapolenog;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    @Override
    public String toString() {
        return imePrezime;
    }
    
}
