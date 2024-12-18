
package javaassignment.Admin;


public class User {
    private String userId;
    private String fullName;
    private String email;
    private String PhNumber;
    private String IcNumber;
    private String password;
    private String userRole;
    
    
    
    public User(){

    }
    
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    
   public User(String[] userDetails) {
        this.userId = userDetails[0];
        this.fullName = userDetails[1];
        this.email = userDetails[2];
        this.PhNumber = userDetails[3];
        this.IcNumber = userDetails[4];
        this.password = userDetails[5];
        this.userRole = userDetails[6];
        
        
    }

  

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

 
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhNumber() {
        return PhNumber;
    }

    public void setPhNumber(String PhNumber) {
        this.PhNumber = PhNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcNumber() {
        return IcNumber;
    }

    public void setIcNumber(String IcNumber) {
        this.IcNumber = IcNumber;
    }
    
    public String[] toDataString() {
        return new String[] { userId, fullName, email, PhNumber, IcNumber, password, userRole };
    }
   
}
