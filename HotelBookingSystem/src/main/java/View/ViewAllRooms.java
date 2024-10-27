/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ViewAllRoomsController;

/**
 *
 * @author alex
 */
public class ViewAllRooms extends javax.swing.JFrame {

    /**
     * Creates new form ViewAllRooms
     */
    public ViewAllRooms() {
        initComponents();
        new ViewAllRoomsController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewRoomsPanel = new javax.swing.JPanel();
        rooms = new javax.swing.JLabel();
        scrollRoomPanel = new javax.swing.JScrollPane();
        roomDisplayArea = new javax.swing.JTextArea();
        hotelChoice = new javax.swing.JComboBox<>();
        returnPreviousMenu = new javax.swing.JButton();
        search = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        viewRoomsPanel.setBackground(new java.awt.Color(255, 204, 255));

        rooms.setBackground(new java.awt.Color(255, 204, 255));
        rooms.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        rooms.setForeground(new java.awt.Color(0, 0, 0));
        rooms.setText("Rooms");
        rooms.setPreferredSize(new java.awt.Dimension(220, 50));

        roomDisplayArea.setColumns(20);
        roomDisplayArea.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        roomDisplayArea.setRows(5);
        roomDisplayArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scrollRoomPanel.setViewportView(roomDisplayArea);

        hotelChoice.setBackground(new java.awt.Color(255, 204, 255));
        hotelChoice.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        hotelChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Auckland Skyline", "Queenstown Grand" }));

        returnPreviousMenu.setFont(new java.awt.Font("STSong", 1, 18)); // NOI18N
        returnPreviousMenu.setForeground(new java.awt.Color(153, 0, 153));
        returnPreviousMenu.setText("Return");

        search.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        search.setForeground(new java.awt.Color(153, 0, 153));
        search.setText("Search");

        javax.swing.GroupLayout viewRoomsPanelLayout = new javax.swing.GroupLayout(viewRoomsPanel);
        viewRoomsPanel.setLayout(viewRoomsPanelLayout);
        viewRoomsPanelLayout.setHorizontalGroup(
            viewRoomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollRoomPanel, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(viewRoomsPanelLayout.createSequentialGroup()
                .addGroup(viewRoomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hotelChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(viewRoomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewRoomsPanelLayout.createSequentialGroup()
                            .addGap(168, 168, 168)
                            .addComponent(rooms, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(viewRoomsPanelLayout.createSequentialGroup()
                            .addGap(206, 206, 206)
                            .addComponent(returnPreviousMenu))))
                .addGap(18, 18, 18)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        viewRoomsPanelLayout.setVerticalGroup(
            viewRoomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewRoomsPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(rooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(viewRoomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hotelChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollRoomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnPreviousMenu)
                .addContainerGap())
        );

        getContentPane().add(viewRoomsPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public javax.swing.JButton getReturnPreviousMenu(){
        return returnPreviousMenu;
    }
    
    public javax.swing.JTextArea getRoomDisplayArea(){
        return roomDisplayArea;
    } 
    
    public javax.swing.JComboBox<String> getHotelChoice() {
        return hotelChoice;
    }

    public javax.swing.JButton getSearch() {
        return search;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> hotelChoice;
    private javax.swing.JButton returnPreviousMenu;
    private javax.swing.JTextArea roomDisplayArea;
    private javax.swing.JLabel rooms;
    private javax.swing.JScrollPane scrollRoomPanel;
    private javax.swing.JButton search;
    private javax.swing.JPanel viewRoomsPanel;
    // End of variables declaration//GEN-END:variables

   
   
}
