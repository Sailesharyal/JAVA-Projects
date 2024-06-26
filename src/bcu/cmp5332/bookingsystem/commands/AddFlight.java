package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.time.LocalDate;

/**
 * The AddFlight class represents a command to add a new flight to the flight booking system.
 * It implements the Command interface.
 */
public class AddFlight implements Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private int numberOfSeats;
    private int price;

    /**
     * Constructs an AddFlight command with the specified flight details.
     *
     * @param flightNumber   The flight number.
     * @param origin         The origin of the flight.
     * @param destination    The destination of the flight.
     * @param departureDate  The departure date of the flight.
     * @param numberOfSeats  The number of seats available on the flight.
     * @param price          The price of the flight.
     */
    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate, int numberOfSeats, int price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
    }

    /**
     * Executes the AddFlight command by creating a new Flight object with the provided details
     * and adding it to the flight booking system.
     *
     * @param flightBookingSystem The FlightBookingSystem instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs during the flight addition process.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // Find the maximum flight ID currently in the system
        int maxId = 0;
        if (!flightBookingSystem.getFlights().isEmpty()) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
        
        // Create a new Flight object with the next available ID and add it to the system
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate, numberOfSeats, price);
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added.");
    }
}
