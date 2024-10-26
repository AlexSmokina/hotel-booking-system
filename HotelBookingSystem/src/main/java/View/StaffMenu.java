/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.StaffMenuController;

/**
 *
 * @author minthihakoko
 */
public class StaffMenu extends javax.swing.JFrame {

    /**
     * Creates new form StaffMenu
     */
    public StaffMenu() {
        initComponents();
        new StaffMenuController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        staffPanel = new javax.swing.JPanel();
        guestMenu = new javax.swing.JLabel();
        choseOptionPrompt = new javax.swing.JLabel();
        bookingManagement = new javax.swing.JButton();
        roomManagement = new javax.swing.JButton();
        hotelManagement = new javax.swing.JButton();
        logoutStaffMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        staffPanel.setBackground(new java.awt.Color(255, 255, 255));

        guestMenu.setBackground(new java.awt.Color(255, 255, 255));
        guestMenu.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        guestMenu.setForeground(new java.awt.Color(0, 0, 0));
        guestMenu.setText("Staff Menu");
        guestMenu.setPreferredSize(new java.awt.Dimension(220, 50));

        choseOptionPrompt.setBackground(new java.awt.Color(255, 255, 255));
        choseOptionPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        choseOptionPrompt.setForeground(new java.awt.Color(0, 0, 0));
        choseOptionPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        choseOptionPrompt.setText("Choose your option");

        bookingManagement.setBackground(new java.awt.Color(255, 204, 255));
        bookingManagement.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        bookingManagement.setForeground(new java.awt.Color(0, 0, 0));
        bookingManagement.setText("Booking Management");

        roomManagement.setBackground(new java.awt.Color(255, 204, 255));
        roomManagement.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        roomManagement.setForeground(new java.awt.Color(0, 0, 0));
        roomManagement.setText("Room Management");

        hotelManagement.setBackground(new java.awt.Color(255, 204, 255));
        hotelManagement.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        hotelManagement.setForeground(new java.awt.Color(0, 0, 0));
        hotelManagement.setText("Hotel Management");

        logoutStaffMenu.setBackground(new java.awt.Color(153, 0, 153));
        logoutStaffMenu.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        logoutStaffMenu.setForeground(new java.awt.Color(255, 255, 255));
        logoutStaffMenu.setText("Logout");

        javax.swing.GroupLayout staffPanelLayout = new javax.swing.GroupLayout(staffPanel);
        staffPanel.setLayout(staffPanelLayout);
        staffPanelLayout.setHorizontalGroup(
            staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffPanelLayout.createSequentialGroup()
                .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(staffPanelLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(guestMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(staffPanelLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(choseOptionPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(staffPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roomManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hotelManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookingManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logoutStaffMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        staffPanelLayout.setVerticalGroup(
            staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(guestMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(choseOptionPrompt)
                .addGap(38, 38, 38)
                .addComponent(hotelManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roomManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bookingManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutStaffMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        getContentPane().add(staffPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JButton getBookingManagementButton(){
        return bookingManagement;
    }
    
    public javax.swing.JButton getHotelManagementButton(){
        return hotelManagement;
    }
    
    public javax.swing.JButton getRoomManagementButton(){
        return roomManagement;
    }
    
    public javax.swing.JButton getLogoutStaffMenuButton(){
        return logoutStaffMenu;
    }
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bookingManagement;
    private javax.swing.JLabel choseOptionPrompt;
    private javax.swing.JLabel guestMenu;
    private javax.swing.JButton hotelManagement;
    private javax.swing.JButton logoutStaffMenu;
    private javax.swing.JButton roomManagement;
    private javax.swing.JPanel staffPanel;
    // End of variables declaration//GEN-END:variables
}
