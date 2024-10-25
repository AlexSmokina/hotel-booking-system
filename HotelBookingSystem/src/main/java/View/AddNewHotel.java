/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author alex
 */
public class AddNewHotel extends javax.swing.JFrame {

    /**
     * Creates new form NewHotel
     */
    public AddNewHotel() {
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

        newHotelPanel = new javax.swing.JPanel();
        guestMenu = new javax.swing.JLabel();
        createNewHotelPrompt = new javax.swing.JLabel();
        hotelName = new javax.swing.JTextField();
        hotelAddress = new javax.swing.JTextField();
        standardRoomsNumber = new javax.swing.JTextField();
        premiumRoomsNumber = new javax.swing.JTextField();
        numberOfSuites = new javax.swing.JTextField();
        addNewHotel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        newHotelPanel.setBackground(new java.awt.Color(255, 255, 255));

        guestMenu.setBackground(new java.awt.Color(255, 255, 255));
        guestMenu.setFont(new java.awt.Font("STSong", 1, 48)); // NOI18N
        guestMenu.setForeground(new java.awt.Color(0, 0, 0));
        guestMenu.setText("New Hotel");
        guestMenu.setPreferredSize(new java.awt.Dimension(220, 50));

        createNewHotelPrompt.setBackground(new java.awt.Color(255, 255, 255));
        createNewHotelPrompt.setFont(new java.awt.Font("STSong", 0, 18)); // NOI18N
        createNewHotelPrompt.setForeground(new java.awt.Color(0, 0, 0));
        createNewHotelPrompt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createNewHotelPrompt.setText("Create new hotel");

        hotelName.setBackground(new java.awt.Color(255, 204, 255));
        hotelName.setForeground(new java.awt.Color(102, 102, 102));
        hotelName.setText("Enter Hotel Name");

        hotelAddress.setBackground(new java.awt.Color(255, 204, 255));
        hotelAddress.setForeground(new java.awt.Color(102, 102, 102));
        hotelAddress.setText("Enter Hotel Address");

        standardRoomsNumber.setBackground(new java.awt.Color(255, 204, 255));
        standardRoomsNumber.setForeground(new java.awt.Color(102, 102, 102));
        standardRoomsNumber.setText("Enter Number of Standard Rooms");

        premiumRoomsNumber.setBackground(new java.awt.Color(255, 204, 255));
        premiumRoomsNumber.setForeground(new java.awt.Color(102, 102, 102));
        premiumRoomsNumber.setText("Enter Number of Premium Rooms");

        numberOfSuites.setBackground(new java.awt.Color(255, 204, 255));
        numberOfSuites.setForeground(new java.awt.Color(102, 102, 102));
        numberOfSuites.setText("Enter Number of Suites");

        addNewHotel.setBackground(new java.awt.Color(153, 0, 153));
        addNewHotel.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        addNewHotel.setForeground(new java.awt.Color(255, 255, 255));
        addNewHotel.setText("Add New Hotel");

        javax.swing.GroupLayout newHotelPanelLayout = new javax.swing.GroupLayout(newHotelPanel);
        newHotelPanel.setLayout(newHotelPanelLayout);
        newHotelPanelLayout.setHorizontalGroup(
            newHotelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newHotelPanelLayout.createSequentialGroup()
                .addGroup(newHotelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newHotelPanelLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(guestMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newHotelPanelLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(createNewHotelPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newHotelPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(newHotelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberOfSuites, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNewHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(premiumRoomsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(standardRoomsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hotelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(newHotelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(newHotelPanelLayout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(hotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE)))
        );
        newHotelPanelLayout.setVerticalGroup(
            newHotelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newHotelPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(guestMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createNewHotelPrompt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(hotelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(standardRoomsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(premiumRoomsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numberOfSuites, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addNewHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(newHotelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(newHotelPanelLayout.createSequentialGroup()
                    .addGap(188, 188, 188)
                    .addComponent(hotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(372, Short.MAX_VALUE)))
        );

        getContentPane().add(newHotelPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AddNewHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewHotel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewHotel;
    private javax.swing.JLabel createNewHotelPrompt;
    private javax.swing.JLabel guestMenu;
    private javax.swing.JTextField hotelAddress;
    private javax.swing.JTextField hotelName;
    private javax.swing.JPanel newHotelPanel;
    private javax.swing.JTextField numberOfSuites;
    private javax.swing.JTextField premiumRoomsNumber;
    private javax.swing.JTextField standardRoomsNumber;
    // End of variables declaration//GEN-END:variables
}
