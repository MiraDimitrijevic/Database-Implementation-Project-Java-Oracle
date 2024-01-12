/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Cena;
import domain.Pice;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelCena extends AbstractTableModel implements Runnable {
Pice pice;
    private ArrayList<Cena> lista;
    private String[] kolone = {"DatumOd", "DatumDo", "Iznos"};
    

    public TableModelCena(Pice pice) {
        this.pice= pice;
    try {
        lista = ClientController.getInstance().getAllCena(pice);
    } catch (Exception ex) {
        Logger.getLogger(TableModelCena.class.getName()).log(Level.SEVERE, null, ex);
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
        Cena cena = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cena.getDatumOd();
            case 1:
                return cena.getDatumDo();
            case 2:
                return cena.getIznos();
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
        lista= ClientController.getInstance().getAllCena(pice);
    } catch (Exception ex) {
        Logger.getLogger(TableModelCena.class.getName()).log(Level.SEVERE, null, ex);
    }
        fireTableDataChanged();
    }

    public ArrayList<Cena> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Cena> lista) {
        this.lista = lista;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }
    
   public double getOcekivanaCena(){
       Cena aktuelna = lista.get(0);
       for (Cena cena : lista) {
           if(cena.getDatumOd().after(aktuelna.getDatumOd()))
               aktuelna = cena;
       }
       System.out.println("AKTIELNA CENA" +aktuelna.getIznos());
       return aktuelna.getIznos();
   }

    public Cena getCena(int row) {
        return lista.get(row);
    }

}
