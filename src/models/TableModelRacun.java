/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Racun;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelRacun extends AbstractTableModel implements Runnable {

    private ArrayList<Racun> lista;
    private String[] kolone = {"Broj racuna", "Datum i vreme racuna", "Iznos racuna",
        "Poreska oznaka", "Iznos poreza", "Zaposleni"};

    public TableModelRacun() {
        try {
            lista = ClientController.getInstance().getAllRacun();
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
        Racun rac = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rac.getBrojRacuna();
            case 1:
                return rac.getDatumVremeRac();
            case 2:
                return rac.getIznosRacuna();
            case 3:
                return rac.getPoreskaOznaka();
            case 4:
                return rac.getIznosPoreza();
            case 5:
                return rac.getZaposleni();

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
            lista = ClientController.getInstance().getAllRacun();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelRacun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Racun getRacun(int selectedRow) {
        return lista.get(selectedRow);
    }

}
