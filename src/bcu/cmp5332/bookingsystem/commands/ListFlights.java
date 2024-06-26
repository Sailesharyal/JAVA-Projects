package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * The ListFlights class represents a command to list all flights in the flight booking system.
 * It implements the Command interface.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class ListFlights implements Command {

    /**
     * Executes the ListFlights command by retrieving and printing all flights in the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while retrieving flights.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> flights = flightBookingSystem.getFlights(); // Retrieve all flights from the system
        for (Flight flight : flights) {
            System.out.println(flight.getDetailsShort()); // Print short details of each flight
        }
        System.out.println(flights.size() + " flight(s)"); // Print total number of flights
    }
}
