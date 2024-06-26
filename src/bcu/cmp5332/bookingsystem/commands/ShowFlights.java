package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The ShowFlights class represents a command to display detailed information about a specific flight.
 * It implements the Command interface.
 */
public class ShowFlights implements Command {

    private final int id;

    /**
     * Constructs a ShowFlights command with the specified flight ID.
     *
     * @param id The ID of the flight to display details for.
     * @throws FlightBookingSystemException If an error occurs while initializing the command.
     */
    public ShowFlights(int id) throws FlightBookingSystemException {
        this.id = id;
    }

    /**
     * Executes the ShowFlights command by retrieving and printing detailed information about the flight.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while retrieving flight details.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        System.out.print(flightBookingSystem.flightDetailsLong(id)); // Print long details of the flight
    }
}
