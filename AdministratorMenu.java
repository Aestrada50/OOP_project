import java.io.*;
import java.util.*;

/**
 * AdministratorMenu class implements the UserMenu interface and provides a menu for administrators 
 * to manage users in the system.
 * It allows the user to create, manage, and delete users.
 * Updated to fully implement CSV file storage for users.
 * Author: Noel Lozano
 */
public class AdministratorMenu implements UserMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String USER_FILE = "users.csv";

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. Create User");
            System.out.println("2. Manage User");
            System.out.println("3. Delete User");
            System.out.println("4. Back");
            System.out.print("Select an option: ");
            int choice = getInput();
            switch (choice) {
                case 1:
                    SystemLog.log("Administrator created a new user.");
                    createUser();
                    break;
                case 2:
                    SystemLog.log("Administrator managed a user.");
                    manageUser();
                    break;
                case 3:
                    SystemLog.log("Administrator deleted a user.");
                    deleteUser();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }

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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(username + "," + password + "," + userType);
            bw.newLine();
            System.out.println("User created successfully.");
        } catch (IOException e) {
            System.out.println("Failed to create user: " + e.getMessage());
        }
    }

    private void manageUser() {
        System.out.print("Enter username to manage: ");
        String targetUsername = scanner.nextLine().trim();

        List<String[]> users = loadUsers();
        boolean found = false;

        for (String[] user : users) {
            if (user[0].equals(targetUsername)) {
                found = true;
                System.out.print("Enter new username (or press Enter to keep current): ");
                String newUsername = scanner.nextLine().trim();
                System.out.print("Enter new password (or press Enter to keep current): ");
                String newPassword = scanner.nextLine().trim();

                if (!newUsername.isEmpty()) user[0] = newUsername;
                if (!newPassword.isEmpty()) user[1] = newPassword;
                break;
            }
        }

        if (found) {
            saveUsers(users);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void deleteUser() {
        System.out.print("Enter username to delete: ");
        String targetUsername = scanner.nextLine().trim();

        List<String[]> users = loadUsers();
        boolean removed = users.removeIf(user -> user[0].equals(targetUsername));

        if (removed) {
            saveUsers(users);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

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
            // File might not exist yet - that's okay on first run
        }
        return users;
    }

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
