/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.CancelBookingStaffController;

/**
 *
 * @author alex
 */
public class CancelBookingStaff extends javax.swing.JFrame {

    /**
     * Creates new form CancelBooking
     */
    public CancelBookingStaff() {
        initComponents();
        new CancelBookingStaffController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelBookingStaffPanel = new javax.swing.JPanel();
        bookingID = new javax.swing.JTextField();
        confirmCancel = new javax.swing.JButton();
        cancelBooking = new javax.swing.JLabel();
        bookingDetailsPrompt = new javax.swing.JLabel();
        returnPreviousMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        cancelBookingStaffPanel.setBackground(new java.awt.Color(255, 255, 255));

        bookingID.setBackground(new java.awt.Color(255, 204, 255));
        bookingID.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        bookingID.setForeground(new java.awt.Color(102, 102, 102));
        bookingID.setText("Enter booking ID you want to cancel");

        confirmCancel.setBackground(new java.awt.Color(153, 0, 153));
        confirmCancel.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        confirmCancel.setForeground(new java.awt.Color(255, 255, 255));
        confirmCancel.setText("Confirm");

        cancelBooking.setBackground(new java.awt.Color(255, 255, 255));
        cancelBooking.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        cancelBooking.setForeground(new java.awt.Color(0, 0, 0));
        cancelBooking.setText("Cancel Booking");
        cancelBooking.setPreferredSize(new java.awt.Dimension(220, 50));

        bookingDetailsPrompt.setBackground(new java.awt.Color(255, 255, 255));
        bookingDetailsPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        bookingDetailsPrompt.setForeground(new java.awt.Color(0, 0, 0));
        bookingDetailsPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookingDetailsPrompt.setText("Enter booking details");

        returnPreviousMenu.setFont(new java.awt.Font("STSong", 1, 18)); // NOI18N
        returnPreviousMenu.setForeground(new java.awt.Color(153, 0, 153));
        returnPreviousMenu.setText("Return");

        javax.swing.GroupLayout cancelBookingStaffPanelLayout = new javax.swing.GroupLayout(cancelBookingStaffPanel);
        cancelBookingStaffPanel.setLayout(cancelBookingStaffPanelLayout);
        cancelBookingStaffPanelLayout.setHorizontalGroup(
            cancelBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cancelBookingStaffPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cancelBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cancelBookingStaffPanelLayout.createSequentialGroup()
                        .addComponent(cancelBooking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cancelBookingStaffPanelLayout.createSequentialGroup()
                        .addComponent(returnPreviousMenu)
                        .addGap(209, 209, 209))))
            .addGroup(cancelBookingStaffPanelLayout.createSequentialGroup()
                .addGroup(cancelBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(confirmCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cancelBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cancelBookingStaffPanelLayout.createSequentialGroup()
                            .addGap(163, 163, 163)
                            .addComponent(bookingDetailsPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(cancelBookingStaffPanelLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(bookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        cancelBookingStaffPanelLayout.setVerticalGroup(
            cancelBookingStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelBookingStaffPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(cancelBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(bookingDetailsPrompt)
                .addGap(69, 69, 69)
                .addComponent(bookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(returnPreviousMenu)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        getContentPane().add(cancelBookingStaffPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookingDetailsPrompt;
    private javax.swing.JTextField bookingID;
    private javax.swing.JLabel cancelBooking;
    private javax.swing.JPanel cancelBookingStaffPanel;
    private javax.swing.JButton confirmCancel;
    private javax.swing.JButton returnPreviousMenu;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTextField getBookingID() {
        return bookingID;
    }

    public javax.swing.JButton getConfirmCancel() {
        return confirmCancel;
    }

    public javax.swing.JButton getReturnPreviousMenu() {
        return returnPreviousMenu;
    }
}
