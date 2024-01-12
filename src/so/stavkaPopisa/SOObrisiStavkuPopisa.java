/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaPopisa;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaPopisa;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOObrisiStavkuPopisa extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
         if(!(ado instanceof StavkaPopisa))
            throw new Exception("Prosledjeni objekat nije klase StavkaPopisa!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
