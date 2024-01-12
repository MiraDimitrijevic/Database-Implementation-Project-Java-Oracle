/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaRacuna;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSveStavkeRacuna extends AbstractSO {

    private ArrayList<StavkaRacuna> list;
    private Racun racun;

    public ArrayList<StavkaRacuna> getList() {
        return list;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaRacuna)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Stavka racuna");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        StavkaRacuna sr = (StavkaRacuna) ado;
        sr.setRacun(racun);
        list = (ArrayList<StavkaRacuna>) (ArrayList<?>) DBBroker.getInstance().select(sr);
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

}
