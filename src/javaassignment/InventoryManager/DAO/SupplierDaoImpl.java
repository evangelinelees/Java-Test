/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.DAO;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javaassignment.InventoryManager.Models.Supplier;


/**
 *
 * @author User
 */
public class SupplierDaoImpl implements SupplierDao{
   private final List<Supplier> supplierList = new ArrayList<>();
   private final String FILE_PATH = "src/Databases/supplierData.txt";
   private final String logFilePath = "src/Databases/Log.txt";
   private String loggedInUser;

  

   
    public void addSupplier(Supplier supplier) {
        // Add supplier to the list
        supplierList.add(supplier);
        writeToLog(loggedInUser," | Supplier added | ","SUCCESS");

        // Write supplier to the file
        writeToFile(supplier);
    }

   
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) { 
                    Supplier supplier = new Supplier();
                    supplier.setSupplierId(parts[0]);
                    supplier.setName(parts[1]);
                    supplier.setAddress(parts[2]);
                    supplier.setEmail(parts[3]);
                    supplier.setPhone(parts[4]);
                   
                    suppliers.add(supplier); // Add the supplier to the list
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log any exceptions
        } 
        return suppliers; // Return the list of suppliers
    }

    
    public Supplier findById(String supplierId) {
        List<Supplier> suppliers = loadAll(); // Load all items to find the specific one
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierId().equals(supplierId)) {
                return supplier; // Return the found item
            }
        }
        return null;
    }

    public void saveSupplier(Supplier supplier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(supplierToString(supplier));
            writer.newLine();
            writeToLog(loggedInUser," | Supplier created | ","SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void updateSupplier(Supplier supplier) {
        List<Supplier> suppliers = getAllSuppliers();
        boolean updated = false;

        for (int i = 0; i < suppliers.size(); i++) {
            if (suppliers.get(i).getSupplierId().equals(supplier.getSupplierId())) {
                suppliers.set(i, supplier); // Update the supplier
                updated = true;
                break;
            }
        }

        if (updated) {
            saveAll(suppliers); // Save updated list
            writeToLog(loggedInUser," | Supplier updated | ","SUCCESS");
        } else {
            System.out.println("Supplier with ID " + supplier.getSupplierId() + " not found for update.");
        }
    }

    public void deleteSupplier(String supplierId) {
        List<Supplier> suppliers = getAllSuppliers();
        boolean removed = suppliers.removeIf(supplier -> supplier.getSupplierId().equals(supplierId));

        if (removed) {
            saveAll(suppliers);
            writeToLog(loggedInUser," | Supplier deleted | ","SUCCESS");
        } else {
            System.out.println("Supplier with ID " + supplierId + " not found.");
        }
    }
    
    public Supplier findByName(String name) {
    List<Supplier> suppliers = getAllSuppliers(); // Load all suppliers
    for (Supplier supplier : suppliers) {
        if (supplier.getName().equalsIgnoreCase(name)) {
            return supplier;
        }
    }
    return null; // Return null if supplier is not found
}
   

    // Helper methods
    private String supplierToString(Supplier supplier) {
        return String.join("|", String.valueOf(supplier.getSupplierId()), supplier.getName(),
                supplier.getAddress(),supplier.getEmail(), supplier.getPhone());
    }

    private Supplier stringToSupplier(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid supplier data format: " + line);
        }

        
        String supplierId = parts[0];
        String name = parts[1];
        String address = parts[2];
        String email = parts[3];
        String phone = parts[4];

        return new Supplier(supplierId,name, email, phone, address);
    }

    private void writeToFile(Supplier supplier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(supplierToString(supplier));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAll(List<Supplier> suppliers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) { // Overwrite file
            for (Supplier supplier : suppliers) {
                writer.write(supplierToString(supplier));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Supplier> loadAll() {
        List<Supplier> supplierList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                supplierList.add(stringToSupplier(line));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle file reading exceptions
        }
        return supplierList; // Return the list of items
    }

    
    public List<String> getAllSupplierNames() {
    List<String> supplierNames = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 2) { // Assuming the name is the second part
                supplierNames.add(parts[1].trim()); // Add the supplier name to the list
            }
        }
        // Debugging: Check if supplier names are read correctly
        System.out.println("Supplier names loaded: " + supplierNames);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return supplierNames;
    }
    
   @Override
    public void writeToLog(String uniqueId, String description, String status) {
        
        try {
                File logFilePath = new File("src/Databases/Log.txt");
                int counter = 1;

                // Create log.txt if it doesn't exist
                if (!logFilePath.exists()) {
                    logFilePath.createNewFile();
            }

            // Read existing log entries and calculate the counter
            try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    counter++;  // Increment the counter for each existing line
                }
            } catch (IOException e) {
                System.err.println("Error reading log file: " + e.getMessage());
            }

            // Prepare the log entry with the counter
            String logEntry = counter + " | "+ loggedInUser + description + status;

            // Append log entry
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFilePath, true))) {
                logWriter.write(logEntry);
                logWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
        
    }
} 

