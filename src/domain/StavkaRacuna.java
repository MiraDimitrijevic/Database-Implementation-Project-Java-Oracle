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
public class StavkaRacuna extends AbstractDomainObject {

    private Racun racun;
    private int brojStavkeRac;
    private double kolicina;
    private Pice pice;
    private String nazivPica;

    public StavkaRacuna(Racun racun, int brojStavkeRac, double kolicina, Pice pice, String nazivPica) {
        this.racun = racun;
        this.brojStavkeRac = brojStavkeRac;
        this.kolicina = kolicina;
        this.pice = pice;
        this.nazivPica = nazivPica;
    }

    public StavkaRacuna() {
    }

    @Override
    public String nazivTabele() {
        return "StavkaRacuna";
    }

    @Override
    public String alijas() {
        return "sr";
    }

    @Override
    public String join() {
        return " JOIN Racun rac ON (rac.BrojRacuna = sr.BrojRacuna) "
                + " JOIN Zaposleni z ON (z.sifraZaposlenog = rac.sifraZaposlenog)"
                + " JOIN Pice pice ON (pice.SifraPica = sr.SifraPica) "
                + " JOIN VrstaPica vp ON (pice.SifraVrstePica = vp.SifraVrstePica)"
                + " JOIN JedinicaMere jm ON (pice.SifraJM = jm.SifraJM)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("sifraZaposlenog"), rs.getString("imePrezime"));
            JedinicaMere jm = new JedinicaMere(rs.getInt("sifraJM"), rs.getString("jedinica"));
            VrstaPica vp = new VrstaPica(rs.getInt("sifraVrstePica"), rs.getString("nazivVrstePica"));
            Pice pice = new Pice(rs.getInt("sifrapica"), rs.getString("nazivPica"), rs.getInt("kolicinaNaStanju"), jm, vp, rs.getDouble("trenutnacena"), null);
            Racun rac = new Racun(rs.getInt("brojRacuna"), rs.getDate("datumVremeRac"), rs.getDouble("iznosRacuna"),
                    rs.getString("poreskaOznaka"), rs.getDouble("iznosPoreza"), z);
            StavkaRacuna sr = new StavkaRacuna(rac, rs.getInt("brojStavkeRac"), rs.getDouble("kolicina"), pice, rs.getString("nazivPica"));
            lista.add(sr);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(brojRacuna, brojStavkeRac, kolicina, sifraPica, nazivPica)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "brojRacuna = " + racun.getBrojRacuna()+ " AND brojStavkeRac = " + brojStavkeRac;

    }

    @Override
    public String vrednostiZaInsert() {
        return "" +racun.getBrojRacuna()+", "+ brojStavkeRac+" ,"+kolicina+" ,"+
                pice.getSifraPica()+" , '"+nazivPica+"'";
    }

    @Override
    public String vrednostiZaUpdate() {
        if(nazivPica.equals(pice.getNazivPica())) return update2();
        return " kolicina = "+kolicina+" , sifraPica = "+pice.getSifraPica() +
                ", nazivPica = '"+nazivPica+"'";
    }

    @Override
    public String uslov() {
        return "WHERE sr.BrojRacuna = "+racun.getBrojRacuna();
    }

    @Override
    public String atributPK() {
        return "brojStavkeRac" ;
    }

    @Override
    public String vrednostZaDelete() {
        return vrednostZaPrimarniKljuc();
    }

    @Override
    public String koloneZaSelect() {
        return "*";
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getBrojStavkeRac() {
        return brojStavkeRac;
    }

    public void setBrojStavkeRac(int brojStavkeRac) {
        this.brojStavkeRac = brojStavkeRac;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public Pice getPice() {
        return pice;
    }

    public void setPice(Pice pice) {
        this.pice = pice;
    }

    public String getNazivPica() {
        return nazivPica;
    }

    public void setNazivPica(String nazivPica) {
        this.nazivPica = nazivPica;
    }

    private String update2() {
         return " kolicina = "+kolicina+" , sifraPica = "+pice.getSifraPica();
    }

}
