/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaassignment.Admin.SM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javaassignment.SalesManager.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vroom
 */
public class AdminViewDeleteSalesEntry extends javax.swing.JFrame {
    public String loggedInUser;

    /**
     * Creates new form EditDailySalesEntry
     */
    public AdminViewDeleteSalesEntry(String loggedInUser) {
        initComponents();
        this.loggedInUser = loggedInUser;
        
        dateField.setEnabled(false);
        itemName.setEnabled(false);
        itemCode.setEnabled(false);
        grossProfit.setEnabled(false);
        price.setEnabled(false);
        quantitySold.setEnabled(false);
        lossesField.setEnabled(false);

        // Load items into the table
        loadItemsToTable();

        
    }
    public AdminViewDeleteSalesEntry(){
        
    }
            
    
    private void loadItemsToTable() {
    try {
        // Path to the DAILY.txt file
        String filePath = "src/Databases/DAILY.txt";  // Replace with the actual path to your file
        // Get the table model
        try ( // Open the file for reading
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
            // Get the table model
            DefaultTableModel tableModel = (DefaultTableModel) dailyTable.getModel();
            // Clear any existing rows
            tableModel.setRowCount(0);
            // Read each line from the file and add to the table
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the file contains comma-separated values: date,itemCode,itemName,quantitySold,loss,grossProfit
                String[] data = line.split("\\|");
                
                // Validate data length (make sure it matches your expected number of columns)
                if (data.length == 7) {
                    tableModel.addRow(new Object[] {
                        data[0].trim(), // Date
                        data[1].trim(), // Item Code
                        data[2].trim(), // Item Name
                        Double.parseDouble(data[3].trim()), //Price
                        Integer.parseInt(data[4].trim()), // Quantity Sold
                        Integer.parseInt(data[5].trim()), // Loss
                        Double.parseDouble(data[6].trim()) // Gross Profit
                    });
                } else {
                    System.err.println("Invalid data format in file: " + line);
                }
            }
            // Close the reader
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    private void filterTableByMonthYear() {
    String month = monthField.getText().trim();  // Get input month
    String year = yearField.getText().trim();    // Get input year

    // Validate inputs
    if (month.isEmpty() || year.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please enter both month and year.");
        return;
    }

    // Ensure month and year are valid
    if (!month.matches("\\d{2}") || Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
        javax.swing.JOptionPane.showMessageDialog(this, "Invalid month. Please enter MM (01-12).");
        return;
    }

    if (!year.matches("\\d{4}")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Invalid year. Please enter YYYY.");
        return;
    }

    try {
        DefaultTableModel tableModel = (DefaultTableModel) dailyTable.getModel();
        // Clear existing rows
        tableModel.setRowCount(0);

        // Reload the data from file
        String filePath = "src/Databases/DAILY.txt";  // Path to your file
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Assuming the file contains | delimited values: date|itemCode|itemName|price|quantitySold|loss|grossProfit
                String[] data = line.split("\\|");

                if (data.length == 7) {
                    String date = data[0].trim();  // Extract the date (YYYY-MM-DD)
                    String fileYear = date.substring(0, 4);  // Extract year
                    String fileMonth = date.substring(5, 7); // Extract month

                    // Check if the date matches the given month and year
                    if (fileYear.equals(year) && fileMonth.equals(month)) {
                        tableModel.addRow(new Object[] {
                            data[0].trim(), // Date
                            data[1].trim(), // Item Code
                            data[2].trim(), // Item Name
                            Double.parseDouble(data[3].trim()), // Price
                            Integer.parseInt(data[4].trim()),   // Quantity Sold
                            Integer.parseInt(data[5].trim()),   // Loss
                            Double.parseDouble(data[6].trim())  // Gross Profit
                        });
                    }
                } else {
                    System.err.println("Invalid data format in file: " + line);
                }
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Error reading the file.");
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
        dailyTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dateField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        itemCode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        quantitySold = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lossesField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        grossProfit = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        itemName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        monthField = new javax.swing.JTextField();
        yearField = new javax.swing.JTextField();
        calendarSearch = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        refreshTableBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Daily Sales Record");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        dailyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Item Code", "Item Name", "Price", "Quantity Sold", "Loss", "Gross Profit"
            }
        ));
        dailyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dailyTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dailyTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 510, 250));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Delete Sales Report");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, -1, -1));

        jLabel6.setText("Date of Report:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, -1, 20));

        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
            }
        });
        jPanel1.add(dateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 130, -1));

        jLabel7.setText("Item Code:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, 20));

        itemCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCodeActionPerformed(evt);
            }
        });
        jPanel1.add(itemCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 130, -1));

        jLabel8.setText("Item Name:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, 20));

        jLabel9.setText("Quantity Sold:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, -1, 20));

        quantitySold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantitySoldActionPerformed(evt);
            }
        });
        jPanel1.add(quantitySold, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 80, -1));

        jLabel12.setText("Losses:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, -1, 20));

        lossesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lossesFieldActionPerformed(evt);
            }
        });
        jPanel1.add(lossesField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 80, -1));

        jLabel11.setText("Gross Profit:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, -1, 20));

        grossProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grossProfitActionPerformed(evt);
            }
        });
        jPanel1.add(grossProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 220, -1));

        deleteButton.setBackground(new java.awt.Color(255, 204, 204));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel1.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 130, 40));

        backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        jPanel1.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel1.add(itemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 130, -1));

        jLabel3.setText("Price per Item");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, -1, -1));

        price.setText("Price");
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        jPanel1.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 310, 100, -1));

        monthField.setText("MM");
        jPanel1.add(monthField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, 30));

        yearField.setText("YYYY");
        jPanel1.add(yearField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, 30));

        calendarSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        calendarSearch.setText("Search");
        calendarSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarSearchActionPerformed(evt);
            }
        });
        jPanel1.add(calendarSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, 30));

        jLabel4.setText("Make sure both fields are filled before searching.");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 270, -1));

        refreshTableBtn.setText("Refresh Table");
        refreshTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTableBtnActionPerformed(evt);
            }
        });
        jPanel1.add(refreshTableBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed

    }//GEN-LAST:event_dateFieldActionPerformed

    private void itemCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemCodeActionPerformed

    private void quantitySoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantitySoldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantitySoldActionPerformed

    private void lossesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lossesFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lossesFieldActionPerformed

    private void grossProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grossProfitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grossProfitActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        AdminSMPage SMP = new AdminSMPage(loggedInUser);
        SMP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void dailyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyTableMouseClicked
    // Get the selected row index
    int selectedRow = dailyTable.getSelectedRow();

    if (selectedRow != -1) { // Ensure a row is selected
        DefaultTableModel tableModel = (DefaultTableModel) dailyTable.getModel();

        // Populate text fields with data from the selected row
        dateField.setText(tableModel.getValueAt(selectedRow, 0).toString());
        itemCode.setText(tableModel.getValueAt(selectedRow, 1).toString());
        itemName.setText(tableModel.getValueAt(selectedRow, 2).toString());
        price.setText(tableModel.getValueAt(selectedRow, 3).toString());
        quantitySold.setText(tableModel.getValueAt(selectedRow, 4).toString());
        lossesField.setText(tableModel.getValueAt(selectedRow, 5).toString());
        grossProfit.setText(tableModel.getValueAt(selectedRow, 6).toString());
    }



    }//GEN-LAST:event_dailyTableMouseClicked

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void calendarSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarSearchActionPerformed
        filterTableByMonthYear();
    }//GEN-LAST:event_calendarSearchActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = dailyTable.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel tableModel = (DefaultTableModel) dailyTable.getModel();
        tableModel.removeRow(selectedRow); // Remove from the table

        // Update the file by rewriting its contents
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("src/Databases/DAILY.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(
                    tableModel.getValueAt(i, 0) + "|" +
                    tableModel.getValueAt(i, 1) + "|" +
                    tableModel.getValueAt(i, 2) + "|" +
                    tableModel.getValueAt(i, 3) + "|" +
                    tableModel.getValueAt(i, 4) + "|" +
                    tableModel.getValueAt(i, 5) + "|" +
                    tableModel.getValueAt(i, 6)
                );
                writer.newLine();
                
            }
            writeToLog(loggedInUser," | Sales entry deleted | ","SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error updating file.");
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "No row selected.");
    }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void refreshTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTableBtnActionPerformed
        loadItemsToTable();
    }//GEN-LAST:event_refreshTableBtnActionPerformed

    
    public void writeToLog(String loggedInUser, String description, String status) {
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
            java.util.logging.Logger.getLogger(AdminViewDeleteSalesEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminViewDeleteSalesEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminViewDeleteSalesEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminViewDeleteSalesEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new AdminViewDeleteSalesEntry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton calendarSearch;
    private javax.swing.JTable dailyTable;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField grossProfit;
    private javax.swing.JTextField itemCode;
    private javax.swing.JTextField itemName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lossesField;
    private javax.swing.JTextField monthField;
    private javax.swing.JTextField price;
    private javax.swing.JTextField quantitySold;
    private javax.swing.JButton refreshTableBtn;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables
}
