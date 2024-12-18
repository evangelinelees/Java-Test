/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.Admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class UnifiedFileHandler {
     private static final String UNIFIED_FILE = "UnifiedData.txt"; // The unified output file

    /**
     * Merges multiple text files into a single unified text file.
     * 
     * @param fileNames List of text files to merge.
     * @throws IOException if an error occurs during file reading or writing.
     */
    public static void mergeFilesToUnifiedFile(List<String> fileNames) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(UNIFIED_FILE, true))) { // Open in append mode
            for (String fileName : fileNames) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line); // Write each line from the current file to the unified file
                        writer.newLine(); // Ensure each entry is on a new line
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file " + fileName + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to the unified file: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Creates an example list of files to merge and calls the merge function.
     */
    public static void main(String[] args) {
        // Example list of text files to merge
        List<String> fileNames = List.of("ITEMS.txt", "USERS.txt", "REQUISITION.txt"); // Add your file names here

        try {
            mergeFilesToUnifiedFile(fileNames);
            System.out.println("Files merged successfully into " + UNIFIED_FILE);
        } catch (IOException e) {
            System.err.println("Error during the file merge operation: " + e.getMessage());
        }
    }
}
