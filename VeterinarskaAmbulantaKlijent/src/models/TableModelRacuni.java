package models;

import domain.Racun;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kosta
 */
public class TableModelRacuni extends AbstractTableModel {

    private List<Racun> data = new ArrayList<>();
    private final String[] kolone = {"ID", "Datum izdavanja", "Zaposleni", "Vlasnik životinje", "Ukupan iznos"};
    private final SimpleDateFormat formatDatuma = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public void setData(List<Racun> data) {
        this.data = data == null ? new ArrayList<>() : data;
        fireTableDataChanged();
    }

    public Racun get(int red) {
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
        Racun racun = data.get(red);
        switch (kolona) {
            case 0:
                return racun.getIdRacun();
            case 1:
                return racun.getDatumIzdavanja() == null ? "" : formatDatuma.format(racun.getDatumIzdavanja());
            case 2:
                return racun.getZaposleni();
            case 3:
                return racun.getVlasnikZivotinje();
            default:
                return String.format("%.2f RSD", racun.getUkupanIznos());
        }
    }
}
