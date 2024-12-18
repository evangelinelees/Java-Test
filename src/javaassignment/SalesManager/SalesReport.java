package javaassignment.SalesManager;

import java.io.*;
import java.util.*;

public class SalesReport {

    public static List<Sale> readSalesFile(String filename) throws IOException {
        List<Sale> sales = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        // Read the file line by line
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("\\|");

            // Ensure the line has at least 4 fields (date, itemName, quantitySold, and profit)
            if (fields.length >= 4) {
                String date = fields[0].trim();                          // Date of sale
                String itemName = fields[2].trim();                       // Item name
                int quantitySold = Integer.parseInt(fields[4].trim());   // Quantity sold
                double profit = Double.parseDouble(fields[6].trim());    // Profit made on this sale

                // Create Sale object with extracted values and add to the list
                sales.add(new Sale(date, itemName, quantitySold, profit));
            } else {
                // Handle cases where the line doesn't have enough data
                System.err.println("Invalid line format: " + line);
            }
        }

        reader.close();
        return sales;
    }
}
