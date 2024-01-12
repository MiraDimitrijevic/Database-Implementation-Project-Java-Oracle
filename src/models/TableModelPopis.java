/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Popis;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelPopis extends AbstractTableModel implements Runnable{

    private ArrayList<Popis> lista;
    private String[] kolone = {"Sifra popisa", "Datum popisa", "Ukupan promet", "Status",
         "Zaposleni", "Smena"};

    public TableModelPopis() {
        try {
            lista = ClientController.getInstance().getAllPopis();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPopis.class.getName()).log(Level.SEVERE, null, ex);
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
        Popis popis = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return popis.getSifraPopisa();
            case 1:
                return popis.getDatumPopisa();
            case 2:
                return popis.getUkupanPromet();
            case 3:
                return popis.getStatus();
            case 4:
                return popis.getZaposleni().getImePrezime();
            case 5:
                return popis.getSmena().getNazivSmene();

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
            lista = ClientController.getInstance().getAllPopis();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPopis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Popis getPopis(int selectedRow) {
        return lista.get(selectedRow);
    }

}
