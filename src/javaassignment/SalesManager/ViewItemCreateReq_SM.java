package javaassignment.SalesManager;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javaassignment.PurchaseManager.Requisition;
import javaassignment.PurchaseManager.RequisitionDAOImpl;
import javaassignment.Admin.AdminDAO;
import javaassignment.Admin.AdminDAOImpl;
import javaassignment.Admin.User;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class ViewItemCreateReq_SM extends javax.swing.JFrame {
    private final ItemsDAO itemsDAO;
    private String loggedInUser;
    /**
     * Creates new form ViewSalesItemPage_SM
     */
    public ViewItemCreateReq_SM(String loggedInUser) {
        
        initComponents();
        this.loggedInUser = loggedInUser;
        
        
        ItemCodeField.setEnabled(false);
        ItemNameField.setEnabled(false);
        CurrentQuantityField.setEnabled(false);
        
        this.itemsDAO = new ItemsDAOImpl();
        
        
        clearFields();
        loadItemsToTable();
    }
    
    
            
    public ViewItemCreateReq_SM(){
        this.itemsDAO = null;
        initComponents();
        
    }
    
    private void clearFields() {
        ProposedQuantityField.setText("");
        UserIDField.setText("");
        
    }
    
    private void loadItemsToTable() {
        try {
            // Fetch items using DAO
            List<Items> items = itemsDAO.viewItems();

            // Get the table model
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();

            // Clear any existing rows
            tableModel.setRowCount(0);

            // Add each item to the table
            for (Items item : items) {
                tableModel.addRow(new Object[]{
                    item.getItemCode(),
                    item.getItemName(),            // Item name
                    item.getQuantity(),            // Quantity
                    item.getPrice(),
                    item.getSupplierID(),
                    item.isNeedReorder() ? "Yes" : "No"  // Below reorder threshold
                    
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void updateTable(List<Items> items) {
    DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
    tableModel.setRowCount(0); // Clear existing rows

    for (Items item : items) {
        tableModel.addRow(new Object[]{
            item.getItemCode(),
            item.getItemName(),
            item.getQuantity(),
            item.getPrice(),
            item.getSupplierID(),
            item.isNeedReorder() ? "Yes" : "No"
        });
    }
}




    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BackBTN = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ItemCodeField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ItemNameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CurrentQuantityField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ProposedQuantityField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        UserIDField = new javax.swing.JTextField();
        SubmitRequisition = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        showReorderBtn = new javax.swing.JButton();
        refreshTableBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        itemCodeSearch = new javax.swing.JTextField();
        itemCodeSearchBtn = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        itemNameSearch = new javax.swing.JTextField();
        searchItemNameBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Create Requisition Form");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Name", "Quantity", "Price", "Supplier ID", "Need Reorder?"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 530, 240));

        BackBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BackBTN.setText("Back");
        BackBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBTNActionPerformed(evt);
            }
        });
        jPanel1.add(BackBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel2.setText("Please select row to fill in Requisition Form.");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 350, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Items List");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        ItemCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemCodeFieldActionPerformed(evt);
            }
        });
        jPanel1.add(ItemCodeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, 280, -1));

        jLabel5.setText("Item Code");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 60, 20));
        jPanel1.add(ItemNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, 280, -1));

        jLabel6.setText("Item Name");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 200, 60, 20));
        jPanel1.add(CurrentQuantityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, 70, -1));

        jLabel7.setText("Proposed Quantity");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, 110, 20));
        jPanel1.add(ProposedQuantityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 240, 70, -1));

        jLabel8.setText("UserID");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 280, 90, 20));

        jLabel9.setText("Current Quantity");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 90, 20));

        UserIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserIDFieldActionPerformed(evt);
            }
        });
        jPanel1.add(UserIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, 260, -1));

        SubmitRequisition.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SubmitRequisition.setText("Submit Requisition");
        SubmitRequisition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitRequisitionActionPerformed(evt);
            }
        });
        jPanel1.add(SubmitRequisition, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(5, 340));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, -1, 410));

        showReorderBtn.setText("Show only need Reorder");
        showReorderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showReorderBtnActionPerformed(evt);
            }
        });
        jPanel1.add(showReorderBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, -1, -1));

        refreshTableBtn.setText("Refresh Table");
        refreshTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTableBtnActionPerformed(evt);
            }
        });
        jPanel1.add(refreshTableBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, -1, -1));

        jLabel4.setText("Search by Item Code");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));
        jPanel1.add(itemCodeSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 90, -1));

        itemCodeSearchBtn.setText("Search (Item Code)");
        itemCodeSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCodeSearchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(itemCodeSearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, -1, -1));

        jLabel13.setText("Search by Item Name");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, -1, -1));
        jPanel1.add(itemNameSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 90, -1));

        searchItemNameBtn.setText("Search (Item Name)");
        searchItemNameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchItemNameBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchItemNameBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void BackBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBTNActionPerformed
       SalesManagerMainPage SMM = new SalesManagerMainPage(loggedInUser);
       SMM.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_BackBTNActionPerformed

    private void SubmitRequisitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitRequisitionActionPerformed
    String itemCode = ItemCodeField.getText();
    String itemName = ItemNameField.getText();
    String currentQuantity = CurrentQuantityField.getText();
    String proposedQuantity = ProposedQuantityField.getText();
    String userId = UserIDField.getText();

    if (itemCode.isEmpty() || itemName.isEmpty() || currentQuantity.isEmpty() || proposedQuantity.isEmpty() || userId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Integer.parseInt(proposedQuantity);
    } catch (NumberFormatException e){
        JOptionPane.showMessageDialog(this, "Proposed Quantity must be valid integer.");
        return;
    }

    try {
        AdminDAO adminDAO = new AdminDAOImpl();
        User user = adminDAO.getUserById(userId);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid UserID. Please enter a valid UserID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a Requisition object
        Requisition requisition = new Requisition(itemCode, itemName, currentQuantity, proposedQuantity, userId, loggedInUser);

        // Save requisition using DAO
        RequisitionDAOImpl requisitionDAO = new RequisitionDAOImpl();
        boolean success = requisitionDAO.saveRequisition(itemCode, itemName, currentQuantity, proposedQuantity, userId);
        if (success) {
            JOptionPane.showMessageDialog(null, "Requisition submitted successfully.");
            writeToLog(loggedInUser," | Requisition created | ","SUCCESS");
        } 
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    clearFields();
    
    }//GEN-LAST:event_SubmitRequisitionActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // Get the selected row index
    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow != -1) { // Ensure a row is selected
        // Fetch data from the table model
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();

        // Populate text fields with data from the selected row
        ItemCodeField.setText(tableModel.getValueAt(selectedRow, 0).toString());
        ItemNameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
        CurrentQuantityField.setText(tableModel.getValueAt(selectedRow, 2).toString());
        ProposedQuantityField.setText(""); // Clear this field for user input
        UserIDField.setText(""); // Optional: Clear or prepopulate this field
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void ItemCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ItemCodeFieldActionPerformed

    private void refreshTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTableBtnActionPerformed
        loadItemsToTable();
    }//GEN-LAST:event_refreshTableBtnActionPerformed

    private void itemCodeSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCodeSearchBtnActionPerformed
        String itemCode = itemCodeSearch.getText();
    if (!itemCode.isEmpty()) {
        try {
            List<Items> filteredItems = itemsDAO.searchItemsByCode(itemCode);
            updateTable(filteredItems);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please enter an Item Code to search.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    }//GEN-LAST:event_itemCodeSearchBtnActionPerformed

    private void searchItemNameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchItemNameBtnActionPerformed
        String itemName = itemNameSearch.getText();
    if (!itemName.isEmpty()) {
        try {
            List<Items> filteredItems = itemsDAO.searchItemsByName(itemName);
            updateTable(filteredItems);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please enter an Item Name to search.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_searchItemNameBtnActionPerformed

    private void showReorderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showReorderBtnActionPerformed
        try {
        List<Items> reorderItems = itemsDAO.getItemsNeedingReorder();
        updateTable(reorderItems);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_showReorderBtnActionPerformed

    private void UserIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserIDFieldActionPerformed

    
    public void writeToLog(String uniqueId, String description, String status) {
        try {
                File logFilePath = new File("src/Databases/Log.txt");
                int counter = 1;

                // Create log.txt if it doesn't exist
                if (!logFilePath.exists()) {
                    logFilePath.createNewFile();
            }

            // Read existing log entries and calculate the counter
            try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    counter++;  // Increment the counter for each existing line
                }
            } catch (IOException e) {
                System.err.println("Error reading log file: " + e.getMessage());
            }

            // Prepare the log entry with the counter
            String logEntry = counter + " | "+ loggedInUser + description + status;

            // Append log entry
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFilePath, true))) {
                logWriter.write(logEntry);
                logWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(ViewItemCreateReq_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        ViewItemCreateReq_SM frame = new ViewItemCreateReq_SM();
        frame.setVisible(true); 

    });
}

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBTN;
    private javax.swing.JTextField CurrentQuantityField;
    private javax.swing.JTextField ItemCodeField;
    private javax.swing.JTextField ItemNameField;
    private javax.swing.JTextField ProposedQuantityField;
    private javax.swing.JButton SubmitRequisition;
    private javax.swing.JTextField UserIDField;
    private javax.swing.JTextField itemCodeSearch;
    private javax.swing.JButton itemCodeSearchBtn;
    private javax.swing.JTextField itemNameSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshTableBtn;
    private javax.swing.JButton searchItemNameBtn;
    private javax.swing.JButton showReorderBtn;
    // End of variables declaration//GEN-END:variables
}
