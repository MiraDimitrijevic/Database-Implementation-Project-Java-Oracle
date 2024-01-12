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
public class Grad extends AbstractDomainObject {

    private int postanskiBrpj;
    private String nazivGrada;

    public Grad(int postanskiBrpj, String nazivGrada) {
        this.postanskiBrpj = postanskiBrpj;
        this.nazivGrada = nazivGrada;
    }

    public Grad() {
    }

    @Override
    public String nazivTabele() {
        return "Grad";
    }

    @Override
    public String alijas() {
        return "g";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista= new ArrayList<>();
        while(rs.next()){
            Grad g = new Grad(rs.getInt("postanskiBroj"), rs.getString("nazivGrada"));
            lista.add(g);
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

    public int getPostanskiBrpj() {
        return postanskiBrpj;
    }

    public void setPostanskiBrpj(int postanskiBrpj) {
        this.postanskiBrpj = postanskiBrpj;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }

    @Override
    public String toString() {
        return nazivGrada;
    }
    
    

}
