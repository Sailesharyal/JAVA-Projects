package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A graphical user interface window for adding new customers to the flight booking system.
 * Users can enter customer name, phone number, and email to create a new customer record.
 * It handles user input validation and executes the AddCustomer command upon user confirmation.
 * 
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class AddCustomerWindow extends JFrame implements ActionListener {

    private JFrame parentFrame; // Accepts any JFrame as parent
    private JTextField custNameField = new JTextField();
    private JTextField phoneNumField = new JTextField();
    private JTextField emailField = new JTextField();
    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an AddCustomerWindow with the specified parent JFrame.
     * Initializes the GUI components and sets up event listeners for Add and Cancel buttons.
     *
     * @param parentFrame The parent JFrame to which this window is attached.
     */
    public AddCustomerWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the contents of the AddCustomerWindow.
     * Configures the layout, adds input fields for customer name, phone number, and email.
     * Sets up event listeners for Add and Cancel buttons.
     */
    private void initialize() {
        setTitle("Add a New Customer");
        setSize(350, 220);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.add(new JLabel("Customer Name : "));
        topPanel.add(custNameField);
        topPanel.add(new JLabel("Phone Number : "));
        topPanel.add(phoneNumField);
        topPanel.add(new JLabel("Email : "));
        topPanel.add(emailField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        getContentPane().add(topPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    /**
     * Handles ActionEvents for Add and Cancel buttons.
     * If Add button is clicked, calls addCustomer() method.
     * If Cancel button is clicked, hides the AddCustomerWindow.
     *
     * @param ae The ActionEvent triggered by Add or Cancel button clicks.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addCustomer();
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    /**
     * Attempts to add a new customer to the flight booking system.
     * Reads customer name, phone number, and email from input fields.
     * Executes the AddCustomer command and updates the parent window's view.
     * Displays error messages for invalid input or system errors.
     */
    private void addCustomer() {
        try {
            String custName = custNameField.getText();
            String phoneNum = phoneNumField.getText();
            String email = emailField.getText();

            // Create and execute the AddCustomer Command
            Command addCust = new AddCustomer(custName, phoneNum, email, null);
            if (parentFrame instanceof MainWindow) {
                addCust.execute(((MainWindow) parentFrame).getFlightBookingSystem());
            } else if (parentFrame instanceof AdminWindow) {
                addCust.execute(((AdminWindow) parentFrame).getFlightBookingSystem());
            } else if (parentFrame instanceof UserWindow) {
                addCust.execute(((UserWindow) parentFrame).getFlightBookingSystem());
            }

            // Hide (close) the AddCustomerWindow
            setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
