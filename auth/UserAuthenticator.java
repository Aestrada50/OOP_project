package auth;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * UserAuthenticator handles login authentication based on users.csv.
 */
public class UserAuthenticator {
    private static final String USER_FILE = "users.csv";
    private static final Map<String, User> users = new HashMap<>();

    // Static block: load users and seed default admin if needed
    static {
        File file = new File(USER_FILE);
        try {
            // Seed admin if file doesn't exist or is empty
            if (!file.exists() || file.length() == 0) {
                try (PrintWriter out = new PrintWriter(new FileWriter(USER_FILE, true))) {
                    out.println("admin,admin123,Administrator");
                    System.out.println("[INFO] Default admin seeded: admin / admin123");
                }
            }

            // Load all users into memory
            try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String username = parts[0];
                        String password = parts[1];
                        String role = parts[2];
                        users.put(username, new User(username, password, role));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to load users: " + e.getMessage());
        }
    }

    /**
     * Prompts the user for login based on the given role.
     * @param expectedRole Role the user must have to access
     * @return true if login is successful
     */
    public static boolean login(String expectedRole) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter username: ");
            String username = reader.readLine().trim();

            System.out.print("Enter password: ");
            String password = reader.readLine().trim();

            User user = users.get(username);
            if (user != null && user.getPassword().equals(password) && user.getRole().equalsIgnoreCase(expectedRole)) {
                System.out.println("Login successful as " + expectedRole);
                return true;
            } else {
                System.out.println("Login failed. Incorrect credentials or role mismatch.");
                return false;
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Login failed: " + e.getMessage());
            return false;
        }
    }
}
