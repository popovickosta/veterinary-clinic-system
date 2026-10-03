package models;

import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kosta
 */
public class TableModelStavkeRacuna extends AbstractTableModel {

    private List<StavkaRacuna> data = new ArrayList<>();
    private final String[] kolone = {"Rb", "Usluga", "Jedinična cena", "Količina", "Iznos"};

    public void setData(List<StavkaRacuna> data) {
        this.data = data == null ? new ArrayList<>() : data;
        fireTableDataChanged();
    }

    public StavkaRacuna get(int red) {
        return data.get(red);
    }

    public List<StavkaRacuna> getData() {
        return data;
    }

    public void add(StavkaRacuna stavka) {
        data.add(stavka);
        fireTableDataChanged();
    }

    public void remove(int red) {
        data.remove(red);
        fireTableDataChanged();
    }

    public void osveziRed(int red) {
        fireTableRowsUpdated(red, red);
    }

    public int sledeciSlobodniRb() {
        int max = 0;
        for (StavkaRacuna s : data) {
            if (s.getRb() > max) {
                max = s.getRb();
            }
        }
        return max + 1;
    }

    public int nadjiRedSaUslugom(long idUsluga) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getUsluga() != null && data.get(i).getUsluga().getIdUsluga() == idUsluga) {
                return i;
            }
        }
        return -1;
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
        StavkaRacuna stavka = data.get(red);
        switch (kolona) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getUsluga().getNazivUsluge();
            case 2:
                return String.format("%.2f", stavka.getJedinicnaCena());
            case 3:
                return stavka.getKolicina();
            default:
                return String.format("%.2f", stavka.getIznosStavke());
        }
    }
}
