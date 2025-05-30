package menu;
import java.io.*;
import java.util.*;

import log.SystemLog;
import auth.UserAuthenticator;

/**
 * AdministratorMenu class provides a menu for administrators to create, manage,
 * and delete users in the system. Extends BaseMenu for shared functionality.
 * Users are stored in a CSV file.
 * 
 * Author: Noel Lozano
 */
public class AdministratorMenu extends BaseMenu {
    private static final String USER_FILE = "users.csv";

    /**
     * Displays the administrator's menu options and handles user input.
     */
    @Override
    public void showMenu() {
        
        String[] options = {"Create User", "Manage User", "Delete User", "Back"};
        while (true) {
            printOptions("Administrator Menu", options);
            int choice = getInput();
            switch (choice) {
                case 1 -> createUser();
                case 2 -> manageUser();
                case 3 -> deleteUser();
                case 4 -> { return; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }


    /**
     * Prompts the administrator to create a new user.
     * User can be of type Scientist, Space Agency Representative, Policymaker, or Administrator.
     */
    private void createUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
    
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
    
        System.out.print("Enter user type (Scientist, Space Agency Representative, Policymaker, Administrator): ");
        String userType = scanner.nextLine().trim();
    
        if (username.isEmpty() || password.isEmpty() || userType.isEmpty()) {
            System.out.println("Invalid input. All fields are required.");
            return;
        }
    
        try {
            UserAuthenticator.createUser(username, password, userType);
            System.out.println("User created successfully.");
            SystemLog.log("Administrator created a new user: " + username);
        } catch (auth.AuthenticationException e) {
            System.out.println("[ERROR] " + e.getMessage());
            SystemLog.log("User creation failed for '" + username + "': " + e.getMessage());
        }
    }
    
    /**
     * Allows the administrator to manage an existing user.
     * This includes updating the username and password.
     */
    private void manageUser() {
        System.out.print("Enter username to manage: ");
        String targetUsername = scanner.nextLine().trim();
        List<String[]> users = loadUsers();
        boolean found = false;

        for (String[] user : users) {
            if (user[0].equals(targetUsername)) {
                System.out.print("Enter new username (or press Enter to keep current): ");
                String newUsername = scanner.nextLine().trim();
                System.out.print("Enter new password (or press Enter to keep current): ");
                String newPassword = scanner.nextLine().trim();
                if (!newUsername.isEmpty()) user[0] = newUsername;
                if (!newPassword.isEmpty()) user[1] = newPassword;
                found = true;
                break;
            }
        }

        if (found) {
            saveUsers(users);
            System.out.println("User updated successfully.");
            SystemLog.log("Administrator managed a user.");
        } else {
            System.out.println("User not found.");
        }
    }

    /*
     * Allows the administrator to delete an existing user.
     */
    private void deleteUser() {
        System.out.print("Enter username to delete: ");
        String targetUsername = scanner.nextLine().trim();
        List<String[]> users = loadUsers();
        boolean removed = users.removeIf(user -> user[0].equals(targetUsername));

        if (removed) {
            saveUsers(users);
            System.out.println("User deleted successfully.");
            SystemLog.log("Administrator deleted a user.");
        } else {
            System.out.println("User not found.");
        }
    }

    /*
     * Loads existinjg users from the CSV file.
     */
    private List<String[]> loadUsers() {
        List<String[]> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    users.add(parts);
                }
            }
        } catch (IOException e) {
            // File might not exist yet - that's okay
        }
        return users;
    }

    /*
     * Saves the updated list of users to the CSV file.
     */
    private void saveUsers(List<String[]> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (String[] user : users) {
                bw.write(String.join(",", user));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save users: " + e.getMessage());
        }
    }
}
