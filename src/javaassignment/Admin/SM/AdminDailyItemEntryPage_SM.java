/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaassignment.Admin.SM;

import javaassignment.SalesManager.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vroom
 */
public class AdminDailyItemEntryPage_SM extends javax.swing.JFrame {
    private final ItemsDAO itemsDAO;
    private int selectedItemInitialQuantity;
    private double selectedItemPrice;
    private String loggedInUser;

    /**
     * Creates new form DailyItemEntryPage
     */
    public AdminDailyItemEntryPage_SM(String loggedInUser) {
         initComponents();
        this.itemsDAO = new ItemsDAOImpl();
        this.loggedInUser = loggedInUser;

        // Set the current date in the dateField
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateField.setText(today.format(formatter));
        dateField.setEnabled(false);

        // Set fields to be non-editable by default
        itemName.setEnabled(false);
        itemCode.setEnabled(false);
        initialQuantity.setEnabled(false);
        grossProfit.setEnabled(false);
        price.setEnabled(false);

        // Add listeners to calculate grossProfit
        addRealTimeListeners();

        // Load items into the table
        loadItemsToTable();
        
        
    }
    public AdminDailyItemEntryPage_SM(){
        this.itemsDAO = null;
        
    }
    
     
    private void loadItemsToTable() {
        try {
            // Fetch items using DAO
            List<Items> items = itemsDAO.viewItems();

            // Get the table model
            DefaultTableModel tableModel = (DefaultTableModel) listTable.getModel();

            // Clear any existing rows
            tableModel.setRowCount(0);

            for (Items item : items) {
            tableModel.addRow(new Object[]{
                item.getItemCode(),
                item.getItemName(),
                item.getQuantity(),
                item.getPrice()

    });
}

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
     private void addRealTimeListeners() {
        // Add a listener to quantitySold
        quantitySold.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateGrossProfit();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateGrossProfit();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateGrossProfit();
            }
        });
        
