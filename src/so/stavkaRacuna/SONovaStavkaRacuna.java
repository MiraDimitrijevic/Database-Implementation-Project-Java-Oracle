/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaRacuna;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaRacuna;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SONovaStavkaRacuna extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
 if(!(ado instanceof StavkaRacuna))
            throw new Exception("Prosledjeni objekat nije instanca klase Stavka racuna");    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        int id = DBBroker.getInstance().getID(ado);
        StavkaRacuna sr = (StavkaRacuna) ado;
        sr.setBrojStavkeRac(id);
        DBBroker.getInstance().insert(sr);
    }
    
}
