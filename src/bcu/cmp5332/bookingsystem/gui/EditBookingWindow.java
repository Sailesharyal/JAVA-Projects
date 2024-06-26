package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * The EditBookingWindow class represents a graphical user interface for editing a booking.
 * It extends JFrame and implements ActionListener.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class EditBookingWindow extends JFrame implements ActionListener {

    private JFrame parentFrame; // Accepts any JFrame as parent
    private JTextField oldFlightIdText = new JTextField();
    private JTextField custIdText = new JTextField();
    private JTextField newFlightIdText = new JTextField();
    private JTextField newBookingDate = new JTextField();

    private JButton editBtn = new JButton("Edit");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an EditBookingWindow with a given parent JFrame.
     *
     * @param parentFrame The parent JFrame that this window is displayed on top of.
     */
    public EditBookingWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initialize();
    }

    /**
     * Initializes the contents of the EditBookingWindow.
     * Sets up text fields, buttons, and layout.
     */
    private void initialize() {
        setTitle("Edit Booking");
        setSize(400, 200);

        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(new JLabel("Old Flight ID:"));
        topPanel.add(oldFlightIdText);
        topPanel.add(new JLabel("Customer ID:"));
        topPanel.add(custIdText);
        topPanel.add(new JLabel("New Flight ID:"));
        topPanel.add(newFlightIdText);
        topPanel.add(new JLabel("New Booking Date (YYYY-MM-DD):"));
        topPanel.add(newBookingDate);

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
     * Calls editBooking() when editBtn is clicked and hides the window when cancelBtn is clicked.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == editBtn) {
            editBooking();
        } else if (ae.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    /**
     * Edits a booking based on the information entered in the text fields.
     * Executes the EditBooking command to perform the edit operation.
     * Displays success message if the booking is edited successfully or error message if any exception occurs.
     */
    private void editBooking() {
        try {
            int oldFlightId = Integer.parseInt(oldFlightIdText.getText().trim());
            int custId = Integer.parseInt(custIdText.getText().trim());
            int newFlightId = Integer.parseInt(newFlightIdText.getText().trim());
            LocalDate date = LocalDate.parse(newBookingDate.getText());

            // Create and execute the EditBooking Command
            Command editBookingCommand = new EditBooking(custId, oldFlightId, newFlightId, date);
            if (parentFrame instanceof MainWindow) {
                editBookingCommand.execute(((MainWindow) parentFrame).getFlightBookingSystem());
            } else if (parentFrame instanceof AdminWindow) {
                editBookingCommand.execute(((AdminWindow) parentFrame).getFlightBookingSystem());
            } else if (parentFrame instanceof UserWindow) {
                editBookingCommand.execute(((UserWindow) parentFrame).getFlightBookingSystem());
            }

            // Notify success
            JOptionPane.showMessageDialog(this, "Booking details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Close the EditBookingWindow
            setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
