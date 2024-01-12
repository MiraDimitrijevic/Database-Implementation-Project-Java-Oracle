/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencija;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.TipEvidencije;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOObrisiEvidencije extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
         if(!(ado instanceof TipEvidencije))
            throw new Exception("Prosledjeni objekat nije tipa TipEvidencije!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
