
package javaassignment.Admin;

import java.util.List;


public abstract class Admin extends User implements AdminDAO{
    private static final String ADMIN_ID = "admin";
    private static final String ADMIN_PASSWORD = "password123";

    
    public static boolean validate(String id, String password) {
        return ADMIN_ID.equals(id) && ADMIN_PASSWORD.equals(password);
    }
    
    public static String getAdminId() {
        return ADMIN_ID;
    }

    public static String getAdminPassword() {
        return ADMIN_PASSWORD;
    }

    
    @Override
    public boolean validateLogin(String id, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUserRole(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean deleteUser(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}


