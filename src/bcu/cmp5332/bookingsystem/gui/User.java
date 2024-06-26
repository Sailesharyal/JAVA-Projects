package bcu.cmp5332.bookingsystem.gui;

/**
 * The User class represents a user with a username and password.
 * It provides methods to get and set the username and password.
 */
public class User {
    private String username;
    private String password;

    /**
     * Constructs a User object with the specified username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
