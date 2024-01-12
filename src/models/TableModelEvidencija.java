/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.TipEvidencije;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelEvidencija extends AbstractTableModel implements Runnable{
      private ArrayList<TipEvidencije> lista;
    private String[] kolone = {"Tip", "Iznos"};
    

    public TableModelEvidencija() {
          try {
              lista = ClientController.getInstance().getAllEvidencija();
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
        TipEvidencije tip = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tip.getNazivTipa();
            case 1:
                return tip.getIznos();
       
                
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
              lista= ClientController.getInstance().getAllEvidencija();
              fireTableDataChanged();
          } catch (Exception ex) {
              Logger.getLogger(TableModelEvidencija.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    public TipEvidencije getTip(int selectedRow) {
        return lista.get(selectedRow);
    }


    
}
