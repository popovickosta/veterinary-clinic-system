package models;

import domain.StrucnaSprema;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kosta
 */
public class TableModelStrucneSpreme extends AbstractTableModel {

    private List<StrucnaSprema> data = new ArrayList<>();
    private final String[] kolone = {"ID", "Naziv stručne spreme", "Stepen"};

    public void setData(List<StrucnaSprema> data) {
        this.data = data == null ? new ArrayList<>() : data;
        fireTableDataChanged();
    }

    public StrucnaSprema get(int red) {
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
        StrucnaSprema strucnaSprema = data.get(red);
        switch (kolona) {
            case 0:
                return strucnaSprema.getIdStrucnaSprema();
            case 1:
                return strucnaSprema.getNazivSS();
            default:
                return strucnaSprema.getStepenSS();
        }
    }
}
