/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.Services;


import java.util.List;
import javaassignment.InventoryManager.DAO.SupplierDao;
import javaassignment.InventoryManager.DAO.SupplierDaoImpl;
import javaassignment.InventoryManager.Models.Supplier;

/**
 *
 * @author User
 */
public class SupplierService {
    private final SupplierDao supplierDao = new SupplierDaoImpl();
    public String loggedInUser;

    // Add a supplier
    public void addSupplier(Supplier supplier) {
        if (supplier == null || supplier.getName().isEmpty() || supplier.getAddress().isEmpty() ||
            supplier.getEmail().isEmpty() || supplier.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Supplier details cannot be null or empty.");
        }
        supplierDao.saveSupplier(supplier);
    }

    // Retrieve all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAllSuppliers();
    }

    // Save or update a supplier
    public void saveSupplier(String supplierId, String name, String address, String email, String phone) {
        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            throw new IllegalArgumentException("All fields are required.");
        }

        Supplier existingSupplier = supplierDao.findById(supplierId);

        if (existingSupplier != null) {
            // Update existing supplier
            existingSupplier.setName(name);
            existingSupplier.setAddress(address);
            existingSupplier.setEmail(email);
            existingSupplier.setPhone(phone);
            supplierDao.updateSupplier(existingSupplier);
        } else {
            // Save new supplier
            Supplier newSupplier = new Supplier(supplierId, name, address, email, phone);
            supplierDao.saveSupplier(newSupplier);
        }
    }

    // Update supplier
    public void updateSupplier(String supplierId, String name, String address, String phone, String email) {
        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("All fields are required.");
        }

        Supplier supplier = new Supplier(supplierId, name, address, phone, email);
        supplierDao.updateSupplier(supplier);
    }

    // Delete a supplier
    public void deleteSupplier(String supplierId) {
        supplierDao.deleteSupplier(supplierId);
    }

    // Retrieve a supplier by ID
    public Supplier getSupplierById(String supplierId) {
        return supplierDao.findById(supplierId);
    }
    
    public List<String> getAllSupplierNames() {
        return supplierDao.getAllSupplierNames();
    }
}        
