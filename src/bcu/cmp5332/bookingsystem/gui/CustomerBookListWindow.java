package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The CustomerBookListWindow class represents a graphical user interface for displaying bookings associated with a specific customer ID.
 * It extends JFrame and implements ActionListener.
 * 
 * @author Sandip, Sailesh
 */
public class CustomerBookListWindow extends JFrame implements ActionListener {

    private JFrame parentFrame;
    private JTextField custIdField = new JTextField();
    private JButton confirmBtn = new JButton("Confirm");
    private FlightBookingSystem fbs;

    /**
     * Constructs a CustomerBookListWindow with a given parent JFrame and FlightBookingSystem instance.
     *
     * @param parentFrame The parent JFrame that this window is displayed on top of.
     * @param fbs         The FlightBookingSystem instance used to retrieve booking information.
     */
    public CustomerBookListWindow(JFrame parentFrame, FlightBookingSystem fbs) {
        this.parentFrame = parentFrame;
        this.fbs = fbs;
        initialize();
    }

    /**
     * Initializes the contents of the CustomerBookListWindow.
     * Sets up text fields, buttons, and layout.
     */
    private void initialize() {
        setTitle("Passenger List by Flight ID");
        setSize(350, 220);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(new JLabel("Customer ID: "));
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
     * Retrieves booking information based on the customer ID entered.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmBtn) {
            bookingList();
        }
    }

    /**
     * Retrieves booking information based on the customer ID entered in the text field.
     * Displays the booking details in a table format.
     * Shows an error message if the entered customer ID is not numeric.
     */
    private void bookingList() {
        try {
            int custId = Integer.parseInt(custIdField.getText());
            List<Booking> bookingList = fbs.getBookings();
            List<Booking> custBookingList = new ArrayList<>();
            for (Booking booking : bookingList) {
                if (booking.getCustomer().getId() == custId) {
                    custBookingList.add(booking);
                }
            }

            String[] columns = {"Name", "Flight ID", "Price", "Booking Date"};
            Object[][] data = new Object[custBookingList.size()][4];

            for (int i = 0; i < custBookingList.size(); i++) {
                Booking booking = custBookingList.get(i);
                Flight flight = booking.getFlight();
                Customer customer = booking.getCustomer();

                data[i][0] = customer.getName();
                data[i][1] = flight.getId();
                data[i][2] = flight.getPrice();
                data[i][3] = booking.getBookingDate();
            }

            JTable table = new JTable(data, columns);
            displayTableInFrame(table);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Customer ID (numeric).", "Invalid Customer ID", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a given JTable within a new JFrame.
     *
     * @param table The JTable to display.
     */
    private void displayTableInFrame(JTable table) {
        JFrame frame = new JFrame("Booking List for customer ID: " + custIdField.getText());
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);
        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
}
