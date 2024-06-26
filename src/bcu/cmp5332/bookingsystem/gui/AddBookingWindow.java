package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A graphical user interface window for adding new bookings to the flight booking system.
 * Users can enter customer ID, flight ID, and booking date to create a new booking.
 * It handles user input validation and executes the AddBooking command upon user confirmation.
 * 
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class AddBookingWindow extends JFrame implements ActionListener {

    private JFrame parentFrame; // Accepts any JFrame as parent
    private JTextField custIdField = new JTextField();
    private JTextField flightIdField = new JTextField();
    private JTextField bookingDateField = new JTextField();
    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an AddBookingWindow with the specified parent JFrame.
     * Initializes the GUI components and sets up event listeners for Add and Cancel buttons.
     *
     * @param parentFrame The parent JFrame to which this window is attached.
     */
    public AddBookingWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the contents of the AddBookingWindow.
     * Configures the layout, adds input fields for customer ID, flight ID, and booking date.
     * Sets up event listeners for Add and Cancel buttons.
     */
    private void initialize() {
        setTitle("New Booking Add:");
        setSize(350, 220);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.add(new JLabel("Customer Id : "));
        topPanel.add(custIdField);
        topPanel.add(new JLabel("Flight Id : "));
        topPanel.add(flightIdField);
        topPanel.add(new JLabel("Booking Date (YYYY-MM-DD): "));
        topPanel.add(bookingDateField);

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
     * If Add button is clicked, calls addBooking() method.
     * If Cancel button is clicked, hides the AddBookingWindow.
     *
     * @param ae The ActionEvent triggered by Add or Cancel button clicks.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addBooking();
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    /**
     * Attempts to add a new booking to the flight booking system.
     * Reads customer ID, flight ID, and booking date from input fields.
     * Executes the AddBooking command and refreshes the parent window's view.
     * Displays error messages for invalid input or system errors.
     */
    private void addBooking() {
        try {
            int custId = Integer.parseInt(custIdField.getText());
            int flightId = Integer.parseInt(flightIdField.getText());
            LocalDate bookingDate = LocalDate.parse(bookingDateField.getText());

            // Create and execute the AddBooking Command
            Command addBookingCommand = new AddBooking(custId, flightId, bookingDate);
            if (parentFrame instanceof MainWindow) {
                addBookingCommand.execute(((MainWindow) parentFrame).getFlightBookingSystem());
                ((MainWindow) parentFrame).displayFlights(); // Refresh flights view in MainWindow
            } else if (parentFrame instanceof AdminWindow) {
                addBookingCommand.execute(((AdminWindow) parentFrame).getFlightBookingSystem());
                ((AdminWindow) parentFrame).displayFlights(); // Refresh flights view in AdminWindow
            } else if (parentFrame instanceof UserWindow) {
                addBookingCommand.execute(((UserWindow) parentFrame).getFlightBookingSystem());
                ((UserWindow) parentFrame).displayFlights(); // Refresh flights view in UserWindow
            }

            // Hide (close) the AddBookingWindow
            setVisible(false);
        } catch (DateTimeParseException | FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
