package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.EditCustomer;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EditCustomerWindow class represents a graphical user interface for editing customer details.
 * It extends JFrame and implements ActionListener.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class EditCustomerWindow extends JFrame implements ActionListener {

    private JFrame parentFrame; // Accepts any JFrame as parent
    private JTextField flightIdText = new JTextField();
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();

    private JButton editBtn = new JButton("Edit");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an EditCustomerWindow with a given parent JFrame.
     *
     * @param parentFrame The parent JFrame that this window is displayed on top of.
     */
    public EditCustomerWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the contents of the EditCustomerWindow.
     * Sets up text fields, buttons, and layout.
     */
    private void initialize() {
        setTitle("Edit Customer");
        setSize(400, 200);

        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(new JLabel("Flight ID:"));
        topPanel.add(flightIdText);
        topPanel.add(new JLabel("New Name:"));
        topPanel.add(nameText);
        topPanel.add(new JLabel("New Phone:"));
        topPanel.add(phoneText);
        topPanel.add(new JLabel("New Email:"));
        topPanel.add(emailText);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.add(editBtn);
        bottomPanel.add(cancelBtn);

        editBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    /**
     * Handles action events from the editBtn and cancelBtn buttons.
     * Calls editCustomer() when editBtn is clicked and hides the window when cancelBtn is clicked.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == editBtn) {
            editCustomer();
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    /**
     * Edits a customer's details based on the information entered in the text fields.
     * Executes the EditCustomer command to perform the edit operation.
     * Displays success message if the customer is edited successfully or error message if any exception occurs.
     */
    private void editCustomer() {
        try {
            int flightId = Integer.parseInt(flightIdText.getText().trim());
            String newName = nameText.getText().trim();
            String newPhone = phoneText.getText().trim();
            String newEmail = emailText.getText().trim();

            // Create and execute the EditCustomer Command
            Command editCustomerCommand = new EditCustomer(flightId, newName, newPhone, newEmail);
            if (parentFrame instanceof MainWindow) {
                editCustomerCommand.execute(((MainWindow) parentFrame).getFlightBookingSystem());
                ((MainWindow) parentFrame).displayCustomers(); // Refresh customers view in MainWindow
            } else if (parentFrame instanceof AdminWindow) {
                editCustomerCommand.execute(((AdminWindow) parentFrame).getFlightBookingSystem());
                ((AdminWindow) parentFrame).displayCustomers(); // Refresh customers view in AdminWindow
            } else if (parentFrame instanceof UserWindow) {
                // Adjust accordingly if UserWindow needs to display customer details
                JOptionPane.showMessageDialog(this, "User cannot edit customers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Notify success
            JOptionPane.showMessageDialog(this, "Customer details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Close the EditCustomerWindow
            setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid flight ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
