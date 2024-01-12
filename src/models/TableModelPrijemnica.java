/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Prijemnica;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelPrijemnica extends AbstractTableModel implements Runnable {

    private ArrayList<Prijemnica> lista;
    private String[] kolone = {"Sifra", "Datum izdavanja", "Dobavljac", "Primio", "Smena",
        "Nabavna vrednost", "Prodajna vrednost", "Razlika u ceni"};

    public TableModelPrijemnica() {
        try {
            lista = ClientController.getInstance().getAllPrijemnica();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPrijemnica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prijemnica p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getSifraPrijemnice();
            case 1:
                return p.getDatumPrijema();
            case 2:
                return p.getDobavljac().getNazivDobavljaca();
            case 3:
                return p.getZaposleni().getImePrezime();
            case 4:
                return p.getSmena().getNazivSmene();
            case 5:
                return p.getUkupnaNabVred();
            case 6:
                return p.getUkupnaProdVred();
            case 7:
                return  p.getUkupnaRazUCeni();
            default:
                return null;
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(10000);
                refreshData();
            } catch (InterruptedException ex) {
                Logger.getLogger(TableModelPrijemnica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refreshData() {
        try {
            lista = ClientController.getInstance().getAllPrijemnica();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPrijemnica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Prijemnica getPrijemnica(int row) {
        return lista.get(row);
    }

}
