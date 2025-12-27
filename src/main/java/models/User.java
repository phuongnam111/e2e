package models;

/**
 * User data model
 */
public class User {
    private String employeeName;
    private String username;
    private String userRole;
    private String status;
    private String password;
    
    public User() {
        // Default constructor
    }
    
    public User(String employeeName, String username, String userRole, String status, String password) {
        this.employeeName = employeeName;
        this.username = username;
        this.userRole = userRole;
        this.status = status;
        this.password = password;
    }
    
    // Getters and Setters
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUserRole() {
        return userRole;
    }
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "employeeName='" + employeeName + '\'' +
                ", username='" + username + '\'' +
                ", userRole='" + userRole + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
