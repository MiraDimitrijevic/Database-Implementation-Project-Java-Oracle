/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.cena;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Cena;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOCenePoKriterijumu extends AbstractSO{
    
    private ArrayList<Cena> list;
    private String kriterijum;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Cena)) {
            throw new Exception("Prosledjeni objekat nije klase Cena!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Cena>) (ArrayList<?>) DBBroker.getInstance().selectParticion(ado, kriterijum);
    }

    public ArrayList<Cena> getList() {
        return list;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }


}
