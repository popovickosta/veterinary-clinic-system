package models;

import domain.Usluga;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kosta
 */
public class TableModelUsluge extends AbstractTableModel {

    private List<Usluga> data = new ArrayList<>();
    private final String[] kolone = {"ID", "Naziv usluge", "Cena (RSD)", "Trajanje (min)"};

    public void setData(List<Usluga> data) {
        this.data = data == null ? new ArrayList<>() : data;
        fireTableDataChanged();
    }

    public Usluga get(int red) {
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
        Usluga usluga = data.get(red);
        switch (kolona) {
            case 0:
                return usluga.getIdUsluga();
            case 1:
                return usluga.getNazivUsluge();
            case 2:
                return String.format("%.2f", usluga.getCenaUsluge());
            default:
                return usluga.getTrajanje();
        }
    }
}
