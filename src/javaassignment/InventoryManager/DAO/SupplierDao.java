/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.DAO;


import java.util.List;
import javaassignment.InventoryManager.Models.Supplier;

/**
 *
 * @author User
 */

public interface SupplierDao {
    void addSupplier(Supplier supplier);
    Supplier findById(String supplierId);
    Supplier findByName(String name);
    List<String> getAllSupplierNames();
    List<Supplier> getAllSuppliers();
    void updateSupplier(Supplier supplier);
    void deleteSupplier(String supplierId);
    List<Supplier> loadAll();
    void saveSupplier(Supplier supplier);
    void writeToLog(String uniqueId, String description, String status);
    

} 

