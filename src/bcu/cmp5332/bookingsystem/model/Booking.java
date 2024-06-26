package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

/**
 * Represents a booking made by a customer for a flight.
 * 
 * This class encapsulates information about the customer making the booking,
 * the flight being booked, and the date when the booking was made.
 * 
 * @author Sandip Magar, Sailesh Aryal
 *
 */
public class Booking {
    
    private Customer customer; // The customer making the booking
    private Flight flight; // The flight being booked
    private LocalDate bookingDate; // The date when the booking was made
    
    /**
     * Constructs a new Booking object with the specified customer, flight, and booking date.
     * 
     * @param customer The customer making the booking
     * @param flight The flight being booked
     * @param bookingDate The date when the booking was made
     */
    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
    }
    
    /**
     * Retrieves the customer associated with this booking.
     * 
     * @return The customer object
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Sets the customer associated with this booking.
     * 
     * @param newCustomer The new customer object
     */
    public void setCustomer(Customer newCustomer) {
        this.customer = newCustomer;
    }
    
    /**
     * Retrieves the flight associated with this booking.
     * 
     * @return The flight object
     */
    public Flight getFlight() {
        return flight;
    }
    
    /**
     * Sets the flight associated with this booking.
     * 
     * @param newFlight The new flight object
     */
    public void setFlight(Flight newFlight) {
        this.flight = newFlight;
    }
    
    /**
     * Retrieves the date when this booking was made.
     * 
     * @return The booking date
     */
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    
    /**
     * Sets the date when this booking was made.
     * 
     * @param newBookingDate The new booking date
     */
    public void setBookingDate(LocalDate newBookingDate) {
        this.bookingDate = newBookingDate;
    }
}
