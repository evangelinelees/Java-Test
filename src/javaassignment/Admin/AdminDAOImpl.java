package javaassignment.Admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javaassignment.Admin.AdminDAO;
import javax.swing.JOptionPane;

public class AdminDAOImpl implements AdminDAO {
    private final String filePath = "src/Databases/USERS.txt";
    private String loggedInUser;
    
    

    @Override
    public boolean validateLogin(String id, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split("\\|");
                if (userDetails.length >= 7 && userDetails[0].equals(id) && userDetails[5].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getUserRole(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split("\\|");
                if (userDetails.length >= 7 && userDetails[0].equals(id)) {
                    return userDetails[6]; // Assuming the 7th field is the role
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.join("|", user.toDataString()));
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
    
    List<User> users = getAllUsers();
    boolean updated = false;
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        // Go through all users and update the target one
        for (User existingUser : users) {
            if (existingUser.getUserId().equals(user.getUserId())) {
                // If we find the user, we update their information
                writer.write(String.join("|", user.toDataString()));
                updated = true;
            } else {
                // If the user isn't the one we're updating, write their data as is
                writer.write(String.join("|", existingUser.toDataString()));
            }
            writer.newLine();  // Ensure each record is written on a new line
            
        }
        writeToLog("admin"," | User updated | ","SUCCESS");
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    return updated;
}

    @Override
    public boolean deleteUser(String id) {
        List<User> users = getAllUsers();
        boolean deleted = false;

        // Show a confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete the user with ID: " + id + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION
        );

        // Check user response
        if (confirm == JOptionPane.YES_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (User user : users) {
                    if (!user.getUserId().equals(id)) {
                        writer.write(String.join("|", user.toDataString()));
                        writer.newLine();
                    } else {
                        deleted = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Show a message indicating success or failure
            if (deleted) {
                JOptionPane.showMessageDialog(
                    null,
                    "User with ID: " + id + " has been successfully deleted.",
                    "Deletion Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );
                writeToLog("admin"," | User deleted | ","SUCCESS");
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "No user found with ID: " + id + ". Deletion failed.",
                    "Deletion Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(
                null,
                "Deletion canceled by the user.",
                "Action Canceled",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        return deleted;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split("\\|");
                if (userDetails.length >= 7) {
                    users.add(new User(userDetails));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean isIcNumberDuplicate(String icNumber) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
               String line;
               while ((line = reader.readLine()) != null) {
                   String[] userDetails = line.split("\\|");
                   if (userDetails.length > 2 && userDetails[2].equals(icNumber)) {
                       return true;
                   }
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
           return false;    
    }

    @Override
    public boolean saveUser(String fullName, String icNumber, String email, String phoneNumber, String password, String role) {
        String prefix = switch (role) {
            case "Sales Manager" -> "SM";
            case "Purchase Manager" -> "PM";
            case "Inventory Manager" -> "IM";
            case "Finance Manager" -> "FM";
            default -> "";
        };

        // Generate unique ID
        String uniqueId = prefix + new Random().nextInt(9999);

        String userDetails =  uniqueId + "|" + fullName + "|" + icNumber + "|" + email + "|" + phoneNumber + "|" + password + "|" + role;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(userDetails);
            writer.newLine();
            
            writeToLog("admin"," | New user created | ","SUCCESS");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            
            return false;
        }
    }

    @Override
    public User getUserById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Split the line by '|' to extract the user details
                    String[] userDetails = line.split("\\|");

                    // Check if the line contains the expected number of columns and matches the user ID
                    if (userDetails.length >= 7 && userDetails[0].equals(id)) {
                        // Return a new User object by passing the userDetails array to the User constructor
                        return new User(userDetails);  // This returns a User object
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;  // Return null if the user is not found   
            }

    @Override
    public void writeToLog(String loggedInUser, String description, String status) {
        try {
                File logFilePath = new File("src/Databases/Log.txt");
                int counter = 1;

                // Create log.txt if it doesn't exist
                if (!logFilePath.exists()) {
                    logFilePath.createNewFile();
            }

            // Read existing log entries and calculate the counter
            try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    counter++;  // Increment the counter for each existing line
                }
            } catch (IOException e) {
                System.err.println("Error reading log file: " + e.getMessage());
            }

            // Prepare the log entry with the counter
            String logEntry = counter + " | "+ loggedInUser + description + status;

            // Append log entry
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFilePath, true))) {
                logWriter.write(logEntry);
                logWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
    }
}
}
    

   

