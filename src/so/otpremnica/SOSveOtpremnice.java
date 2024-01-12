/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.otpremnica;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Otpremnica;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SOSveOtpremnice extends AbstractSO{
private ArrayList<Otpremnica> list;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
  if (!(ado instanceof Otpremnica)) {
            throw new Exception("Prosledjeni objekat nije klase otpremnica!");
        }    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        list = (ArrayList<Otpremnica>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Otpremnica> getList() {
        return list;
    }
    
    
}
