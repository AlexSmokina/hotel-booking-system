/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author alex
 */
public class Register extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    public Register() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registerPanel = new javax.swing.JPanel();
        registerLabel = new javax.swing.JLabel();
        createAccountPrompt = new javax.swing.JLabel();
        registerUsername = new javax.swing.JTextField();
        registerPassword = new javax.swing.JTextField();
        registerName = new javax.swing.JTextField();
        registerPhone = new javax.swing.JTextField();
        registerEmail = new javax.swing.JTextField();
        userTypePrompt = new javax.swing.JLabel();
        guest = new javax.swing.JButton();
        staff = new javax.swing.JButton();
        signUp = new javax.swing.JButton();
        loginOptionPrompt = new javax.swing.JLabel();
        loginOption = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        registerPanel.setBackground(new java.awt.Color(255, 255, 255));
        registerPanel.setPreferredSize(new java.awt.Dimension(500, 600));

        registerLabel.setBackground(new java.awt.Color(255, 255, 255));
        registerLabel.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        registerLabel.setForeground(new java.awt.Color(0, 0, 0));
        registerLabel.setText("Register");

        createAccountPrompt.setBackground(new java.awt.Color(255, 255, 255));
        createAccountPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        createAccountPrompt.setForeground(new java.awt.Color(0, 0, 0));
        createAccountPrompt.setText("Create your account");

        registerUsername.setBackground(new java.awt.Color(255, 204, 255));
        registerUsername.setForeground(new java.awt.Color(102, 102, 102));
        registerUsername.setText("Username");
        registerUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerUsernameActionPerformed(evt);
            }
        });

        registerPassword.setBackground(new java.awt.Color(255, 204, 255));
        registerPassword.setForeground(new java.awt.Color(102, 102, 102));
        registerPassword.setText("Password");

        registerName.setBackground(new java.awt.Color(255, 204, 255));
        registerName.setForeground(new java.awt.Color(102, 102, 102));
        registerName.setText("Name");
        registerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerNameActionPerformed(evt);
            }
        });

        registerPhone.setBackground(new java.awt.Color(255, 204, 255));
        registerPhone.setForeground(new java.awt.Color(102, 102, 102));
        registerPhone.setText("Phone");

        registerEmail.setBackground(new java.awt.Color(255, 204, 255));
        registerEmail.setForeground(new java.awt.Color(102, 102, 102));
        registerEmail.setText("Email");

        userTypePrompt.setBackground(new java.awt.Color(255, 255, 255));
        userTypePrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        userTypePrompt.setForeground(new java.awt.Color(0, 0, 0));
        userTypePrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userTypePrompt.setText("Choose user type");

        guest.setBackground(new java.awt.Color(255, 204, 255));
        guest.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        guest.setForeground(new java.awt.Color(51, 51, 51));
        guest.setText("Guest");

        staff.setBackground(new java.awt.Color(255, 204, 255));
        staff.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        staff.setForeground(new java.awt.Color(51, 51, 51));
        staff.setText("Staff");

        signUp.setBackground(new java.awt.Color(153, 0, 153));
        signUp.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        signUp.setForeground(new java.awt.Color(255, 255, 255));
        signUp.setText("Sign Up");

        loginOptionPrompt.setBackground(new java.awt.Color(255, 255, 255));
        loginOptionPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        loginOptionPrompt.setForeground(new java.awt.Color(0, 0, 0));
        loginOptionPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginOptionPrompt.setText("Already have an account?");

        loginOption.setFont(new java.awt.Font("STSong", 1, 18)); // NOI18N
        loginOption.setForeground(new java.awt.Color(153, 0, 153));
        loginOption.setText("Login");
        loginOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginOptionSignUpOptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registerPanelLayout = new javax.swing.GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(userTypePrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(staff, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(signUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(registerPanelLayout.createSequentialGroup()
                                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(registerPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(registerUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(registerName, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(registerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(registerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(guest, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(registerPanelLayout.createSequentialGroup()
                                        .addComponent(loginOptionPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(loginOption, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPanelLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registerPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(createAccountPrompt))
                            .addComponent(registerLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        registerPanelLayout.setVerticalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(registerLabel)
                .addGap(11, 11, 11)
                .addComponent(createAccountPrompt)
                .addGap(18, 18, 18)
                .addComponent(registerUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userTypePrompt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(signUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginOptionPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginOption, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        getContentPane().add(registerPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerUsernameActionPerformed

    private void registerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerNameActionPerformed

    private void loginOptionSignUpOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginOptionSignUpOptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginOptionSignUpOptionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel createAccountPrompt;
    private javax.swing.JButton guest;
    private javax.swing.JButton loginOption;
    private javax.swing.JLabel loginOptionPrompt;
    private javax.swing.JTextField registerEmail;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JTextField registerName;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JTextField registerPassword;
    private javax.swing.JTextField registerPhone;
    private javax.swing.JTextField registerUsername;
    private javax.swing.JButton signUp;
    private javax.swing.JButton staff;
    private javax.swing.JLabel userTypePrompt;
    // End of variables declaration//GEN-END:variables
}
