package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The Command interface defines a contract for executing commands within a flight booking system.
 * Each command represents a specific action that can be performed, such as adding a flight, listing
 * bookings, or canceling a booking. Implementing classes must provide an implementation of the
 * {@link #execute(FlightBookingSystem)} method.
 *
 * <p>The interface also defines a {@link #HELP_MESSAGE} constant that provides a list of available
 * commands and their descriptions.
 *
 * @author Sandip Magar, Sailesh Aryal
 */
public interface Command {

    /**
     * Help message containing descriptions of all available commands.
     */
    public static final String HELP_MESSAGE = "Commands:\n"
            + "\tlistflights                               print all flights\n"
            + "\tlistbookings                              print all bookings\n"
            + "\tlistcustomers                             print all customers\n"
            + "\taddflight                                 add a new flight\n"
            + "\taddcustomer                               add a new customer\n"
            + "\tshowflight [flight id]                    show flight details\n"
            + "\tshowcustomer [customer id]                show customer details\n"
            + "\tcustomerbookinglist [customer id]         show customer's booking list\n"
            + "\tdeleteflight [flight id]                  delete flight details\n"
            + "\tdeletecustomer [customer id]              delete customer details\n"
            + "\tremainingseats [flight id]                show flight's remaining seats details\n"
            + "\teditcustomer [customer id]                edit customer details\n"
            + "\taddbooking [customer id] [flight id]      add a new booking\n"
            + "\tcancelbooking [customer id] [flight id]   cancel a booking\n"
            + "\teditbooking [customer id] [flight id]     update a booking\n"
            + "\tloadgui                                   loads the GUI version of the app\n"
            + "\thelp                                      prints this help message\n"
            + "\texit                                      exits the program";

    /**
     * Executes the command using the provided {@link FlightBookingSystem} instance.
     *
     * @param flightBookingSystem The {@link FlightBookingSystem} instance to execute the command on.
     * @throws FlightBookingSystemException If an error occurs while executing the command.
     */
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException;

}
