package bcu.cmp5332.bookingsystem.commands;

import java.util.ArrayList;
import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The AddCustomer class represents a command to add a new customer to the flight booking system.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class AddCustomer implements Command {

    private final String name;
    private final String phone;
    private final String email;
    private final List<Booking> bookings;

    /**
     * Constructs an AddCustomer command with the specified customer details.
     *
     * @param name     The name of the customer.
     * @param phone    The phone number of the customer.
     * @param email    The email address of the customer.
     * @param bookings The list of bookings associated with the customer.
     */
    public AddCustomer(String name, String phone, String email, List<Booking> bookings) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bookings = new ArrayList<>();
        if (bookings != null) {
            this.bookings.addAll(bookings);
        }
    }

    /**
     * Executes the AddCustomer command by creating a new Customer object with the provided details
     * and adding it to the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs during the customer addition process.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // Find the maximum customer ID currently in the system
        int maxId = 0;
        if (!flightBookingSystem.getCustomers().isEmpty()) {
            int lastIndex = flightBookingSystem.getCustomers().size() - 1;
            maxId = flightBookingSystem.getCustomers().get(lastIndex).getId();
        }
        
        // Create a new Customer object with the next available ID and add it to the system
        Customer customer = new Customer(++maxId, name, phone, email, bookings);
        flightBookingSystem.addCustomer(customer);
    }
}
