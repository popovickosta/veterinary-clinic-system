package form_vlasnik;

import controller.ClientController;
import domain.Mesto;
import domain.VlasnikZivotinje;
import javax.swing.JOptionPane;

/**
 *
 * @author Kosta
 */
public class DialogVlasnik extends javax.swing.JDialog {

    private VlasnikZivotinje vlasnik;
    private boolean saved;

    public DialogVlasnik(java.awt.Frame owner, VlasnikZivotinje vlasnik) {
        super(owner, vlasnik == null ? "Novi vlasnik životinje" : "Izmena vlasnika životinje", true);
        this.vlasnik = vlasnik;
        initComponents();
        getRootPane().setDefaultButton(btnSave);
        setResizable(false);
        if (vlasnik != null) {
            txtIme.setText(vlasnik.getIme());
            txtPrezime.setText(vlasnik.getPrezime());
        }
        setLocationRelativeTo(owner);
        ucitajMesta();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIme = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        lblPrezime = new javax.swing.JLabel();
        txtPrezime = new javax.swing.JTextField();
        lblMesto = new javax.swing.JLabel();
        cmbMesto = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblIme.setText("Ime:");

        lblPrezime.setText("Prezime:");

        lblMesto.setText("Mesto:");

        btnSave.setText("Sačuvaj");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIme)
                    .addComponent(lblPrezime)
                    .addComponent(lblMesto))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIme, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(txtPrezime)
                    .addComponent(cmbMesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIme)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrezime)
                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMesto)
                    .addComponent(cmbMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                )
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        boolean novo = vlasnik == null;
        try {
            Mesto izabranoMesto = (Mesto) cmbMesto.getSelectedItem();
            String ime = txtIme.getText().trim();
            String prezime = txtPrezime.getText().trim();
            if (novo) {
                VlasnikZivotinje noviVlasnik = new VlasnikZivotinje(0, ime, prezime, izabranoMesto);
                ClientController.getInstance().addVlasnik(noviVlasnik);
                vlasnik = noviVlasnik;
            } else {
                VlasnikZivotinje izmenjeniVlasnik = new VlasnikZivotinje(
                        vlasnik.getIdVlasnik(), ime, prezime, izabranoMesto);
                ClientController.getInstance().updateVlasnik(izmenjeniVlasnik);
                vlasnik.setIme(ime);
                vlasnik.setPrezime(prezime);
                vlasnik.setMesto(izabranoMesto);
            }
            JOptionPane.showMessageDialog(this,
                    novo ? "Sistem je ubacio vlasnika životinje." : "Sistem je zapamtio vlasnika životinje.",
                    "Informacija", JOptionPane.INFORMATION_MESSAGE);
            saved = true;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    (novo ? "Sistem ne može da ubaci vlasnika životinje.\n" : "Sistem ne može da zapamti vlasnika životinje.\n") + e.getMessage(),
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void ucitajMesta() {
        try {
            for (Mesto mesto : ClientController.getInstance().getAllMesto()) {
                cmbMesto.addItem(mesto);
            }
            if (vlasnik != null) {
                cmbMesto.setSelectedItem(vlasnik.getMesto());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isSaved() {
        return saved;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<Mesto> cmbMesto;
    private javax.swing.JLabel lblIme;
    private javax.swing.JLabel lblMesto;
    private javax.swing.JLabel lblPrezime;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtPrezime;
    // End of variables declaration//GEN-END:variables
}
