/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.Utilities;


import java.util.List;
import javaassignment.InventoryManager.DAO.ItemDao;
import javaassignment.InventoryManager.DAO.SupplierDao;
import javaassignment.InventoryManager.Models.Item;

/**
 *
 * @author User
 */
public class DataHandling {
    private ItemDao itemDao;
    private SupplierDao supplierDao;
    
    public DataHandling(ItemDao itemDAO) {
        this.itemDao = itemDAO; 
    }    
    
    public DataHandling(SupplierDao supplierDao) {
        this.supplierDao = supplierDao; 
    }   
    
    public boolean validateItemId(String itemId) {
        itemId = itemId.trim();
        return itemId.matches("I\\d{4}");
    }

    public boolean validateQuantity(String quantityText) {
        quantityText = quantityText.trim();
        try {
            int quantity = Integer.parseInt(quantityText);
            return quantity >= 0; // Quantity must be non-negative
        } catch (NumberFormatException e) {
            return false; // Invalid number
        }
    }

    public boolean validatePrice(String priceText) {
        priceText = priceText.trim();
        try {
            double price = Double.parseDouble(priceText);
            return price >= 0; // Price must be non-negative
        } catch (NumberFormatException e) {
            return false; // Invalid number
        }
    }
    
    public boolean isItemIdExists(String itemId) {
        List<Item> items = itemDao.loadAll(); // Load all items from the data source
        for (Item item : items) {
            System.out.println("Checking item ID: " + item.getItemId());
            if (item.getItemId().equals(itemId)) {
                return true; // Item ID exists
            }
        }
        return false; // Item ID does not exist
    }

   
}

