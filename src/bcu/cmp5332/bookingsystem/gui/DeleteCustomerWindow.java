package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.DeleteCustomer;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DeleteCustomerWindow class represents a graphical user interface for deleting a customer.
 * It extends JFrame and implements ActionListener.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class DeleteCustomerWindow extends JFrame implements ActionListener {
    private JFrame parentFrame; // Accepts any JFrame as parent
    private JTextField custIdField = new JTextField();
    private JButton confirmBtn = new JButton("Confirm");

    /**
     * Constructs a DeleteCustomerWindow with a given parent JFrame.
     *
     * @param parentFrame The parent JFrame that this window is displayed on top of.
     */
    public DeleteCustomerWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the contents of the DeleteCustomerWindow.
     * Sets up text fields, buttons, and layout.
     */
    private void initialize() {
        setTitle("Delete Customer with Id");
        setSize(350, 220);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(new JLabel("Customer Id : "));
        topPanel.add(custIdField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 1));
        bottomPanel.add(confirmBtn);

        confirmBtn.addActionListener(this);

        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    /**
     * Handles action events from the confirmBtn button.
     * Calls deleteCustomer() when confirmBtn is clicked.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmBtn) {
            deleteCustomer();
        }
    }

    /**
     * Deletes a customer based on the customer ID entered in the text field.
     * Executes the DeleteCustomer command to perform the deletion.
     * Displays success message if the customer is deleted successfully or error message if any exception occurs.
     */
    private void deleteCustomer() {
        try {
            int custId = Integer.parseInt(custIdField.getText());

            // Create and execute the DeleteCustomer Command
            Command deleteCustomerCommand = new DeleteCustomer(custId);
            if (parentFrame instanceof MainWindow) {
                deleteCustomerCommand.execute(((MainWindow) parentFrame).getFlightBookingSystem());
                ((MainWindow) parentFrame).displayCustomers(); // Refresh customers view in MainWindow
            } else if (parentFrame instanceof AdminWindow) {
                deleteCustomerCommand.execute(((AdminWindow) parentFrame).getFlightBookingSystem());
                ((AdminWindow) parentFrame).displayCustomers(); // Refresh customers view in AdminWindow
            } else if (parentFrame instanceof UserWindow) {
                deleteCustomerCommand.execute(((UserWindow) parentFrame).getFlightBookingSystem());
                ((UserWindow) parentFrame).displayCustomers(); // Refresh customers view in UserWindow
            }

            // Notify success
            JOptionPane.showMessageDialog(this, "Customer deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Hide (close) the DeleteCustomerWindow
            setVisible(false);
        } catch (NumberFormatException | FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
