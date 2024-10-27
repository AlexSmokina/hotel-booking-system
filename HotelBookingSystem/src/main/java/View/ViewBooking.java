/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ViewBookingController;

/**
 *
 * @author alex
 */
public class ViewBooking extends javax.swing.JFrame {

    /**
     * Creates new form DisplayBooking
     */
    public ViewBooking() {
        initComponents();
        new ViewBookingController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayBookingPanel = new javax.swing.JPanel();
        bookings = new javax.swing.JLabel();
        scrollBookingsPanel = new javax.swing.JScrollPane();
        bookingViewArea = new javax.swing.JTextArea();
        returnPreviousMenu = new javax.swing.JButton();
        enterGuestName = new javax.swing.JTextField();
        search = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        displayBookingPanel.setBackground(new java.awt.Color(255, 204, 255));

        bookings.setBackground(new java.awt.Color(255, 204, 255));
        bookings.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        bookings.setForeground(new java.awt.Color(0, 0, 0));
        bookings.setText("Bookings");
        bookings.setPreferredSize(new java.awt.Dimension(220, 50));

        bookingViewArea.setColumns(20);
        bookingViewArea.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        bookingViewArea.setRows(5);
        bookingViewArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Booking details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Helvetica Neue", 1, 13), new java.awt.Color(153, 0, 153))); // NOI18N
        scrollBookingsPanel.setViewportView(bookingViewArea);

        returnPreviousMenu.setFont(new java.awt.Font("STSong", 1, 18)); // NOI18N
        returnPreviousMenu.setForeground(new java.awt.Color(153, 0, 153));
        returnPreviousMenu.setText("Return");

        enterGuestName.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        enterGuestName.setForeground(new java.awt.Color(102, 102, 102));
        enterGuestName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterGuestName.setText("Enter your guest username to view details");

        search.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        search.setForeground(new java.awt.Color(153, 0, 153));
        search.setText("Search");

        javax.swing.GroupLayout displayBookingPanelLayout = new javax.swing.GroupLayout(displayBookingPanel);
        displayBookingPanel.setLayout(displayBookingPanelLayout);
        displayBookingPanelLayout.setHorizontalGroup(
            displayBookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollBookingsPanel, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(displayBookingPanelLayout.createSequentialGroup()
                .addGroup(displayBookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayBookingPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(enterGuestName, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(displayBookingPanelLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(bookings, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayBookingPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(returnPreviousMenu)
                .addGap(206, 206, 206))
        );
        displayBookingPanelLayout.setVerticalGroup(
            displayBookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayBookingPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(bookings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayBookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(enterGuestName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBookingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnPreviousMenu)
                .addContainerGap())
        );

        getContentPane().add(displayBookingPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bookingViewArea;
    private javax.swing.JLabel bookings;
    private javax.swing.JPanel displayBookingPanel;
    private javax.swing.JTextField enterGuestName;
    private javax.swing.JButton returnPreviousMenu;
    private javax.swing.JScrollPane scrollBookingsPanel;
    private javax.swing.JButton search;
    // End of variables declaration//GEN-END:variables

    
    public javax.swing.JTextField getEnterGuestName() {
        return enterGuestName;
    }

    public javax.swing.JTextArea getBookingViewArea() {
        return bookingViewArea;
    }

    public javax.swing.JButton getReturnPreviousMenu() {
        return returnPreviousMenu;
    }

    public javax.swing.JButton getSearch() {
        return search;
    }
}
