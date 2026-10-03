package models;

import domain.VlasnikZivotinje;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kosta
 */
public class TableModelVlasnici extends AbstractTableModel {

    private List<VlasnikZivotinje> data = new ArrayList<>();
    private final String[] kolone = {"ID", "Ime", "Prezime", "Mesto"};

    public void setData(List<VlasnikZivotinje> data) {
        this.data = data == null ? new ArrayList<>() : data;
        fireTableDataChanged();
    }

    public VlasnikZivotinje get(int red) {
        return data.get(red);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int kolona) {
        return kolone[kolona];
    }

    @Override
    public Object getValueAt(int red, int kolona) {
        VlasnikZivotinje vlasnik = data.get(red);
        switch (kolona) {
            case 0:
                return vlasnik.getIdVlasnik();
            case 1:
                return vlasnik.getIme();
            case 2:
                return vlasnik.getPrezime();
            default:
                return vlasnik.getMesto();
        }
    }
}
