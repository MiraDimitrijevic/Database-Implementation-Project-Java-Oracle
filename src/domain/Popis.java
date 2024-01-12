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
//import java.sql.Date;

/**
 *
 * @author 38169
 */
public class Popis extends AbstractDomainObject {

    private int sifraPopisa;
    private java.util.Date datumPopisa;
    private java.util.Date vremePocetka;
    private java.util.Date vremeZavrsetka;
    private double ukupanPromet;
    private String status;
    private Zaposleni zaposleni;
    private Smena smena;

    @Override
    public String toString() {
        return "Popis{" + "sifraPopisa=" + sifraPopisa + ", datumPopisa=" + datumPopisa + ", vremePocetka=" + vremePocetka + ", vremeZavrsetka=" + vremeZavrsetka + ", ukupanPromet=" + ukupanPromet + ", status=" + status + ", zaposleni=" + zaposleni + ", smena=" + smena + '}';
    }

    public Popis() {
    }

    public Popis(int sifraPopisa, java.util.Date datumPopisa, java.util.Date vremePocetka, java.util.Date vremeZavrsetka, double ukupanPromet, String status, Zaposleni zaposleni, Smena smena) {
        this.sifraPopisa = sifraPopisa;
        this.datumPopisa = datumPopisa;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.ukupanPromet = ukupanPromet;
        this.status = status;
        this.zaposleni = zaposleni;
        this.smena = smena;
    }

    @Override
    public String nazivTabele() {
        return "Popis";
    }

    @Override
    public String alijas() {
        return "pop";
    }

    @Override
    public String join() {
        return "JOIN Zaposleni z ON (z.sifraZaposlenog = pop.sifraZaposlenog)"
                + " JOIN Smena s ON (s.sifraSmene = pop.sifraSmene)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("sifraZaposlenog"), rs.getString("imePrezime"));
            Smena s = new Smena(rs.getInt("sifraSmene"), rs.getString("nazivSmene"));
            Popis popis = new Popis(rs.getInt("sifraPopisa"), rs.getDate("datumPopisa"), rs.getTimestamp("vremePocetka"), rs.getTimestamp("vremeZavrsetka"), rs.getDouble("ukupanPromet"), rs.getString("status"), z, s);
            lista.add(popis);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(SifraPopisa, DatumPopisa, VremePocetka, UkupanPromet, Status, SifraZaposlenog, SifraSmene)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "SifraPopisa = " + sifraPopisa;
    }

    @Override
    public String vrednostiZaInsert() {
        vremePocetka = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yy. HH:mm:ss,S");

        return " " + sifraPopisa + ", '" + sdf.format(datumPopisa) + "' ,'" + sdf2.format(vremePocetka) + "' ," + ukupanPromet
                + ", '" + status + "', " + zaposleni.getSifraZapolenog() + " , " + smena.getSifraSmene();
    }

    @Override
    public String vrednostiZaUpdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yy. HH:mm:ss,S");
        vremeZavrsetka = (status.equals("Zakljucen")) ? new Date() : null;
        return "datumPopisa = '" + sdf.format(datumPopisa) + "', vremeZavrsetka = " + 
                ((status.equals("Zakljucen")) ? "'"+ sdf2.format(vremeZavrsetka) +"'" : null)
                + ", status = '" + status + "' , ukupanPromet = " + ukupanPromet;
    }

    @Override
    public String uslov() {
        return "WHERE Status = 'Nezakljucen'";
    }

    @Override
    public String atributPK() {
        return "sifraPopisa";
    }

    @Override
    public String vrednostZaDelete() {
        return "";
    }

    @Override
    public String koloneZaSelect() {
        return "*";
    }

    public int getSifraPopisa() {
        return sifraPopisa;
    }

    public void setSifraPopisa(int sifraPopisa) {
        this.sifraPopisa = sifraPopisa;
    }

    public java.util.Date getDatumPopisa() {
        return datumPopisa;
    }

    public void setDatumPopisa(java.util.Date datumPopisa) {
        this.datumPopisa = datumPopisa;
    }

    public java.util.Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(java.util.Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public java.util.Date getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(java.util.Date vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public double getUkupanPromet() {
        return ukupanPromet;
    }

    public void setUkupanPromet(double ukupanPromet) {
        this.ukupanPromet = ukupanPromet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

}
