package form_racun;

import controller.ClientController;
import domain.Racun;
import domain.Usluga;
import domain.VlasnikZivotinje;
import domain.Zaposleni;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import models.TableModelRacuni;

/**
 *
 * @author Kosta
 */
public class FormRacuni extends javax.swing.JFrame {

    private final TableModelRacuni model = new TableModelRacuni();

    public FormRacuni() {
        initComponents();
        setTitle("Računi");
        table.setModel(model);
        podesiTabelu();
        centrirajKolone(0, 4);
        setLocationRelativeTo(null);
        ucitajFiltere();
        ucitajSve();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblZaposleniF = new javax.swing.JLabel();
        cmbZaposleniF = new javax.swing.JComboBox<>();
        lblVlasnikF = new javax.swing.JLabel();
        cmbVlasnikF = new javax.swing.JComboBox<>();
        lblUslugaF = new javax.swing.JLabel();
        cmbUslugaF = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        scrollTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblId.setText("ID:");

        lblZaposleniF.setText("Zaposleni:");

        lblVlasnikF.setText("Vlasnik:");

        lblUslugaF.setText("Usluga:");

        btnSearch.setText("Pretraži");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAll.setText("Prikaži sve");
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        table.setFillsViewportHeight(true);
        table.setRowHeight(24);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        scrollTable.setViewportView(table);

        btnNew.setText("Dodaj račun");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setText("Prikaži račun");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblId)
                        .addGap(8, 8, 8)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblZaposleniF)
                        .addGap(8, 8, 8)
                        .addComponent(cmbZaposleniF, 0, 240, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addComponent(lblVlasnikF)
                        .addGap(8, 8, 8)
                        .addComponent(cmbVlasnikF, 0, 260, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUslugaF)
                        .addGap(8, 8, 8)
                        .addComponent(cmbUslugaF, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                )
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblZaposleniF)
                    .addComponent(cmbZaposleniF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVlasnikF)
                    .addComponent(cmbVlasnikF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUslugaF)
                    .addComponent(cmbUslugaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnAll))
                .addGap(12, 12, 12)
                .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnEdit))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            Long idRacuna = null;
            if (!txtId.getText().trim().isEmpty()) {
                idRacuna = Long.valueOf(txtId.getText().trim());
            }
            Object izabraniZaposleni = cmbZaposleniF.getSelectedItem();
            Object izabraniVlasnik = cmbVlasnikF.getSelectedItem();
            Object izabranaUsluga = cmbUslugaF.getSelectedItem();
            Long idZaposleni = izabraniZaposleni instanceof Zaposleni ? ((Zaposleni) izabraniZaposleni).getIdZaposleni() : null;
            Long idVlasnik = izabraniVlasnik instanceof VlasnikZivotinje ? ((VlasnikZivotinje) izabraniVlasnik).getIdVlasnik() : null;
            Long idUsluga = izabranaUsluga instanceof Usluga ? ((Usluga) izabranaUsluga).getIdUsluga() : null;
            model.setData(ClientController.getInstance().searchRacun(idRacuna, idZaposleni, idVlasnik, idUsluga));
            if (model.getRowCount() > 0) {
                JOptionPane.showMessageDialog(this, "Sistem je našao račune po zadatim kriterijumima.",
                        "Informacija", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sistem ne može da nađe račune po zadatim kriterijumima.",
                        "Informacija", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID računa mora biti broj.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        ocistiFiltere();
        ucitajSve();
    }//GEN-LAST:event_btnAllActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        otvoriDijalog(null);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Racun izabrani = izabraniRacun();
        if (izabrani == null) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da nađe račun.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Sistem je našao račun.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
            otvoriDijalog(izabrani);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private Racun izabraniRacun() {
        int red = table.getSelectedRow();
        return red < 0 ? null : model.get(table.convertRowIndexToModel(red));
    }

    private void otvoriDijalog(Racun racun) {
        try {
            DialogRacun dijalog = new DialogRacun(this, racun);
            dijalog.setVisible(true);
            if (dijalog.isSaved()) {
                ocistiFiltere();
                ucitajSve();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ucitajFiltere() {
        try {
            cmbZaposleniF.addItem("Svi zaposleni");
            for (Zaposleni zaposleni : ClientController.getInstance().getAllZaposleni()) {
                cmbZaposleniF.addItem(zaposleni);
            }
            cmbVlasnikF.addItem("Svi vlasnici");
            for (VlasnikZivotinje vlasnik : ClientController.getInstance().getAllVlasnik()) {
                cmbVlasnikF.addItem(vlasnik);
            }
            cmbUslugaF.addItem("Sve usluge");
            for (Usluga usluga : ClientController.getInstance().getAllUsluga()) {
                cmbUslugaF.addItem(usluga);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ucitajSve() {
        try {
            model.setData(ClientController.getInstance().getAllRacun());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ocistiFiltere() {
        txtId.setText("");
        if (cmbZaposleniF.getItemCount() > 0) {
            cmbZaposleniF.setSelectedIndex(0);
        }
        if (cmbVlasnikF.getItemCount() > 0) {
            cmbVlasnikF.setSelectedIndex(0);
        }
        if (cmbUslugaF.getItemCount() > 0) {
            cmbUslugaF.setSelectedIndex(0);
        }
    }

    private void centrirajKolone(int... kolone) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        for (int kolona : kolone) {
            if (kolona < table.getColumnCount()) {
                table.getColumnModel().getColumn(kolona).setCellRenderer(renderer);
            }
        }
    }

    private void podesiTabelu() {
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setPreferredWidth(210);
        table.getColumnModel().getColumn(3).setPreferredWidth(240);
        table.getColumnModel().getColumn(4).setPreferredWidth(130);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<Object> cmbUslugaF;
    private javax.swing.JComboBox<Object> cmbVlasnikF;
    private javax.swing.JComboBox<Object> cmbZaposleniF;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblUslugaF;
    private javax.swing.JLabel lblVlasnikF;
    private javax.swing.JLabel lblZaposleniF;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
