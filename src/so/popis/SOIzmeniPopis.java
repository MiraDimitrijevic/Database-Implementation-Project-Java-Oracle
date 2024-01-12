/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.popis;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Popis;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOIzmeniPopis extends AbstractSO{
     @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
           if(!(ado instanceof Popis))
            throw new Exception("Prosledjeni objekat nije klase popis!");
              Popis pop= (Popis) ado;
              if(pop.getUkupanPromet()<0)
            throw new Exception("Ukupan promet ne moze biti negativan!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
     DBBroker.getInstance().update(ado);
        System.out.println("Sistemska op iyvrsena");
    }
}
