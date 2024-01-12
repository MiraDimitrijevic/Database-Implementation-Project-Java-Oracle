/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zaposleni;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSviZaposleni extends AbstractSO{
        ArrayList<Zaposleni> list;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Zaposleni)) 
            throw new Exception("Prosledjeni objekat nije instanca klase Smena");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Zaposleni> getList() {
        return list;
    }
}
