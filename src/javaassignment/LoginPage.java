
package javaassignment;


import javaassignment.Admin.AdminMainPage;
import javaassignment.Admin.Admin;
import javaassignment.Admin.AdminDAO;
import javaassignment.Admin.AdminDAOImpl;
import javaassignment.InventoryManager.Main;
import javaassignment.SalesManager.SalesManagerMainPage;
import javax.swing.JOptionPane;

public class LoginPage extends javax.swing.JFrame {
    private AdminDAO userDAO;
    private String loggedInUser = null;
    
    public LoginPage() {
        initComponents();
        userDAO = new AdminDAOImpl(); // Initialize the DAO
        

    }


    
   

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ID_TF = new javax.swing.JTextField();
        Pass_TF = new javax.swing.JTextField();
        Login_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Login Page");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Password");

        ID_TF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_TFActionPerformed(evt);
            }
        });

        Login_Button.setBackground(new java.awt.Color(204, 255, 255));
        Login_Button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Login_Button.setForeground(new java.awt.Color(0, 0, 0));
        Login_Button.setText("Login");
        Login_Button.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Login_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 256, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ID_TF)
                            .addComponent(Pass_TF, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(Login_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ID_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Pass_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(Login_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ID_TFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_TFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_TFActionPerformed

    private void Login_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_ButtonActionPerformed
        
        String ID = ID_TF.getText();
        String Pass = Pass_TF.getText();

        if (validateLogin(ID, Pass)) {
            if (Admin.validate(ID, Pass)) {
                loggedInUser = ID;
                JOptionPane.showMessageDialog(this, "Welcome Admin");
                AdminMainPage AMP = new AdminMainPage(loggedInUser);
                AMP.setVisible(true);
                this.dispose();
            } else {
                String role = userDAO.getUserRole(ID);
                if (role != null) {
                    navigateToPageBasedOnRole(role);
                } else {
                    JOptionPane.showMessageDialog(this, "User role not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID or password", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_Login_ButtonActionPerformed

   

    
    private boolean validateLogin(String ID, String Pass) {              
        if (Admin.validate(ID, Pass)) {
            return true; 
        }
          
        AdminDAO userDAO = new AdminDAOImpl();
        return userDAO.validateLogin(ID, Pass);
    }

         
  
    private void navigateToPageBasedOnRole(String role) {
        String ID = ID_TF.getText();
        switch (role) {
            case "Admin":
                // Open Sales Manager Page
                JOptionPane.showMessageDialog(this, "Welcome Admin");
                AdminMainPage AMP = new AdminMainPage();
                AMP.setVisible(true);
                this.dispose();
                break;
            case "Sales Manager":
                loggedInUser = ID;
                SalesManagerMainPage SMMP = new SalesManagerMainPage(loggedInUser);
                SMMP.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(this, "Welcome "+ ID);
                break;
            case "Purchase Manager":
                loggedInUser = ID;
                //SalesManagerMainPage SMMP = new SalesManagerMainPage(loggedInUser);
                //SMMP.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(this, "Welcome "+ ID);
                break;
            case "Inventory Manager":
                loggedInUser = ID;
                Main IM_Main = new Main(loggedInUser);
                IM_Main.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(this, "Welcome "+ ID);
                break;
            case "Finance Manager":
                loggedInUser = ID;
                //SalesManagerMainPage SMMP = new SalesManagerMainPage(loggedInUser);
                //SMMP.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(this, "Welcome "+ ID);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Role not recognized", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

        
   


          
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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID_TF;
    private javax.swing.JButton Login_Button;
    private javax.swing.JTextField Pass_TF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables


}
