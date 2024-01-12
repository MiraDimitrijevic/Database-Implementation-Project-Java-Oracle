/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 38169
 */
public class Otpremnica extends AbstractDomainObject {

    private int sifraOtpremnice;
    private Date datumIzdavanja;
    private String izdao;
    private Zaposleni zaposleni;
    private Dobavljac dobavljac;

    public Otpremnica() {
    }

    public Otpremnica(int sifraOtpremnice, Date datumIzdavanja, String izdao, Zaposleni zaposleni, Dobavljac dobavljac) {
        this.sifraOtpremnice = sifraOtpremnice;
        this.datumIzdavanja = datumIzdavanja;
        this.izdao = izdao;
        this.zaposleni = zaposleni;
        this.dobavljac = dobavljac;
    }

    @Override
    public String nazivTabele() {
        return "Otpremnica";
    }

    @Override
    public String alijas() {
        return "o";
    }

    @Override
    public String join() {
        return "JOIN Zaposleni z ON (z.sifraZaposlenog = o.sifraZaposlenog) " +
                "JOIN Dobavljac d ON (d.PIB=o.PIB) "+
                "JOIN Grad g ON(g.postanskiBroj = d.postanskiBroj)";
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
            lista.add(o);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String koloneZaSelect() {
        return "*";
    }

    public int getSifraOtpremnice() {
        return sifraOtpremnice;
    }

    public void setSifraOtpremnice(int sifraOtpremnice) {
        this.sifraOtpremnice = sifraOtpremnice;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public String getIzdao() {
        return izdao;
    }

    public void setIzdao(String izdao) {
        this.izdao = izdao;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

}
