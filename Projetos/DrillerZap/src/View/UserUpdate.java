package View;

import Controller.ApprovedObserver;
import Controller.UpdateObserver;
import Controller.UserController;
import Model.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo
 */
public class UserUpdate extends javax.swing.JFrame implements UpdateObserver{

    private UserController controller;
   

    public UserUpdate() {
        initComponents();
        this.setLocationRelativeTo(null);
        controller = new UserController();
        controller.observUpdate(this);
        
        jTextName.setText(Configuration.getInstance().getLoggedUser().getName());
        jTextPassword.setText(Configuration.getInstance().getLoggedUser().getPassword());
        jTextPhone.setText(Configuration.getInstance().getLoggedUser().getTelephone());
    }
    


    public boolean validaCampos() {
        boolean preenchido = true;
        if (jTextName.getText().length() == 0
                || jTextEmail.getText().length() == 0
                || jTextPhone.getText().length() == 0
                || jTextPassword.getText().length() == 0) {
            preenchido = false;
        }
        return preenchido;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextEmail = new javax.swing.JTextField();
        jLabelPhone = new javax.swing.JLabel();
        jTextPhone = new javax.swing.JFormattedTextField();
        jLabelPassword = new javax.swing.JLabel();
        jTextPassword = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabelCadastre = new javax.swing.JLabel();
        jButtonUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 52, 72));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabelName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName.setText("Nome");

        jTextName.setBackground(new java.awt.Color(126, 117, 136));
        jTextName.setForeground(new java.awt.Color(255, 255, 255));
        jTextName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNameActionPerformed(evt);
            }
        });

        jLabelEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmail.setText("E-mail");

        jTextEmail.setEditable(false);
        jTextEmail.setBackground(new java.awt.Color(126, 117, 136));
        jTextEmail.setForeground(new java.awt.Color(255, 255, 255));

        jLabelPhone.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPhone.setText("Telefone");

        jTextPhone.setBackground(new java.awt.Color(126, 117, 136));
        jTextPhone.setForeground(new java.awt.Color(255, 255, 255));
        try {
            jTextPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPhoneActionPerformed(evt);
            }
        });

        jLabelPassword.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPassword.setText("Senha");

        jTextPassword.setBackground(new java.awt.Color(126, 117, 136));
        jTextPassword.setForeground(new java.awt.Color(255, 255, 255));
        jTextPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPasswordActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        jLabelCadastre.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCadastre.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelCadastre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCadastre.setText("Perfil");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabelCadastre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabelCadastre)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jButtonUpdate.setBackground(new java.awt.Color(0, 204, 51));
        jButtonUpdate.setText("Alterar");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPhone)
                    .addComponent(jLabelPassword)
                    .addComponent(jTextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonUpdate)
                .addGap(156, 156, 156))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPhone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButtonUpdate)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPasswordActionPerformed

    }//GEN-LAST:event_jTextPasswordActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (validaCampos() == true) {            
            try {
                controller.submitUserToServer(jTextName.getText(),
                        jTextEmail.getText(),
                        jTextPhone.getText(),
                        jTextPassword.getText());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    
        } else {
            JOptionPane.showMessageDialog(rootPane, "Alguns dos campos não foi informado");
        }

    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jTextNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNameActionPerformed

    private void jTextPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPhoneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabelCadastre;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPhone;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextName;
    private javax.swing.JPasswordField jTextPassword;
    private javax.swing.JFormattedTextField jTextPhone;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateApproved() {
        JOptionPane.showMessageDialog(rootPane, "Informações Salvas");
    }

    @Override
    public void updateNotApproved() {
        JOptionPane.showMessageDialog(rootPane, "Erro ao salvar as informações");
    }
}
