/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 38169
 */
public class Racun extends AbstractDomainObject {

    private int brojRacuna;
    private Date datumVremeRac;
    private double iznosRacuna;
    private String poreskaOznaka;
    private double iznosPoreza;
    private Zaposleni zaposleni;
    private ArrayList<StavkaRacuna> list;
    private double iznosListe;

    public void setIznosListe(double iznosListe) {
        this.iznosListe = iznosListe;
    }
    
   

    public ArrayList<StavkaRacuna> getList() {
        return list;
    }

    public void setList(ArrayList<StavkaRacuna> list) {
        this.list = list;
    }
    
    

    public Racun() {
    }

    public Racun(int brojRacuna, Date datumVremeRac, double iznosRacuna, String poreskaOznaka, double iznosPoreza, Zaposleni zaposleni) {
        this.brojRacuna = brojRacuna;
        this.datumVremeRac = datumVremeRac;
        this.iznosRacuna = iznosRacuna;
        this.poreskaOznaka = poreskaOznaka;
        this.iznosPoreza = iznosPoreza;
        this.zaposleni = zaposleni;
    }

    @Override
    public String nazivTabele() {
        return "Racun";
    }

    @Override
    public String alijas() {
        return "rac";
    }

    @Override
    public String join() {
        return "JOIN Zaposleni z ON (z.sifraZaposlenog = rac.sifraZaposlenog)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("sifraZaposlenog"), rs.getString("imePrezime"));
            Racun rac = new Racun(rs.getInt("brojRacuna"), rs.getDate("datumVremeRac"), rs.getDouble("iznosRacuna"),
                    rs.getString("poreskaOznaka"), rs.getDouble("iznosPoreza"), z);
            lista.add(rac);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(brojRacuna, datumVremeRac, iznosRacuna, poreskaOznaka, iznosPoreza, sifraZaposlenog)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "brojRacuna = " + brojRacuna;

    }

    @Override
    public String vrednostiZaInsert() {
        datumVremeRac = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yy. HH:mm:ss,S");

        return brojRacuna + ", '" + sdf2.format(datumVremeRac) + "' ," + iznosRacuna + " ,'" + poreskaOznaka + "', "
                + iznosPoreza + ", " + zaposleni.getSifraZapolenog();
    }

    @Override
    public String vrednostiZaUpdate() {
        if(iznosListe == iznosRacuna) return update2();
        //zabrana direktnog azuriranja (izracunaj iznos prema tabeli prilikom update-a)
        return " iznosRacuna = " + iznosRacuna + ", iznosPoreza = " + iznosPoreza;
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String atributPK() {
        return "brojRacuna";
    }

    @Override
    public String vrednostZaDelete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String koloneZaSelect() {
        return "*";
    }

    public int getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(int brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Date getDatumVremeRac() {
        return datumVremeRac;
    }

    public void setDatumVremeRac(Date datumVremeRac) {
        this.datumVremeRac = datumVremeRac;
    }

    public double getIznosRacuna() {
        return iznosRacuna;
    }

    public void setIznosRacuna(double iznosRacuna) {
        this.iznosRacuna = iznosRacuna;
    }

    public String getPoreskaOznaka() {
        return poreskaOznaka;
    }

    public void setPoreskaOznaka(String poreskaOznaka) {
        this.poreskaOznaka = poreskaOznaka;
    }

    public double getIznosPoreza() {
        return iznosPoreza;
    }

    public void setIznosPoreza(double iznosPoreza) {
        this.iznosPoreza = iznosPoreza;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    private String update2() {

        return  " iznosPoreza = " + iznosPoreza;
    }

}
