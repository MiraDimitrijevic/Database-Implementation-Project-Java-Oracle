/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelStavkaRacuna extends AbstractTableModel implements Runnable {

    Racun racun;
    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Redni broj", "Kolicina", "Sifra pica", "Naziv pica"};

    public TableModelStavkaRacuna(Racun racun) {
        try {
            this.racun = racun;
            lista = ClientController.getInstance().getAllStavkaRacuna(racun);

        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkaRacuna.class.getName()).log(Level.SEVERE, null, ex);
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
        StavkaRacuna sr = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sr.getBrojStavkeRac();
            case 1:
                return sr.getKolicina();
            case 2:
                return sr.getPice().getSifraPica();
            case 3:
                return sr.getNazivPica();

            default:
                return null;
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(10000);
                refreshData(racun);
            } catch (InterruptedException ex) {
                Logger.getLogger(TableModelCena.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refreshData(Racun racun) {
        try {
            lista = ClientController.getInstance().getAllStavkaRacuna(racun);
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkaPopisa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StavkaRacuna getStavkaRacuna(int selectedRow) {
        return lista.get(selectedRow);
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }
    
    public double getIznos (){
        double iznos = 0;
        for (StavkaRacuna sr : lista) {
            iznos+= sr.getKolicina()*sr.getPice().getTrenutnaCena();
        }
        return iznos;
    }

}
