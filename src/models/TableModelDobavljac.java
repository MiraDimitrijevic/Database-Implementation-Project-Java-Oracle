/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Dobavljac;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelDobavljac extends AbstractTableModel implements Runnable{

    private ArrayList<Dobavljac> lista;
    private String[] kolone = {"PIB", "Naziv", "Ulica", "Broj", "Grad"};

    public TableModelDobavljac() {
        try {
            lista = ClientController.getInstance().getAllDobavljac();
        } catch (Exception ex) {
            Logger.getLogger(TableModelEvidencija.class.getName()).log(Level.SEVERE, null, ex);
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
        Dobavljac d = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return d.getPIB();
            case 1:
                return d.getNazivDobavljaca();
            case 2:
                return d.getAdresaDobavljaca().getUlica();
            case 3:
                return d.getAdresaDobavljaca().getBroj();
            case 4:
                return d.getGrad().getNazivGrada();

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
                Logger.getLogger(TableModelCena.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refreshData() {
        try {
            lista = ClientController.getInstance().getAllDobavljac();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelEvidencija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Dobavljac getDobavljac(int selectedRow) {
        return lista.get(selectedRow);
    }
}
