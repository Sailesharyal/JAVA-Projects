package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * The ListBookings class represents a command to list all bookings in the flight booking system.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class ListBookings implements Command {

    /**
     * Executes the ListBookings command by retrieving and printing all bookings in the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while retrieving bookings.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Booking> bookings = flightBookingSystem.getBookings();
        for (Booking booking : bookings) {
            Customer customer = booking.getCustomer();
            Flight flight = booking.getFlight();
            System.out.println("Customer #" + customer.getId() + " Flight #" + flight.getId() + " on " + booking.getBookingDate());
        }
        System.out.println(bookings.size() + " booking(s)");
    }
}
