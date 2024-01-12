/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vrstaPica;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.VrstaPica;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSveVrstePica extends AbstractSO{
   private ArrayList<VrstaPica> list;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof VrstaPica))
            throw new Exception("Prosledjeni objekat nije klase VrstaPica!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<VrstaPica>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<VrstaPica> getList() {
        return list;
    }

   
    
    
}
