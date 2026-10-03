package form_vlasnik;

import controller.ClientController;
import domain.Mesto;
import domain.VlasnikZivotinje;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import models.TableModelVlasnici;

/**
 *
 * @author Kosta
 */
public class FormVlasnici extends javax.swing.JFrame {

    private final TableModelVlasnici model = new TableModelVlasnici();

    public FormVlasnici() {
        initComponents();
        setTitle("Vlasnici životinja");
        table.setModel(model);
        podesiTabelu();
        centrirajKolone(0);
        setLocationRelativeTo(null);
        ucitajFiltere();
        ucitajSve();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblMestoF = new javax.swing.JLabel();
        cmbMestoF = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        scrollTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblSearch.setText("Ime/prezime:");

        lblMestoF.setText("Mesto:");

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

        btnNew.setText("Dodaj");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setText("Izmeni");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Obriši");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
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
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lblMestoF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMestoF, 0, 190, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                    .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                )
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMestoF)
                    .addComponent(cmbMestoF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnAll)
                )
                .addGap(12, 12, 12)
                .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                )
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            Object izabranoMesto = cmbMestoF.getSelectedItem();
            Long idMesto = izabranoMesto instanceof Mesto ? ((Mesto) izabranoMesto).getIdMesto() : null;
            model.setData(ClientController.getInstance().searchVlasnik(txtSearch.getText(), idMesto));
            if (model.getRowCount() > 0) {
                JOptionPane.showMessageDialog(this, "Sistem je našao vlasnike životinja po zadatim kriterijumima.",
                        "Informacija", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sistem ne može da nađe vlasnike životinja po zadatim kriterijumima.",
                        "Informacija", JOptionPane.INFORMATION_MESSAGE);
            }
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
        VlasnikZivotinje izabrani = izabraniVlasnik();
        if (izabrani == null) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da nađe vlasnika životinje.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Sistem je našao vlasnika životinje.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
            otvoriDijalog(izabrani);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        VlasnikZivotinje izabrani = izabraniVlasnik();
        if (izabrani == null) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da nađe vlasnika životinje.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Sistem je našao vlasnika životinje.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
        if (JOptionPane.showConfirmDialog(this, "Da li ste sigurni da želite da obrišete vlasnika životinje?", "Potvrda",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                ClientController.getInstance().deleteVlasnik(izabrani);
                ucitajSve();
                JOptionPane.showMessageDialog(this, "Sistem je obrisao vlasnika životinje.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Sistem ne može da obriše vlasnika životinje.\n" + e.getMessage(),
                        "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private VlasnikZivotinje izabraniVlasnik() {
        int red = table.getSelectedRow();
        return red < 0 ? null : model.get(table.convertRowIndexToModel(red));
    }

    private void otvoriDijalog(VlasnikZivotinje vlasnik) {
        DialogVlasnik dijalog = new DialogVlasnik(this, vlasnik);
        dijalog.setVisible(true);
        if (dijalog.isSaved()) {
            ocistiFiltere();
            ucitajSve();
        }
    }

    private void ocistiFiltere() {
        txtSearch.setText("");
        if (cmbMestoF.getItemCount() > 0) {
            cmbMestoF.setSelectedIndex(0);
        }
    }

    private void ucitajFiltere() {
        try {
            cmbMestoF.addItem("Sva mesta");
            for (Mesto mesto : ClientController.getInstance().getAllMesto()) {
                cmbMestoF.addItem(mesto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ucitajSve() {
        try {
            model.setData(ClientController.getInstance().getAllVlasnik());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
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
        table.getColumnModel().getColumn(1).setPreferredWidth(190);
        table.getColumnModel().getColumn(2).setPreferredWidth(210);
        table.getColumnModel().getColumn(3).setPreferredWidth(240);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<Object> cmbMestoF;
    private javax.swing.JLabel lblMestoF;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
