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
public class StavkaPopisa extends AbstractDomainObject {

    private Popis popis;
    private int redBrojStavke;
    private double pocetnaKol;
    private Double krajnjaKol;
    private Double prodataKol;
    private Date datumPopisa;
    private Pice pice;

    public StavkaPopisa() {
    }

    public StavkaPopisa(Popis popis, int redBrojStavke, double pocetnaKol, Double krajnjaKol, Double prodataKol, Date datumPopisa, Pice pice) {
        this.popis = popis;
        this.redBrojStavke = redBrojStavke;
        this.pocetnaKol = pocetnaKol;
        this.krajnjaKol = krajnjaKol;
        this.prodataKol = prodataKol;
        this.datumPopisa = datumPopisa;
        this.pice = pice;
    }

    @Override
    public String nazivTabele() {
        return "StavkaPopisa";
    }

    @Override
    public String alijas() {
        return "sp";
    }

    @Override
    public String join() {
        return "JOIN Popis pop ON (pop.SifraPopisa = sp.SifraPopisa) "
                + "JOIN Zaposleni z ON (z.sifraZaposlenog = pop.sifraZaposlenog)"
                + " JOIN Smena s ON(s.sifraSmene = pop.sifraSmene)"
                + "JOIN Pice pice ON (pice.SifraPica = sp.SifraPica)"
                + "JOIN VrstaPica vp ON (pice.SifraVrstePica = vp.SifraVrstePica)"
                + " JOIN JedinicaMere jm ON (pice.SifraJM = jm.SifraJM)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("sifraZaposlenog"), rs.getString("imePrezime"));
            Smena s = new Smena(rs.getInt("sifraSmene"), rs.getString("nazivSmene"));
            Popis popis = new Popis(rs.getInt("sifraPopisa"), rs.getDate("datumPopisa"), rs.getTimestamp("vremePocetka"), rs.getTimestamp("vremeZavrsetka"), rs.getDouble("ukupanPromet"), rs.getString("status"), z, s);
            JedinicaMere jm = new JedinicaMere(rs.getInt("sifraJM"), rs.getString("jedinica"));
            VrstaPica vp = new VrstaPica(rs.getInt("sifraVrstePica"), rs.getString("nazivVrstePica"));
            Pice pice = new Pice(rs.getInt("sifrapica"), rs.getString("nazivPica"), rs.getInt("kolicinaNaStanju"), jm, vp, rs.getDouble("trenutnacena"), null);
            StavkaPopisa sp = new StavkaPopisa(popis, rs.getInt("redBrojStavke"), rs.getDouble("pocetnaKol"), rs.getDouble("krajnjaKol"), rs.getDouble("prodataKol"), rs.getDate("datumPopisa"), pice);
            lista.add(sp);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(SifraPopisa, RedBrojStavke, PocetnaKol, KrajnjaKol, ProdataKol, DatumPopisa, SifraPica)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "SifraPopisa = " + popis.getSifraPopisa()+ " AND RedBrojStavke = "+ redBrojStavke;
    }

    @Override
    public String vrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

        return popis.getSifraPopisa() + ", " + redBrojStavke + ", " + pocetnaKol
                + ", " + krajnjaKol + ", " + prodataKol + " , '" + sdf.format(datumPopisa) + "' , " + pice.getSifraPica();
    }

    @Override
    public String vrednostiZaUpdate() {
        if (datumPopisa.getTime() == popis.getDatumPopisa().getTime()) {
            return update2();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        return "datumPopisa = '" + sdf.format(datumPopisa) + "' , pocetnaKol = " + pocetnaKol + " , krajnjaKol = " + krajnjaKol + " , prodataKol = " + prodataKol + " , sifraPica = " + pice.getSifraPica();
    }

    @Override
    public String uslov() {
        if (popis == null) {
            System.out.println("Popis je NULL!!!");
        }
        return "WHERE sp.sifraPopisa = " + popis.getSifraPopisa();
    }

    @Override
    public String atributPK() {
        return "redBrojStavke";
    }

    @Override
    public String vrednostZaDelete() {
        return vrednostZaPrimarniKljuc();
    }

    @Override
    public String koloneZaSelect() {
        return "*";
    }

    public Popis getPopis() {
        return popis;
    }

    public void setPopis(Popis popis) {
        this.popis = popis;
    }

    public int getRedBrojStavke() {
        return redBrojStavke;
    }

    public void setRedBrojStavke(int redBrojStavke) {
        this.redBrojStavke = redBrojStavke;
    }

    public double getPocetnaKol() {
        return pocetnaKol;
    }

    public void setPocetnaKol(double pocetnaKol) {
        this.pocetnaKol = pocetnaKol;
    }

    public Double getKrajnjaKol() {
        return krajnjaKol;
    }

    public void setKrajnjaKol(Double krajnjaKol) {
        this.krajnjaKol = krajnjaKol;
    }

    public Double getProdataKol() {
        return prodataKol;
    }

    public void setProdataKol(Double prodataKol) {
        this.prodataKol = prodataKol;
    }

    public Date getDatumPopisa() {
        return datumPopisa;
    }

    public void setDatumPopisa(Date datumPopisa) {
        this.datumPopisa = datumPopisa;
    }

    public Pice getPice() {
        return pice;
    }

    public void setPice(Pice pice) {
        this.pice = pice;
    }

    private String update2() {
        return "pocetnaKol = " + pocetnaKol + " , krajnjaKol = " + krajnjaKol + " , prodataKol = " + prodataKol + " , sifraPica = " + pice.getSifraPica();
    }

}
