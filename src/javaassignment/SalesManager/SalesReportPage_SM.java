/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaassignment.SalesManager;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vroom
 */
public class SalesReportPage_SM extends javax.swing.JFrame {

    /**
     * Creates new form SalesReportPage_SM
     */
    public SalesReportPage_SM() {
        initComponents();
        loadItemsToTable();
    }
    
    

    private void loadItemsToTable() {
    try {
        // Path to the DAILY.txt file
        String filePath = "DAILY.txt";  // Replace with the actual path to your file
        // Get the table model
        try ( // Open the file for reading
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
            // Get the table model
            DefaultTableModel tableModel = (DefaultTableModel) dailyTable.getModel();
            // Clear any existing rows
            tableModel.setRowCount(0);
            // Read each line from the file and add to the table
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the file contains comma-separated values: date,itemCode,itemName,quantitySold,loss,grossProfit
                String[] data = line.split("\\|");
                
                // Validate data length (make sure it matches your expected number of columns)
                if (data.length == 7) {
                    tableModel.addRow(new Object[] {
                        data[0].trim(), // Date
                        data[1].trim(), // Item Code
                        data[2].trim(), // Item Name
                        Double.parseDouble(data[3].trim()), //Price
                        Integer.parseInt(data[4].trim()), // Quantity Sold
                        Integer.parseInt(data[5].trim()), // Loss
                        Double.parseDouble(data[6].trim()) // Gross Profit
                    });
                } else {
                    System.err.println("Invalid data format in file: " + line);
                }
            }
            // Close the reader
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    private void displayMostToLeastItemSold() {
    try {
        // Read sales data from the file
        List<Sale> sales = SalesReport.readSalesFile("DAILY.txt");

        // Create a map to store the total quantity sold for each item
        Map<String, Integer> itemSalesCount = new HashMap<>();

        for (Sale sale : sales) {
            String itemName = sale.getItemName();
            int quantitySold = sale.getQuantitySold();

            // Add to the existing count
            itemSalesCount.put(itemName, itemSalesCount.getOrDefault(itemName, 0) + quantitySold);
        }

        // Sort the items by quantity sold (most to least)
        List<Map.Entry<String, Integer>> sortedItemSales = itemSalesCount.entrySet().stream()
            .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())  // Sort descending
            .collect(Collectors.toList());

        // Prepare the result string for display
        StringBuilder sb = new StringBuilder();
        sb.append("Most to Least Items Sold:\n");
        for (Map.Entry<String, Integer> entry : sortedItemSales) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" sold\n");
        }

        // Display the result in the text area
        jTextArea1.setText(sb.toString());

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error displaying the item sales data: " + ex.getMessage());
    }
}

    
    private void displayMostToLeastProfitMonth() {
    try {
        // Read sales data from the file
        List<Sale> sales = SalesReport.readSalesFile("DAILY.txt");

        // Create a map to store the total profit for each month (1 - January, 12 - December)
        Map<Integer, Double> monthlyProfit = new HashMap<>();

        for (Sale sale : sales) {
            String date = sale.getDate();
            double profit = sale.getProfit();

            // Extract the month from the date (format: YYYY-MM-DD)
            int month = Integer.parseInt(date.split("-")[1]);

            // Add the profit to the corresponding month
            monthlyProfit.put(month, monthlyProfit.getOrDefault(month, 0.0) + profit);
        }

        // Sort the months by total profit (most to least)
        List<Map.Entry<Integer, Double>> sortedMonthlyProfit = monthlyProfit.entrySet().stream()
            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))  // Sort descending
            .collect(Collectors.toList());

        // Prepare the result string for display
        StringBuilder sb = new StringBuilder();
        sb.append("Most to Least Profitable Months:\n");
        for (Map.Entry<Integer, Double> entry : sortedMonthlyProfit) {
            String monthName = getMonthName(entry.getKey());
            sb.append(monthName).append(" - $").append(String.format("%.2f", entry.getValue())).append("\n");
        }

        // Display the result in the text area
        jTextArea1.setText(sb.toString());

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error displaying the monthly profit data: " + ex.getMessage());
    }
}

