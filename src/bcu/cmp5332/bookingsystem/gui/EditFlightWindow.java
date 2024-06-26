package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.EditFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EditFlightWindow class represents a graphical user interface for editing flight details.
 * It extends JFrame and implements ActionListener.
 * 
 * @author Sandip Magar,  Sailesh Aryal
 */
public class EditFlightWindow extends JFrame implements ActionListener {

    private JFrame parentFrame; // Accepts any JFrame as parent
    private JTextField flightIdText = new JTextField();
    private JTextField numberOfSeatsText = new JTextField();
    private JTextField priceText = new JTextField();

    private JButton editBtn = new JButton("Edit");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an EditFlightWindow with a given parent JFrame.
     *
     * @param parentFrame The parent JFrame that this window is displayed on top of.
     */
    public EditFlightWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the contents of the EditFlightWindow.
     * Sets up text fields, buttons, and layout.
     */
    private void initialize() {
        setTitle("Edit Flight");
        setSize(350, 180);

        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        topPanel.add(new JLabel("Flight ID:"));
        topPanel.add(flightIdText);
        topPanel.add(new JLabel("Number of Seats:"));
        topPanel.add(numberOfSeatsText);
        topPanel.add(new JLabel("Price:"));
        topPanel.add(priceText);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
     * Calls editFlight() when editBtn is clicked and hides the window when cancelBtn is clicked.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == editBtn) {
            editFlight();
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    /**
     * Edits a flight's details based on the information entered in the text fields.
     * Executes the EditFlight command to perform the edit operation.
     * Displays success message if the flight is edited successfully or error message if any exception occurs.
     */
    private void editFlight() {
        try {
            int flightId = Integer.parseInt(flightIdText.getText().trim());
            int seats = Integer.parseInt(numberOfSeatsText.getText().trim());
            int flightPrice = Integer.parseInt(priceText.getText().trim());

            // Create and execute the EditFlight Command
            Command editFlightCommand = new EditFlight(flightId, seats, flightPrice);
            if (parentFrame instanceof MainWindow) {
                editFlightCommand.execute(((MainWindow) parentFrame).getFlightBookingSystem());
                ((MainWindow) parentFrame).displayFlights(); // Refresh flights view in MainWindow
            } else if (parentFrame instanceof AdminWindow) {
                editFlightCommand.execute(((AdminWindow) parentFrame).getFlightBookingSystem());
                ((AdminWindow) parentFrame).displayFlights(); // Refresh flights view in AdminWindow
            } else if (parentFrame instanceof UserWindow) {
                // Adjust accordingly if UserWindow needs to display flight details
                JOptionPane.showMessageDialog(this, "User cannot edit flights.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Notify success
            JOptionPane.showMessageDialog(this, "Flight details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Close the EditFlightWindow
            setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for seats and price.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
