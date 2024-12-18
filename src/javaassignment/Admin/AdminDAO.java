
package javaassignment.Admin;

import java.util.List;


public interface AdminDAO {
    boolean validateLogin(String id, String password);
    String getUserRole(String id);
    User getUserById(String id);  
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(String id);
    List<User> getAllUsers();
    boolean isIcNumberDuplicate(String icNumber);
    boolean saveUser(String fullName, String icNumber, String email, String phoneNumber, String password, String role);
    void writeToLog(String uniqueId, String description, String status);
    
    
}

