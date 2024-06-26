package bcu.cmp5332.bookingsystem.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LoginDialog class represents a modal dialog for administrator login.
 * It extends JDialog and implements ActionListener.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class LoginDialog extends JDialog implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private boolean isAuthenticated = false;

    private User adminUser = new User("admin", "1234"); // Hardcoded admin credentials

    /**
     * Constructs a LoginDialog with a given parent JFrame.
     *
     * @param parent The parent JFrame that this dialog is displayed on top of.
     */
    public LoginDialog(JFrame parent) {
        super(parent, "Admin Login", true); // Modal dialog
        setSize(300, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(passwordField, constraints);

        loginButton = new JButton("Login");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        loginButton.addActionListener(this);
        panel.add(loginButton, constraints);

        cancelButton = new JButton("Cancel");
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        cancelButton.addActionListener(this);
        panel.add(cancelButton, constraints);

        add(panel);
    }

    /**
     * Handles action events from the loginButton and cancelButton buttons.
     * Authenticates the user when loginButton is clicked and closes the dialog.
     * Closes the dialog when cancelButton is clicked.
     *
     * @param e The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Check if entered username and password match the admin credentials
            if (username.equals(adminUser.getUsername()) && password.equals(adminUser.getPassword())) {
                isAuthenticated = true;
                dispose(); // Close the dialog
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            dispose(); // Close the dialog
        }
    }

    /**
     * Checks if the user has been successfully authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
