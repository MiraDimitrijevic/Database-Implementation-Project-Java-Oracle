/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Cena;
import domain.Dobavljac;
import domain.Grad;
import domain.JedinicaMere;
import domain.Otpremnica;
import domain.Pice;
import domain.Popis;
import domain.Prijemnica;
import domain.Racun;
import domain.Smena;
import domain.StavkaPopisa;
import domain.StavkaRacuna;
import domain.TipEvidencije;
import domain.VrstaPica;
import domain.Zaposleni;
import java.util.ArrayList;
import so.cena.SOCenePoKriterijumu;
import so.cena.SOIzmeniCenu;
import so.cena.SONovaCena;
import so.cena.SOObrisiCenu;
import so.cena.SOSveCene;
import so.dobavljac.SOIzmeniDobavljaca;
import so.dobavljac.SONoviDObavljac;
import so.dobavljac.SOObrisiDobavljaca;
import so.dobavljac.SOSviDobavljaci;
import so.evidencija.SOIzmeniEvidenciju;
import so.evidencija.SONovaEvidencija;
import so.evidencija.SOObrisiEvidencije;
import so.evidencija.SOSveEvidencije;
import so.grad.SOSviGradovi;
import so.jm.SOSveJM;
import so.otpremnica.SOSveOtpremnice;
import so.pice.SOIzmeniPice;
import so.pice.SONovoPice;
import so.pice.SOSvaPica;
import so.popis.SOIzmeniPopis;
import so.popis.SONoviPopis;
import so.popis.SOSviPopisi;
import so.prijemnica.SOIzmeniPrijemnicu;
import so.prijemnica.SONovaPrijemnica;
import so.prijemnica.SOObrisiPrijemnicu;
import so.prijemnica.SOsvePrijemnice;
import so.racun.SOIzmeniRacun;
import so.racun.SONoviRacun;
import so.racun.SOSviRacuni;
import so.smena.SOSveSmene;
import so.stavkaPopisa.SOIzmeniStavkuPopisa;
import so.stavkaPopisa.SONovaStavkaPopisa;
import so.stavkaPopisa.SOObrisiStavkuPopisa;
import so.stavkaPopisa.SOSveStavkePopisa;
import so.stavkaRacuna.SOIzmeniStavkuRacuna;
import so.stavkaRacuna.SONovaStavkaRacuna;
import so.stavkaRacuna.SOObrisiStavkuRacuna;
import so.stavkaRacuna.SOSveStavkeRacuna;
import so.vrstaPica.SOSveVrstePica;
import so.zaposleni.SOSviZaposleni;

