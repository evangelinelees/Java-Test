package javaassignment.InventoryManager.Services;


import java.util.List;
import javaassignment.InventoryManager.DAO.ItemDao;
import javaassignment.InventoryManager.DAO.ItemDaoImpl;
import javaassignment.InventoryManager.Models.Item;


public class InventoryService {
    private final ItemDao itemDao = new ItemDaoImpl();
    public String loggedInUser;
    
    public void addItem(Item item) {
        itemDao.addItem(item);
        this.loggedInUser = loggedInUser;
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }
    public void saveItem(Item item) {
        if (item.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        
        Item existingItem = itemDao.findById(item.getItemId());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            existingItem.setPrice(item.getPrice()); // Update price if necessary
            itemDao.updateItem(existingItem); // Update the existing item
        } else {
            itemDao.saveItem(item); // Save the new item
        }
    }
    
    public void updateItem(String itemId, String itemName, String description, int quantity, double price, String name) {
        Item item = new Item(itemId, itemName, description, quantity, price, name); // Create new Item object
        itemDao.updateItem(item); // Delegate to DAO
    }
    
    public void deleteItem(String itemId) {
        itemDao.deleteItem(itemId);
    }
}