package form_usluga;

import controller.ClientController;
import domain.Usluga;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Kosta
 */
public class DialogUsluga extends javax.swing.JDialog {

    private Usluga usluga;
    private boolean saved;

    public DialogUsluga(java.awt.Frame owner, Usluga usluga) {
        super(owner, usluga == null ? "Nova usluga" : "Izmena usluge", true);
        this.usluga = usluga;
        initComponents();
        getRootPane().setDefaultButton(btnSave);
        setResizable(false);
        if (usluga != null) {
            txtNaziv.setText(usluga.getNazivUsluge());
            spnCena.setValue(usluga.getCenaUsluge());
            spnTrajanje.setValue(usluga.getTrajanje());
        }
        setLocationRelativeTo(owner);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNaziv = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        lblCena = new javax.swing.JLabel();
        spnCena = new javax.swing.JSpinner(new SpinnerNumberModel(1000.0, 0.0, 100000.0, 100.0));
        lblTrajanje = new javax.swing.JLabel();
        spnTrajanje = new javax.swing.JSpinner(new SpinnerNumberModel(30, 1, 1440, 5));
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNaziv.setText("Naziv usluge:");

        lblCena.setText("Cena (RSD):");

        lblTrajanje.setText("Trajanje (min):");

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
                    .addComponent(lblNaziv)
                    .addComponent(lblCena)
                    .addComponent(lblTrajanje))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(spnCena)
                    .addComponent(spnTrajanje))
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
                    .addComponent(lblNaziv)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCena)
                    .addComponent(spnCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrajanje)
                    .addComponent(spnTrajanje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        boolean novo = usluga == null;
        try {
            String naziv = txtNaziv.getText().trim();
            double cenaVrednost = ((Number) spnCena.getValue()).doubleValue();
            int trajanjeVrednost = ((Number) spnTrajanje.getValue()).intValue();
            if (novo) {
                Usluga novaUsluga = new Usluga(0, naziv, cenaVrednost, trajanjeVrednost);
                ClientController.getInstance().addUsluga(novaUsluga);
                usluga = novaUsluga;
            } else {
                Usluga izmenjenaUsluga = new Usluga(usluga.getIdUsluga(), naziv, cenaVrednost, trajanjeVrednost);
                ClientController.getInstance().updateUsluga(izmenjenaUsluga);
                usluga.setNazivUsluge(naziv);
                usluga.setCenaUsluge(cenaVrednost);
                usluga.setTrajanje(trajanjeVrednost);
            }
            JOptionPane.showMessageDialog(this,
                    novo ? "Sistem je kreirao uslugu." : "Sistem je zapamtio izmene usluge.",
                    "Informacija", JOptionPane.INFORMATION_MESSAGE);
            saved = true;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    (novo ? "Sistem ne može da kreira uslugu.\n" : "Sistem ne može da zapamti izmene usluge.\n") + e.getMessage(),
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    public boolean isSaved() {
        return saved;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblCena;
    private javax.swing.JLabel lblNaziv;
    private javax.swing.JLabel lblTrajanje;
    private javax.swing.JSpinner spnCena;
    private javax.swing.JSpinner spnTrajanje;
    private javax.swing.JTextField txtNaziv;
    // End of variables declaration//GEN-END:variables
}
