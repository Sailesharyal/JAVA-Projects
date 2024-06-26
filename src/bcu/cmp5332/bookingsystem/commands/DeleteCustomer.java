package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The DeleteCustomer class represents a command to delete a customer from the flight booking system.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class DeleteCustomer implements Command {

    private final int id;

    /**
     * Constructs a DeleteCustomer command with the specified customer ID.
     *
     * @param id The ID of the customer to be deleted.
     */
    public DeleteCustomer(int id) {
        this.id = id;
    }

    /**
     * Executes the DeleteCustomer command by deleting the customer with the specified ID from the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while deleting the customer.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        flightBookingSystem.deleteCustomer(id);
        System.out.println("Customer detail Deleted successfully");
    }
}
