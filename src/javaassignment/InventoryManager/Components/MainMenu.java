package javaassignment.InventoryManager.Components;


import javaassignment.InventoryManager.Main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;




public class MainMenu extends JComponent {
    public String loggedInUser;

    public MenuEvent getEvent() {
        return event;
    }
    
    public MainMenu(Main mainFrame) {
        this.mainFrame = mainFrame; // Store the reference
        init();
    }
    
    public void setEvent(MenuEvent event) {
        this.event = event;
    }
    
    private Main mainFrame;
    private MenuEvent event;
    private MigLayout layout;
    private JButton logoutButton;
    private String[][] menuItems = new String[][]{
        {"Inventory","Item Input","Inventory Report"},
        {"Supplier","Suppliers Input","Update Stock From Purchase Order"},
    };
    
    
     
    public MainMenu(String loggedInUser) {
        this.loggedInUser = loggedInUser;
        init();
    }

    public MainMenu() {
        
        init();
    }
    
    private void init() {
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 0", "fill");
        setLayout(layout);
        setOpaque(true);

        // Set the background color to match the menu
        setBackground(new Color(21, 110, 71)); // Example color, adjust as necessary

        // Create a panel to hold the logout button
        JPanel logoutPanel = new JPanel();
        logoutPanel.setBackground(new Color(106,123,157)); // Match menu color
        logoutPanel.setLayout(new MigLayout("insets 0", "[]", "[]")); // No insets

        
        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(106,123,157)); // Match menu color
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(null); // Remove border completely
        logoutButton.setFocusPainted(false);
        logoutButton.setPreferredSize(new Dimension(200, 40));

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to log out?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    performLogout(); // Call the logout method if confirmed
                }
            }
        });

    // Add the button to the logout panel
    logoutPanel.add(logoutButton, "align center");

    // Add the logout panel to the main menu
    add(logoutPanel, "align center, gapbottom 0"); // Center align with no bottom gap

    // Initialize MenuItems
    for (int i = 0; i < menuItems.length; i++) {
        addMenu(menuItems[i][0], i);
    }

    revalidate();
    repaint();
}
    private void performLogout() {
        // Implement your logout logic here
        System.out.println("Logging out..."); // Replace with actual logout logic
        // Example logic: exit application
         System.exit(0); // Uncomment if //you want to close the application
    }
    
    private Icon getIcon(int index) {
        URL url = getClass().getResource("/raven/menu/" + index + ".png");
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }
    }

    private void addMenu(String menuName, int index) {
    int length = menuItems[index].length;
    MenuItem item = new MenuItem(menuName, index, length > 1);
    Icon icon = getIcon(index);
    if (icon != null) {
        item.setIcon(icon);
    }
    item.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (length > 1) {
                if (!item.isSelected()) {
                    item.setSelected(true);
                    addSubMenu(item, index, length, getComponentZOrder(item));
                } else {
                    // Hide menu
                    hideMenu(item, index);
                    item.setSelected(false);
                }
            } else {
                // Trigger the event for selected form
                if (event != null) {
                    event.selected(index, 0);
                }
            }
        }
    });
    add(item);
    revalidate();
    repaint();
}

    private void addSubMenu(MenuItem item, int index, int length, int indexZorder) {
        Color submenuColor = new Color(39, 41, 61);
        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setBackground(submenuColor);
        for (int i = 1; i < length; i++) {
            MenuItem subItem = new MenuItem(menuItems[index][i], i, false);
            subItem.addActionListener((ActionEvent ae) -> {
                if (event != null) {
                    event.selected(index, subItem.getIndex());
                }
            });
            subItem.initSubMenu(i, length);
            panel.add(subItem);
        }
        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        MenuAnimation.showMenu(panel, item, layout, true);
    }

    private void hideMenu(MenuItem item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                MenuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }
    }
   
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(39, 41, 61));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(grphcs);
    }

}
