/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SONoviRacun extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
   if(!(ado instanceof Racun))
            throw new Exception("Prosledjeni objekat nije instanca klase Racun");    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        int ID = DBBroker.getInstance().getID(ado);
        Racun racun = (Racun) ado;
        racun.setBrojRacuna(ID);
        DBBroker.getInstance().insert(racun);
    }
    
}
