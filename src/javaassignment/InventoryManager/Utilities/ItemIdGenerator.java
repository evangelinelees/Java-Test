/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.InventoryManager.Utilities;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ItemIdGenerator {
    private static final Random RANDOM = new Random();
    private static final Set<String> existingIds = new HashSet<>();

    public static String generateItemId() {
        String id;
        do {
            int randomNum = RANDOM.nextInt(9000) + 1000; // Generate a number between 1000 and 9999
            id = "I" + randomNum; // Create ID
        } while (existingIds.contains(id)); // Check if ID already exists

        existingIds.add(id); // Store the new ID
        return id; // Return unique ID
    }
}
