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
public class VrstaPica extends AbstractDomainObject{
private int sifraVrstePica;
private String nazivVrstePica;

    public VrstaPica() {
    }

    public VrstaPica(int sifraVrstePica, String nazivVrstePica) {
        this.sifraVrstePica = sifraVrstePica;
        this.nazivVrstePica = nazivVrstePica;
    }


    @Override
    public String nazivTabele() {
        return "VrstaPica";
    }

    @Override
    public String alijas() {
        return "vp";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
ArrayList<AbstractDomainObject> lista= new ArrayList<>();
        while (rs.next()) {            
            VrstaPica vp= new VrstaPica(rs.getInt("sifraVrstePica"), rs.getString("nazivVrstePica"));
            lista.add(vp);
          
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

    public int getSifraVrstePica() {
        return sifraVrstePica;
    }

    public void setSifraVrstePica(int sifraVrstePica) {
        this.sifraVrstePica = sifraVrstePica;
    }

    public String getNazivVrstePica() {
        return nazivVrstePica;
    }

    public void setNazivVrstePica(String nazivVrstePica) {
        this.nazivVrstePica = nazivVrstePica;
    }

    @Override
    public String toString() {
        return nazivVrstePica;
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
        if (!(obj instanceof VrstaPica)) {
            return false;
        }
        VrstaPica vp = (VrstaPica) obj;
        if (vp.getSifraVrstePica()!= this.getSifraVrstePica()) {
            return false;
        }
        return true;
    }    

    
}
