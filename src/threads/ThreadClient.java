/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import controller.ServerController;
import domain.Cena;
import domain.Dobavljac;
import domain.Pice;
import domain.Popis;
import domain.Prijemnica;
import domain.Racun;
import domain.StavkaPopisa;
import domain.StavkaRacuna;
import domain.TipEvidencije;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
public class ThreadClient extends Thread {

    private Socket socket;

    public ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
                //  out.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, ResponseStatus.SUCCESS, null);

        try {
            switch (request.getOperation()) {
                case Operation.VRATI_JM:
                    response.setData(ServerController.getInstance().allJM());
                    break;
                case Operation.VRATI_VRSTE_PICA:
                    response.setData(ServerController.getInstance().allVrstaPica());
                    break;
                case Operation.VRATI_PICA:
                    response.setData(ServerController.getInstance().allPica());
                    break;
                case Operation.SVE_EVIDENCIJE:
                    response.setData(ServerController.getInstance().allEvidencije());
                    break;
                case Operation.NOVA_EVIDENCIJA:
                    ServerController.getInstance().createEvidencija((TipEvidencije) request.getData());
                    break;
                case Operation.IZMENI_EVIDENCIJU:
                    ServerController.getInstance().editEvidencija((TipEvidencije) request.getData());
                    break;
                case Operation.OBRISI_EVIDENCIJE:
                    ServerController.getInstance().deleteEvidencija((TipEvidencije) request.getData());
                    break;
                case Operation.SVI_GRADOVI:
                    response.setData(ServerController.getInstance().allGrad());
                    break;
                case Operation.SVI_DOBAVLJACI:
                    response.setData(ServerController.getInstance().allDobavljac());
                    break;
                case Operation.NOVI_DOBAVLJAC:
                    ServerController.getInstance().createDobavljac((Dobavljac) request.getData());
                    break;
                case Operation.IZMENI_DOBAVLJACA:
                    ServerController.getInstance().editDobavljac((Dobavljac) request.getData());
                    break;
                case Operation.OBRISI_DOBAVLJACA:
                    ServerController.getInstance().deleteDobavljac((Dobavljac) request.getData());
                    break;
                case Operation.SVI_POPISI:
                    response.setData(ServerController.getInstance().allPopis());
                    break;
                case Operation.IZMENI_POPIS:
                    ServerController.getInstance().updatePopis((Popis) request.getData());
                    break;
                case Operation.VRATI_STAVKE_POPISA:
                    response.setData(ServerController.getInstance().allStavkaPopisa((Popis) request.getData()));
                    break;
                case Operation.NOVA_STAVKA_POPISA:
                    ServerController.getInstance().createStavkaPopisa((StavkaPopisa) request.getData());
                    break;
                case Operation.IZMENI_STAVKU_POPISA:
                    ServerController.getInstance().updateStavkaPopisa((StavkaPopisa) request.getData());
                    break;
                case Operation.OBRISI_STAVKU_POPISA:
                    ServerController.getInstance().deleteStavkaPopisa((StavkaPopisa) request.getData());
                    break;
                case Operation.SVE_SMENE:
                    response.setData(ServerController.getInstance().allSmena());
                    break;
                case Operation.SVI_ZAPOSLENI:
                    response.setData(ServerController.getInstance().allZaposleni());
                    break;
                case Operation.NOVI_POPIS:
                    ServerController.getInstance().addPopis((Popis) request.getData());
                    break;
                case Operation.SVI_RACUNI:
                    response.setData(ServerController.getInstance().allRacun());
                    break;
                case Operation.VRATI_STAVKE_RACUNA:
                    response.setData(ServerController.getInstance().allStavkaRacuna((Racun) request.getData()));
                    break;
                case Operation.NOVI_RACUN:
                    ServerController.getInstance().createRacun((Racun) request.getData());
                    break;
                case Operation.IZMENI_RACUN:
                    ServerController.getInstance().updateRacun((Racun) request.getData());
                    break;
                case Operation.NOVA_STAVKA_RACUNA:
                    ServerController.getInstance().createStavkaRacuna((StavkaRacuna) request.getData());
                    break;
                case Operation.IZMENI_STAVKU_RACUNA:
                    ServerController.getInstance().updateStavkaRacuna((StavkaRacuna) request.getData());
                    break;
                case Operation.OBRISI_STAVKU_RACUNA:
                    ServerController.getInstance().deleteStavkaRacuna((StavkaRacuna) request.getData());
                    break;
                case Operation.NOVO_PICE:
                    ServerController.getInstance().addPice((Pice) request.getData());
                    break;
                case Operation.SVE_CENE_PICA:
                    response.setData(ServerController.getInstance().allCenaPica((Pice) request.getData()));
                    break;
                case Operation.IZMENI_PICE:
                    ServerController.getInstance().updatePice((Pice) request.getData());
                    break;
                case Operation.NOVA_CENA:
                    ServerController.getInstance().createCena((Cena) request.getData());
                    break;
                case Operation.IZMENI_CENU:
                    ServerController.getInstance().updateCena((Cena) request.getData());
                    break;
                case Operation.OBRISI_CENU:
                    ServerController.getInstance().deleteCena((Cena) request.getData());
                    break;
                case Operation.CENE_PO_PRITERIJUMU:
                    response.setData(ServerController.getInstance().allCenaPoKriterijumu((String) request.getData()));
                    break;
                case Operation.SVE_OTPREMNICE:
                    response.setData(ServerController.getInstance().allOtpremnica());
                    break;
                case Operation.SVE_PRIJEMNICE:
                    response.setData(ServerController.getInstance().allPrijemnica());
                    break;
                case Operation.OBRISI_PRIJEMNICU:
                    ServerController.getInstance().detetePrijemnica((Prijemnica) request.getData());
                    break;   
                case Operation.IZMENI_PRIJEMNICU:
                    ServerController.getInstance().updatePrijemnica((Prijemnica) request.getData());
                    break;  
                case Operation.NOVA_PRIJEMNICA:
                    ServerController.getInstance().addPrijemnica((Prijemnica) request.getData());
                    break;

            }
            System.out.println("Nije doslo do exc na serveru");

        } catch (Exception ex) {
            System.out.println("Desio se exception na serveru");
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            response.setException(ex);
            response.setResponseStatus(ResponseStatus.ERROR);
        }
        return response;

    }
}
