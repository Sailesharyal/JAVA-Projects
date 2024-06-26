package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a flight in the booking system.
 * 
 * This class encapsulates information about a flight including its unique ID,
 * flight number, origin, destination, departure date, number of seats available,
 * price per seat, remaining number of seats, and a set of passengers (customers)
 * booked on the flight.
 * It provides methods to retrieve and modify flight details, manage passengers,
 * and generate short and long-form textual representations of flight details.
 * 
 * @author Sandip Magar, Sailesh Aryal
 * 
 */
public class Flight {
    
    private int id; // The unique identifier of the flight
    private String flightNumber; // The flight number
    private String origin; // The origin airport
    private String destination; // The destination airport
    private LocalDate departureDate; // The departure date of the flight
    private int numberOfSeats; // Total number of seats available on the flight
    private int price; // Price per seat
    private int remainingNumberOfSeats; // Number of seats still available on the flight
    private boolean deleted; // Indicates if the flight is marked as deleted
    
    private final Set<Customer> passengers; // Set of passengers (customers) booked on the flight

    /**
     * Constructs a new Flight object with the specified attributes.
     * 
     * @param id The unique identifier of the flight
     * @param flightNumber The flight number
     * @param origin The origin airport
     * @param destination The destination airport
     * @param departureDate The departure date of the flight
     * @param numberOfSeats The total number of seats available on the flight
     * @param price The price per seat
     */
    public Flight(int id, String flightNumber, String origin, String destination,
                  LocalDate departureDate, int numberOfSeats, int price) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        passengers = new HashSet<>();
        remainingNumberOfSeats = numberOfSeats;
        this.deleted = false;
    }

    /**
     * Retrieves the unique identifier of the flight.
     * 
     * @return The flight ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the flight.
     * 
     * @param id The new flight ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the flight number.
     * 
     * @return The flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the flight number.
     * 
     * @param flightNumber The new flight number
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    /**
     * Retrieves the origin airport of the flight.
     * 
     * @return The origin airport
     */
    public String getOrigin() {
        return origin;
    }
    
    /**
     * Sets the origin airport of the flight.
     * 
     * @param origin The new origin airport
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Retrieves the destination airport of the flight.
     * 
     * @return The destination airport
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination airport of the flight.
     * 
     * @param destination The new destination airport
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Retrieves the departure date of the flight.
     * 
     * @return The departure date
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the departure date of the flight.
     * 
     * @param departureDate The new departure date
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Retrieves a copy of the list of passengers (customers) booked on the flight.
     * 
     * @return A list of Customer objects
     */
    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers); // Return a copy to prevent external modifications
    }
	
    /**
     * Generates a short textual representation of the flight's details.
     * 
     * @return A short string describing the flight
     */
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf)
                + " price " + price + " Number of seats " + numberOfSeats;
    }

    /**
     * Generates a long-form textual representation of the flight's details,
     * including details of all passengers (customers) booked on the flight.
     * 
     * @return A detailed string describing the flight and its passengers
     */
    public String getDetailsLong() {
        StringBuilder details = new StringBuilder();
        details.append("Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                       + destination + " on " + departureDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "\n");

        for (Customer customer : passengers) {
            details.append(" Passenger Name: " + customer.getName() + " Phone Number: " + customer.getPhone() +
                           " Email: " + customer.getEmail() + "\n");
        }

        return details.toString();
    }
    
    /**
     * Adds a passenger (customer) to the flight's list of passengers.
     * 
     * @param passenger The Customer object representing the passenger to add
     */
    public void addPassenger(Customer passenger) {
        passengers.add(passenger);
    }
    
    /**
     * Removes a passenger (customer) from the flight's list of passengers.
     * 
     * @param passenger The Customer object representing the passenger to remove
     */
    public void removePassenger(Customer passenger) {
        passengers.remove(passenger);
    }
    
    /**
     * Retrieves the current number of passengers (customers) booked on the flight.
     * 
     * @return The number of passengers
     */
    public int numOfPassengers() {
        return passengers.size();
    }
    
    /**
     * Retrieves the total number of seats available on the flight.
     * 
     * @return The total number of seats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    
    /**
     * Retrieves the price per seat of the flight.
     * 
     * @return The price per seat
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * Sets the total number of seats available on the flight.
     * 
     * @param numberOfSeats The new total number of seats
     */
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    
    /**
     * Sets the price per seat of the flight.
     * 
     * @param price The new price per seat
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    /**
     * Retrieves the remaining number of seats available on the flight.
     * 
     * @return The remaining number of seats
     */
    public int getRemainingNumberOfSeats() {
        return remainingNumberOfSeats;
    }
    
    /**
     * Decreases the remaining number of seats available on the flight by 1.
     */
    public void reduceNumberOfSeats() {
        remainingNumberOfSeats--;
    }
    
    /**
     * Increases the remaining number of seats available on the flight by 1.
     */
    public void increaseNumberOfSeats() {
        remainingNumberOfSeats++;
    }
    
    /**
     * Retrieves the deletion status of the flight.
     * 
     * @return True if the flight is marked as deleted, false otherwise
     */
    public boolean getDeleted() {
        return deleted;
    }
    
    /**
     * Sets the deletion status of the flight.
     * 
     * @param deleted True to mark the flight as deleted, false otherwise
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
