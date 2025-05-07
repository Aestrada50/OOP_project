/**
 * The {@code UserAuthenticator} class handles user authentication and user management.
 * It provides functionality to authenticate users, create new users, and load user data
 * from a CSV file. A default administrator account is seeded if no users exist.
 */
package auth;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserAuthenticator {
    private static final String USER_FILE = "users.csv";
    private static final Map<String, User> users = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in); // Shared scanner

    static {
        try {
            seedDefaultAdminIfNeeded();
            loadUsersFromCSV();
        } catch (IOException e) {
            System.err.println("[ERROR] Initialization failed: " + e.getMessage());
        }
    }

    /**
     * Prompts the user for login credentials and authenticates against stored users.
     *
     * @param expectedRole The required role to allow login.
     * @return {@code true} if login is successful.
     * @throws AuthenticationException if login fails due to incorrect credentials or role mismatch.
     */
    public static boolean login(String expectedRole) throws AuthenticationException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        User user = users.get(username);
        if (user == null)
            throw new AuthenticationException("User not found.");
        if (!user.getPassword().equals(password))
            throw new AuthenticationException("Incorrect password.");
        if (!user.getRole().equalsIgnoreCase(expectedRole))
            throw new AuthenticationException("Role mismatch. Expected: " + expectedRole);

        System.out.println("Login successful as " + expectedRole);
        return true;
    }

    /**
     * Creates and stores a new user in memory and appends the user to the CSV file.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param role     The role of the new user (e.g., "Administrator", "Scientist").
     * @throws AuthenticationException if validation fails or the user already exists.
     */
    public static void createUser(String username, String password, String role) throws AuthenticationException {
        if (users.containsKey(username)) {
            throw new AuthenticationException("User already exists.");
        }

        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            throw new AuthenticationException("Username, password, and role cannot be empty.");
        }

        if (!role.equalsIgnoreCase("Administrator") &&
            !role.equalsIgnoreCase("Scientist") &&
            !role.equalsIgnoreCase("Space Agency Representative") &&
            !role.equalsIgnoreCase("Policymaker")) {
            throw new AuthenticationException("Invalid role: " + role);
        }

        User newUser = new User(username, password, role);
        users.put(username, newUser);

        try (PrintWriter out = new PrintWriter(new FileWriter(USER_FILE, true))) {
            out.printf("%s,%s,%s%n", username, password, role);
        } catch (IOException e) {
            throw new AuthenticationException("Failed to save user: " + e.getMessage());
        }

        System.out.println("[SUCCESS] User created: " + username + " (" + role + ")");
    }

    /**
     * Seeds a default administrator account if the user file does not exist or is empty.
     *
     * @throws IOException if an error occurs while writing to the file.
     */
    private static void seedDefaultAdminIfNeeded() throws IOException {
        File file = new File(USER_FILE);
        if (!file.exists() || file.length() == 0) {
            try (PrintWriter out = new PrintWriter(new FileWriter(USER_FILE, true))) {
                out.println("admin,admin123,Administrator");
                System.out.println("[INFO] Default admin created: admin / admin123");
            }
        }
    }

    /**
     * Loads user data from the CSV file into memory.
     *
     * @throws IOException if an error occurs while reading the file.
     */
    private static void loadUsersFromCSV() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    String role = parts[2].trim();
                    users.put(username, new User(username, password, role));
                }
            }
        }
    }
}
