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
public class SOSviPopisi extends AbstractSO {

    ArrayList<Popis> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Popis)) {
            throw new Exception("Prosledjeni objekat nije klase popis!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Popis>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Popis> getList() {
        return list;
    }
}
