package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The AddBooking class represents a command to add a booking for a customer on a flight
 * in the flight booking system. It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class AddBooking implements Command {

    private final int cusId;
    private final int flightId;
    private final LocalDate bookingDate;

    /**
     * Constructs an AddBooking command with the specified customer ID, flight ID,
     * and booking date.
     *
     * @param cusId       The ID of the customer making the booking.
     * @param flightId    The ID of the flight for which the booking is being made.
     * @param bookingDate The date on which the booking is made.
     */
    public AddBooking(int cusId, int flightId, LocalDate bookingDate) {
        this.cusId = cusId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
    }

    /**
     * Executes the AddBooking command by adding a booking for the specified customer
     * on the specified flight using the provided booking date.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs during the booking addition process.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        flightBookingSystem.addBookingByIds(cusId, flightId, bookingDate);
    }
}
