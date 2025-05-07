/**
 * The {@code User} class represents a user in the authentication system.
 * It contains the user's credentials and role information.
 */
package auth;

public class User {
    private final String username;
    private final String password;
    private final String role;

    /**
     * Constructs a new {@code User} object with the specified username, password, and role.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param role     The role of the user (e.g., "admin", "user").
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() { return username; }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() { return password; }

    /**
     * Gets the role of the user.
     *
     * @return The role of the user.
     */
    public String getRole() { return role; }
}
