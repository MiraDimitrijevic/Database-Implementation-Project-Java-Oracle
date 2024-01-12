/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.cena;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Cena;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOObrisiCenu extends AbstractSO{
        @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Cena)) {
            throw new Exception("Prosledjeni objekat nije klase Cena!");
        }
   

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
