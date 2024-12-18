package javaassignment.SalesManager;

import java.util.*;

public class SalesAggregator {
    public static Map<String, Double> getDailySales(List<Sale> sales) {
        Map<String, Double> dailySales = new HashMap<>();

        for (Sale sale : sales) {
            String date = sale.getDate();
            double profit = sale.getProfit();
            dailySales.put(date, dailySales.getOrDefault(date, 0.0) + profit);
        }

        return dailySales;
    }
}