        // Add a listener to lossesField
        lossesField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateGrossProfit();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateGrossProfit();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateGrossProfit();
            }
        });
    }
     
     private void updateGrossProfit() {
    try {
        // Get the values from the text fields
        String quantitySoldText = quantitySold.getText();
        String lossesText = lossesField.getText();

        // Check if either field is empty
        if (quantitySoldText.isEmpty() || lossesText.isEmpty()) {
            grossProfit.setText(""); // Clear the gross profit field if any field is empty
            return;
        }

        // Try to parse the values as integers and doubles
        int quantitySoldValue = Integer.parseInt(quantitySoldText);
        int lossesValue = Integer.parseInt(lossesText);

        // Ensure quantitySold is not greater than initial quantity
        if (quantitySoldValue > selectedItemInitialQuantity) {
            grossProfit.setText("Error: Exceeds stock");
            return;
        }

        // Ensure losses do not exceed the remaining stock after sales
        int remainingStock = selectedItemInitialQuantity - quantitySoldValue;
        if (lossesValue > remainingStock) {
            grossProfit.setText("Error: Losses exceed remaining stock");
            return;
        }

        // Calculate gross profit
        double grossProfitValue = (quantitySoldValue - lossesValue) * selectedItemPrice;

        // Display the result in the grossProfit field
        grossProfit.setText(String.format("%.2f", grossProfitValue));
    } catch (NumberFormatException e) {
        // Handle invalid inputs gracefully
        grossProfit.setText("Invalid input");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        listTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dateField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        itemCode = new javax.swing.JTextField();
        itemName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        initialQuantity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        quantitySold = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        grossProfit = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lossesField = new javax.swing.JTextField();
        submitReportButton = new javax.swing.JButton();
        price = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Name", "Quantity", "Price"
            }
        ));
        listTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listTable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 400, 320));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Daily Sales Item Report");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, -1, -1));

        jLabel3.setText("Select Item you wish to create daily report for.");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Item List");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jLabel6.setText("Date of Report:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, -1, 20));

        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
            }
        });
        jPanel1.add(dateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 130, -1));

        jLabel7.setText("Item Code:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, -1, 20));

        itemCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCodeActionPerformed(evt);
            }
        });
        jPanel1.add(itemCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 130, -1));

        itemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameActionPerformed(evt);
            }
        });
        jPanel1.add(itemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 130, -1));

        jLabel8.setText("Item Name:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, 20));

        initialQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initialQuantityActionPerformed(evt);
            }
        });
        jPanel1.add(initialQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 130, -1));

        jLabel9.setText("Quantity Sold:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, -1, 20));

        jLabel10.setText("Initial Quantity:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, -1, 20));

        quantitySold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantitySoldActionPerformed(evt);
            }
        });
        jPanel1.add(quantitySold, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 80, -1));

        jLabel11.setText("Gross Profit:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, -1, 20));

        grossProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grossProfitActionPerformed(evt);
            }
        });
        jPanel1.add(grossProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 220, -1));

        backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        jPanel1.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel12.setText("Losses:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, -1, 20));

        lossesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lossesFieldActionPerformed(evt);
            }
        });
        jPanel1.add(lossesField, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 80, -1));

        submitReportButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        submitReportButton.setText("Submit Daily Report");
        submitReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitReportButtonActionPerformed(evt);
            }
        });
        jPanel1.add(submitReportButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, 350, -1));

        price.setText("Price");
        jPanel1.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 100, -1));

        jLabel1.setText("Price per Item");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed

    }//GEN-LAST:event_dateFieldActionPerformed

    private void itemCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemCodeActionPerformed

    private void itemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameActionPerformed

    private void initialQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initialQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_initialQuantityActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
       AdminSMPage SMP = new AdminSMPage(loggedInUser);
       SMP.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void listTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listTableMouseClicked
      // Get the selected row index
    int selectedRow = listTable.getSelectedRow();

    if (selectedRow != -1) { // Ensure a row is selected
        DefaultTableModel tableModel = (DefaultTableModel) listTable.getModel();

        // Populate text fields with data from the selected row
        itemCode.setText(tableModel.getValueAt(selectedRow, 0).toString());
        itemName.setText(tableModel.getValueAt(selectedRow, 1).toString());
        initialQuantity.setText(tableModel.getValueAt(selectedRow, 2).toString());
        price.setText(tableModel.getValueAt(selectedRow, 3).toString());

        // Save selected item's price and initial quantity for calculations
        selectedItemPrice = Double.parseDouble(tableModel.getValueAt(selectedRow, 3).toString());  // Adjust column index if needed
        selectedItemInitialQuantity = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
    }
    }//GEN-LAST:event_listTableMouseClicked

    private void quantitySoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantitySoldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantitySoldActionPerformed

    private void lossesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lossesFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lossesFieldActionPerformed

    private void grossProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grossProfitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grossProfitActionPerformed

    private void submitReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitReportButtonActionPerformed
        try {
        // Step 1: Collect the data
        String reportDate = dateField.getText();
        String Code = this.itemCode.getText();
        String Name = this.itemName.getText();
        Double Price = Double.valueOf(price.getText());
        int quantitySoldValue = Integer.parseInt(quantitySold.getText());
        int lossesValue = Integer.parseInt(lossesField.getText());

        // Step 2: Check if this report already exists in DAILY.txt
        boolean isDuplicate = checkDuplicateInDailyFile(reportDate, Code, Name);
        if (isDuplicate) {
            grossProfit.setText("Error: Duplicate record");
            JOptionPane.showMessageDialog(null, "Duplicate record found. Report not saved.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Don't proceed if duplicate exists
        }

        // Step 3: Calculate the new gross profit
        double grossProfitValue = (quantitySoldValue - lossesValue) * Price; // Use Price here
        grossProfit.setText(String.format("%.2f", grossProfitValue));

        // Step 4: Update the quantity in ITEMS.txt
        updateItemQuantityInItemsFile(Code, quantitySoldValue, lossesValue);

        // Step 5: Write the daily report to DAILY.txt
        writeReportToDailyFile(reportDate, Code, Name, Price, quantitySoldValue, lossesValue, grossProfitValue);
        
        // Load items into the table again to refresh it
        loadItemsToTable();

        // Success message
        JOptionPane.showMessageDialog(null, "Report successfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
        writeToLog(loggedInUser," | Submitted daily report | ","SUCCESS");

    } catch (NumberFormatException e) {
        grossProfit.setText("Invalid input");
        JOptionPane.showMessageDialog(null, "Please enter valid numbers for Price, Quantity Sold, and Losses.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
        e.printStackTrace();
        grossProfit.setText("Error processing files");
        JOptionPane.showMessageDialog(null, "An error occurred while processing files.", "File Error", JOptionPane.ERROR_MESSAGE);
    }
}

private boolean checkDuplicateInDailyFile(String reportDate, String itemCode, String itemName) throws IOException {
    // Read the DAILY.txt file
    File dailyFile = new File("src/Databases/DAILY.txt");
    if (!dailyFile.exists()) {
        return false; // No file exists, no duplicates
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(dailyFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // Split line by "|"
            String[] fields = line.split("\\|");

            // Ensure the line contains at least 3 fields
            if (fields.length >= 4) { // Updated to expect at least Price as the 4th field
                String existingDate = fields[0].trim();
                String existingItemCode = fields[1].trim();
                String existingItemName = fields[2].trim();

                // Check for duplicate record (same date, item code, and item name)
                if (existingDate.equals(reportDate.trim()) && 
                    existingItemCode.equals(itemCode.trim()) && 
                    existingItemName.equals(itemName.trim())) {
                    return true; // Duplicate found
                }
            } else {
                System.out.println("Invalid line in DAILY.txt: " + line);
            }
        }
    }
    return false; // No duplicate found
}


private void writeReportToDailyFile(String reportDate, String Code, String Name, Double Price, int quantitySold, int losses, double grossProfit) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("DAILY.txt", true))) {
        // Include Price in the file output
        writer.write(String.format("%s|%s|%s|%.2f|%d|%d|%.2f%n", reportDate, Code, Name, Price, quantitySold, losses, grossProfit));
    }
}


