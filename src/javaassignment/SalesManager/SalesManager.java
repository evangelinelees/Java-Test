
package javaassignment.SalesManager;

import javaassignment.Admin.User;


public class SalesManager extends User {

    // Constructor using User's constructor
    public SalesManager(String userId, String password) {
        super(userId, password);
    }

    public SalesManager(String[] userDetails) {
        super(userDetails);
    }

    public void viewItems() {
        //I don't think i need this?        
    }
}
