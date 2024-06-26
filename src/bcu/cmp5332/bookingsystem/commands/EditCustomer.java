package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The EditCustomer class represents a command to edit an existing customer's information in the flight booking system.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class EditCustomer implements Command {

    private final int id;
    private final String name;
    private final String phoneNum;
    private final String email;

    /**
     * Constructs an EditCustomer command with the specified parameters.
     *
     * @param id The ID of the customer to be edited.
     * @param name The new name for the customer.
     * @param phoneNum The new phone number for the customer.
     * @param email The new email address for the customer.
     */
    public EditCustomer(int id, String name, String phoneNum, String email) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    /**
     * Executes the EditCustomer command by updating the customer's information in the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while updating the customer information.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        flightBookingSystem.updateCustomer(id, name, phoneNum, email);
        System.out.println("Customer info updated successfully..");
    }
}
