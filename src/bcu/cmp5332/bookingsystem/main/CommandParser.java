package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.commands.ShowCustomer;
import bcu.cmp5332.bookingsystem.commands.ShowFlights;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.commands.ListFlights;
import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.CustomerBookingList;
import bcu.cmp5332.bookingsystem.commands.DeleteCustomer;
import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.commands.EditCustomer;
import bcu.cmp5332.bookingsystem.commands.EditFlight;
import bcu.cmp5332.bookingsystem.commands.GetRemainingSeats;
import bcu.cmp5332.bookingsystem.commands.Help;
import bcu.cmp5332.bookingsystem.commands.ListBookings;
import bcu.cmp5332.bookingsystem.commands.ListCustomers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Parses user commands from the input and returns corresponding Command objects.
 * Commands supported include adding flights, customers, bookings, editing and deleting
 * flights and customers, listing flights, customers, bookings, showing details of flights
 * and customers, and handling GUI loading, help, and remaining seats queries.
 * Provides methods to parse user input and create corresponding Command objects.
 * 
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class CommandParser {
	   /**
     * Parses the given command line input and returns the corresponding Command object.
     * Commands supported include addflight, addcustomer, loadgui, listflights, listcustomers,
     * listbookings, help, showflight, showcustomer, deleteflight, deletecustomer, editcustomer,
     * editflight, customerbookinglist, remainingseats, addbooking, editbooking, and cancelbooking.
     *
     * @param line The command line input provided by the user.
     * @return The Command object corresponding to the parsed input.
     * @throws IOException If an I/O error occurs while reading user input.
     * @throws FlightBookingSystemException If there is an error in parsing or executing the command.
     */
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            
            if (cmd.equals("addflight")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Flight Number: ");
                String flighNumber = reader.readLine();
                System.out.print("Origin: ");
                String origin = reader.readLine();
                System.out.print("Destination: ");
                String destination = reader.readLine();
                System.out.print("Departure: ");
                LocalDate departureDate = parseDateWithAttempts(reader);
                System.out.print("Number of Seats: ");
                String numberOfSeats = reader.readLine();
                int seats = Integer.parseInt(numberOfSeats);
                System.out.print("Price: ");
                String flightPrice = reader.readLine();
                int price = Integer.parseInt(flightPrice);
                return new AddFlight(flighNumber, origin, destination, departureDate, seats, price);
            } else if (cmd.equals("addcustomer")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Name: ");
                String cusName = reader.readLine();
                System.out.print("Phone Number: ");
                String number = reader.readLine();
                System.out.print("Email: ");
                String email = reader.readLine();
                
                return new AddCustomer(cusName, number,email, null);
          } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (parts.length == 1) {
                if (line.equals("listflights")) {
                    return new ListFlights();
                } else if (line.equals("listcustomers")) {
                    return new ListCustomers();
                }else if (line.equals("listbookings")) {
                    return new ListBookings();
                }
                
                else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);
                if (cmd.equals("showflight")) {
                	return new ShowFlights(id);
                } else if (cmd.equals("showcustomer")) { 
                	return new ShowCustomer(id);
                } else if (cmd.equals("deleteflight")) {
                	return new DeleteFlight(id);
                } else if (cmd.equals("deletecustomer")) {
                	return new DeleteCustomer(id);
                } else if(cmd.equals("editcustomer")) {
                	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Name: ");
                    String cusName = reader.readLine();
                    System.out.print("Phone Number: ");
                    String number = reader.readLine();
                    System.out.print("Email: ");
                    String email = reader.readLine();
                    return new EditCustomer(id, cusName, number, email);
                } else if(cmd.equals("editflight")) {
                	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Number of Seats: ");
                    String numOfSeats = reader.readLine();
                    int seats = Integer.parseInt(numOfSeats);
                    		
                    System.out.print("Price : ");
                    String flightPrice = reader.readLine();
                    int price = Integer.parseInt(flightPrice);
                    
                    return new EditFlight(id, seats, price);
                }  else if (cmd.equals("customerbookinglist")) {
                	return new CustomerBookingList(id);
                } else if (cmd.equals("remainingseats")) {
                	return new GetRemainingSeats(id);
                } 
                
                
            } else if (parts.length == 3) {
                

                if (cmd.equals("addbooking")) {
                	int cusId = Integer.parseInt(parts[1]);
                	int flightId = Integer.parseInt(parts[2]);
                	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                	System.out.print("Booking Date: ");
                	LocalDate bookingDate = parseDateWithAttempts(reader);
                    
                	return new AddBooking(cusId, flightId, bookingDate);
                } else if (cmd.equals("editbooking")) {
                	int cusId = Integer.parseInt(parts[1]);
                	int flightId = Integer.parseInt(parts[2]);
                	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                	System.out.print("New flight id: ");
                    String flightid = reader.readLine();
                    int new_flightid = Integer.parseInt(flightid);
                	LocalDate bookingDate = parseDateWithAttempts(reader);
                	
                	return new EditBooking(cusId, flightId, new_flightid, bookingDate);
                	
                } else if (cmd.equals("cancelbooking")) {
                	int cusId = Integer.parseInt(parts[1]);
                	int flightId = Integer.parseInt(parts[2]);
                    return new CancelBooking(cusId, flightId);
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }
    /**
     * Parses the date input from the user with the specified number of attempts.
     * Prompts the user to enter a date in "YYYY-MM-DD" format and retries up to the given attempts.
     *
     * @param br The BufferedReader object used for reading user input.
     * @param attempts The number of attempts allowed for entering the date correctly.
     * @return The parsed LocalDate object representing the entered date.
     * @throws IOException If an I/O error occurs while reading user input.
     * @throws FlightBookingSystemException If the date format is incorrect after all attempts.
     */
    
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    /**
     * Parses the date input from the user with a default of 3 attempts.
     * Prompts the user to enter a date in "YYYY-MM-DD" format and retries up to 3 attempts.
     *
     * @param br The BufferedReader object used for reading user input.
     * @return The parsed LocalDate object representing the entered date.
     * @throws IOException If an I/O error occurs while reading user input.
     * @throws FlightBookingSystemException If the date format is incorrect after all attempts.
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
