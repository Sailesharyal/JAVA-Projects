package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The FlightBookingSystem class manages the core functionality of a flight booking system.
 * It maintains collections of customers, flights, and bookings, and provides methods to add,
 * retrieve, update, and delete customers, flights, and bookings. It also includes methods to
 * retrieve system date, customer details, flight details, and perform operations like adding,
 * deleting bookings, and retrieving passengers by flight.
 * 
 * @author Sandip Magar, Sailesh aryal
 *
 *
 */


public class FlightBookingSystem {

	/**
	 * The system date used by the flight booking system.
	 */
	private final LocalDate systemDate = LocalDate.parse("2020-11-11"); // System date


	/**
	 * Map of customers in the system.
	 */
	private final Map<Integer, Customer> customers = new TreeMap<>(); // List of customers in map

    /**
     * Map of flights in the system.
     */
    private final Map<Integer, Flight> flights = new TreeMap<>(); // List of flights in map

    /**
     * List of bookings in the system.
     */
    private final List<Booking> bookings = new ArrayList<>(); // List of bookings

    /**
     * Constructor for the FlightBookingSystem class.
     */
    public FlightBookingSystem() {
        // Constructor implementation
    }

    /**
     * Retrieves the current system date used by the flight booking system.
     *
     * @return The current system date.
     */
    
    public LocalDate getSystemDate() {
        return systemDate;
    }

