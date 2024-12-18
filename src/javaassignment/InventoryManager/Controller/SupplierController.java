/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.Controller;

import java.util.List;
import javaassignment.InventoryManager.Models.Supplier;
import javaassignment.InventoryManager.Services.SupplierService;

/**
 *
 * @author User
 */
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController() {
        this.supplierService = new SupplierService();
    }

    // Save or add a supplier
    public void saveSupplier(String supplierId, String name, String address, String phone, String email) {
        Supplier newSupplier = new Supplier(supplierId, name, address, phone, email);
        supplierService.addSupplier(newSupplier);
    }

    // Retrieve all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Update a supplier's details
    public void updateSupplier(String supplierId, String name, String address, String phone, String email) {
        supplierService.updateSupplier(supplierId, name, address, phone, email);
    }

    // Delete a supplier by ID
    public void deleteSupplier(String supplierId) {
        supplierService.deleteSupplier(supplierId);
    }

    // Find a supplier by ID
    public Supplier findSupplierById(String supplierId) {
        return supplierService.getSupplierById(supplierId);
    }
    
     public List<String> getAllSupplierNames() {
        // This method fetches supplier names from the service layer
        return supplierService.getAllSupplierNames();
    }
} 

