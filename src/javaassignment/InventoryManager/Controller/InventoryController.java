/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.Controller;


import java.util.List;
import javaassignment.InventoryManager.Models.Item;
import javaassignment.InventoryManager.Services.InventoryService;

public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController() {
        this.inventoryService = new InventoryService();
    }

    public void saveItem(String itemId, String itemName, String description, int quantity, double price, String name) {
        Item newItem = new Item(itemId, itemName, description, quantity, price,name);
        inventoryService.addItem(newItem);
    }

    public List<Item> getAllItems() {
        return inventoryService.getAllItems();
    }
    
    public void updateItem(String itemId, String itemName, String description, int quantity, double price, String name) {
        inventoryService.updateItem(itemId, itemName, description, quantity, price, name);
    }
    
    public void deleteItem(String itemId) {
        inventoryService.deleteItem(itemId);
    }
    
}