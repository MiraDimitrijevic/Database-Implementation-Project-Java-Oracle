/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaPopisa;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Popis;
import domain.StavkaPopisa;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSveStavkePopisa extends AbstractSO{
     private ArrayList<StavkaPopisa> list;
     private Popis popis;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
           if(!(ado instanceof StavkaPopisa))
            throw new Exception("Prosledjeni objekat nije klase StavkaPopisa!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        StavkaPopisa sp = (StavkaPopisa) ado;
        sp.setPopis(popis);
        list = (ArrayList<StavkaPopisa>) (ArrayList<?>) DBBroker.getInstance().select(sp);
    }

    public ArrayList<StavkaPopisa> getList() {
        return list;
    }

    public void setPopis(Popis popis) {
        this.popis = popis;
    }
    
    
}
