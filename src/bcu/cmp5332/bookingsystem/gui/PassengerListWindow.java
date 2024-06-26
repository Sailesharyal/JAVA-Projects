package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The PassengerListWindow class represents a GUI window to display the list of passengers
 * for a given flight ID in a Flight Booking System.
 * 
 * @author Sandip Magar
 */
public class PassengerListWindow extends JFrame implements ActionListener {

    private JFrame parentFrame;
    private JTextField flightIdField = new JTextField();
    private JButton confirmBtn = new JButton("Confirm");
    private FlightBookingSystem fbs;

    /**
     * Constructs a PassengerListWindow with a specified parent frame and FlightBookingSystem instance.
     *
     * @param parentFrame The parent JFrame from which this window is launched.
     * @param fbs         The FlightBookingSystem instance associated with the application.
     */
    public PassengerListWindow(JFrame parentFrame, FlightBookingSystem fbs) {
        this.parentFrame = parentFrame;
        this.fbs = fbs;
        initialize();
    }

    /**
     * Initializes the PassengerListWindow by setting up its components and layout.
     */
    private void initialize() {
        setTitle("Passenger List by Flight ID");
        setSize(350, 220);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(new JLabel("Flight ID: "));
        topPanel.add(flightIdField);

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
     * Handles the action events generated by the Confirm button.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmBtn) {
            passengerList();
        }
    }

    /**
     * Retrieves the list of passengers for the specified flight ID and displays them in a table.
     * Shows an error message if the flight ID is invalid or if there are no passengers.
     */
    private void passengerList() {
        try {
            int flightId = Integer.parseInt(flightIdField.getText());
            List<Customer> customersList = fbs.listOfPassengersByFlight(flightId);

            String[] columns = {"Name", "Phone Number", "Email"};
            Object[][] data = new Object[customersList.size()][3];

            for (int i = 0; i < customersList.size(); i++) {
                Customer customer = customersList.get(i);
                data[i][0] = customer.getName();
                data[i][1] = customer.getPhone();
                data[i][2] = customer.getEmail();
            }

            JTable table = new JTable(data, columns);
            displayTableInFrame(table);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Flight ID (numeric).", "Invalid Flight ID", JOptionPane.ERROR_MESSAGE);
        } 
    }

    /**
     * Displays a JFrame containing the provided JTable within a scrollable pane.
     *
     * @param table The JTable to be displayed.
     */
    private void displayTableInFrame(JTable table) {
        JFrame frame = new JFrame("Passenger List for Flight ID: " + flightIdField.getText());
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);
        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
}