/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.HotelManagementController;

/**
 *
 * @author alex
 */
public class HotelManagement extends javax.swing.JFrame {

    /**
     * Creates new form HotelManagement
     */
    public HotelManagement() {
        initComponents();
        new HotelManagementController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hotelManagementPanel = new javax.swing.JPanel();
        hotelManagementMenu = new javax.swing.JLabel();
        chooseOptionPrompt = new javax.swing.JLabel();
        addNewHotel = new javax.swing.JButton();
        updateHotelDetails = new javax.swing.JButton();
        viewAllHotels = new javax.swing.JButton();
        previousMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        hotelManagementPanel.setBackground(new java.awt.Color(255, 255, 255));

        hotelManagementMenu.setBackground(new java.awt.Color(255, 255, 255));
        hotelManagementMenu.setFont(new java.awt.Font("STSong", 1, 40)); // NOI18N
        hotelManagementMenu.setForeground(new java.awt.Color(0, 0, 0));
        hotelManagementMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hotelManagementMenu.setText("Hotel Management Menu");
        hotelManagementMenu.setPreferredSize(new java.awt.Dimension(220, 50));

        chooseOptionPrompt.setBackground(new java.awt.Color(255, 255, 255));
        chooseOptionPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        chooseOptionPrompt.setForeground(new java.awt.Color(0, 0, 0));
        chooseOptionPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseOptionPrompt.setText("Choose your option");

        addNewHotel.setBackground(new java.awt.Color(255, 204, 255));
        addNewHotel.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        addNewHotel.setForeground(new java.awt.Color(0, 0, 0));
        addNewHotel.setText("Add New Hotel");

        updateHotelDetails.setBackground(new java.awt.Color(255, 204, 255));
        updateHotelDetails.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        updateHotelDetails.setForeground(new java.awt.Color(0, 0, 0));
        updateHotelDetails.setText("Update Hotel Details");

        viewAllHotels.setBackground(new java.awt.Color(255, 204, 255));
        viewAllHotels.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        viewAllHotels.setForeground(new java.awt.Color(0, 0, 0));
        viewAllHotels.setText("View All Hotels");

        previousMenu.setBackground(new java.awt.Color(153, 0, 153));
        previousMenu.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        previousMenu.setForeground(new java.awt.Color(255, 255, 255));
        previousMenu.setText("Return to Previous Menu");

        javax.swing.GroupLayout hotelManagementPanelLayout = new javax.swing.GroupLayout(hotelManagementPanel);
        hotelManagementPanel.setLayout(hotelManagementPanelLayout);
        hotelManagementPanelLayout.setHorizontalGroup(
            hotelManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hotelManagementPanelLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(hotelManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hotelManagementPanelLayout.createSequentialGroup()
                        .addGroup(hotelManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateHotelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNewHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewAllHotels, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(previousMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hotelManagementMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hotelManagementPanelLayout.createSequentialGroup()
                        .addComponent(chooseOptionPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172))))
        );
        hotelManagementPanelLayout.setVerticalGroup(
            hotelManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hotelManagementPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(hotelManagementMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(chooseOptionPrompt)
                .addGap(42, 42, 42)
                .addComponent(addNewHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateHotelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewAllHotels, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(previousMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        getContentPane().add(hotelManagementPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JButton getAddNewHotelButton() {
        return addNewHotel;
    }

    public javax.swing.JButton getPreviousMenuButton() {
        return previousMenu;
    }

    public javax.swing.JButton getUpdateHotelDetailsButton() {
        return updateHotelDetails;
    }

    public javax.swing.JButton getViewAllHotelsButton() {
        return viewAllHotels;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewHotel;
    private javax.swing.JLabel chooseOptionPrompt;
    private javax.swing.JLabel hotelManagementMenu;
    private javax.swing.JPanel hotelManagementPanel;
    private javax.swing.JButton previousMenu;
    private javax.swing.JButton updateHotelDetails;
    private javax.swing.JButton viewAllHotels;
    // End of variables declaration//GEN-END:variables

    
    
}
