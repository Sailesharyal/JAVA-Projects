package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The Help class implements the {@link Command} interface to display a help message
 * listing all available commands in the flight booking system.
 * It prints out the {@link Command#HELP_MESSAGE} to the console when executed.
 * 
 * <p>This class is used to provide users with information on how to use the various
 * commands available in the flight booking system application.
 * 
 * @author Your Name
 */
public class Help implements Command {
	/**
	 * Default constructor for the Help command.
	 */
	public Help() {
	    // Constructor implementation
	}

    /**
     * Executes the Help command by printing the {@link Command#HELP_MESSAGE} to the console.
     *
     * @param flightBookingSystem The {@link FlightBookingSystem} instance (not used in this implementation).
     */
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
        System.out.println(Command.HELP_MESSAGE);
    }
}
