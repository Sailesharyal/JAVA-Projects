package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DeleteBookingWindow class represents a graphical user interface for deleting a booking.
 * It extends JFrame and implements ActionListener.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class DeleteBookingWindow extends JFrame implements ActionListener {
    private JFrame parentFrame; // Accepts any JFrame as parent
    private JTextField custIdField = new JTextField();
    private JTextField flightIdField = new JTextField();
    private JButton confirmBtn = new JButton("Confirm");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs a DeleteBookingWindow with a given parent JFrame.
     *
     * @param parentFrame The parent JFrame that this window is displayed on top of.
     */
    public DeleteBookingWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the contents of the DeleteBookingWindow.
     * Sets up text fields, buttons, and layout.
     */
    private void initialize() {
        setTitle("Delete Booking");
        setSize(350, 220);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("Customer Id : "));
        topPanel.add(custIdField);
        topPanel.add(new JLabel("Flight Id : "));
        topPanel.add(flightIdField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(confirmBtn);
        bottomPanel.add(cancelBtn);

        confirmBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        getContentPane().add(topPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    /**
     * Handles action events from the confirmBtn and cancelBtn buttons.
     * Calls deleteBooking() when confirmBtn is clicked or hides the window when cancelBtn is clicked.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmBtn) {
            deleteBooking();
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    /**
     * Deletes a booking based on the customer ID and flight ID entered in the text fields.
     * Executes the CancelBooking command to perform the deletion.
     * Displays success message if the booking is deleted successfully or error message if any exception occurs.
     */
    private void deleteBooking() {
        try {
            int custId = Integer.parseInt(custIdField.getText());
            int flightId = Integer.parseInt(flightIdField.getText());

            // Create and execute the CancelBooking Command
            Command cancelBookingCommand = new CancelBooking(flightId, custId);
            if (parentFrame instanceof MainWindow) {
                cancelBookingCommand.execute(((MainWindow) parentFrame).getFlightBookingSystem());
            } else if (parentFrame instanceof AdminWindow) {
                cancelBookingCommand.execute(((AdminWindow) parentFrame).getFlightBookingSystem());
            } else if (parentFrame instanceof UserWindow) {
                cancelBookingCommand.execute(((UserWindow) parentFrame).getFlightBookingSystem());
            }

            // Notify success
            JOptionPane.showMessageDialog(this, "Booking canceled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Hide (close) the DeleteBookingWindow
            setVisible(false);
        } catch (NumberFormatException | FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