    /**
     * Retrieves a list of active flights in the system (flights that are not marked as deleted).
     *
     * @return An unmodifiable list of active flights.
     */
    public List<Flight> getFlights() {
        List<Flight> out = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (!flight.getDeleted()) {
                out.add(flight);
            }
        }
        return Collections.unmodifiableList(out);
    }

    /**
     * Retrieves a flight object by its ID.
     *
     * @param id The ID of the flight to retrieve.
     * @return The Flight object corresponding to the given ID.
     * @throws FlightBookingSystemException If no flight exists with the provided ID.
     */
    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    /**
     * Retrieves a customer object by its ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The Customer object corresponding to the given ID.
     * @throws FlightBookingSystemException If no customer exists with the provided ID.
     */
    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID.");
        }
        return customers.get(id);
    }

    /**
     * Adds a new flight to the system.
     *
     * @param flight The Flight object to add.
     * @throws FlightBookingSystemException If there is a duplicate flight ID or a flight with
     *                                      the same number and departure date already exists.
     */
    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber())
                    && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with the same number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }

    /**
     * Adds a new customer to the system.
     *
     * @param customer The Customer object to add.
     * @throws FlightBookingSystemException If there is a duplicate customer ID or a customer with
     *                                      the same name and phone number already exists.
     */
    public void addCustomer(Customer customer) throws FlightBookingSystemException {
        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate ID no.");
        }
        for (Customer existing : customers.values()) {
            if (existing.getName().equals(customer.getName())
                    && existing.getPhone().equals(customer.getPhone())) {
                throw new FlightBookingSystemException("There is a customer with the same name and phone in the system");
            }
        }
        customers.put(customer.getId(), customer);
    }

    /**
     * Retrieves a list of all customers in the system.
     *
     * @return An unmodifiable list of all customers.
     */
    public List<Customer> getCustomers() {
        List<Customer> out = new ArrayList<>(customers.values());
        return Collections.unmodifiableList(out);
    }

    /**
     * Retrieves all bookings associated with a specific customer ID.
     *
     * @param id The ID of the customer whose bookings are to be retrieved.
     * @return An unmodifiable list of bookings associated with the specified customer.
     * @throws FlightBookingSystemException If no customer exists with the provided ID.
     */
    public List<Booking> getBookingByCusIDs(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID.");
        }
        Customer customer = customers.get(id);
        return customer.getBookings();
    }

    /**
     * Deletes a flight from the system by marking it as deleted.
     *
     * @param id The ID of the flight to delete.
     * @throws FlightBookingSystemException If no flight exists with the provided ID.
     */
    public void deleteFlight(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        Flight flight = flights.get(id);
        flight.setDeleted(true); // Mark flight as deleted
    }

    /**
     * Deletes a customer from the system.
     *
     * @param id The ID of the customer to delete.
     * @throws FlightBookingSystemException If no customer exists with the provided ID.
     */
    public void deleteCustomer(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no Customer with that ID.");
        }
        customers.remove(id);
    }

    /**
     * Adds a new booking to the system.
     *
     * @param booking The Booking object to add.
     * @throws FlightBookingSystemException If the customer or flight associated with the booking
     *                                      does not exist, or if there is a booking with the same
     *                                      customer, flight, and booking date already exists.
     */
    public void addBooking(Booking booking) throws FlightBookingSystemException {
        Customer customer = booking.getCustomer();
        Flight flight = booking.getFlight();
        if (!customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Customer unavailable");
        }
        if (!flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Flight Not available");
        }

        for (Booking existing : bookings) {
            if (existing.getCustomer().equals(booking.getCustomer())
                    && existing.getFlight().equals(booking.getFlight())
                    && existing.getBookingDate().equals(booking.getBookingDate())) {
                throw new FlightBookingSystemException("There is a booking with the same customer, flight, and booking date in the system");
            }
        }
        bookings.add(booking);
        customer.addBooking(booking); // Add booking to customer
        flight.addPassenger(customer); // Add passenger to flight
        flight.reduceNumberOfSeats(); // Reduce the number of remaining seats
    }

    /**
     * Retrieves a list of all bookings in the system.
     *
     * @return An unmodifiable list of all bookings.
     */
    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }

    /**
     * Adds a booking to the system based on customer ID, flight ID, and booking date.
     *
     * @param cusId       The ID of the customer making the booking.
     * @param flightId    The ID of the flight for which booking is made.
     * @param bookingDate The date on which the booking is made.
     * @throws FlightBookingSystemException If the customer or flight does not exist,
     *                                      or if the flight has no remaining seats.
     */
    public void addBookingByIds(int cusId, int flightId, LocalDate bookingDate) throws FlightBookingSystemException {
        if (!customers.containsKey(cusId)) {
            throw new IllegalArgumentException("Customer with that customer id not available");
        }
        if (!flights.containsKey(flightId)) {
            throw new IllegalArgumentException("Flight with that flight id not available");
        }
        Customer customer = customers.get(cusId);
        Flight flight = flights.get(flightId);
        Booking booking = new Booking(customer, flight, bookingDate);
        if (numberOfRemainingSeats(flightId) > 0) {
            bookings.add(booking);
            customer.addBooking(booking); // Add booking to customer
            flight.addPassenger(customer); // Add passenger to flight
            flight.reduceNumberOfSeats(); // Reduce the number of remaining seats
        } else {
            throw new FlightBookingSystemException("Seats for this flight are fully booked");
        }
    }

    /**
     * Updates customer details in the system.
     *
     * @param id       The ID of the customer to update.
     * @param name     The new name of the customer (optional).
     * @param phoneNum The new phone number of the customer (optional).
     * @param email    The new email of the customer (optional).
     * @throws FlightBookingSystemException If no customer exists with the provided ID.
     */
    public void updateCustomer(int id, String name, String phoneNum, String email) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("Customer with that ID not found.");
        }

        Customer customer = customers.get(id);

        // Update name if provided, otherwise keep existing name
        if (name != null && !name.trim().isEmpty()) {
            customer.setName(name);
        }

        // Update phoneNum if provided, otherwise keep existing phoneNum
        if (phoneNum != null && !phoneNum.trim().isEmpty()) {
            customer.setPhone(phoneNum);
        }

        // Update email if provided, otherwise keep existing email
        if (email != null && !email.trim().isEmpty()) {
            customer.setEmail(email);
        }

        customers.put(id, customer); // Update the customer in the map
    }

    /**
     * Updates flight details in the system.
     *
     * @param id         The ID of the flight to update.
     * @param numOfSeats The new number of seats of the flight.
     * @param price      The new price of the flight.
     * @throws FlightBookingSystemException If no flight exists with the provided ID,
     *                                      or if the number of seats or price is invalid.
     */
    public void updateFlight(int id, int numOfSeats, int price) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("Flight with that ID not found.");
        }

        Flight flight = flights.get(id);

        // Update number of seats if provided
        if (numOfSeats > 0) {
            flight.setNumberOfSeats(numOfSeats);
        } else {
            throw new IllegalArgumentException("Number of seats must be a positive integer.");
        }

        // Update price if provided
        if (price >= 0) {
            flight.setPrice(price);
        } else {
            throw new IllegalArgumentException("Price must be a non-negative integer.");
        }

        flights.put(id, flight); // Update the flight in the map
    }

    /**
     * Cancels a booking based on customer ID and flight ID.
     *
     * @param cusId   The ID of the customer whose booking is to be canceled.
     * @param flightId The ID of the flight for which the booking is to be canceled.
     * @throws FlightBookingSystemException If no booking is found for the provided customer and flight IDs.
     */
    public void cancelBooking(int cusId, int flightId) throws FlightBookingSystemException {
        Booking bookingRemove = null;

        for (Booking booking : bookings) {
            if (booking.getCustomer().getId() == cusId && booking.getFlight().getId() == flightId) {
                bookingRemove = booking;
                break;
            }
        }

        if (bookingRemove != null) {
            bookings.remove(bookingRemove); // Remove booking from main bookings list
            bookingRemove.getCustomer().removeBooking(bookingRemove); // Remove booking reference from customer
            bookingRemove.getFlight().removePassenger(bookingRemove.getCustomer()); // Remove customer from flight
            bookingRemove.getFlight().increaseNumberOfSeats(); // Increase the number of remaining seats
        } else {
            throw new FlightBookingSystemException("No booking found for customer ID " + cusId + " and flight ID " + flightId);
        }
    }

    /**
     * Retrieves a list of bookings associated with a specific customer ID.
     *
     * @param cusId The ID of the customer whose bookings are to be retrieved.
     * @return An unmodifiable list of bookings associated with the specified customer.
     * @throws FlightBookingSystemException If no customer exists with the provided ID.
     */
    public List<Booking> bookingListByCustomer(int cusId) throws FlightBookingSystemException {
        if (!customers.containsKey(cusId)) {
            throw new FlightBookingSystemException("Customer with that id does not exist");
        }
        List<Booking> cusBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomer().getId() == cusId) {
                cusBookings.add(booking);
            }
        }
        return Collections.unmodifiableList(cusBookings);
    }

    /**
     * Updates an existing booking with new flight ID and booking date.
     *
     * @param cusId         The ID of the customer whose booking is to be updated.
     * @param flightId      The ID of the flight to update the booking with.
     * @param new_flightId  The new ID of the flight for the booking.
     * @param newbookingDate The new booking date for the booking.
     * @throws FlightBookingSystemException If no booking is found for the provided customer and flight IDs,
     *                                      or if the new flight ID does not exist in the system.
     */
    public void updateBooking(int cusId, int flightId, int new_flightId, LocalDate newbookingDate) throws FlightBookingSystemException {

        Booking bookingUpdate = null;

        for (Booking booking : bookings) {
            if (booking.getCustomer().getId() == cusId && booking.getFlight().getId() == flightId) {
                bookingUpdate = booking;
                break;
            }
        }

        if (bookingUpdate != null) {
            if (!flights.containsKey(new_flightId)) {
                throw new FlightBookingSystemException("Flight with that id does not exist !!!");
            }
            Flight new_flight = flights.get(new_flightId);

            bookingUpdate.setFlight(new_flight); // Update flight for the booking

            // Update booking date if provided
            if (newbookingDate != null) {
                bookingUpdate.setBookingDate(newbookingDate);
            }

            int index = bookings.indexOf(bookingUpdate);
            if (index != -1) {
                bookings.set(index, bookingUpdate);
            }
        } else {
            throw new FlightBookingSystemException("No booking found for cus ID " + cusId + " and flight ID " + flightId);
        }
    }

    /**
     * Retrieves a list of customers (passengers) booked on a specific flight.
     *
     * @param id The ID of the flight to retrieve passengers for.
     * @return An unmodifiable list of customers booked on the specified flight.
     */
    public List<Customer> listOfPassengersByFlight(int id) {
        List<Customer> customers = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getFlight().getId() == id) {
                Customer customer = booking.getCustomer();
                customers.add(customer);
            }
        }
        return customers;
    }

    /**
     * Calculates the number of remaining seats on a specific flight.
     *
     * @param id The ID of the flight to calculate remaining seats for.
     * @return The number of remaining seats on the specified flight.
     * @throws FlightBookingSystemException If no flight exists with the provided ID.
     */
    public int numberOfRemainingSeats(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new IllegalArgumentException("Flight with that customer id not available");
        }
        Flight flight = flights.get(id);
        int totalSeats = flight.getNumberOfSeats();
        List<Customer> passengers = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getFlight().getId() == id) {
                Customer customer = booking.getCustomer();
                passengers.add(customer);
            }
        }
        return totalSeats - passengers.size();
    }

    /**
     * Retrieves detailed information about a customer including their bookings.
     *
     * @param id The ID of the customer to retrieve details for.
     * @return A formatted string containing detailed information about the customer and their bookings.
     * @throws FlightBookingSystemException If no customer exists with the provided ID.
     */
    public String customerDetailsLong(int id) throws FlightBookingSystemException {
        StringBuilder details = new StringBuilder();
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("No Customer with that id");
        }
        Customer customer = customers.get(id);
        details.append("Customer #" + id + " - " + customer.getName() + " - " + customer.getPhone() + " - " + customer.getEmail() + "\n");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        List<Booking> custBook = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomer().getId() == id) {
                custBook.add(booking);
            }
        }
        for (Booking booking : custBook) {

            Flight flight = booking.getFlight();
            details.append(" Flight #" + flight.getFlightNumber() + " Origin " + flight.getOrigin() +
                    " Destination " + flight.getDestination() + " Date " + flight.getDepartureDate().format(dtf) + " Price " + flight.getPrice() + "\n");
        }

        return details.toString();
    }

    /**
     * Retrieves detailed information about a flight including its passengers.
     *
     * @param id The ID of the flight to retrieve details for.
     * @return A formatted string containing detailed information about the flight and its passengers.
     * @throws FlightBookingSystemException If no flight exists with the provided ID.
     */
    public String flightDetailsLong(int id) throws FlightBookingSystemException {
        StringBuilder details = new StringBuilder();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("No Flight with that id");
        }
        Flight flight = flights.get(id);
        details.append("Flight #" + id + " - " + flight.getFlightNumber() + " - " + flight.getOrigin() + " to "
                + flight.getDestination() + " on " + flight.getDepartureDate().format(dtf) + "\n");
        List<Booking> book = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getFlight().getId() == id) {
                book.add(booking);
            }
        }
        List<Customer> passengers = new ArrayList<>();
        for (Booking booking : book) {
            Customer customer = booking.getCustomer();
            passengers.add(customer);
        }
        for (Customer customer : passengers) {
            details.append(" Passenger Name :" + customer.getName() + " Phone Number :" + customer.getPhone() +
                    " Email :" + customer.getEmail() + "\n");
        }

        return details.toString();

    }
}

