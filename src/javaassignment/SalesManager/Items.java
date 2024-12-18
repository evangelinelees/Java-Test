package javaassignment.SalesManager;

public class Items {

    private String itemCode;
    private String itemName;
    private int quantity;
    private boolean needReorder;
    private double price;
    private String supplierID;

    public Items(String itemCode, String itemName, int quantity, double price, String supplierID) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.needReorder = quantity <= 3;
        this.price = price;
        this.supplierID=supplierID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
    
    



    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isNeedReorder() {
        return needReorder;
    }

    public void setNeedReorder(boolean needReorder) {
        this.needReorder = needReorder;
    }

    @Override
    public String toString() {
        return itemCode + " | " + itemName + " | " + quantity + " | " + (needReorder ? "Yes" : "No")+ price + " | " + supplierID ;
    }
}
