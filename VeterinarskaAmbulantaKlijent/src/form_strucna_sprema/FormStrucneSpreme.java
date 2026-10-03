package form_strucna_sprema;

import controller.ClientController;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import models.TableModelStrucneSpreme;

/**
 * Forma za rad sa stručnim spremama. U realizovanom obimu aplikacije
 * stručna sprema je jednostavan šifarnik: forma prikazuje postojeću listu
 * (GET_ALL_STRUCNA_SPREMA) i omogućava dodavanje nove (ADD_STRUCNA_SPREMA),
 * bez zasebne pretrage, izmene ili brisanja (SK21).
 *
 * @author Kosta
 */
public class FormStrucneSpreme extends javax.swing.JFrame {

    private final TableModelStrucneSpreme model = new TableModelStrucneSpreme();

    public FormStrucneSpreme() {
        initComponents();
        setTitle("Stručne spreme");
        table.setModel(model);
        podesiTabelu();
        centrirajKolone(0, 2);
        setLocationRelativeTo(null);
        ucitajSve();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTable = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(btnNew)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        DialogStrucnaSprema dijalog = new DialogStrucnaSprema(this, null);
        dijalog.setVisible(true);
        if (dijalog.isSaved()) {
            ucitajSve();
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void ucitajSve() {
        try {
            model.setData(ClientController.getInstance().getAllStrucnaSprema());
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
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNew;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
