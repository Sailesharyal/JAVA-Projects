  package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class GetRemainingSeats implements Command {


    private final int flightId;


    public GetRemainingSeats(int flightId) {
        this.flightId = flightId;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	int seats = flightBookingSystem.numberOfRemainingSeats(flightId);
    	System.out.println("Number of seats remaining: "+ seats);
    }
}
