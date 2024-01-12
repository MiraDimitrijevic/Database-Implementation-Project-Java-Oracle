/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dobavljac;

import dbb.DBBroker;
import domain.AbstractDomainObject;
import domain.Dobavljac;
import so.AbstractSO;

/**
 *
 * @author 38169
 */
public class SONoviDObavljac extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Dobavljac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase dobavljac!");
        }
        Dobavljac d = (Dobavljac) ado;
        if (d.getNazivDobavljaca().length() == 0) {
            throw new Exception("Naziv dobavljaca ne sme biti prazan!");
        }

        if (d.getNazivDobavljaca().length() > 30) {
            throw new Exception("Naziv dobavljaca ne sme biti veci od 30 karaktera!");
        }
        if (d.getAdresaDobavljaca().getUlica().isEmpty()) {
            throw new Exception("Naziv ulice ne sme biti prazan!");
        }
        if (d.getAdresaDobavljaca().getUlica().length() > 50) {
            throw new Exception("Naziv ulice ne sme biti duzi od 50 karaktera!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
