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
public class Cena extends AbstractDomainObject {

    private Pice pice;
    private Date datumOd;
    private Date datumDo;
    private double iznos;

    public Cena() {
    }

    public Cena(Pice pice, Date datumOd, Date datumDo, double iznos) {
        this.pice = pice;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.iznos = iznos;
    }

    @Override
    public String nazivTabele() {
        return "Cena";
    }

    @Override
    public String alijas() {
        return "c";
    }

    @Override
    public String join() {
        return "JOIN Pice pice ON (pice.SifraPica= c.SifraPica)"
                + " JOIN VrstaPica vp ON (pice.SifraVrstePica = vp.SifraVrstePica)"
                + " JOIN JedinicaMere jm ON (pice.SifraJM = jm.SifraJM)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            JedinicaMere jm = new JedinicaMere(rs.getInt("sifraJM"), rs.getString("jedinica"));
            VrstaPica vp = new VrstaPica(rs.getInt("sifraVrstePica"), rs.getString("nazivVrstePica"));
            Pice pice = new Pice(rs.getInt("sifrapica"), rs.getString("nazivPica"), rs.getInt("kolicinaNaStanju"), jm, vp, rs.getDouble("trenutnacena"), null);
            Cena cena = new Cena(pice, rs.getDate("datumOd"), rs.getDate("datumDo"), rs.getDouble("iznos"));
            lista.add(cena);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(datumod, sifraPica, datumdo, iznos)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        return "sifraPica = " + pice.getSifraPica() + " AND datumOd='"+sdf.format(datumOd)+"'";
    }

    @Override
    public String vrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        return "'" + sdf.format(datumOd) + "', " + pice.getSifraPica() + ", '" + sdf.format(datumDo) + "', " + iznos;
    }

    @Override
    public String vrednostiZaUpdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        return "datumOd = '" + sdf.format(datumOd) + "', datumDo = '" + sdf.format(datumDo) + "', iznos = " + iznos;
    }

    @Override
    public String uslov() {
        return "WHERE pice.sifraPica = " + pice.getSifraPica();
    }

    public Pice getPice() {
        return pice;
    }

    public void setPice(Pice pice) {
        this.pice = pice;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    @Override
    public String atributPK() {
        return "!";
    }

    @Override
    public String vrednostZaDelete() {
        return vrednostZaPrimarniKljuc();
    }

    @Override
    public String koloneZaSelect() {
        return "*";
    }

}
