package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The CancelBooking class represents a command to cancel a booking for a customer on a flight.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class CancelBooking implements Command {

    private final int cusId;
    private final int flightId;

    /**
     * Constructs a CancelBooking command with the specified customer ID and flight ID.
     *
     * @param cusId    The ID of the customer for whom the booking needs to be cancelled.
     * @param flightId The ID of the flight for which the booking needs to be cancelled.
     */
    public CancelBooking(int cusId, int flightId) {
        this.cusId = cusId;
        this.flightId = flightId;
    }

    /**
     * Executes the CancelBooking command by cancelling the booking for the specified customer on the specified flight.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs during the booking cancellation process.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        flightBookingSystem.cancelBooking(cusId, flightId);
        System.out.println("Booking cancelled successfully !!!");
    }
}
