package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The ShowCustomer class represents a command to display detailed information about a specific customer.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class ShowCustomer implements Command {

    private final int id;

    /**
     * Constructs a ShowCustomer command with the specified customer ID.
     *
     * @param id The ID of the customer to display details for.
     * @throws FlightBookingSystemException If an error occurs while initializing the command.
     */
    public ShowCustomer(int id) throws FlightBookingSystemException {
        this.id = id;
    }

    /**
     * Executes the ShowCustomer command by retrieving and printing detailed information about the customer.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while retrieving customer details.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        System.out.print(flightBookingSystem.customerDetailsLong(id)); // Print long details of the customer
    }
}
