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
public class SOObrisiStavkuRacuna extends AbstractSO{
        @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
 if(!(ado instanceof StavkaRacuna))
            throw new Exception("Prosledjeni objekat nije instanca klase Stavka racuna");    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
