package forms;

import db.DBBroker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Kosta
 */
public class KonfiguracijaBaze extends javax.swing.JDialog {

    public KonfiguracijaBaze(java.awt.Frame owner) {
        super(owner, "Konfiguracija baze", true);
        initComponents();
        getRootPane().setDefaultButton(btnSave);
        setResizable(false);
        setLocationRelativeTo(owner);
        ucitajPodesavanja();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUrl = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        btnTest = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblUrl.setText("JDBC URL:");

        lblUser.setText("Korisnik:");

        lblPass.setText("Lozinka:");

        btnTest.setText("Testiraj vezu");
        btnTest.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        btnSave.setText("Sačuvaj");
        btnSave.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClose.setText("Zatvori");
        btnClose.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUrl)
                    .addComponent(lblUser)
                    .addComponent(lblPass))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(txtUser)
                    .addComponent(txtPass))
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnTest, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUrl)
                    .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPass)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTest)
                    .addComponent(btnSave)
                    .addComponent(btnClose)
                )
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        sacuvajPodesavanja();
        try {
            boolean uspesno = DBBroker.getInstance().testConnection();
            JOptionPane.showMessageDialog(this,
                    uspesno ? "Veza sa bazom je uspešna." : "Veza sa bazom nije uspostavljena.",
                    "Test veze",
                    uspesno ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTestActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (sacuvajPodesavanja()) {
            JOptionPane.showMessageDialog(this, "Konfiguracija je sačuvana.");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private File konfiguracioniFajl() {
        return new File("dbconfig.properties");
    }

    private void ucitajPodesavanja() {
        try {
            Properties podesavanja = new Properties();
            File fajl = konfiguracioniFajl();
            if (fajl.exists()) {
                FileInputStream in = new FileInputStream(fajl);
                podesavanja.load(in);
                in.close();
            }
            txtUrl.setText(podesavanja.getProperty("url",
                    "jdbc:mysql://localhost:3306/veterinarska_ambulanta?useSSL=false&serverTimezone=Europe/Belgrade&useUnicode=true&characterEncoding=UTF-8"));
            txtUser.setText(podesavanja.getProperty("username", "root"));
            txtPass.setText(podesavanja.getProperty("password", ""));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean sacuvajPodesavanja() {
        try {
            Properties podesavanja = new Properties();
            podesavanja.setProperty("url", txtUrl.getText().trim());
            podesavanja.setProperty("username", txtUser.getText().trim());
            podesavanja.setProperty("password", new String(txtPass.getPassword()));
            FileOutputStream out = new FileOutputStream(konfiguracioniFajl());
            podesavanja.store(out, "Veterinarska ambulanta - konfiguracija baze");
            out.close();
            DBBroker.reset();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTest;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtUrl;
    private javax.swing.JTextField txtUser;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
