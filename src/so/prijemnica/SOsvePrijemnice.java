/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prijemnica;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Prijemnica;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOsvePrijemnice extends AbstractSO{
    private ArrayList<Prijemnica> list;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
  if (!(ado instanceof Prijemnica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Prijemnica!");
        }    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Prijemnica>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Prijemnica> getList() {
        return list;
    }
}
