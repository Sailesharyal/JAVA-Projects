package bcu.cmp5332.bookingsystem.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer of the flight booking system.
 * 
 * This class encapsulates information about a customer including their ID, name,
 * phone number, email, and a list of bookings made by the customer.
 * It provides methods to retrieve and modify customer details, manage bookings,
 * and generate short and long-form textual representations of customer details.
 * 
 * @author Sandip Magar, Sailesh Aryal
 * 
 */
public class Customer {
    
    private int id; // The unique identifier of the customer
    private String name; // The name of the customer
    private String phone; // The phone number of the customer
    private String email; // The email address of the customer
    private final List<Booking> bookings = new ArrayList<>(); // List of bookings made by the customer
    
    /**
     * Constructs a new Customer object with the specified attributes.
     * 
     * @param id The unique identifier of the customer
     * @param name The name of the customer
     * @param phone The phone number of the customer
     * @param email The email address of the customer
     * @param bookings The list of bookings made by the customer
     */
    public Customer(int id, String name, String phone, String email, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bookings.addAll(bookings); // Add all provided bookings to the customer's list
    }
    
    /**
     * Retrieves the unique identifier of the customer.
     * 
     * @return The customer ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the unique identifier of the customer.
     * 
     * @param newId The new customer ID
     */
    public void setId(int newId) {
        this.id = newId;
    }
    
    /**
     * Retrieves the name of the customer.
     * 
     * @return The customer's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the customer.
     * 
     * @param newName The new name of the customer
     */
    public void setName(String newName) {
        this.name = newName;
    }
    
    /**
     * Retrieves the phone number of the customer.
     * 
     * @return The customer's phone number
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Sets the phone number of the customer.
     * 
     * @param newPhone The new phone number of the customer
     */
    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }
    
    /**
     * Retrieves a copy of the list of bookings made by the customer.
     * 
     * @return A list of Booking objects
     */
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings); // Return a copy to prevent external modifications
    }
    
    /**
     * Adds a new booking to the customer's list of bookings.
     * 
     * @param booking The new Booking object to add
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    /**
     * Generates a short textual representation of the customer's details.
     * 
     * @return A short string describing the customer
     */
    public String getDetailsShort() {
        return "Customer #" + id + " - " + name + " - " + phone + " - " + email;
    }
    
    /**
     * Generates a long-form textual representation of the customer's details,
     * including details of all bookings made by the customer.
     * 
     * @return A detailed string describing the customer and their bookings
     */
    public String getDetailsLong() {
        StringBuilder details = new StringBuilder();
        details.append("Customer #" + id + " - " + name + " - " + phone + "\n");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        for (Booking booking : bookings) {
            Flight flight = booking.getFlight();
            details.append(" Flight #" + flight.getFlightNumber() + " Origin " + flight.getOrigin() +
                    " Destination " + flight.getDestination() + " Date " + flight.getDepartureDate().format(dtf) +
                    " Price " + flight.getPrice() + "\n");
        }

        return details.toString();
    }
    
    /**
     * Removes a booking from the customer's list of bookings.
     * 
     * @param booking The Booking object to remove
     */
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
    
    /**
     * Retrieves the email address of the customer.
     * 
     * @return The customer's email address
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Sets the email address of the customer.
     * 
     * @param email The new email address of the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
