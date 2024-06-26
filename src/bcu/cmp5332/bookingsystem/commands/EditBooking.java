package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The EditBooking class represents a command to edit an existing booking in the flight booking system.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal 
 */
public class EditBooking implements Command {

    private final int cusId;
    private final int new_flightid;
    private final int flightId;
    private final LocalDate bookingDate;

    /**
     * Constructs an EditBooking command with the specified parameters.
     *
     * @param cusId The ID of the customer whose booking is to be edited.
     * @param flightId The ID of the current flight for the booking.
     * @param new_flightid The new flight ID to update the booking with.
     * @param bookingDate The new booking date to update the booking with.
     */
    public EditBooking(int cusId, int flightId, int new_flightid, LocalDate bookingDate) {
        this.cusId = cusId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
        this.new_flightid = new_flightid;
    }

    /**
     * Executes the EditBooking command by updating the booking details in the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while updating the booking.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        flightBookingSystem.updateBooking(cusId, flightId, new_flightid, bookingDate);
        System.out.println("Booking updated successfully");
    }
}
