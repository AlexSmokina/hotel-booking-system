/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ViewAllHotelsController;

/**
 *
 * @author alex
 */
public class ViewAllHotels extends javax.swing.JFrame {

    /**
     * Creates new form ViewAllHotels
     */
    public ViewAllHotels() {
        initComponents();
        new ViewAllHotelsController(this);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hotelViewPanel = new javax.swing.JPanel();
        hotels = new javax.swing.JLabel();
        scrollHotelPanel = new javax.swing.JScrollPane();
        hotelViewArea = new javax.swing.JTextArea();
        returnPreviousMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        hotelViewPanel.setBackground(new java.awt.Color(255, 204, 255));

        hotels.setBackground(new java.awt.Color(255, 204, 255));
        hotels.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        hotels.setForeground(new java.awt.Color(0, 0, 0));
        hotels.setText("Hotels");
        hotels.setPreferredSize(new java.awt.Dimension(220, 50));

        hotelViewArea.setColumns(20);
        hotelViewArea.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        hotelViewArea.setRows(5);
        hotelViewArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scrollHotelPanel.setViewportView(hotelViewArea);

        returnPreviousMenu.setFont(new java.awt.Font("STSong", 1, 18)); // NOI18N
        returnPreviousMenu.setForeground(new java.awt.Color(153, 0, 153));
        returnPreviousMenu.setText("Return");

        javax.swing.GroupLayout hotelViewPanelLayout = new javax.swing.GroupLayout(hotelViewPanel);
        hotelViewPanel.setLayout(hotelViewPanelLayout);
        hotelViewPanelLayout.setHorizontalGroup(
            hotelViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollHotelPanel, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(hotelViewPanelLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(hotels, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hotelViewPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(returnPreviousMenu)
                .addGap(207, 207, 207))
        );
        hotelViewPanelLayout.setVerticalGroup(
            hotelViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hotelViewPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(hotels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollHotelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnPreviousMenu)
                .addContainerGap())
        );

        getContentPane().add(hotelViewPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JTextArea getHotelViewArea(){
        return hotelViewArea;
    }
    
    public javax.swing.JButton getReturnPreviousMenu(){
        return returnPreviousMenu;
    }
        



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea hotelViewArea;
    private javax.swing.JPanel hotelViewPanel;
    private javax.swing.JLabel hotels;
    private javax.swing.JButton returnPreviousMenu;
    private javax.swing.JScrollPane scrollHotelPanel;
    // End of variables declaration//GEN-END:variables
}
