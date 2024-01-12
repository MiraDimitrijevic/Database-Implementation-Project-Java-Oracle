/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Pice;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelPice extends AbstractTableModel implements Runnable {

    private ArrayList<Pice> lista;
    private String[] kolone = {"NazivPica", "Kolicina na stanju", "Jedinica mere", "Vrsta pica", "Aktuelna cena"};

    public TableModelPice()  {
        try {
            lista = ClientController.getInstance().getAllPice();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
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
        Pice pice = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pice.getNazivPica();
            case 1:
                return pice.getKolicinaNaStanju();
            case 2:
                return pice.getJedinicaMere().getJedinica();
            case 3:
                return pice.getVrstaPica().getNazivVrstePica();
            case 4:
                
                return pice.getTrenutnaCena() ==null ? "Cena nije definisana" : pice.getTrenutnaCena();
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
                Logger.getLogger(TableModelPice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refreshData() {
        try {
            lista= ClientController.getInstance().getAllPice();
            fireTableDataChanged();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public ArrayList<Pice> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Pice> lista) {
        this.lista = lista;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public Pice getPice(int rowSelected) {
        return lista.get(rowSelected);
    }
}
