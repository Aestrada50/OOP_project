/**
 * The {@code AuthenticationException} class represents an exception that is thrown
 * when an authentication error occurs.
 * It extends the {@code Exception} class to provide custom error messages for authentication failures.
 */
package auth;

public class AuthenticationException extends Exception {

    /**
     * Constructs a new {@code AuthenticationException} with the specified detail message.
     *
     * @param message The detail message explaining the reason for the authentication error.
     */
    public AuthenticationException(String message) {
        super(message);
    }
}
