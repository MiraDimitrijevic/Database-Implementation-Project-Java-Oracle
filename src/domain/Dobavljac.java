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
public class Dobavljac extends AbstractDomainObject {

    private int PIB;
    private String nazivDobavljaca;
    private Adresa adresaDobavljaca;
    private Grad grad;

    public Dobavljac(int PIB, String nazivDobavljaca, Adresa adresaDobavljaca, Grad grad) {
        this.PIB = PIB;
        this.nazivDobavljaca = nazivDobavljaca;
        this.adresaDobavljaca = adresaDobavljaca;
        this.grad = grad;
    }

    public Dobavljac() {
    }

    @Override
    public String nazivTabele() {
        return "Dobavljac";
    }

    @Override
    public String alijas() {
        return "d";
    }

    @Override
    public String join() {
        return "JOIN Grad g ON(g.postanskiBroj = d.postanskiBroj)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Grad g = new Grad(rs.getInt("postanskiBroj"), rs.getString("nazivGrada"));
            Adresa a = new Adresa(rs.getString("ulica"), rs.getInt("broj"));
            Dobavljac d = new Dobavljac(rs.getInt("PIB"), rs.getString("nazivDobavljaca"), a, g);
            lista.add(d);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(PIB, NazivDobavljaca, AdresaDobavljaca, PostanskiBroj)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "PIB = " + PIB;
    }

    @Override
    public String vrednostiZaInsert() {
        return PIB + ", '" + nazivDobavljaca + "', Adresa('" + adresaDobavljaca.getUlica() + "', " + adresaDobavljaca.getBroj() + ")," + grad.getPostanskiBrpj();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "PIB = " + PIB + ", NazivDobavljaca = '" + nazivDobavljaca + "' ,  AdresaDobavljaca=Adresa('" + adresaDobavljaca.getUlica() + "', "
                + adresaDobavljaca.getBroj() + ") , PostanskiBroj = " + grad.getPostanskiBrpj();
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String atributPK() {
        return "PIB";
    }

    @Override
    public String vrednostZaDelete() {
        return " AdresaDobavljaca = Adresa('" + adresaDobavljaca.getUlica() + "', "
                + adresaDobavljaca.getBroj() + ")";
    }

    @Override
    public String koloneZaSelect() {
        return " d.PIB, "
                + "d.NazivDobavljaca, "
                + "d. AdresaDobavljaca.get_ulica() \"Ulica\", "
                + "d. AdresaDobavljaca.get_broj() \"Broj\", "
                + "g.NazivGrada,"
                + "g.PostanskiBroj";
    }

    public int getPIB() {
        return PIB;
    }

    public void setPIB(int PIB) {
        this.PIB = PIB;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public Adresa getAdresaDobavljaca() {
        return adresaDobavljaca;
    }

    public void setAdresaDobavljaca(Adresa adresaDobavljaca) {
        this.adresaDobavljaca = adresaDobavljaca;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

}
