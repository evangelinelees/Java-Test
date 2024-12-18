/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaassignment.InventoryManager.Forms;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javaassignment.InventoryManager.Components.SupplierInputPanel;
import javaassignment.InventoryManager.Controller.SupplierController;
import javaassignment.InventoryManager.DAO.SupplierDao;
import javaassignment.InventoryManager.Models.Supplier;
import javaassignment.InventoryManager.Utilities.DataHandling;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class SupplierInputForm extends javax.swing.JPanel {

    private final SupplierController supplierController;
    private final DataHandling inputValidator;
    public String loggedInUser;
    
    public void SupplierInputForm() {
        initComponents();
        this.loggedInUser = loggedInUser;
    }

    public SupplierInputForm(SupplierController supplierController,SupplierInputPanel supplierInputPanel, SupplierDao supplierDao) {
        this.supplierController = supplierController;
        this.supplierInputPanel1 = supplierInputPanel; 
        this.inputValidator = new DataHandling(supplierDao);
        initComponents();
        refreshSupplierTable();
        loadSupplier(); 
        addListeners();
        clearFields();
        customizeSaveButton("Save");
        customizeUpdateButton("Update");
        customizeDeleteButton("Delete");
    }
    
    private void loadSupplier() {
        List<Supplier> suppliers = supplierController.getAllSuppliers(); // Assuming this method exists
        if (supplierInputPanel1 != null) {
            supplierInputPanel1.populateSupplier(suppliers);
        }
    }

    private void saveSupplier() {
        // Get values from the fields
        String supplierId = supplierIdField.getText().trim();
        String name = supplierNameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        
        int selectedRow = supplierInputPanel1.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Supplier info cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        /*// Validate inputs
        if (!inputValidator.validateSupplierId(supplierId)) {
            JOptionPane.showMessageDialog(this, "Supplier ID must be valid", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!inputValidator.validateEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!inputValidator.validatePhone(phone)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        */
        // Call the controller to save the supplier
        supplierController.saveSupplier(supplierId, name, address, email, phone);

        // Optionally update the supplier input panel or table with the new supplier
        supplierInputPanel1.addSupplier(supplierId, name, address, email, phone);
        writeToLog(loggedInUser," | Supplier added | ","SUCCESS");

        // Clear the fields after saving
        clearFields();
    }

    private void updateSupplier() {
        String supplierId = supplierIdField.getText().trim();
        String name = supplierNameField.getText().trim();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        

        int selectedRow = supplierInputPanel1.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No supplier selected for update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        /*// Validate supplier ID
        if (!inputValidator.validateSupplierId(supplierId)) {
            JOptionPane.showMessageDialog(this, "Supplier ID must be valid", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate email
        if (!inputValidator.validateEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate phone number
        if (!inputValidator.validatePhone(phone)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        */ 
        // Call the controller to update the supplier
        supplierController.updateSupplier(supplierId, name, address, phone, email);

        // Reload the suppliers and refresh the UI
        loadSupplier();
        clearFields();
        refreshSupplierTable();
    }

    private void deleteSupplier() {
        int selectedRow = supplierInputPanel1.getTable().getSelectedRow();
        if (selectedRow != -1) {
            String supplierId = supplierInputPanel1.getTable().getValueAt(selectedRow, 0).toString();

            

            // Remove the supplier from the service
            supplierController.deleteSupplier(supplierId); // Assuming this method exists

            // Clear the supplier input panel or table
            supplierInputPanel1.clearSupplier();

            // Clear the text fields
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a supplier to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Utility Methods
    private void clearFields() {
        supplierIdField.setText("");
        supplierNameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    private void refreshSupplierTable() { 
        List<Supplier> suppliers = supplierController.getAllSuppliers();
        supplierInputPanel1.populateSupplier(suppliers);
    }
    
    public void writeToLog(String uniqueId, String description, String status) {
        try {
                File logFilePath = new File("log.txt");
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
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        supplierIdField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        supplierNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        universalButton2 = new javaassignment.InventoryManager.Components.UniversalButton();
        universalButton1 = new javaassignment.InventoryManager.Components.UniversalButton();
        universalButton3 = new javaassignment.InventoryManager.Components.UniversalButton();
        supplierInputPanel1 = new javaassignment.InventoryManager.Components.SupplierInputPanel();

        setPreferredSize(new java.awt.Dimension(900, 680));

        jLabel1.setText("Supplier ID");

        supplierIdField.setText("jTextField1");

        jLabel2.setText("Email");

        supplierNameField.setText("jTextField2");
        supplierNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierNameFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Address");

        addressField.setText("jTextField3");

        jLabel4.setText("Phone");

        phoneField.setText("jTextField4");

        jLabel5.setText("Supplier Name");

        emailField.setText("jTextField5");
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        universalButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                universalButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(universalButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(universalButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(universalButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(supplierInputPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addComponent(supplierIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(supplierNameField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(phoneField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addressField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(supplierInputPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(universalButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(universalButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(universalButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void supplierNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierNameFieldActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void universalButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_universalButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_universalButton2ActionPerformed
    
    private void customizeSaveButton(String text) {
        universalButton1.setText(text);
    }      
    
    private void customizeUpdateButton(String text) {
        universalButton2.setText(text);
    } 
    
    private void customizeDeleteButton(String text) {
        universalButton3.setText(text);
    }   
    
    private void addListeners() {
        universalButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSupplier();
                loadSupplier();
            }
        });
        
        universalButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSupplier();
                loadSupplier();
            }
        });
        
        universalButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSupplier();
                loadSupplier();
            }
        });
        
        
       
        
        supplierInputPanel1.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Only react when selection is final
                    int selectedRow = supplierInputPanel1.getTable().getSelectedRow();
                    if (selectedRow >= 0) {
                        // Get values from the selected row and populate the fields
                        String supplierId = supplierInputPanel1.getTable().getValueAt(selectedRow, 0).toString(); // Assuming ID is in the first column
                        String name = supplierInputPanel1.getTable().getValueAt(selectedRow, 1).toString();
                        String address = supplierInputPanel1.getTable().getValueAt(selectedRow, 2).toString();
                        String phone = supplierInputPanel1.getTable().getValueAt(selectedRow, 3).toString();
                        String email = supplierInputPanel1.getTable().getValueAt(selectedRow, 4).toString();
                        

                        // Populate the fields for editing
                        supplierIdField.setText(supplierId);
                        supplierNameField.setText(name);
                        addressField.setText(address);
                        phoneField.setText(phone);
                        emailField.setText(email);
                    }
                }
            }
            
        });
    
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField supplierIdField;
    private javaassignment.InventoryManager.Components.SupplierInputPanel supplierInputPanel1;
    private javax.swing.JTextField supplierNameField;
    private javaassignment.InventoryManager.Components.UniversalButton universalButton1;
    private javaassignment.InventoryManager.Components.UniversalButton universalButton2;
    private javaassignment.InventoryManager.Components.UniversalButton universalButton3;
    // End of variables declaration//GEN-END:variables
}