private void updateItemQuantityInItemsFile(String Code, int quantitySold, int losses) throws IOException {
    File itemsFile = new File("src/Databases/ITEMS.txt");
    if (!itemsFile.exists()) {
        return; // If the file does not exist, nothing to update
    }

    StringBuilder fileContent;
    boolean itemUpdated;
        try ( // Read the file and update the quantity of the item
                BufferedReader reader = new BufferedReader(new FileReader(itemsFile))) {
            fileContent = new StringBuilder();
            String line;
            itemUpdated = false;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                String existingItemCode = fields[0];
                
                if (existingItemCode.equals(Code)) {
                    // Update the quantity
                    int currentQuantity = Integer.parseInt(fields[2]);
                    int newQuantity = currentQuantity - quantitySold - losses;
                    fields[2] = String.valueOf(newQuantity);
                    itemUpdated = true;
                }
                
                // Rebuild the file content
                fileContent.append(String.join("|", fields)).append("\n");
            }   }

    if (itemUpdated) {
        try ( // Write the updated content back to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(itemsFile))) {
            writer.write(fileContent.toString());
        }
    }
    }//GEN-LAST:event_submitReportButtonActionPerformed

    public void writeToLog(String uniqueId, String description, String status) {
        try {
            File logFilePath = new File("src/Databases/log.txt");

            // Create log.txt if it doesn't exist
            if (!logFilePath.exists()) {
                logFilePath.createNewFile();
            }

            // Append log entry
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFilePath, true))) {
                String logEntry = uniqueId  + description  + status;
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
            java.util.logging.Logger.getLogger(AdminDailyItemEntryPage_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDailyItemEntryPage_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDailyItemEntryPage_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDailyItemEntryPage_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDailyItemEntryPage_SM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField dateField;
    private javax.swing.JTextField grossProfit;
    private javax.swing.JTextField initialQuantity;
    private javax.swing.JTextField itemCode;
    private javax.swing.JTextField itemName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable listTable;
    private javax.swing.JTextField lossesField;
    private javax.swing.JTextField price;
    private javax.swing.JTextField quantitySold;
    private javax.swing.JButton submitReportButton;
    // End of variables declaration//GEN-END:variables
}
