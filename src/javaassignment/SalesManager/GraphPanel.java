/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.SalesManager;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GraphPanel extends JPanel {
    private Map<String, Double> data;
    private String title;

    public GraphPanel(Map<String, Double> data, String title) {
        this.data = data;
        this.title = title;
    }

    @Override
    public Dimension getPreferredSize() {
        int barCount = data.size();
        int barWidth = 50; // Width of each bar
        int padding = 100; // Left and right padding
        int width = barCount * barWidth * 2 + padding; // Total width
        int height = 800; // Fixed height

        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Anti-aliasing for smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Adjust the font size dynamically for a smaller font
        int titleFontSize = Math.max(10, getHeight() / 40);  // Make it smaller
        g2d.setFont(new Font("Arial", Font.BOLD, titleFontSize));
        g2d.drawString(title, getWidth() / 2 - g2d.getFontMetrics().stringWidth(title) / 2, titleFontSize + 10);

        int labelFontSize = Math.max(8, getHeight() / 80);  // Make labels smaller
        g2d.setFont(new Font("Arial", Font.PLAIN, labelFontSize));

        drawBarGraph(g2d);
    }

    private void drawBarGraph(Graphics2D g2d) {
        int width = getWidth();
        int height = getHeight();
        int barWidth = 50; // Width of each bar
        int maxBarHeight = height - 100;

        double maxValue = data.values().stream().max(Double::compare).orElse(1.0);

        int x = 50; // Starting X position
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            double profit = entry.getValue();
            int barHeight = (int) ((profit / maxValue) * maxBarHeight);

            g2d.setColor(Color.BLUE);
            g2d.fillRect(x, height - barHeight - 50, barWidth, barHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, height - barHeight - 50, barWidth, barHeight);

            // Draw labels
            g2d.drawString(entry.getKey(), x, height - 30);
            g2d.drawString(String.format("%.2f", profit), x, height - barHeight - 60);

            x += barWidth * 2; // Space between bars
        }
    }
}





