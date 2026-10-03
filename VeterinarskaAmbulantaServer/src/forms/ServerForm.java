package forms;

import db.DBBroker;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import thread.ThreadServer;

/**
 *
 * @author Kosta
 */
public class ServerForm extends javax.swing.JFrame {

    private ThreadServer server;

    public ServerForm() {
        initComponents();
        setTitle("Veterinarska ambulanta - Server");
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        lblStatusL = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblClientsL = new javax.swing.JLabel();
        lblClients = new javax.swing.JLabel();
        lblDbL = new javax.swing.JLabel();
        lblDb = new javax.swing.JLabel();
        scrollLog = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnTestDb = new javax.swing.JButton();
        btnDbConfig = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPort.setText("Port:");

        txtPort.setText("9000");

        lblStatusL.setText("Status servera:");

        lblStatus.setFont(lblStatus.getFont().deriveFont(java.awt.Font.BOLD));
        lblStatus.setText("ZAUSTAVLJEN");

        lblClientsL.setText("Povezani klijenti:");

        lblClients.setText("0");

        lblDbL.setText("Baza:");

        lblDb.setText("nije proverena");

        txtLog.setColumns(20);
        txtLog.setRows(8);
        txtLog.setEditable(false);
        txtLog.setFont(new java.awt.Font("Monospaced", 0, 12));
        txtLog.setMargin(new java.awt.Insets(6, 6, 6, 6));
        scrollLog.setViewportView(txtLog);

        btnStart.setText("Pokreni server");
        btnStart.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setEnabled(false);
        btnStop.setText("Zaustavi server");
        btnStop.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnTestDb.setText("Proveri bazu");
        btnTestDb.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnTestDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestDbActionPerformed(evt);
            }
        });

        btnDbConfig.setText("Konfiguracija baze");
        btnDbConfig.setMargin(new java.awt.Insets(6, 12, 6, 12));
        btnDbConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDbConfigActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPort)
                            .addComponent(lblStatusL)
                            .addComponent(lblClientsL)
                            .addComponent(lblDbL))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblClients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(scrollLog, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnTestDb, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnDbConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPort)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatusL)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClientsL)
                    .addComponent(lblClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDbL)
                    .addComponent(lblDb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(scrollLog, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop)
                    .addComponent(btnTestDb)
                    .addComponent(btnDbConfig)
                )
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        try {
            int port;
            try {
                port = Integer.parseInt(txtPort.getText().trim());
            } catch (NumberFormatException e) {
                throw new Exception("Port mora biti broj između 1 i 65535.");
            }
            if (!DBBroker.getInstance().testConnection()) {
                throw new Exception("Server nije moguće pokrenuti jer veza sa bazom nije uspostavljena.");
            }
            server = new ThreadServer(port);
            server.setOnChange(() -> SwingUtilities.invokeLater(()
                    -> lblClients.setText(String.valueOf(server == null ? 0 : server.getClientCount()))));
            server.start();
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
            btnDbConfig.setEnabled(false);
            txtPort.setEnabled(false);
            lblStatus.setText("POKRENUT");
            lblDb.setText("povezana");
            zabeleziPoruku("Server je pokrenut na portu " + port + ".");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        if (server != null) {
            server.stopServer();
        }
        server = null;
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        btnDbConfig.setEnabled(true);
        txtPort.setEnabled(true);
        lblStatus.setText("ZAUSTAVLJEN");
        lblClients.setText("0");
        zabeleziPoruku("Server je zaustavljen.");
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnTestDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestDbActionPerformed
        try {
            boolean uspesno = DBBroker.getInstance().testConnection();
            lblDb.setText(uspesno ? "povezana" : "nije povezana");
            zabeleziPoruku(uspesno ? "Veza sa bazom je uspešna." : "Veza sa bazom nije uspostavljena.");
        } catch (Exception e) {
            lblDb.setText("greška");
            zabeleziPoruku("Greška baze: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTestDbActionPerformed

    private void btnDbConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDbConfigActionPerformed
        new KonfiguracijaBaze(this).setVisible(true);
    }//GEN-LAST:event_btnDbConfigActionPerformed

    private void zabeleziPoruku(String poruka) {
        SwingUtilities.invokeLater(() -> {
            txtLog.append(poruka + "\n");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ignored) {
                }
                new ServerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDbConfig;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnTestDb;
    private javax.swing.JLabel lblClients;
    private javax.swing.JLabel lblClientsL;
    private javax.swing.JLabel lblDb;
    private javax.swing.JLabel lblDbL;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusL;
    private javax.swing.JScrollPane scrollLog;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
