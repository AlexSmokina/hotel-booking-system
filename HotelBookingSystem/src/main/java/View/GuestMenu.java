/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.GuestMenuController;
import java.awt.Color;

/**
 *
 * @author minthihakoko
 */
public class GuestMenu extends javax.swing.JFrame {

    /**
     * Creates new form GuestMenu
     */
    public GuestMenu() {
        initComponents();
        new GuestMenuController(this);
        // Setting the background color
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guestPanel = new javax.swing.JPanel();
        guestMenu = new javax.swing.JLabel();
        choseOptionPrompt = new javax.swing.JLabel();
        bookRoom = new javax.swing.JButton();
        cancelBooking = new javax.swing.JButton();
        extendBooking = new javax.swing.JButton();
        changeRoom = new javax.swing.JButton();
        logoutGuestMenu = new javax.swing.JButton();
        viewBookings = new javax.swing.JButton();
        displayInvoice = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        guestPanel.setBackground(new java.awt.Color(255, 255, 255));
        guestPanel.setPreferredSize(new java.awt.Dimension(500, 600));

        guestMenu.setBackground(new java.awt.Color(255, 255, 255));
        guestMenu.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        guestMenu.setForeground(new java.awt.Color(0, 0, 0));
        guestMenu.setText("Guest Menu");

        choseOptionPrompt.setBackground(new java.awt.Color(255, 255, 255));
        choseOptionPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        choseOptionPrompt.setForeground(new java.awt.Color(0, 0, 0));
        choseOptionPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        choseOptionPrompt.setText("Choose your option");

        bookRoom.setBackground(new java.awt.Color(255, 204, 255));
        bookRoom.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        bookRoom.setForeground(new java.awt.Color(0, 0, 0));
        bookRoom.setText("Book a room");

        cancelBooking.setBackground(new java.awt.Color(255, 204, 255));
        cancelBooking.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        cancelBooking.setForeground(new java.awt.Color(0, 0, 0));
        cancelBooking.setText("Cancel booking");

        extendBooking.setBackground(new java.awt.Color(255, 204, 255));
        extendBooking.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        extendBooking.setForeground(new java.awt.Color(0, 0, 0));
        extendBooking.setText("Extend Booking");

        changeRoom.setBackground(new java.awt.Color(255, 204, 255));
        changeRoom.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        changeRoom.setForeground(new java.awt.Color(0, 0, 0));
        changeRoom.setText("Change Room");

        logoutGuestMenu.setBackground(new java.awt.Color(153, 0, 153));
        logoutGuestMenu.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        logoutGuestMenu.setForeground(new java.awt.Color(255, 255, 255));
        logoutGuestMenu.setText("Logout");

        viewBookings.setBackground(new java.awt.Color(255, 204, 255));
        viewBookings.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        viewBookings.setForeground(new java.awt.Color(0, 0, 0));
        viewBookings.setText("View Bookings");

        displayInvoice.setBackground(new java.awt.Color(255, 204, 255));
        displayInvoice.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        displayInvoice.setForeground(new java.awt.Color(0, 0, 0));
        displayInvoice.setText("Display Invoice");

        javax.swing.GroupLayout guestPanelLayout = new javax.swing.GroupLayout(guestPanel);
        guestPanel.setLayout(guestPanelLayout);
        guestPanelLayout.setHorizontalGroup(
            guestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, guestPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(choseOptionPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
            .addGroup(guestPanelLayout.createSequentialGroup()
                .addGroup(guestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(guestPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(guestMenu))
                    .addGroup(guestPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(guestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoutGuestMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(guestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(guestPanelLayout.createSequentialGroup()
                                    .addComponent(bookRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(guestPanelLayout.createSequentialGroup()
                                    .addComponent(extendBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(changeRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(viewBookings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(displayInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        guestPanelLayout.setVerticalGroup(
            guestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guestPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(guestMenu)
                .addGap(18, 18, 18)
                .addComponent(choseOptionPrompt)
                .addGap(27, 27, 27)
                .addGroup(guestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(guestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extendBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(viewBookings, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(displayInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutGuestMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        getContentPane().add(guestPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JButton getBookRoomButton() {
        return bookRoom;
    }

    public javax.swing.JButton getCancelBookingButton() {
        return cancelBooking;
    }

    public javax.swing.JButton getChangeRoomButton() {
        return changeRoom;
    }

    public javax.swing.JButton getExtendBookingButton() {
        return extendBooking;
    }

    public javax.swing.JButton getLogoutGuestMenuButton() {
        return logoutGuestMenu;
    }
    
    public javax.swing.JButton getViewBookings() {
        return viewBookings;
    }
    
    public javax.swing.JButton getDisplayInvoice() {
        return displayInvoice;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bookRoom;
    private javax.swing.JButton cancelBooking;
    private javax.swing.JButton changeRoom;
    private javax.swing.JLabel choseOptionPrompt;
    private javax.swing.JButton displayInvoice;
    private javax.swing.JButton extendBooking;
    private javax.swing.JLabel guestMenu;
    private javax.swing.JPanel guestPanel;
    private javax.swing.JButton logoutGuestMenu;
    private javax.swing.JButton viewBookings;
    // End of variables declaration//GEN-END:variables

    
}
