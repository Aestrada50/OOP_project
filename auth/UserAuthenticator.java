package auth;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles user authentication and loading from users.csv.
 */
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
     * Prompts for login input and authenticates against stored users.
     * @param expectedRole The required role to allow login.
     * @return true if login is successful.
     * @throws AuthenticationException if login fails.
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
     * Creates and stores a new user in memory and appends to the CSV.
     * @throws AuthenticationException if validation fails or user exists.
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

    private static void seedDefaultAdminIfNeeded() throws IOException {
        File file = new File(USER_FILE);
        if (!file.exists() || file.length() == 0) {
            try (PrintWriter out = new PrintWriter(new FileWriter(USER_FILE, true))) {
                out.println("admin,admin123,Administrator");
                System.out.println("[INFO] Default admin created: admin / admin123");
            }
        }
    }

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
