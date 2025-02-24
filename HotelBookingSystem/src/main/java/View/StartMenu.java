/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.StartMenuController;

/**
 *
 * @author minthihakoko 
 */
public class StartMenu extends javax.swing.JFrame {

    /**
     * Creates new form StartMenu
     */
    public StartMenu() {
        initComponents();
        StartMenuController startMenuController = new StartMenuController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomePanel = new javax.swing.JPanel();
        signIn = new javax.swing.JButton();
        welcome = new javax.swing.JLabel();
        register = new javax.swing.JButton();
        registerLabel = new javax.swing.JLabel();
        hotelImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        welcomePanel.setBackground(new java.awt.Color(255, 255, 255));
        welcomePanel.setPreferredSize(new java.awt.Dimension(500, 600));

        signIn.setBackground(new java.awt.Color(153, 0, 153));
        signIn.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        signIn.setForeground(new java.awt.Color(255, 255, 255));
        signIn.setText("Register");

        welcome.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        welcome.setForeground(new java.awt.Color(0, 0, 0));
        welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcome.setText("Hotel Booking System");

        register.setBackground(new java.awt.Color(153, 0, 153));
        register.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        register.setForeground(new java.awt.Color(255, 255, 255));
        register.setText("Sign In");

        registerLabel.setBackground(new java.awt.Color(255, 255, 255));
        registerLabel.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        registerLabel.setForeground(new java.awt.Color(0, 0, 0));
        registerLabel.setText("Welcome to");

        hotelImage.setIcon(new javax.swing.ImageIcon("/Users/alex/Alex/AUT/4 Semester/COMP603/Assignment 1 - 2/hotel-booking-system/HotelBookingSystem/resources/hotel.png")); // NOI18N

        javax.swing.GroupLayout welcomePanelLayout = new javax.swing.GroupLayout(welcomePanel);
        welcomePanel.setLayout(welcomePanelLayout);
        welcomePanelLayout.setHorizontalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(welcome, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(signIn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(registerLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hotelImage)
                .addGap(149, 149, 149))
        );
        welcomePanelLayout.setVerticalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(registerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(welcome)
                .addGap(25, 25, 25)
                .addComponent(hotelImage, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signIn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101))
        );

        getContentPane().add(welcomePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public javax.swing.JButton getSignInButton(){
        return this.signIn;
    }
    
    public javax.swing.JButton getRegisterButton(){
        return this.register;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hotelImage;
    private javax.swing.JButton register;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JButton signIn;
    private javax.swing.JLabel welcome;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables
}
