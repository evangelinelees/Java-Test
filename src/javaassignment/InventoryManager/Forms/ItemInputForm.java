/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaassignment.InventoryManager.Forms;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javaassignment.InventoryManager.Components.ItemInputPanel;
import javaassignment.InventoryManager.Components.SupplierSelection;
import javaassignment.InventoryManager.Controller.InventoryController;
import javaassignment.InventoryManager.Controller.SupplierController;
import javaassignment.InventoryManager.DAO.ItemDao;
import javaassignment.InventoryManager.Models.Item;
import javaassignment.InventoryManager.Utilities.DataHandling;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public final class ItemInputForm extends JPanel {
    private final SupplierController supplierController;
    private final InventoryController inventoryController;
    private final DataHandling inputValidator;
    private final ItemInputPanel itemInputPanel;
    public String loggedInUser;
    
    
   
    
    public void ItemInputForm(){
        initComponents();
        
    }

    public ItemInputForm(InventoryController inventoryController, SupplierSelection supplierSelection, ItemInputPanel itemInputPanel1,
            ItemDao itemDao, SupplierController supplierController,String loggedInUser ) {
        this.loggedInUser = loggedInUser;
        this.supplierController = supplierController;
        this.inventoryController = inventoryController;
        this.itemInputPanel = itemInputPanel1;
        this.supplierSelection = supplierSelection;
        this.inputValidator = new DataHandling(itemDao);
        System.out.println(loggedInUser);
        initComponents();
        loadItems();
        populateComboBox();
        addListeners();
        clearFields();
        customizeSaveButton("Save");
        customizeUpdateButton("Update");
        customizeDeleteButton("Delete");
    }
    
    
    private void loadItems() {
        List<Item> items = inventoryController.getAllItems(); // Assuming this method exists
        if (itemInputPanel1 != null) {
            itemInputPanel1.populateItems(items);
        }
    }

    private void saveItem() {
        // Get values from the fields
        String itemId = itemIdField.getText().trim();
        String itemName = itemNameField.getText().trim();
        String description = descriptionField.getText().trim();
        String quantityText = quantityField.getText().trim();
        String priceText = priceField.getText().trim();
        String name = supplierSelection.getSelectedSupplier();
        
        
        
        if (inputValidator.isItemIdExists(itemId)) {
            JOptionPane.showMessageDialog(this, "Item ID already exists. Please choose a unique ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;  
        }
        
        if (!inputValidator.validateItemId(itemId)) {
            JOptionPane.showMessageDialog(this, "Item ID must be valid", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!inputValidator.validateQuantity(quantityText)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid non-negative integer for quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!inputValidator.validatePrice(priceText)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid non-negative number for price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int quantity = Integer.parseInt(quantityText); // Convert after validation
        double price = Double.parseDouble(priceText);
        
        // Call the controller to save the item
        inventoryController.saveItem(itemId, itemName, description, quantity, price, name);

        // Optionally update the item input panel or table with the new item
        itemInputPanel1.addItem(itemId, itemName, description, String.valueOf(quantity), String.valueOf(price),name);
        // Clear the fields after saving
        clearFields();
        supplierSelection.resetSelection();
    }
    
    
    
    private void updateItem() {
        String itemId = itemIdField.getText().trim();
        String itemName = itemNameField.getText().trim();
        String description = descriptionField.getText().trim();
        String quantityText = quantityField.getText().trim();
        String priceText = priceField.getText().trim();
        String name = supplierSelection.getSelectedSupplier();

        int selectedRow = itemInputPanel1.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No item selected for update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate item ID
        if (!inputValidator.validateItemId(itemId)) {
            JOptionPane.showMessageDialog(this, "Item ID must be valid", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate and convert quantity
        int quantity = 0;  // Default value
        if (!inputValidator.validateQuantity(quantityText)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid non-negative integer for quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            quantity = Integer.parseInt(quantityText);  // Convert string to int
        }

        // Validate and convert price
        double price = 0.0;  // Default value
        if (!inputValidator.validatePrice(priceText)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid non-negative number for price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            price = Double.parseDouble(priceText);  // Convert string to double
        }
           
        
        // Call the controller to update the item
        inventoryController.updateItem(itemId, itemName, description, quantity, price, name);

        // Reload the items and refresh the UI
        loadItems();
        clearFields();
        refreshItemTable();
    }
    
    private void deleteItem() {
        int selectedRow = itemInputPanel1.getTable().getSelectedRow();
        if (selectedRow != -1) {
            String itemId = itemInputPanel1.getTable().getValueAt(selectedRow, 0).toString();
            
            
            if (!inputValidator.isItemIdExists(itemId)) {
            JOptionPane.showMessageDialog(this, "Item ID not found.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further processing
            }
            // Remove the item from the inventory service
            inventoryController.deleteItem(itemId); // Assuming this method exists

            
            itemInputPanel1.clearItems();

            // Clear the text fields
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void clearFields() {
        itemIdField.setText("");
        itemNameField.setText("");
        descriptionField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }
    
    private void refreshItemTable() {
    // Reload and populate the table with updated items
    List<Item> items = inventoryController.getAllItems();
    itemInputPanel1.populateItems(items);
    }
    
    public void populateComboBox() {
    SupplierController supplierController = new SupplierController();
    
    
     List<String> supplierNames = supplierController.getAllSupplierNames();
    
    // Debugging: Check if supplier names are loaded correctly
    System.out.println("Supplier names from controller: " + supplierNames);
    
    // Populate the combo box
    supplierSelection.setSupplierNames(supplierNames);
    }
    
    public void writeToLog(String uniqueId, String description, String status) {
        try {
            File logFilePath = new File("src/Databases/Log.txt");

            // Create log.txt if it doesn't exist
            if (!logFilePath.exists()) {
                logFilePath.createNewFile();
            }

            // Append log entry
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFilePath, true))) {
                String logEntry = uniqueId + description  + status;
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

        itemIdField = new javax.swing.JTextField();
        itemNameField = new javax.swing.JTextField();
        descriptionField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        priceField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        itemInputPanel1 = new javaassignment.InventoryManager.Components.ItemInputPanel();
        universalButton2 = new javaassignment.InventoryManager.Components.UniversalButton();
        universalButton3 = new javaassignment.InventoryManager.Components.UniversalButton();
        universalButton1 = new javaassignment.InventoryManager.Components.UniversalButton();
        supplierSelection = new javaassignment.InventoryManager.Components.SupplierSelection();

        setMaximumSize(new java.awt.Dimension(900, 680));
        setMinimumSize(new java.awt.Dimension(900, 680));

        itemIdField.setText("jTextField1");

        itemNameField.setText("jTextField2");

        descriptionField.setText("jTextField3");

        quantityField.setText("jTextField4");

        priceField.setText("jTextField5");

        jLabel1.setText("Item ID");

        jLabel2.setText("Price");

        jLabel3.setText("Description");

        jLabel4.setText("Quanitity");

        jLabel5.setText("Item Name");

        jLabel6.setText("Supplier");

        universalButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                universalButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(itemInputPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(itemIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(supplierSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(6, 6, 6))
                                    .addComponent(priceField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(itemNameField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(quantityField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(descriptionField, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(universalButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(universalButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(universalButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(itemInputPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(supplierSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(universalButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(universalButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(universalButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButton1ActionPerformed

    private void universalButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_universalButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_universalButton2ActionPerformed
   
    private void customizeSaveButton(String text) {
        universalButton1.setText(text);
        System.out.println(loggedInUser);
        writeToLog(loggedInUser," | Submitted daily report | ","SUCCESS");
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
                saveItem();
                loadItems();
            }
        });
        
        universalButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItem();
                loadItems();
            }
        });
        
        universalButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem();
                loadItems();
            }
        });
        
       
        itemInputPanel1.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Only react when selection is final
                    int selectedRow = itemInputPanel1.getTable().getSelectedRow();
                    if (selectedRow >= 0) {
                        // Get values from the selected row and populate the fields
                        String itemId = itemInputPanel1.getTable().getValueAt(selectedRow, 0).toString(); // Assuming ID is in the first column
                        String itemName = itemInputPanel1.getTable().getValueAt(selectedRow, 1).toString();
                        String description = itemInputPanel1.getTable().getValueAt(selectedRow, 2).toString();
                        String quantity = itemInputPanel1.getTable().getValueAt(selectedRow, 3).toString();
                        String price = itemInputPanel1.getTable().getValueAt(selectedRow, 4).toString();

                        // Populate the fields for editing
                        itemIdField.setText(itemId);
                        itemNameField.setText(itemName);
                        descriptionField.setText(description);
                        quantityField.setText(quantity);
                        priceField.setText(price);
                    }
                }
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField descriptionField;
    private javax.swing.JTextField itemIdField;
    private javaassignment.InventoryManager.Components.ItemInputPanel itemInputPanel1;
    private javax.swing.JTextField itemNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField priceField;
    private javax.swing.JTextField quantityField;
    private javaassignment.InventoryManager.Components.SupplierSelection supplierSelection;
    private javaassignment.InventoryManager.Components.UniversalButton universalButton1;
    private javaassignment.InventoryManager.Components.UniversalButton universalButton2;
    private javaassignment.InventoryManager.Components.UniversalButton universalButton3;
    // End of variables declaration//GEN-END:variables

    

   
}
