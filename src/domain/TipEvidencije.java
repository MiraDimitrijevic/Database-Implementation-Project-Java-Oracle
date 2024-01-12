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
public class TipEvidencije extends AbstractDomainObject{
private int sifraTipa;
private String nazivTipa;
private int iznos;

    public TipEvidencije(int sifraTipa, String nazivTipa, int iznos) {
        this.sifraTipa = sifraTipa;
        this.nazivTipa = nazivTipa;
        this.iznos = iznos;
    }

    public TipEvidencije() {
    }

    @Override
    public String nazivTabele() {
        return "TipEvidencije";
    }

    @Override
    public String alijas() {
        return "tp";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
ArrayList<AbstractDomainObject> lista = new ArrayList<>();
while(rs.next()){
    TipEvidencije tip = new TipEvidencije(rs.getInt("sifraTipa"), rs.getString(2), rs.getInt("iznos"));
    lista.add(tip);
}
return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(SifraTipa, NazivTipa, Iznos)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "SifraTipa = " +sifraTipa;
    }

    @Override
    public String vrednostiZaInsert() {
        return sifraTipa + ", tipStavkeEvidencije('" +nazivTipa+"') , "+iznos;
    }

    @Override
    public String vrednostiZaUpdate() {
        return " nazivTipa= tipStavkeEvidencije('"+nazivTipa+"'), iznos = "+iznos;
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String atributPK() {
        return "sifraTipa";
    }

    public int getSifraTipa() {
        return sifraTipa;
    }

    public void setSifraTipa(int sifraTipa) {
        this.sifraTipa = sifraTipa;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    public int getIznos() {
        return iznos;
    }

    public void setIznos(int iznos) {
        this.iznos = iznos;
    }

    @Override
    public String vrednostZaDelete() {
        return vrednostZaPrimarniKljuc();
    }
    
        @Override
    public String koloneZaSelect() {
        return " tp.SifraTipa, tp.NazivTipa.get_tip(), tp.Iznos";
    }

    
}
