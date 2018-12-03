package View;

import Controller.AddContactObserver;
import Controller.UserController;
import Model.Configuration;
import Model.MessageModel;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Paulo
 */
public class Chat extends javax.swing.JFrame implements AddContactObserver {

    private UserController controller;
    private DefaultListModel model;
    private HashMap<String, List<String>> messages;
    private List<MessageModel> messagesChat;

    public Chat() {
        initComponents();
        this.setLocationRelativeTo(null);
        addListeners();
        init();
    }

    private void init() {
        jTextAreaChat.setText("Bem vindo ao DrillerZap!\n"
                + "Desenvolvido por: \n"
                + "- Ivens Diego Müller.\n"
                + "- Paulo Henrique Rodrigues.");
        this.messages = new HashMap<>();
        this.model = new DefaultListModel();
        this.jListContatos.setModel(model);
        controller = new UserController();
        controller.observAddContact(this);
        controller.processContacts();
        controller.processAliveContacts();
        

    }

    private void listContactOnline(int x) {
        if (x > -1) {
            boolean alive = controller.AliveContacts((String) model.getElementAt(x));
            if (alive == false) {
                jListContatos.setSelectionForeground(Color.RED);
                jButtonEnviar.setEnabled(false);
                jTextAreaMensagem.setEnabled(false);
            } else {
                jListContatos.setSelectionForeground(Color.GREEN);
                jButtonEnviar.setEnabled(true);
                jTextAreaMensagem.setEnabled(true);
            }
        }
    }

    private void UpdateJTextAtea(String email) {
        String aux = "";
        jTextAreaChat.setText("");
        for (MessageModel message : controller.returnMessages()) {
            if (message.getFrom().getUser().getEmail().equals(Configuration.getInstance().getLoggedUser().getUser().getEmail())
                    || message.getTo().getUser().getEmail().equals(Configuration.getInstance().getLoggedUser().getUser().getEmail())) {
                if (email.equals(message.getFrom().getUser().getEmail())
                        || email.equals(message.getTo().getUser().getEmail())) {
                    aux = jTextAreaChat.getText();
                    if ("".equals(aux)) {
                        jTextAreaChat.setText(message.getFrom().getUser().getName() + " diz: " + message.getMessage());
                    } else {
                        jTextAreaChat.setText(aux + "\n" + message.getFrom().getUser().getName() + " diz: " + message.getMessage());

                    }
                }
            }
        }
        jTextAreaMensagem.setText("");
    }

    private void addListeners() {
        jListContatos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                processMessages((String) model.getElementAt(jListContatos.getSelectedIndex()));

            }
        });
    }

    private void processMessages(String email) {
        jTextAreaChat.setText("");
        List<String> chat = messages.get(email);
        if (chat != null) {
            for (String s : chat) {
                jTextAreaChat.append(s + "\n");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListContatos = new javax.swing.JList<>();
        jButtonAdicionar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaChat = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMensagem = new javax.swing.JTextArea();
        jButtonEnviar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 52, 72));

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DrillerZap");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(239, 239, 239))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(126, 117, 136));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Contatos"));
        jPanel3.setToolTipText("");

        jListContatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListContatos.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jListContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListContatosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListContatos);

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonAdicionar)
                        .addGap(0, 59, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdicionar)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(126, 117, 136));

        jTextAreaChat.setEditable(false);
        jTextAreaChat.setColumns(20);
        jTextAreaChat.setRows(5);
        jScrollPane1.setViewportView(jTextAreaChat);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(126, 117, 136));

        jTextAreaMensagem.setColumns(20);
        jTextAreaMensagem.setRows(5);
        jScrollPane2.setViewportView(jTextAreaMensagem);

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonUpdate.setBackground(new java.awt.Color(126, 117, 136));
        jButtonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/perfil.png"))); // NOI18N
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonEnviar)
                        .addComponent(jButtonLimpar))
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        UserUpdate perfil = new UserUpdate();
        perfil.setVisible(true);
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        String email = JOptionPane.showInputDialog("Informa o e-mail do contato");
        if (email != null) {
            try {
                controller.addContact(email);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        try {
            controller.sendMessage(Configuration.getInstance().getLoggedUser(), controller.returnUser((String) model.getElementAt(jListContatos.getSelectedIndex())), jTextAreaMensagem.getText());
            UpdateJTextAtea((String) model.getElementAt(jListContatos.getSelectedIndex()));
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jListContatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListContatosMouseClicked
        listContactOnline(jListContatos.getSelectedIndex());
        UpdateJTextAtea((String) model.getElementAt(jListContatos.getSelectedIndex()));

    }//GEN-LAST:event_jListContatosMouseClicked

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        jTextAreaMensagem.setText("");
    }//GEN-LAST:event_jButtonLimparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jListContatos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaChat;
    private javax.swing.JTextArea jTextAreaMensagem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addContactApproved() {
        JOptionPane.showMessageDialog(rootPane, "Contato Cadastrado com sucesso!");
    }

    @Override
    public void addContactNotApproved(String error) {
        JOptionPane.showMessageDialog(rootPane, error);
    }

    @Override
    public void receiveContact(String email) {
        model.addElement(email);
        messages.put(email, new ArrayList<String>());
        jListContatos.repaint();
    }

    @Override
    public void removeContact(String email) {
        model.removeElement(email);
        messages.remove(email);
        jListContatos.repaint();
    }

    @Override
    public void contactAlive(String email, boolean alive, List<String> messages) {
        System.out.println(alive);

    }

    @Override
    public void messageReceived(String emailContact, MessageModel message) {
        UpdateJTextAtea(emailContact);
    }

}
