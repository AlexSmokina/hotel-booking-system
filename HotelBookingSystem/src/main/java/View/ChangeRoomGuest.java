/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author alex
 */
public class ChangeRoomGuest extends javax.swing.JFrame {

    /**
     * Creates new form ChangeRoomGuest
     */
    public ChangeRoomGuest() {
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

        changeRoomGuestPanel = new javax.swing.JPanel();
        numberOfRoom = new javax.swing.JTextField();
        changeRoom = new javax.swing.JLabel();
        chooseEnterPrompt = new javax.swing.JLabel();
        bookingID = new javax.swing.JTextField();
        confirmChange = new javax.swing.JButton();
        availableRoomsPrompt = new javax.swing.JLabel();
        avaiableRoomsQTY = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        changeRoomGuestPanel.setBackground(new java.awt.Color(255, 255, 255));

        numberOfRoom.setBackground(new java.awt.Color(255, 204, 255));
        numberOfRoom.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        numberOfRoom.setForeground(new java.awt.Color(102, 102, 102));
        numberOfRoom.setText("Enter the number of the room you want to switch to");

        changeRoom.setBackground(new java.awt.Color(255, 255, 255));
        changeRoom.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        changeRoom.setForeground(new java.awt.Color(0, 0, 0));
        changeRoom.setText("Change Room");
        changeRoom.setPreferredSize(new java.awt.Dimension(220, 50));

        chooseEnterPrompt.setBackground(new java.awt.Color(255, 255, 255));
        chooseEnterPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        chooseEnterPrompt.setForeground(new java.awt.Color(0, 0, 0));
        chooseEnterPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseEnterPrompt.setText("Choose & Enter");

        bookingID.setBackground(new java.awt.Color(255, 204, 255));
        bookingID.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        bookingID.setForeground(new java.awt.Color(102, 102, 102));
        bookingID.setText("Enter booking ID for the room you want to change");

        confirmChange.setBackground(new java.awt.Color(153, 0, 153));
        confirmChange.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        confirmChange.setForeground(new java.awt.Color(255, 255, 255));
        confirmChange.setText("Confirm");
        confirmChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmChangeActionPerformed(evt);
            }
        });

        availableRoomsPrompt.setBackground(new java.awt.Color(255, 255, 255));
        availableRoomsPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        availableRoomsPrompt.setForeground(new java.awt.Color(0, 0, 0));
        availableRoomsPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableRoomsPrompt.setText("Available Rooms");

        avaiableRoomsQTY.setBackground(new java.awt.Color(255, 255, 255));
        avaiableRoomsQTY.setFont(new java.awt.Font("STSong", 1, 30)); // NOI18N
        avaiableRoomsQTY.setForeground(new java.awt.Color(153, 0, 153));
        avaiableRoomsQTY.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avaiableRoomsQTY.setText("?");
        avaiableRoomsQTY.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout changeRoomGuestPanelLayout = new javax.swing.GroupLayout(changeRoomGuestPanel);
        changeRoomGuestPanel.setLayout(changeRoomGuestPanelLayout);
        changeRoomGuestPanelLayout.setHorizontalGroup(
            changeRoomGuestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeRoomGuestPanelLayout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addGroup(changeRoomGuestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeRoomGuestPanelLayout.createSequentialGroup()
                        .addComponent(chooseEnterPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeRoomGuestPanelLayout.createSequentialGroup()
                        .addComponent(changeRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))))
            .addGroup(changeRoomGuestPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(changeRoomGuestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(changeRoomGuestPanelLayout.createSequentialGroup()
                        .addComponent(availableRoomsPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(avaiableRoomsQTY, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addComponent(confirmChange, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberOfRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        changeRoomGuestPanelLayout.setVerticalGroup(
            changeRoomGuestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeRoomGuestPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(changeRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(chooseEnterPrompt)
                .addGap(52, 52, 52)
                .addComponent(bookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(numberOfRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmChange, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(changeRoomGuestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availableRoomsPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(avaiableRoomsQTY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        getContentPane().add(changeRoomGuestPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmChangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmChangeActionPerformed

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
            java.util.logging.Logger.getLogger(ChangeRoomGuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeRoomGuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeRoomGuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeRoomGuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeRoomGuest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avaiableRoomsQTY;
    private javax.swing.JLabel availableRoomsPrompt;
    private javax.swing.JTextField bookingID;
    private javax.swing.JLabel changeRoom;
    private javax.swing.JPanel changeRoomGuestPanel;
    private javax.swing.JLabel chooseEnterPrompt;
    private javax.swing.JButton confirmChange;
    private javax.swing.JTextField numberOfRoom;
    // End of variables declaration//GEN-END:variables
}