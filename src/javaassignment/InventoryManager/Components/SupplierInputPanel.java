/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.Components;



import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javaassignment.InventoryManager.Models.Supplier;


public class SupplierInputPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
     
    public SupplierInputPanel() {
        tableModel = new DefaultTableModel(new Object[]{"SupplierId", "Supplier Name", "Address", "Phone", "Email"}, 0);
        table = new JTable(tableModel);
        initComponents();
    }

    private void initComponents() {
        String[] columnNames = {"Supplier ID", "Supplier Name", "Address", "Phone", "Email"};

        // Create the table model and JTable
        tableModel = new DefaultTableModel(columnNames, 0); // Initialize with no rows
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true); // Ensure the table fills the viewport height

        // Add JScrollPane for the JTable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Set preferred size for the table

        // Set custom scrollbar UI
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarWin11UI());
        scrollPane.getHorizontalScrollBar().setUI(new ScrollBarWin11UI());

        // Set the layout and add the scroll pane to the panel
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    
    }

    
    public void addSupplier(String supplierId, String name,String address, String phone, String email) {
        tableModel.addRow(new Object[]{supplierId, name, address, phone, email});
    }

    public void clearSupplier() {
        int selectedRow = table.getSelectedRow(); 
        
        if (selectedRow != -1) { 
            tableModel.removeRow(selectedRow); 
        }
    }

    public void populateSupplier(List<Supplier> suppliers) {
        tableModel.setRowCount(0); 
        for (Supplier supplier : suppliers) {
            tableModel.addRow(new Object[]{
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail()
            });
        }
    }
    
    public JTable getTable() {
        return table;
    }
   
}