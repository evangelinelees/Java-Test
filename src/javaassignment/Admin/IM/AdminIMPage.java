package javaassignment.Admin.IM;


import javaassignment.InventoryManager.*;
import java.awt.Component;
import java.awt.Color;
import javax.swing.*;
import java.awt.BorderLayout;
import javaassignment.InventoryManager.Components.ItemInputPanel;
import javaassignment.InventoryManager.Components.MenuEvent;
import javaassignment.InventoryManager.Components.SupplierInputPanel;
import javaassignment.InventoryManager.Components.SupplierSelection;
import javaassignment.InventoryManager.Controller.InventoryController;
import javaassignment.InventoryManager.Controller.SupplierController;
import javaassignment.InventoryManager.DAO.ItemDao;
import javaassignment.InventoryManager.DAO.ItemDaoImpl;
import javaassignment.InventoryManager.DAO.SupplierDao;
import javaassignment.InventoryManager.DAO.SupplierDaoImpl;
import javaassignment.InventoryManager.Forms.DefaultForm;
import javaassignment.InventoryManager.Forms.InventoryForm;
import javaassignment.InventoryManager.Forms.ItemInputForm;
import javaassignment.InventoryManager.Forms.SupplierForm;
import javaassignment.InventoryManager.Forms.SupplierInputForm;
import javax.swing.plaf.ColorUIResource;




public class AdminIMPage extends javax.swing.JFrame {
    public String loggedInUser;
    
    public AdminIMPage(String loggedInUser) {
        initComponents();
        this.loggedInUser = loggedInUser;
        menu1.setLoggedInUser(loggedInUser);
        System.out.println(loggedInUser);
        
        InventoryController inventoryController = new InventoryController();
        ItemInputPanel itemInputPanel = new ItemInputPanel();
        SupplierSelection supplierSelection = new SupplierSelection();
        ItemDao itemDao = new ItemDaoImpl();
        
        SupplierController supplierController = new SupplierController();
        SupplierInputPanel supplierInputPanel = new SupplierInputPanel();
        SupplierDao supplierDao = new SupplierDaoImpl();
        
        
        
        menu1.setEvent(new MenuEvent() {
            @Override
            public void selected(int index, int subIndex) {
                Component formToShow; // Declare the variable to hold the form

                formToShow = switch (index) {
                    case 0 -> {
                        yield switch (subIndex) {
                            case 0 -> new InventoryForm(); // Inventory form
                            case 1 -> new ItemInputForm(inventoryController, supplierSelection, itemInputPanel, 
                                    itemDao, supplierController, loggedInUser); // Open Item Input Form
                            default -> new DefaultForm("Inventory Form: " + subIndex);
                        };
                    }
                    case 1 -> {
                        yield switch (subIndex) {
                            case 0 -> new SupplierForm(); // Inventory form
                            case 1 -> new SupplierInputForm(supplierController, supplierInputPanel, supplierDao); // Open Item Input Form
                            default -> new DefaultForm("Inventory Form: " + subIndex);
                        };
                    }
                    default -> new DefaultForm("Form: " + index + " " + subIndex);
                };

                showForm((JPanel) formToShow); // Call the method to display the form
            }
        });
    }

    public AdminIMPage() {
        initComponents();
    }
   
    
    public void showForm(JPanel formToShow) {
        body.removeAll();
        body.add(formToShow, BorderLayout.CENTER);
        body.revalidate();
        body.repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu1 = new javaassignment.Admin.IM.IM_MainMenu();
        header1 = new javaassignment.InventoryManager.Components.Header();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        body.setBackground(new java.awt.Color(245, 245, 245));
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
        
        UIManager.put("ScrollBar", new ColorUIResource(Color.BLACK)); // Thumb color
        UIManager.put("ScrollBar.track", new ColorUIResource(Color.GRAY)); // Track color
        UIManager.put("ScrollBar.width", 12);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminIMPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javaassignment.InventoryManager.Components.Header header1;
    private javaassignment.Admin.IM.IM_MainMenu menu1;
    // End of variables declaration//GEN-END:variables
}
