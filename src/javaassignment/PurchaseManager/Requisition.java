/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.PurchaseManager;

/**
 *
 * @author vroom
 */
public class Requisition {
    private String itemCode;
    private String itemName;
    private String currentQuantity;
    private String proposedQuantity;
    private String userId;
    private String loggedInUser;

    // Constructor
    public Requisition(String itemCode, String itemName, String currentQuantity, String proposedQuantity, String userId,String loggedInUser) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.currentQuantity = currentQuantity;
        this.proposedQuantity = proposedQuantity;
        this.userId = userId;
        this.loggedInUser = loggedInUser;
    }
    
    public Requisition(String[] requisitionDetails) {
        this.itemCode = requisitionDetails [0];
        this.itemName = requisitionDetails [1];
        this.currentQuantity = requisitionDetails [2];
        this.proposedQuantity = requisitionDetails [3];
        this.userId = requisitionDetails [4];
    }

    // Getters
    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCurrentQuantity() {
        return currentQuantity;
    }

    public String getProposedQuantity() {
        return proposedQuantity;
    }

    public String getUserId() {
        return userId;
    }

    // Convert to file string format
    public String toFileString() {
        return itemCode + "|" + itemName + "|" + currentQuantity + "|" + proposedQuantity + "|" + userId; //????????? wtffff 
    }
}