// Helper method to convert month number to month name
private String getMonthName(int monthNumber) {
    String[] monthNames = {
        "January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"
    };
    return monthNames[monthNumber - 1];  // Months are 1-indexed
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        barGraph = new javax.swing.JButton();
        downloadBarGraph = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dailyTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        mostLeastItemSold = new javax.swing.JButton();
        mostLeastProfitMonth = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Sales Report Page");

        barGraph.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        barGraph.setText("Open Daily Profit Bar Graph");
        barGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barGraphActionPerformed(evt);
            }
        });

        downloadBarGraph.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        downloadBarGraph.setText("Download Bar Graph");
        downloadBarGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadBarGraphActionPerformed(evt);
            }
        });

        dailyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Item Code", "Item Name", "Price", "Quantity Sold", "Loss", "Gross Profit"
            }
        ));
        dailyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dailyTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dailyTable);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        mostLeastItemSold.setText("Most to Least Item Sold");
        mostLeastItemSold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostLeastItemSoldActionPerformed(evt);
            }
        });

        mostLeastProfitMonth.setText("Most to Least Profitable Month");
        mostLeastProfitMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostLeastProfitMonthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(barGraph)
                                .addGap(64, 64, 64)
                                .addComponent(downloadBarGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mostLeastItemSold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mostLeastProfitMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(mostLeastItemSold)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mostLeastProfitMonth)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downloadBarGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barGraphActionPerformed
     try {
        // Read sales data from the file
        List<Sale> sales = SalesReport.readSalesFile("DAILY.txt");

        // Aggregate sales by date
        Map<String, Double> dailySales = SalesAggregator.getDailySales(sales);

        // Create the bar graph JFrame
        JFrame barFrame = new JFrame("Profit Bar Graph");
        barFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the graph panel
        GraphPanel graphPanel = new GraphPanel(dailySales, "Profit Bar Graph");
        graphPanel.setPreferredSize(new Dimension(2000, 1000)); // Set a larger preferred size for scrolling

        // Wrap the graph panel with a JScrollPane
        JScrollPane scrollPane = new JScrollPane(graphPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to the frame
        barFrame.add(scrollPane);

        // Set the frame size
        barFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        barFrame.setUndecorated(false); // Keep the window decorations (title bar, etc.)
        barFrame.setVisible(true);

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error displaying the bar graph: " + ex.getMessage());
    }

    }//GEN-LAST:event_barGraphActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
    SalesManagerMainPage SMM = new SalesManagerMainPage();
    SMM.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void downloadBarGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadBarGraphActionPerformed
try {
    // Read sales data
    List<Sale> sales = SalesReport.readSalesFile("DAILY.txt");
    Map<String, Double> dailySales = SalesAggregator.getDailySales(sales);

    // Create the graph panel
    GraphPanel graphPanel = new GraphPanel(dailySales, "Profit Bar Graph");

    // Dynamically set the panel size to fit content
    int width = Math.max(1200, dailySales.size() * 200); // Adjust for the number of bars
    int height = 800; // Set height
    graphPanel.setSize(width, height);
    graphPanel.setPreferredSize(new Dimension(width, height));

    // Save the graph as an image
    saveGraphAsImage.saveGraphAsImage(graphPanel, "barGraph.png");
    JOptionPane.showMessageDialog(this, "Bar graph saved as barGraph.png successfully!");
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error saving bar graph: " + e.getMessage());
}

    }//GEN-LAST:event_downloadBarGraphActionPerformed

    private void dailyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyTableMouseClicked
        loadItemsToTable();
    }//GEN-LAST:event_dailyTableMouseClicked

    private void mostLeastItemSoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostLeastItemSoldActionPerformed
        displayMostToLeastItemSold();
    }//GEN-LAST:event_mostLeastItemSoldActionPerformed

    private void mostLeastProfitMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostLeastProfitMonthActionPerformed
        displayMostToLeastProfitMonth();
    }//GEN-LAST:event_mostLeastProfitMonthActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalesReportPage_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesReportPage_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesReportPage_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesReportPage_SM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesReportPage_SM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton barGraph;
    private javax.swing.JTable dailyTable;
    private javax.swing.JButton downloadBarGraph;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton mostLeastItemSold;
    private javax.swing.JButton mostLeastProfitMonth;
    // End of variables declaration//GEN-END:variables
}