/**
 *
 * @author 38169
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addPice(Pice pice) throws Exception {
        (new SONovoPice()).templateExecute(pice);
    }

    public ArrayList<JedinicaMere> allJM() throws Exception {
        SOSveJM so = new SOSveJM();
        so.templateExecute(new JedinicaMere());
        for (JedinicaMere jedinicaMere : so.getList()) {
            System.out.println("jedinica " + jedinicaMere.getJedinica());
        }
        return so.getList();
    }

    public ArrayList<VrstaPica> allVrstaPica() throws Exception {
        SOSveVrstePica so = new SOSveVrstePica();
        so.templateExecute(new VrstaPica());
        return so.getList();

    }

    public ArrayList<Pice> allPica() throws Exception {
        SOSvaPica so = new SOSvaPica();
        so.templateExecute(new Pice());
        return so.getList();
    }

    public ArrayList<TipEvidencije> allEvidencije() throws Exception {
        SOSveEvidencije so = new SOSveEvidencije();
        so.templateExecute(new TipEvidencije());
        return so.getList();

    }

    public void createEvidencija(TipEvidencije tipEvidencije) throws Exception {
        (new SONovaEvidencija()).templateExecute(tipEvidencije);
    }

    public void editEvidencija(TipEvidencije tipEvidencije) throws Exception {
        (new SOIzmeniEvidenciju()).templateExecute(tipEvidencije);
    }

    public void deleteEvidencija(TipEvidencije tipEvidencije) throws Exception {
        (new SOObrisiEvidencije()).templateExecute(tipEvidencije);
    }

    public ArrayList<Grad> allGrad() throws Exception {
        SOSviGradovi so = new SOSviGradovi();
        so.templateExecute(new Grad());
        return so.getList();

    }

    public ArrayList<Dobavljac> allDobavljac() throws Exception {
        SOSviDobavljaci so = new SOSviDobavljaci();
        so.templateExecute(new Dobavljac());
        return so.getList();
    }

    public void createDobavljac(Dobavljac dobavljac) throws Exception {
        (new SONoviDObavljac()).templateExecute(dobavljac);
    }

    public void editDobavljac(Dobavljac dobavljac) throws Exception {
        (new SOIzmeniDobavljaca()).templateExecute(dobavljac);
    }

    public void deleteDobavljac(Dobavljac dobavljac) throws Exception {
        (new SOObrisiDobavljaca()).templateExecute(dobavljac);
    }

    public ArrayList<Popis> allPopis() throws Exception {
        SOSviPopisi so = new SOSviPopisi();
        so.templateExecute(new Popis());
        return so.getList();
    }

    public ArrayList<StavkaPopisa> allStavkaPopisa(Popis popis) throws Exception {
        SOSveStavkePopisa so = new SOSveStavkePopisa();
        System.out.println("Server kontroler: " + popis.toString());
        so.setPopis(popis);
        so.templateExecute(new StavkaPopisa());
        return so.getList();
    }

    public void createStavkaPopisa(StavkaPopisa stavkaPopisa) throws Exception {
        (new SONovaStavkaPopisa()).templateExecute(stavkaPopisa);
    }

    public void updateStavkaPopisa(StavkaPopisa stavkaPopisa) throws Exception {
        (new SOIzmeniStavkuPopisa()).templateExecute(stavkaPopisa);
    }

    public void deleteStavkaPopisa(StavkaPopisa stavkaPopisa) throws Exception {
        (new SOObrisiStavkuPopisa()).templateExecute(stavkaPopisa);
    }

    public void updatePopis(Popis popis) throws Exception {
        (new SOIzmeniPopis()).templateExecute(popis);
    }

    public ArrayList<Smena> allSmena() throws Exception {
        SOSveSmene so = new SOSveSmene();
        so.templateExecute(new Smena());
        return so.getList();
    }

    public ArrayList<Zaposleni> allZaposleni() throws Exception {
        SOSviZaposleni so = new SOSviZaposleni();
        so.templateExecute(new Zaposleni());
        return so.getList();
    }

    public void addPopis(Popis popis) throws Exception {
        (new SONoviPopis()).templateExecute(popis);
    }

    public ArrayList<Racun> allRacun() throws Exception {
        SOSviRacuni so = new SOSviRacuni();
        so.templateExecute(new Racun());
        return so.getList();
    }

    public ArrayList<StavkaRacuna> allStavkaRacuna(Racun racun) throws Exception {
        SOSveStavkeRacuna so = new SOSveStavkeRacuna();
        so.setRacun(racun);
        so.templateExecute(new StavkaRacuna());
        return so.getList();
    }

    public void createRacun(Racun racun) throws Exception {
        (new SONoviRacun()).templateExecute(racun);
    }

    public void updateRacun(Racun racun) throws Exception {
        (new SOIzmeniRacun()).templateExecute(racun);
    }

    public void createStavkaRacuna(StavkaRacuna stavkaRacuna) throws Exception {
        (new SONovaStavkaRacuna()).templateExecute(stavkaRacuna);
    }

    public void updateStavkaRacuna(StavkaRacuna stavkaRacuna) throws Exception {
        (new SOIzmeniStavkuRacuna()).templateExecute(stavkaRacuna);
    }

    public void deleteStavkaRacuna(StavkaRacuna stavkaRacuna) throws Exception {
        (new SOObrisiStavkuRacuna()).templateExecute(stavkaRacuna);
    }

    public ArrayList<Cena> allCenaPica(Pice pice) throws Exception {
        SOSveCene so = new SOSveCene();
        so.setPice(pice);
        so.templateExecute(new Cena());
        return so.getList();
    }

    public void updatePice(Pice pice) throws Exception {
        (new SOIzmeniPice()).templateExecute(pice);
    }

    public void createCena(Cena cena) throws Exception {
        (new SONovaCena()).templateExecute(cena);
    }

    public void updateCena(Cena cena) throws Exception {
        (new SOIzmeniCenu()).templateExecute(cena);
    }

    public void deleteCena(Cena cena) throws Exception {
        (new SOObrisiCenu()).templateExecute(cena);
    }

    public ArrayList<Cena> allCenaPoKriterijumu(String kriterijum) throws Exception {
        SOCenePoKriterijumu so = new SOCenePoKriterijumu();
        so.setKriterijum(kriterijum);
        so.templateExecute(new Cena());
        return so.getList();

    }

    public ArrayList<Otpremnica> allOtpremnica() throws Exception {
        SOSveOtpremnice so = new SOSveOtpremnice();
        so.templateExecute(new Otpremnica());
        return so.getList();
    }

    public ArrayList<Prijemnica> allPrijemnica() throws Exception {
        SOsvePrijemnice so = new SOsvePrijemnice();
        so.templateExecute(new Prijemnica());
        return so.getList(); 
    }

    public void detetePrijemnica(Prijemnica prijemnica) throws Exception {
        (new SOObrisiPrijemnicu()).templateExecute(prijemnica);
    }

    public void updatePrijemnica(Prijemnica prijemnica) throws Exception {
        (new SOIzmeniPrijemnicu()).templateExecute(prijemnica);
    }

    public void addPrijemnica(Prijemnica prijemnica) throws Exception {
        (new SONovaPrijemnica()).templateExecute(prijemnica);
    }

}
