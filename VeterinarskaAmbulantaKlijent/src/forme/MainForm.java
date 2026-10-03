package forme;

import controller.ClientController;
import domain.Zaposleni;
import form_racun.FormRacuni;
import form_strucna_sprema.FormStrucneSpreme;
import form_usluga.FormUsluge;
import form_vlasnik.FormVlasnici;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Kosta
 */
public class MainForm extends javax.swing.JFrame {

    private final Zaposleni zaposleni;

    public MainForm(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
        initComponents();
        setTitle("Veterinarska ambulanta - Glavni meni");
        lblWelcome.setText("Prijavljeni zaposleni: " + zaposleni);
        setSize(780, 480);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                odjaviSe();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAppTitle = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuDokumenti = new javax.swing.JMenu();
        miRacuni = new javax.swing.JMenuItem();
        menuPrimalac = new javax.swing.JMenu();
        miVlasnici = new javax.swing.JMenuItem();
        menuSifarnici = new javax.swing.JMenu();
        miUsluge = new javax.swing.JMenuItem();
        miStrucneSpreme = new javax.swing.JMenuItem();
        menuSistem = new javax.swing.JMenu();
        miOProgramu = new javax.swing.JMenuItem();
        miOdjava = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        lblAppTitle.setFont(lblAppTitle.getFont().deriveFont(java.awt.Font.BOLD, 32f));
        lblAppTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAppTitle.setText("Veterinarska ambulanta");

        lblWelcome.setFont(lblWelcome.getFont().deriveFont(15f));
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setText("Prijavljeni zaposleni:");

        menuDokumenti.setText("Dokumenti");

        miRacuni.setText("Računi");
        miRacuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRacuniActionPerformed(evt);
            }
        });
        menuDokumenti.add(miRacuni);
        jMenuBar1.add(menuDokumenti);

        menuPrimalac.setText("Primalac usluge");

        miVlasnici.setText("Vlasnici životinja");
        miVlasnici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miVlasniciActionPerformed(evt);
            }
        });
        menuPrimalac.add(miVlasnici);
        jMenuBar1.add(menuPrimalac);

        menuSifarnici.setText("Šifarnici");

        miUsluge.setText("Usluge");
        miUsluge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUslugeActionPerformed(evt);
            }
        });
        menuSifarnici.add(miUsluge);

        miStrucneSpreme.setText("Stručne spreme");
        miStrucneSpreme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miStrucneSpremeActionPerformed(evt);
            }
        });
        menuSifarnici.add(miStrucneSpreme);
        jMenuBar1.add(menuSifarnici);
        jMenuBar1.add(javax.swing.Box.createHorizontalGlue());

        menuSistem.setText("Sistem");

        miOProgramu.setText("O programu");
        miOProgramu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miOProgramuActionPerformed(evt);
            }
        });
        menuSistem.add(miOProgramu);
        menuSistem.addSeparator();

        miOdjava.setText("Odjava");
        miOdjava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miOdjavaActionPerformed(evt);
            }
        });
        menuSistem.add(miOdjava);
        jMenuBar1.add(menuSistem);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAppTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(lblAppTitle)
                .addGap(18, 18, 18)
                .addComponent(lblWelcome)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miRacuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRacuniActionPerformed
        new FormRacuni().setVisible(true);
    }//GEN-LAST:event_miRacuniActionPerformed

    private void miVlasniciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miVlasniciActionPerformed
        new FormVlasnici().setVisible(true);
    }//GEN-LAST:event_miVlasniciActionPerformed

    private void miUslugeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUslugeActionPerformed
        new FormUsluge().setVisible(true);
    }//GEN-LAST:event_miUslugeActionPerformed

    private void miStrucneSpremeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miStrucneSpremeActionPerformed
        new FormStrucneSpreme().setVisible(true);
    }//GEN-LAST:event_miStrucneSpremeActionPerformed

    private void miOProgramuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miOProgramuActionPerformed
        JOptionPane.showMessageDialog(this,
                "Softverski sistem za praćenje rada veterinarske ambulante\nProjektovanje softvera - Kosta Popović 2023/0177");
    }//GEN-LAST:event_miOProgramuActionPerformed

    private void miOdjavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miOdjavaActionPerformed
        odjaviSe();
    }//GEN-LAST:event_miOdjavaActionPerformed

    private void odjaviSe() {
        ClientController.getInstance().logout();
        dispose();
        new LoginForma().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblAppTitle;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JMenu menuDokumenti;
    private javax.swing.JMenu menuPrimalac;
    private javax.swing.JMenu menuSifarnici;
    private javax.swing.JMenu menuSistem;
    private javax.swing.JMenuItem miOProgramu;
    private javax.swing.JMenuItem miOdjava;
    private javax.swing.JMenuItem miRacuni;
    private javax.swing.JMenuItem miStrucneSpreme;
    private javax.swing.JMenuItem miUsluge;
    private javax.swing.JMenuItem miVlasnici;
    // End of variables declaration//GEN-END:variables
}
