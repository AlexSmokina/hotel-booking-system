/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author alex
 */
public class ExtendBookingStaff extends javax.swing.JFrame {

    /**
     * Creates new form ExtendBooking
     */
    public ExtendBookingStaff() {
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

        extendBookingStaffPanel = new javax.swing.JPanel();
        extendBooking = new javax.swing.JLabel();
        bookingDetailsPrompt = new javax.swing.JLabel();
        guestUsername = new javax.swing.JTextField();
        bookingID = new javax.swing.JTextField();
        confirmExtend = new javax.swing.JButton();
        checkOutDate = new javax.swing.JTextField();
        returnPreviousMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        extendBookingStaffPanel.setBackground(new java.awt.Color(255, 255, 255));

        extendBooking.setBackground(new java.awt.Color(255, 255, 255));
        extendBooking.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        extendBooking.setForeground(new java.awt.Color(0, 0, 0));
        extendBooking.setText("Extend Booking");
        extendBooking.setPreferredSize(new java.awt.Dimension(220, 50));

        bookingDetailsPrompt.setBackground(new java.awt.Color(255, 255, 255));
        bookingDetailsPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        bookingDetailsPrompt.setForeground(new java.awt.Color(0, 0, 0));
        bookingDetailsPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookingDetailsPrompt.setText("Enter booking details");

        guestUsername.setBackground(new java.awt.Color(255, 204, 255));
        guestUsername.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        guestUsername.setForeground(new java.awt.Color(102, 102, 102));
        guestUsername.setText("Enter guest username");
        guestUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestUsernameActionPerformed(evt);
            }
        });

        bookingID.setBackground(new java.awt.Color(255, 204, 255));
        bookingID.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        bookingID.setForeground(new java.awt.Color(102, 102, 102));
        bookingID.setText("Enter booking ID you want to extend");

        confirmExtend.setBackground(new java.awt.Color(153, 0, 153));
        confirmExtend.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        confirmExtend.setForeground(new java.awt.Color(255, 255, 255));
        confirmExtend.setText("Confirm");
        confirmExtend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmExtendActionPerformed(evt);
            }
        });

        checkOutDate.setBackground(new java.awt.Color(255, 204, 255));
        checkOutDate.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        checkOutDate.setForeground(new java.awt.Color(102, 102, 102));
        checkOutDate.setText("New check-out date");

        returnPreviousMenu.setFont(new java.awt.Font("STSong", 1, 18)); // NOI18N
        returnPreviousMenu.setForeground(new java.awt.Color(153, 0, 153));
        returnPreviousMenu.setText("Return");

        javax.swing.GroupLayout extendBookingStaffPanelLayout = new javax.swing.GroupLayout(extendBookingStaffPanel);
        extendBookingStaffPanel.setLayout(extendBookingStaffPanelLayout);
        extendBookingStaffPanelLayout.setHorizontalGroup(
            extendBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, extendBookingStaffPanelLayout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(extendBooking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(extendBookingStaffPanelLayout.createSequentialGroup()
                .addGroup(extendBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(extendBookingStaffPanelLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(bookingDetailsPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(extendBookingStaffPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(extendBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(guestUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmExtend, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(extendBookingStaffPanelLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(returnPreviousMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        extendBookingStaffPanelLayout.setVerticalGroup(
            extendBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extendBookingStaffPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(extendBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bookingDetailsPrompt)
                .addGap(26, 26, 26)
                .addComponent(guestUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmExtend, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(returnPreviousMenu)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        getContentPane().add(extendBookingStaffPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guestUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guestUsernameActionPerformed

    private void confirmExtendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmExtendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmExtendActionPerformed

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
            java.util.logging.Logger.getLogger(ExtendBookingStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExtendBookingStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExtendBookingStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExtendBookingStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExtendBookingStaff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookingDetailsPrompt;
    private javax.swing.JTextField bookingID;
    private javax.swing.JTextField checkOutDate;
    private javax.swing.JButton confirmExtend;
    private javax.swing.JLabel extendBooking;
    private javax.swing.JPanel extendBookingStaffPanel;
    private javax.swing.JTextField guestUsername;
    private javax.swing.JButton returnPreviousMenu;
    // End of variables declaration//GEN-END:variables
}
