/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pice;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Pice;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSvaPica extends AbstractSO{
    ArrayList<Pice> list;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
           if(!(ado instanceof Pice))
            throw new Exception("Prosledjeni objekat nije klase pice!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Pice>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Pice> getList() {
        return list;
    }
    
    
}
