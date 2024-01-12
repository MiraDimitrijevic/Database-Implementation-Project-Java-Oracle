/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pice;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Pice;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SONovoPice extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Pice)) {
            throw new Exception("Prosledjeni objekat nije klase pice!");
        }
        Pice pice = (Pice) ado;
        if (pice.getNazivPica().length() > 40) {
            throw new Exception("Naziv pica ne moze biti duzi od 40 karaktera!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Pice pice = (Pice) ado;
        pice.setSifraPica(DBBroker.getInstance().getID(ado));
        DBBroker.getInstance().insert(pice);
    }

}
