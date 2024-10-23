/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author alex
 */
public class HotelDetails extends javax.swing.JFrame {

    /**
     * Creates new form HotelDetails
     */
    public HotelDetails() {
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

        hotelDetailsPanel = new javax.swing.JPanel();
        hotelDetails = new javax.swing.JLabel();
        enterHotelDetailsPrompt = new javax.swing.JLabel();
        hotelID = new javax.swing.JTextField();
        newHotelName = new javax.swing.JTextField();
        hotelName2 = new javax.swing.JTextField();
        signUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 600));
        setResizable(false);

        hotelDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));

        hotelDetails.setBackground(new java.awt.Color(255, 255, 255));
        hotelDetails.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        hotelDetails.setForeground(new java.awt.Color(0, 0, 0));
        hotelDetails.setText("Hotel Details");
        hotelDetails.setPreferredSize(new java.awt.Dimension(220, 50));

        enterHotelDetailsPrompt.setBackground(new java.awt.Color(255, 255, 255));
        enterHotelDetailsPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        enterHotelDetailsPrompt.setForeground(new java.awt.Color(0, 0, 0));
        enterHotelDetailsPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enterHotelDetailsPrompt.setText("Enter Hotel Details");

        hotelID.setBackground(new java.awt.Color(255, 204, 255));
        hotelID.setForeground(new java.awt.Color(102, 102, 102));
        hotelID.setText("Enter hotel ID to update");
        hotelID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hotelIDActionPerformed(evt);
            }
        });

        newHotelName.setBackground(new java.awt.Color(255, 204, 255));
        newHotelName.setForeground(new java.awt.Color(102, 102, 102));
        newHotelName.setText("Enter new hotel name");
        newHotelName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newHotelNameActionPerformed(evt);
            }
        });

        hotelName2.setBackground(new java.awt.Color(255, 204, 255));
        hotelName2.setForeground(new java.awt.Color(102, 102, 102));
        hotelName2.setText("Enter new location");
        hotelName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hotelName2ActionPerformed(evt);
            }
        });

        signUp.setBackground(new java.awt.Color(153, 0, 153));
        signUp.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        signUp.setForeground(new java.awt.Color(255, 255, 255));
        signUp.setText("Confirm");
        signUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout hotelDetailsPanelLayout = new javax.swing.GroupLayout(hotelDetailsPanel);
        hotelDetailsPanel.setLayout(hotelDetailsPanelLayout);
        hotelDetailsPanelLayout.setHorizontalGroup(
            hotelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hotelDetailsPanelLayout.createSequentialGroup()
                .addGroup(hotelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hotelDetailsPanelLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(enterHotelDetailsPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hotelDetailsPanelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(hotelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hotelDetailsPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(hotelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newHotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hotelID, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hotelName2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(signUp, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        hotelDetailsPanelLayout.setVerticalGroup(
            hotelDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hotelDetailsPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(hotelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(enterHotelDetailsPrompt)
                .addGap(43, 43, 43)
                .addComponent(hotelID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newHotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hotelName2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(signUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        getContentPane().add(hotelDetailsPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hotelIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hotelIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hotelIDActionPerformed

    private void newHotelNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newHotelNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newHotelNameActionPerformed

    private void hotelName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hotelName2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hotelName2ActionPerformed

    private void signUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpActionPerformed

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
            java.util.logging.Logger.getLogger(HotelDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HotelDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HotelDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HotelDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel enterHotelDetailsPrompt;
    private javax.swing.JLabel hotelDetails;
    private javax.swing.JPanel hotelDetailsPanel;
    private javax.swing.JTextField hotelID;
    private javax.swing.JTextField hotelName2;
    private javax.swing.JTextField newHotelName;
    private javax.swing.JButton signUp;
    // End of variables declaration//GEN-END:variables
}