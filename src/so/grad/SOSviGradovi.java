/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.grad;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Grad;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSviGradovi extends AbstractSO{
   private ArrayList<Grad> list;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Grad)) throw new Exception("Prosledjeni objekat nije instanca klase grad!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Grad>)(ArrayList<?>)DBBroker.getInstance().select(ado);
    }

    public ArrayList<Grad> getList() {
        return list;
    }
    
    
}
