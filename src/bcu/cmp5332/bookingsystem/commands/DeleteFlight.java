package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The DeleteFlight class represents a command to delete a flight from the flight booking system.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class DeleteFlight implements Command {

    private final int id;

    /**
     * Constructs a DeleteFlight command with the specified flight ID.
     *
     * @param id The ID of the flight to be deleted.
     */
    public DeleteFlight(int id) {
        this.id = id;
    }

    /**
     * Executes the DeleteFlight command by deleting the flight with the specified ID from the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while deleting the flight.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        flightBookingSystem.deleteFlight(id);
        System.out.println("Flight details Deleted successfully");
    }
}
