/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Otpremnica;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 38169
 */
public class TableModelOtpremnica extends AbstractTableModel implements Runnable{
    
    private ArrayList<Otpremnica> lista;
    private String[] kolone = {"Sifra Otpremnice", "Datum izdavanja","Dobavljac", "Izdao",
        "Primio"};

    public TableModelOtpremnica() {
        try {
            lista = ClientController.getInstance().getAllOtpremnica();
        } catch (Exception ex) {
            Logger.getLogger(TableModelOtpremnica.class.getName()).log(Level.SEVERE, null, ex);
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
        Otpremnica o = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return o.getSifraOtpremnice();
            case 1:
                return o.getDatumIzdavanja();
            case 2:
                return o.getDobavljac().getNazivDobavljaca();
            case 3:
                return o.getIzdao();
            case 4:
                return o.getZaposleni().getImePrezime();
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
                Logger.getLogger(TableModelOtpremnica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refreshData() {
        try {
            lista = ClientController.getInstance().getAllOtpremnica();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelRacun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Otpremnica getOtpremnica(int row) {
        return lista.get(row);
    }

}
