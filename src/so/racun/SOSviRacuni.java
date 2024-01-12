/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSviRacuni extends AbstractSO{
    ArrayList<Racun> list;

    public ArrayList<Racun> getList() {
        return list;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Racun))
            throw new Exception("Prosledjeni objekat nije instanca klase Racun");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Racun>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }
    
    
}
