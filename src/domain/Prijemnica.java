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
public class Prijemnica extends AbstractDomainObject {

    private int sifraPrijemnice;
    private Date datumPrijema;
    private double ukupnaNabVred;
    private double ukupnaProdVred;
    private double ukupnaRazUCeni;
    private Zaposleni zaposleni;
    private Smena smena;
    private Otpremnica otpremnica;
    private Dobavljac dobavljac;

    @Override
    public String nazivTabele() {
        return "Prijemnica_view";
    }

    @Override
    public String alijas() {
        return "p";
    }

    public Prijemnica() {
    }

    public Prijemnica(int sifraPrijemnice, Date datumPrijema, double ukupnaNabVred, double ukupnaProdVred, double ukupnaRazUCeni, Zaposleni zaposleni, Smena smena, Otpremnica otpremnica, Dobavljac dobavljac) {
        this.sifraPrijemnice = sifraPrijemnice;
        this.datumPrijema = datumPrijema;
        this.ukupnaNabVred = ukupnaNabVred;
        this.ukupnaProdVred = ukupnaProdVred;
        this.ukupnaRazUCeni = ukupnaRazUCeni;
        this.zaposleni = zaposleni;
        this.smena = smena;
        this.otpremnica = otpremnica;
        this.dobavljac = dobavljac;
    }

    @Override
    public String join() {
        return "JOIN Zaposleni z ON (z.sifraZaposlenog = p.sifraZaposlenog) "
                + "JOIN Dobavljac d ON (d.PIB=p.PIB) "
                + "JOIN Grad g ON(g.postanskiBroj = d.postanskiBroj) "
                + "JOIN Otpremnica o ON (o.sifraOtpremnice = p.sifraOtpremnice) "
                + "JOIN Smena s ON (s.sifraSmene = p.sifraSmene) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("sifraZaposlenog"), rs.getString("imePrezime"));
            Grad g = new Grad(rs.getInt("postanskiBroj"), rs.getString("nazivGrada"));
//            Adresa a = new Adresa(rs.getString("ulica"), rs.getInt("broj"));
            Dobavljac d = new Dobavljac(rs.getInt("PIB"), rs.getString("nazivDobavljaca"), null, g);
            Otpremnica o = new Otpremnica(rs.getInt("sifraOtpremnice"), rs.getDate("datumIzdavanja"), rs.getString("izdao"), z, d);
            Smena s = new Smena(rs.getInt("sifraSmene"), rs.getString("nazivSmene"));
            Prijemnica p = new Prijemnica(rs.getInt("sifraPrijemnice"), rs.getDate("datumPrijema"),
                    rs.getDouble("ukupnaNabVred"), rs.getDouble("ukupnaProdVred"),
                    rs.getDouble("ukupnaRazUCeni"), z, s, o, d);
            lista.add(p);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
      return  "sifraPrijemnice = " +sifraPrijemnice;
    }

    @Override
    public String vrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        return sifraPrijemnice+", '"+sdf.format(datumPrijema)+"' , "+ukupnaNabVred
                +" , "+ukupnaProdVred+" , "+ukupnaRazUCeni+", "+dobavljac.getPIB()+
                " , "+zaposleni.getSifraZapolenog()+" , "+smena.getSifraSmene()+" , "+otpremnica.getSifraOtpremnice();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ukupnaNabVred = "+ukupnaNabVred+", ukupnaProdVred = "+ukupnaProdVred+
                ", ukupnaRazUCeni = "+ukupnaRazUCeni;
    }

    @Override
    public String uslov() {
        return "";
    }
    

    @Override
    public String atributPK() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostZaDelete() {
return vrednostZaPrimarniKljuc();
    }

    @Override
    public String koloneZaSelect() {
return "*";
    }

    public int getSifraPrijemnice() {
        return sifraPrijemnice;
    }

    public void setSifraPrijemnice(int sifraPrijemnice) {
        this.sifraPrijemnice = sifraPrijemnice;
    }

    public Date getDatumPrijema() {
        return datumPrijema;
    }

    public void setDatumPrijema(Date datumPrijema) {
        this.datumPrijema = datumPrijema;
    }

    public double getUkupnaNabVred() {
        return ukupnaNabVred;
    }

    public void setUkupnaNabVred(double ukupnaNabVred) {
        this.ukupnaNabVred = ukupnaNabVred;
    }

    public double getUkupnaProdVred() {
        return ukupnaProdVred;
    }

    public void setUkupnaProdVred(double ukupnaProdVred) {
        this.ukupnaProdVred = ukupnaProdVred;
    }

    public double getUkupnaRazUCeni() {
        return ukupnaRazUCeni;
    }

    public void setUkupnaRazUCeni(double ukupnaRazUCeni) {
        this.ukupnaRazUCeni = ukupnaRazUCeni;
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

    public Otpremnica getOtpremnica() {
        return otpremnica;
    }

    public void setOtpremnica(Otpremnica otpremnica) {
        this.otpremnica = otpremnica;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

}
