/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaPopisa;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaPopisa;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SONovaStavkaPopisa extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
 if(!(ado instanceof StavkaPopisa))
            throw new Exception("Prosledjeni objekat nije klase StavkaPopisa!");
 StavkaPopisa sp = (StavkaPopisa) ado;
 if(sp.getPocetnaKol()<0)
            throw new Exception("Pocetna kolicina stavke popisa ne moze biti negativna!");
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        StavkaPopisa sp = (StavkaPopisa) ado;
        sp.setRedBrojStavke(DBBroker.getInstance().getID(ado));
        DBBroker.getInstance().insert(sp);
    }
    
}
