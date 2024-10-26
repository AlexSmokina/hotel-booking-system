/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.AddNewRoomController;

/**
 *
 * @author alex
 */
public class AddNewRoom extends javax.swing.JFrame {

    /**
     * Creates new form NewRoom
     */
    public AddNewRoom() {
        initComponents();
        new AddNewRoomController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addNewRoomPanel = new javax.swing.JPanel();
        newRoom = new javax.swing.JLabel();
        createNewRoomPrompt = new javax.swing.JLabel();
        hotelID = new javax.swing.JTextField();
        addNewRoom = new javax.swing.JButton();
        returnPreviousMenu = new javax.swing.JButton();
        roomType = new javax.swing.JComboBox<>();
        chooseRoomType = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        addNewRoomPanel.setBackground(new java.awt.Color(255, 255, 255));

        newRoom.setBackground(new java.awt.Color(255, 255, 255));
        newRoom.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        newRoom.setForeground(new java.awt.Color(0, 0, 0));
        newRoom.setText("New Room");
        newRoom.setPreferredSize(new java.awt.Dimension(220, 50));

        createNewRoomPrompt.setBackground(new java.awt.Color(255, 255, 255));
        createNewRoomPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        createNewRoomPrompt.setForeground(new java.awt.Color(0, 0, 0));
        createNewRoomPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createNewRoomPrompt.setText("Create new room");

        hotelID.setBackground(new java.awt.Color(255, 204, 255));
        hotelID.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        hotelID.setForeground(new java.awt.Color(102, 102, 102));
        hotelID.setText("Enter Hotel ID");

        addNewRoom.setBackground(new java.awt.Color(153, 0, 153));
        addNewRoom.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        addNewRoom.setForeground(new java.awt.Color(255, 255, 255));
        addNewRoom.setText("Add New Room");

        returnPreviousMenu.setFont(new java.awt.Font("STSong", 1, 18)); // NOI18N
        returnPreviousMenu.setForeground(new java.awt.Color(153, 0, 153));
        returnPreviousMenu.setText("Return");

        roomType.setBackground(new java.awt.Color(255, 204, 255));
        roomType.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        roomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "STANDARD", "PREMIUM", "SUITE" }));

        chooseRoomType.setBackground(new java.awt.Color(255, 255, 255));
        chooseRoomType.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        chooseRoomType.setForeground(new java.awt.Color(0, 0, 0));
        chooseRoomType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseRoomType.setText("Choose Room Type");

        javax.swing.GroupLayout addNewRoomPanelLayout = new javax.swing.GroupLayout(addNewRoomPanel);
        addNewRoomPanel.setLayout(addNewRoomPanelLayout);
        addNewRoomPanelLayout.setHorizontalGroup(
            addNewRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewRoomPanelLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(addNewRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewRoomPanelLayout.createSequentialGroup()
                        .addComponent(newRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewRoomPanelLayout.createSequentialGroup()
                        .addGroup(addNewRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(addNewRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(hotelID, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addNewRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addNewRoomPanelLayout.createSequentialGroup()
                                .addComponent(chooseRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roomType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))))
            .addGroup(addNewRoomPanelLayout.createSequentialGroup()
                .addGroup(addNewRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addNewRoomPanelLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(createNewRoomPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addNewRoomPanelLayout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(returnPreviousMenu)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        addNewRoomPanelLayout.setVerticalGroup(
            addNewRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewRoomPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(newRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(createNewRoomPrompt)
                .addGap(50, 50, 50)
                .addGroup(addNewRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(hotelID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addNewRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(returnPreviousMenu)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        getContentPane().add(addNewRoomPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewRoom;
    private javax.swing.JPanel addNewRoomPanel;
    private javax.swing.JLabel chooseRoomType;
    private javax.swing.JLabel createNewRoomPrompt;
    private javax.swing.JTextField hotelID;
    private javax.swing.JLabel newRoom;
    private javax.swing.JButton returnPreviousMenu;
    private javax.swing.JComboBox<String> roomType;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getAddNewRoom() {
        return addNewRoom;
    }

    public javax.swing.JTextField getHotelID() {
        return hotelID;
    }

    public javax.swing.JButton getReturnPreviousMenu() {
        return returnPreviousMenu;
    }

    public String getRoomType() {
        return (String) roomType.getSelectedItem();
    }
}
