/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.smena;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Smena;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSveSmene extends AbstractSO{
    ArrayList<Smena> list;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Smena)) 
            throw new Exception("Prosledjeni objekat nije instanca klase Smena");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Smena>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Smena> getList() {
        return list;
    }
    
    
    
}
