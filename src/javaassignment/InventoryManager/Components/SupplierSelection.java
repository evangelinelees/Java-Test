/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.Components;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class SupplierSelection extends JPanel{
    private JComboBox<String> comboBox;
    private ComboBoxSelectionHandler selectionHandler;

    public SupplierSelection() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Create an empty JComboBox
        comboBox = new JComboBox<>();
        comboBox.addItem("Select a Supplier");
        
        // Add selection listener
        comboBox.addActionListener(e -> handleSelection());
        
        // Add the comboBox to this panel
        add(comboBox, BorderLayout.CENTER);
    }

    /**
     * Sets supplier names into the combo box.
     * @param supplierNames List of supplier names.
     */
    public void setSupplierNames(List<String> supplierNames) {
    String currentSelection = getSelectedSupplier();

    comboBox.removeAllItems();
    comboBox.addItem("Select a Supplier");
    for (String name : supplierNames) {
        comboBox.addItem(name);
    }
     System.out.println("Combo box populated with suppliers.");
    // Re-select the previous selection if it still exists
    if (supplierNames.contains(currentSelection)) {
        comboBox.setSelectedItem(currentSelection);
    }
}

    /**
     * Retrieves the currently selected supplier name.
     * @return The selected supplier name as a String.
     */
    public String getSelectedSupplier() {
        return comboBox.getSelectedItem() != null ? comboBox.getSelectedItem().toString() : "";
    }

    /**
     * Resets the combo box selection.
     */
    public void resetSelection() {
        comboBox.setSelectedIndex(0);
    }

    /**
     * Prepares the component to use a handler interface for selection events.
     * @param handler Implementation of ComboBoxSelectionHandler interface.
     */
    public void setSelectionHandler(ComboBoxSelectionHandler handler) {
        this.selectionHandler = handler;
    }

    private void handleSelection() {
        if (selectionHandler != null) {
            String selectedSupplier = getSelectedSupplier();
            if (!selectedSupplier.equals("Select a Supplier")) {
                selectionHandler.onSupplierSelected(selectedSupplier);
            }
        }
    }

    /**
     * Interface for handling supplier selection events.
     */
    public interface ComboBoxSelectionHandler {
        void onSupplierSelected(String supplierName);
    }
}

