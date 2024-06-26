package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The CustomerBookingList class represents a command to retrieve and display all bookings 
 * associated with a specific customer.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class CustomerBookingList implements Command {

    private final int cusId;

    /**
     * Constructs a CustomerBookingList command with the specified customer ID.
     *
     * @param cusId The ID of the customer for whom the booking list needs to be retrieved.
     */
    public CustomerBookingList(int cusId) {
        this.cusId = cusId;
    }

    /**
     * Executes the CustomerBookingList command by retrieving and printing all bookings
     * associated with the specified customer.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while retrieving or printing the bookings.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // Retrieve the list of bookings for the specified customer ID
        List<Booking> bookings = flightBookingSystem.bookingListByCustomer(cusId);
        
        // Print each booking details
        for (Booking booking : bookings) {
            System.out.println("Customer #" + booking.getCustomer().getId() + " Flight #" + booking.getFlight().getId() +
                               " on " + booking.getBookingDate());
        }
    }
}
