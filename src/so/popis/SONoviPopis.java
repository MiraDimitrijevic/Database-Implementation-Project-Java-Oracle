/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.popis;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Popis;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SONoviPopis extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Popis))
            throw new Exception("Prosledjeni objekat nije instanca klase popis");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

    
}
