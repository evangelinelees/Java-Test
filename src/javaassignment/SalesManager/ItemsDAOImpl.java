/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.SalesManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vroom
 */
public class ItemsDAOImpl implements ItemsDAO {
    private final String filePath = "src\\Databases\\ITEMS.txt";
    
    
public List<Items> viewItems() {
    List<Items> items = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            //System.out.println("Reading line: " + line); // Debug
            String[] data = line.split("\\|");
            //System.out.println("Parsed values: " + Arrays.toString(data)); // Debug
            if (data.length == 5) {
                try {
                    String itemCode = data[0].trim();
                    String itemName = data[1].trim();
                    int quantity = Integer.parseInt(data[2].trim());
                    double price = Double.parseDouble(data[3].trim());
                    String supplierID = data[4].trim();
                    items.add(new Items(itemCode, itemName, quantity, price, supplierID));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing numeric values: " + line);
                }
            } else {
                System.err.println("Malformed line: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return items;
}

@Override
    public List<Items> searchItemsByCode(String itemCode) throws Exception {
        List<Items> allItems = loadItemsFromFile();
        List<Items> filteredItems = new ArrayList<>();
        for (Items item : allItems) {
            if (item.getItemCode().equalsIgnoreCase(itemCode)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
    
    @Override
    public List<Items> searchItemsByName(String itemName) throws Exception {
        List<Items> allItems = loadItemsFromFile();
        List<Items> filteredItems = new ArrayList<>();
        for (Items item : allItems) {
            if (item.getItemName().toLowerCase().contains(itemName.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
    
    @Override
    public List<Items> getItemsNeedingReorder() throws Exception {
        List<Items> allItems = loadItemsFromFile();
        List<Items> reorderItems = new ArrayList<>();
        for (Items item : allItems) {
            if (item.isNeedReorder()) {
                reorderItems.add(item);
            }
        }
        return reorderItems;
    }

    private List<Items> loadItemsFromFile() throws Exception {
        List<Items> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                Items item = new Items(
                        parts[0],  // Item Code
                        parts[1],  // Item Name
                        Integer.parseInt(parts[2]),  // Quantity
                        Double.parseDouble(parts[3]),  // Price
                        parts[4]  // Supplier ID
//                        Boolean.parseBoolean(parts[5])  // Need Reorder
                );
                items.add(item);
            }
        }
        return items;
    }

    
    


    
    
}
