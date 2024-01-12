/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dobavljac;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Dobavljac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSviDobavljaci extends AbstractSO{
       private ArrayList<Dobavljac> list;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Dobavljac)) throw new Exception("Prosledjeni objekat nije instanca klase dobavljac!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Dobavljac>)(ArrayList<?>)DBBroker.getInstance().select(ado);
    }

    public ArrayList<Dobavljac> getList() {
        return list;
    }
    
}
