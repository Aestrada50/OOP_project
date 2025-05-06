package auth;

import java.io.*;
import java.util.Scanner;

public class UserAuthenticator {
    private static final String USER_FILE = "users.csv";

    /**
     * Prompts the user for credentials and validates against users.csv
     * @param expectedType The expected user role, e.g., "Scientist"
     * @return true if credentials match; false otherwise
     */
    public static boolean login(String expectedType) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- " + expectedType + " Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        scanner.close();

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    String fileType = parts[2].trim();
                    if (fileUsername.equals(username) &&
                        filePassword.equals(password) &&
                        fileType.equalsIgnoreCase(expectedType)) {
                        System.out.println("Login successful.\n");
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        System.out.println("Login failed. Invalid credentials or role.\n");
        return false;
    }
}
