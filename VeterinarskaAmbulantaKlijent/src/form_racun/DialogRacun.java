package form_racun;

import controller.ClientController;
import domain.Racun;
import domain.StavkaRacuna;
import domain.Usluga;
import domain.VlasnikZivotinje;
import domain.Zaposleni;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import models.TableModelStavkeRacuna;
import session.Session;

/**
 *
 * @author Kosta
 */
public class DialogRacun extends javax.swing.JDialog {

    private Racun racun;
    private boolean saved;
    private final TableModelStavkeRacuna stavkeModel = new TableModelStavkeRacuna();

    public DialogRacun(java.awt.Frame owner, Racun racun) {
        super(owner, racun == null ? "Novi račun" : "Detalji računa #" + racun.getIdRacun(), true);
        this.racun = racun;
        initComponents();
        spnDatum.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        spnDatum.setEditor(new JSpinner.DateEditor(spnDatum, "dd.MM.yyyy HH:mm"));
        spnKolicina.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        tableStavke.setModel(stavkeModel);
        podesiTabeluStavki();
        lblUkupno.setFont(lblUkupno.getFont().deriveFont(Font.BOLD, 16f));
        if (racun != null) {
            btnSave.setText("Sačuvaj izmene");
        }
        getRootPane().setDefaultButton(btnSave);
        setSize(900, 650);
        setLocationRelativeTo(owner);
        ucitajListe();
        popuniPolja();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDatum = new javax.swing.JLabel();
        spnDatum = new javax.swing.JSpinner();
        lblZaposleni = new javax.swing.JLabel();
        cmbZaposleni = new javax.swing.JComboBox<>();
        lblVlasnik = new javax.swing.JLabel();
        cmbVlasnik = new javax.swing.JComboBox<>();
        pnlStavke = new javax.swing.JPanel();
        lblUsluga = new javax.swing.JLabel();
        cmbUsluga = new javax.swing.JComboBox<>();
        lblKolicina = new javax.swing.JLabel();
        spnKolicina = new javax.swing.JSpinner();
        btnDodajStavku = new javax.swing.JButton();
        btnUkloniStavku = new javax.swing.JButton();
        scrollStavke = new javax.swing.JScrollPane();
        tableStavke = new javax.swing.JTable();
        lblUkupnoTekst = new javax.swing.JLabel();
        lblUkupno = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblDatum.setText("Datum izdavanja:");

        lblZaposleni.setText("Zaposleni:");

        lblVlasnik.setText("Vlasnik životinje:");

        pnlStavke.setBorder(javax.swing.BorderFactory.createTitledBorder("Stavke računa"));

        lblUsluga.setText("Usluga:");

        lblKolicina.setText("Količina:");

        btnDodajStavku.setText("Dodaj stavku");
        btnDodajStavku.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnDodajStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajStavkuActionPerformed(evt);
            }
        });

        btnUkloniStavku.setText("Ukloni stavku");
        btnUkloniStavku.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnUkloniStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUkloniStavkuActionPerformed(evt);
            }
        });

        tableStavke.setFillsViewportHeight(true);
        tableStavke.setRowHeight(24);
        tableStavke.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableStavke.getTableHeader().setReorderingAllowed(false);
        scrollStavke.setViewportView(tableStavke);

        lblUkupnoTekst.setText("Ukupno:");

        lblUkupno.setText("0.00 RSD");

        javax.swing.GroupLayout pnlStavkeLayout = new javax.swing.GroupLayout(pnlStavke);
        pnlStavke.setLayout(pnlStavkeLayout);
        pnlStavkeLayout.setHorizontalGroup(
            pnlStavkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStavkeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlStavkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollStavke)
                    .addGroup(pnlStavkeLayout.createSequentialGroup()
                        .addComponent(lblUsluga)
                        .addGap(8, 8, 8)
                        .addComponent(cmbUsluga, 0, 260, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(lblKolicina)
                        .addGap(8, 8, 8)
                        .addComponent(spnKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btnDodajStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnUkloniStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStavkeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblUkupnoTekst)
                        .addGap(8, 8, 8)
                        .addComponent(lblUkupno)))
                .addGap(12, 12, 12))
        );
        pnlStavkeLayout.setVerticalGroup(
            pnlStavkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStavkeLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlStavkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsluga)
                    .addComponent(cmbUsluga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKolicina)
                    .addComponent(spnKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDodajStavku)
                    .addComponent(btnUkloniStavku))
                .addGap(10, 10, 10)
                .addComponent(scrollStavke, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(pnlStavkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUkupnoTekst)
                    .addComponent(lblUkupno))
                .addGap(10, 10, 10))
        );

        btnSave.setText("Sačuvaj račun");
        btnSave.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("Odustani");
        btnCancel.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDatum)
                            .addComponent(lblZaposleni)
                            .addComponent(lblVlasnik))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbZaposleni, 0, 460, Short.MAX_VALUE)
                            .addComponent(cmbVlasnik, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(pnlStavke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatum)
                    .addComponent(spnDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblZaposleni)
                    .addComponent(cmbZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVlasnik)
                    .addComponent(cmbVlasnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(pnlStavke, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        boolean novo = racun == null || racun.getIdRacun() == 0;
        try {
            Object izabraniVlasnik = cmbVlasnik.getSelectedItem();
            if (!(izabraniVlasnik instanceof VlasnikZivotinje)) {
                JOptionPane.showMessageDialog(this, "Izaberite vlasnika životinje.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            ArrayList<StavkaRacuna> spisakStavki = new ArrayList<>();
            java.util.HashSet<Long> vidjeneUsluge = new java.util.HashSet<>();
            for (StavkaRacuna stavka : stavkeModel.getData()) {
                long idUsluga = stavka.getUsluga().getIdUsluga();
                if (!vidjeneUsluge.add(idUsluga)) {
                    JOptionPane.showMessageDialog(this,
                            (novo ? "Sistem ne može da ubaci račun." : "Sistem ne može da zapamti račun.")
                                    + "\nIsta usluga se ne sme pojaviti u dve odvojene stavke.",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                StavkaRacuna novaStavka = new StavkaRacuna(null, stavka.getRb(),
                        stavka.getUsluga().getCenaUsluge(), stavka.getKolicina(), 0, stavka.getUsluga());
                novaStavka.preracunajIznos();
                spisakStavki.add(novaStavka);
            }
            long idRacun = novo ? 0 : racun.getIdRacun();
            Racun izmenjeniRacun = new Racun(idRacun, (Date) spnDatum.getValue(), 0,
                    (Zaposleni) cmbZaposleni.getSelectedItem(),
                    (VlasnikZivotinje) izabraniVlasnik, spisakStavki);
            izmenjeniRacun.preracunajUkupanIznos();
            if (novo) {
                ClientController.getInstance().addRacun(izmenjeniRacun);
            } else {
                ClientController.getInstance().updateRacun(izmenjeniRacun);
            }
            racun = izmenjeniRacun;
            JOptionPane.showMessageDialog(this,
                    novo ? "Sistem je ubacio račun." : "Sistem je zapamtio račun.",
                    "Informacija", JOptionPane.INFORMATION_MESSAGE);
            saved = true;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    (novo ? "Sistem ne može da ubaci račun.\n" : "Sistem ne može da zapamti račun.\n") + e.getMessage(),
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDodajStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajStavkuActionPerformed
        dodajStavku();
    }//GEN-LAST:event_btnDodajStavkuActionPerformed

    private void btnUkloniStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUkloniStavkuActionPerformed
        ukloniStavku();
    }//GEN-LAST:event_btnUkloniStavkuActionPerformed

    private void podesiTabeluStavki() {
        tableStavke.getColumnModel().getColumn(0).setPreferredWidth(45);
        tableStavke.getColumnModel().getColumn(0).setMaxWidth(60);
        tableStavke.getColumnModel().getColumn(1).setPreferredWidth(320);
        tableStavke.getColumnModel().getColumn(2).setPreferredWidth(120);
        tableStavke.getColumnModel().getColumn(3).setPreferredWidth(80);
        tableStavke.getColumnModel().getColumn(4).setPreferredWidth(120);
        centrirajKoloneStavki(0, 2, 3, 4);
    }

    private void centrirajKoloneStavki(int... kolone) {
        javax.swing.table.DefaultTableCellRenderer renderer = new javax.swing.table.DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        for (int kolona : kolone) {
            if (kolona < tableStavke.getColumnCount()) {
                tableStavke.getColumnModel().getColumn(kolona).setCellRenderer(renderer);
            }
        }
    }

    private void ucitajListe() {
        try {
            for (Zaposleni zaposleni : ClientController.getInstance().getAllZaposleni()) {
                cmbZaposleni.addItem(zaposleni);
            }
            if (racun == null) {
                cmbVlasnik.addItem("Izaberite vlasnika...");
            }
            for (VlasnikZivotinje vlasnik : ClientController.getInstance().getAllVlasnik()) {
                cmbVlasnik.addItem(vlasnik);
            }
            for (Usluga usluga : ClientController.getInstance().getAllUsluga()) {
                cmbUsluga.addItem(usluga);
            }
            if (racun == null && Session.getInstance().getUlogovani() != null) {
                cmbZaposleni.setSelectedItem(Session.getInstance().getUlogovani());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void popuniPolja() {
        if (racun == null) {
            return;
        }
        spnDatum.setValue(racun.getDatumIzdavanja());
        cmbZaposleni.setSelectedItem(racun.getZaposleni());
        cmbVlasnik.setSelectedItem(racun.getVlasnikZivotinje());
        stavkeModel.setData(new ArrayList<>(racun.getStavkeRacuna()));
        osveziUkupanIznos();
    }

    private void dodajStavku() {
        Usluga usluga = (Usluga) cmbUsluga.getSelectedItem();
        if (usluga == null) {
            return;
        }
        int kolicina = ((Number) spnKolicina.getValue()).intValue();

        int postojeciRed = stavkeModel.nadjiRedSaUslugom(usluga.getIdUsluga());
        if (postojeciRed >= 0) {
            
            StavkaRacuna postojeca = stavkeModel.get(postojeciRed);
            postojeca.setKolicina(postojeca.getKolicina() + kolicina);
            postojeca.preracunajIznos();
            stavkeModel.osveziRed(postojeciRed);
        } else {
            int noviRb = stavkeModel.sledeciSlobodniRb();
            StavkaRacuna stavka = new StavkaRacuna(racun, noviRb,
                    usluga.getCenaUsluge(), kolicina, usluga.getCenaUsluge() * kolicina, usluga);
            stavka.preracunajIznos();
            stavkeModel.add(stavka);
        }
        osveziUkupanIznos();
    }

    private void ukloniStavku() {
        int red = tableStavke.getSelectedRow();
        if (red < 0) {
            JOptionPane.showMessageDialog(this, "Izaberite stavku koju želite da uklonite.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        stavkeModel.remove(tableStavke.convertRowIndexToModel(red));
        osveziUkupanIznos();
    }

    private void osveziUkupanIznos() {
        double ukupno = 0;
        for (StavkaRacuna stavka : stavkeModel.getData()) {
            ukupno += stavka.getIznosStavke();
        }
        lblUkupno.setText(String.format("%.2f RSD", ukupno));
    }

    public boolean isSaved() {
        return saved;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDodajStavku;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUkloniStavku;
    private javax.swing.JComboBox<Usluga> cmbUsluga;
    private javax.swing.JComboBox<Object> cmbVlasnik;
    private javax.swing.JComboBox<Zaposleni> cmbZaposleni;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JLabel lblKolicina;
    private javax.swing.JLabel lblUkupno;
    private javax.swing.JLabel lblUkupnoTekst;
    private javax.swing.JLabel lblUsluga;
    private javax.swing.JLabel lblVlasnik;
    private javax.swing.JLabel lblZaposleni;
    private javax.swing.JPanel pnlStavke;
    private javax.swing.JScrollPane scrollStavke;
    private javax.swing.JSpinner spnDatum;
    private javax.swing.JSpinner spnKolicina;
    private javax.swing.JTable tableStavke;
    // End of variables declaration//GEN-END:variables
}
