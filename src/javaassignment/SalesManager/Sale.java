package javaassignment.SalesManager;

public class Sale {
    private String date;
    private String itemName;       
    private int quantitySold;      
    private double profit;         

    public Sale(String date, String itemName, int quantitySold, double profit) {
        this.date = date;
        this.itemName = itemName;
        this.quantitySold = quantitySold;
        this.profit = profit;
    }

    public String getDate() {
        return date;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getProfit() {
        return profit;
    }
}
