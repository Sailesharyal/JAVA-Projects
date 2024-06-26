package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The EditFlight class represents a command to edit an existing flight's information in the flight booking system.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class EditFlight implements Command {

    private final int id;
    private final int numOfSeats;
    private final int price;

    /**
     * Constructs an EditFlight command with the specified parameters.
     *
     * @param id The ID of the flight to be edited.
     * @param numOfSeats The new number of seats for the flight.
     * @param price The new price for the flight.
     */
    public EditFlight(int id, int numOfSeats, int price) {
        this.id = id;
        this.numOfSeats = numOfSeats;
        this.price = price;
    }

    /**
     * Executes the EditFlight command by updating the flight's information in the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while updating the flight information.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        flightBookingSystem.updateFlight(id, numOfSeats, price);
        System.out.println("Flight info updated successfully..");
    }
}
