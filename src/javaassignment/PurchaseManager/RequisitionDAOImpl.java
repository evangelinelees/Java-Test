/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.PurchaseManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class RequisitionDAOImpl implements RequisitionDAO {
    private final String requisitionFilePath = "C:\\JAVA\\JavaAssignment\\src\\Databases\\REQUISITION.txt";
    

    public boolean saveRequisition(String itemCode, String itemName, String currentQuantity, String proposedQuantity, String userId) {
        String reqDetails = itemCode + "|" + itemName + "|" + currentQuantity + "|" + proposedQuantity + "|" + userId;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(requisitionFilePath, true))) {
            writer.write(reqDetails);
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    
    
}

    

