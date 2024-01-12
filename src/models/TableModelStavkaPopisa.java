/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Popis;
import domain.StavkaPopisa;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelStavkaPopisa extends AbstractTableModel implements Runnable {
Popis popis;
    private ArrayList<StavkaPopisa> lista;
    private String[] kolone = {"Pocetna kolicina", "Krajnja kolicina", "Prodata kolicina", "Datum popisa",
        "Pice"};

    public TableModelStavkaPopisa(Popis popis) {
        try {
            this.popis = popis;
            lista = ClientController.getInstance().getAllStavkaPopisa(popis);
            
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkaPopisa.class.getName()).log(Level.SEVERE, null, ex);
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
        StavkaPopisa sp = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sp.getPocetnaKol();
            case 3:
                return sp.getDatumPopisa();
            case 1:
                return sp.getKrajnjaKol();
            case 2:
                return sp.getProdataKol();
            case 4:
                return sp.getPice().getNazivPica();
            default:
                return null;
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(10000);
                refreshData(popis);
            } catch (InterruptedException ex) {
                Logger.getLogger(TableModelCena.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refreshData(Popis popis) {
        try {
            lista = ClientController.getInstance().getAllStavkaPopisa(popis);
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkaPopisa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StavkaPopisa getStavkaPopis(int selectedRow) {
        return lista.get(selectedRow);
    }

    public void setPopis(Popis popis) {
        this.popis = popis;
    }
    
    public double getUkupanPromet(){
        double promet= 0;
        for (StavkaPopisa stavkaPopisa : lista) {
            promet+=stavkaPopisa.getProdataKol()*stavkaPopisa.getPice().getTrenutnaCena();
        }
        return promet;
    }
    
    
}
