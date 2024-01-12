/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.jm;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.JedinicaMere;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSveJM extends AbstractSO{
    
private ArrayList<JedinicaMere> list; 
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof JedinicaMere))
            throw new Exception("Prosledjeni objekat nije instanca klase JedinicaMere!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list=  (ArrayList<JedinicaMere>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<JedinicaMere> getList() {
        return list;
    }
    
    
    
}
