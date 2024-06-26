package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DeleteFlightWindow class represents a graphical user interface for deleting a flight.
 * It extends JFrame and implements ActionListener.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class DeleteFlightWindow extends JFrame implements ActionListener {
    private JFrame parentFrame; // Accepts any JFrame as parent
    private JTextField flightIdField = new JTextField();
    private JButton confirmBtn = new JButton("Confirm");

    /**
     * Constructs a DeleteFlightWindow with a given parent JFrame.
     *
     * @param parentFrame The parent JFrame that this window is displayed on top of.
     */
    public DeleteFlightWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the contents of the DeleteFlightWindow.
     * Sets up text fields, buttons, and layout.
     */
    private void initialize() {
        setTitle("Delete Flight with Id");
        setSize(350, 220);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(new JLabel("Flight Id : "));
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
     * Handles action events from the confirmBtn button.
     * Calls deleteFlight() when confirmBtn is clicked.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmBtn) {
            deleteFlight();
        }
    }

    /**
     * Deletes a flight based on the flight ID entered in the text field.
     * Executes the DeleteFlight command to perform the deletion.
     * Displays success message if the flight is deleted successfully or error message if any exception occurs.
     */
    private void deleteFlight() {
        try {
            int flightId = Integer.parseInt(flightIdField.getText());

            // Create and execute the DeleteFlight Command
            Command deleteFlightCommand = new DeleteFlight(flightId);
            if (parentFrame instanceof MainWindow) {
                deleteFlightCommand.execute(((MainWindow) parentFrame).getFlightBookingSystem());
                ((MainWindow) parentFrame).displayFlights(); // Refresh flights view in MainWindow
            } else if (parentFrame instanceof AdminWindow) {
                deleteFlightCommand.execute(((AdminWindow) parentFrame).getFlightBookingSystem());
                ((AdminWindow) parentFrame).displayFlights(); // Refresh flights view in AdminWindow
            } else if (parentFrame instanceof UserWindow) {
                deleteFlightCommand.execute(((UserWindow) parentFrame).getFlightBookingSystem());
                ((UserWindow) parentFrame).displayFlights(); // Refresh flights view in UserWindow
            }

            // Notify success
            JOptionPane.showMessageDialog(this, "Flight deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Hide (close) the DeleteFlightWindow
            setVisible(false);
        } catch (NumberFormatException | FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
