package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The AddFlightWindow class provides a graphical user interface for adding a new flight
 * to the flight booking system. It extends JFrame and implements ActionListener to handle
 * user interactions with buttons.
 * 
 * @author Sandip Magar, Sailesh aryal
 */
public class AddFlightWindow extends JFrame implements ActionListener {

    private JFrame parentFrame;
    private JTextField flightNoText = new JTextField();
    private JTextField originText = new JTextField();
    private JTextField destinationText = new JTextField();
    private JTextField depDateText = new JTextField();
    private JTextField numberOfSeats = new JTextField();
    private JTextField price = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an AddFlightWindow with a specified parent frame.
     *
     * @param parentFrame The parent JFrame from which this window is opened.
     */
    public AddFlightWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the AddFlightWindow by setting up its components and layout.
     * Sets the title, size, and layout of the window. Adds text fields for user input
     * and buttons for adding a flight or canceling the operation.
     */
    private void initialize() {
        setTitle("Add a New Flight");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setSize(350, 250);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Flight No : "));
        topPanel.add(flightNoText);
        topPanel.add(new JLabel("Origin : "));
        topPanel.add(originText);
        topPanel.add(new JLabel("Destination : "));
        topPanel.add(destinationText);
        topPanel.add(new JLabel("Departure Date (YYYY-MM-DD) : "));
        topPanel.add(depDateText);
        topPanel.add(new JLabel("Number of Seats : "));
        topPanel.add(numberOfSeats);
        topPanel.add(new JLabel("Price : "));
        topPanel.add(price);

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
     * Handles actions performed on buttons in the AddFlightWindow.
     * If the "Add" button is clicked, it attempts to add a new flight using the input data
     * from the text fields. If the "Cancel" button is clicked, it hides (closes) the window.
     *
     * @param ae The ActionEvent triggered by the user action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addFlight();
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    /**
     * Attempts to add a new flight using input data from the text fields.
     * Displays error messages if input validation fails or if an exception occurs.
     */
    private void addFlight() {
        try {
            String flightNumber = flightNoText.getText();
            String origin = originText.getText();
            String destination = destinationText.getText();
            int seats = Integer.parseInt(numberOfSeats.getText());
            int flightPrice = Integer.parseInt(price.getText());
            LocalDate departureDate = LocalDate.parse(depDateText.getText());

            // Identify the type of parentFrame and execute the AddFlight Command
            if (parentFrame instanceof MainWindow) {
                MainWindow mainWindow = (MainWindow) parentFrame;
                Command addFlight = new AddFlight(flightNumber, origin, destination, departureDate, seats, flightPrice);
                addFlight.execute(mainWindow.getFlightBookingSystem());
                mainWindow.displayFlights();
            } else if (parentFrame instanceof AdminWindow) {
                AdminWindow adminWindow = (AdminWindow) parentFrame;
                Command addFlight = new AddFlight(flightNumber, origin, destination, departureDate, seats, flightPrice);
                addFlight.execute(adminWindow.getFlightBookingSystem());
                adminWindow.displayFlights();
            } else if (parentFrame instanceof UserWindow) {
                UserWindow userWindow = (UserWindow) parentFrame;
                Command addFlight = new AddFlight(flightNumber, origin, destination, departureDate, seats, flightPrice);
                addFlight.execute(userWindow.getFlightBookingSystem());
                userWindow.displayFlights();
            }

            setVisible(false); // Hide (close) the AddFlightWindow
        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input format. Please check and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
