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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author 38169
 */
public class ClientController {

    private static ClientController instance;
    private Socket socket;

    public ClientController() {
        try {
            socket = new Socket("localhost", 11000);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(data, operation);
        System.out.println("Poslat zahtev");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(request);
        //   oos.flush();

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Stigao odgovor");
        Response response = (Response) ois.readObject();

        if (response.getResponseStatus() == ResponseStatus.ERROR) {
            System.out.println("Status je error");
            throw response.getException();
        }
        return response.getData();
    }

    public ArrayList<JedinicaMere> getAllJedinicaMere() throws Exception {
        return (ArrayList<JedinicaMere>) sendRequest(Operation.VRATI_JM, null);
    }

    public ArrayList<VrstaPica> getAllVrstaPica() throws Exception {

        return (ArrayList<VrstaPica>) sendRequest(Operation.VRATI_VRSTE_PICA, null);
    }

    public ArrayList<Pice> getAllPice() throws Exception {
        return (ArrayList<Pice>) sendRequest(Operation.VRATI_PICA, null);
    }

    public void updatePice(Pice pice) throws Exception {
        sendRequest(Operation.IZMENI_PICE, pice);
    }

    public ArrayList<TipEvidencije> getAllEvidencija() throws Exception {
        return (ArrayList<TipEvidencije>) sendRequest(Operation.SVE_EVIDENCIJE, null);
    }

    public void insertEvidencija(TipEvidencije tip) throws Exception {
        sendRequest(Operation.NOVA_EVIDENCIJA, tip);
    }

    public void updateEvidencija(TipEvidencije tip) throws Exception {
        sendRequest(Operation.IZMENI_EVIDENCIJU, tip);
    }

    public void deleteEvidencija(TipEvidencije tip) throws Exception {
        sendRequest(Operation.OBRISI_EVIDENCIJE, tip);
    }

    public ArrayList<Dobavljac> getAllDobavljac() throws Exception {
        return (ArrayList<Dobavljac>) sendRequest(Operation.SVI_DOBAVLJACI, null);
    }

    public ArrayList<Grad> getAllGrad() throws Exception {
        return (ArrayList<Grad>) sendRequest(Operation.SVI_GRADOVI, null);
    }

    public void insertDobavljac(Dobavljac d) throws Exception {
        sendRequest(Operation.NOVI_DOBAVLJAC, d);
    }

    public void updateDobavljac(Dobavljac d) throws Exception {
        sendRequest(Operation.IZMENI_DOBAVLJACA, d);
    }

    public void deleteDobavljac(Dobavljac d) throws Exception {
        sendRequest(Operation.OBRISI_DOBAVLJACA, d);
    }

    public ArrayList<Popis> getAllPopis() throws Exception {
        return (ArrayList<Popis>) sendRequest(Operation.SVI_POPISI, null);
    }

    public ArrayList<StavkaPopisa> getAllStavkaPopisa(Popis popis) throws Exception {
        return (ArrayList<StavkaPopisa>) sendRequest(Operation.VRATI_STAVKE_POPISA, popis);
    }

    public void insertStavkaPopisa(StavkaPopisa sp) throws Exception {
        sendRequest(Operation.NOVA_STAVKA_POPISA, sp);
    }

    public void updateStavkaPopisa(StavkaPopisa sp) throws Exception {
        sendRequest(Operation.IZMENI_STAVKU_POPISA, sp);
    }

    public void deleteStavkaPopisa(StavkaPopisa sp) throws Exception {
        sendRequest(Operation.OBRISI_STAVKU_POPISA, sp);
    }

    public void updatePopis(Popis popis) throws Exception {
        sendRequest(Operation.IZMENI_POPIS, popis);
    }

    public ArrayList<Smena> getAllSmena() throws Exception {
        return (ArrayList<Smena>) sendRequest(Operation.SVE_SMENE, null);
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        return (ArrayList<Zaposleni>) sendRequest(Operation.SVI_ZAPOSLENI, null);
    }

    public void insertPopis(Popis popis) throws Exception {
        sendRequest(Operation.NOVI_POPIS, popis);
    }

    public ArrayList<Racun> getAllRacun() throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.SVI_RACUNI, null);
    }

    public ArrayList<StavkaRacuna> getAllStavkaRacuna(Racun racun) throws Exception {
        return (ArrayList<StavkaRacuna>) sendRequest(Operation.VRATI_STAVKE_RACUNA, racun);

    }

    public void insertRacun(Racun racun) throws Exception {
        sendRequest(Operation.NOVI_RACUN, racun);
    }

    public void updateRacun(Racun racun) throws Exception {
        sendRequest(Operation.IZMENI_RACUN, racun);

    }

    public void insertStavkaRacuna(StavkaRacuna sr) throws Exception {
        sendRequest(Operation.NOVA_STAVKA_RACUNA, sr);
    }

    public void updateStavkaRacuna(StavkaRacuna sr) throws Exception {
        sendRequest(Operation.IZMENI_STAVKU_RACUNA, sr);
    }

    public void deleteStavkaRacuna(StavkaRacuna sr) throws Exception {
        sendRequest(Operation.OBRISI_STAVKU_RACUNA, sr);
    }

    public void createPice(Pice pice) throws Exception {
        sendRequest(Operation.NOVO_PICE, pice);
    }

    public ArrayList<Cena> getAllCena(Pice pice) throws Exception {
        return (ArrayList<Cena>) sendRequest(Operation.SVE_CENE_PICA, pice);
    }

    public void createCena(Cena cena) throws Exception {
        sendRequest(Operation.NOVA_CENA, cena);
    }

    public void updateCena(Cena cena) throws Exception {
        sendRequest(Operation.IZMENI_CENU, cena);
    }

    public void deleteCena(Cena cena) throws Exception {
        sendRequest(Operation.OBRISI_CENU, cena);
    }

    public ArrayList<Cena> getCenePartition(String kriterijum) throws Exception {
       return (ArrayList<Cena>) sendRequest(Operation.CENE_PO_PRITERIJUMU, kriterijum);
    }

    public ArrayList<Otpremnica> getAllOtpremnica() throws Exception {
        return (ArrayList<Otpremnica>) sendRequest(Operation.SVE_OTPREMNICE, null);
    }

    public void addPrijemnica(Prijemnica p) throws Exception {
        sendRequest(Operation.NOVA_PRIJEMNICA, p);
    }

    public ArrayList<Prijemnica> getAllPrijemnica() throws Exception {
                return (ArrayList<Prijemnica>) sendRequest(Operation.SVE_PRIJEMNICE, null);

    }

    public void deletePrijemnica(Prijemnica p) throws Exception {
        sendRequest(Operation.OBRISI_PRIJEMNICU, p);
    }

    public void updatePrijemnica(Prijemnica p) throws Exception {
        sendRequest(Operation.IZMENI_PRIJEMNICU, p);
    }

}
