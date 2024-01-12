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
public class Pice extends AbstractDomainObject {

    private int sifraPica;
    private String nazivPica;
    private int kolicinaNaStanju;
    private JedinicaMere jedinicaMere;
    private VrstaPica vrstaPica;
    private Double trenutnaCena;
    private double ocekivanaCena;
    private ArrayList<Cena> listaCena;

    public Pice() {
    }

    public Pice(int sifraPica, String nazivPica, int kolicinaNaStanju, JedinicaMere jedinicaMere, VrstaPica vrstaPica, Double trenutnaCena, ArrayList<Cena> listaCena) {
        this.sifraPica = sifraPica;
        this.nazivPica = nazivPica;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.jedinicaMere = jedinicaMere;
        this.vrstaPica = vrstaPica;
        this.trenutnaCena = trenutnaCena;
        this.listaCena = listaCena;
    }

    @Override
    public String nazivTabele() {
        return "Pice";
    }

    @Override
    public String alijas() {
        return "pice";
    }

    @Override
    public String join() {
        return "JOIN VrstaPica vp ON (pice.SifraVrstePica = vp.SifraVrstePica)"
                + " JOIN JedinicaMere jm ON (pice.SifraJM = jm.SifraJM)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            JedinicaMere jm = new JedinicaMere(rs.getInt("sifraJM"), rs.getString("jedinica"));
            VrstaPica vp = new VrstaPica(rs.getInt("sifraVrstePica"), rs.getString("nazivVrstePica"));
            Pice pice = new Pice(rs.getInt("sifrapica"), rs.getString("nazivPica"), rs.getInt("kolicinaNaStanju"), jm, vp, rs.getDouble("trenutnacena"), null);
            lista.add(pice);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (SifraPica, NazivPica, KolicinaNaStanju, SifraJM , SifraVrstePica, TrenutnaCena)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "SifraPica = " + sifraPica;
    }

    @Override
    public String vrednostiZaInsert() {
        return sifraPica + ", '" + nazivPica + "', " + kolicinaNaStanju + ", " + jedinicaMere.getSifrajm() + ", "
                + vrstaPica.getSifraVrstePica() + ", " + trenutnaCena;
    }

    @Override
    public String vrednostiZaUpdate() {
        if (trenutnaCena == ocekivanaCena) {
            return update2();
        }
        return "nazivPica = '" + nazivPica + "' , kolicinaNaStanju = " + kolicinaNaStanju
                + " , sifraJM = " + jedinicaMere.getSifrajm() + ", sifraVrstePica= " + vrstaPica.getSifraVrstePica()
                + ", trenutnaCena= " + trenutnaCena;
    }

    @Override
    public String uslov() {
        return "";
    }

    public int getSifraPica() {
        return sifraPica;
    }

    public void setSifraPica(int sifraPica) {
        this.sifraPica = sifraPica;
    }

    public String getNazivPica() {
        return nazivPica;
    }

    public void setNazivPica(String nazivPica) {
        this.nazivPica = nazivPica;
    }

    public int getKolicinaNaStanju() {
        return kolicinaNaStanju;
    }

    public void setKolicinaNaStanju(int kolicinaNaStanju) {
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    public JedinicaMere getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(JedinicaMere jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public VrstaPica getVrstaPica() {
        return vrstaPica;
    }

    public void setVrstaPica(VrstaPica vrstaPica) {
        this.vrstaPica = vrstaPica;
    }

    public Double getTrenutnaCena() {
        return trenutnaCena;
    }

    public void setTrenutnaCena(Double trenutnaCena) {
        this.trenutnaCena = trenutnaCena;
    }

    public ArrayList<Cena> getListaCena() {
        return listaCena;
    }

    public void setListaCena(ArrayList<Cena> listaCena) {
        this.listaCena = listaCena;
    }

    @Override
    public String toString() {
        return nazivPica;
    }

    @Override
    public String atributPK() {
        return "sifraPica";
    }

    @Override
    public String vrednostZaDelete() {
        return vrednostZaPrimarniKljuc();
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
        if (!(obj instanceof Pice)) {
            return false;
        }
        Pice p = (Pice) obj;
        if (p.getSifraPica() != this.getSifraPica()) {
            return false;
        }
        return true;
    }

    private String update2() {
        return "nazivPica = '" + nazivPica + "' , kolicinaNaStanju = " + kolicinaNaStanju
                + " , sifraJM = " + jedinicaMere.getSifrajm() + ", sifraVrstePica= " + vrstaPica.getSifraVrstePica();
    }

    public void setOcekivanaCena(double ocekivanaCena) {
        this.ocekivanaCena = ocekivanaCena;
    }

    
}
