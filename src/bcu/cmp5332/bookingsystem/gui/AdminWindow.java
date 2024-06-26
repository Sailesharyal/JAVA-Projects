package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * The AdminWindow class represents the main graphical user interface for administering a flight booking system.
 * It extends JFrame and implements ActionListener.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class AdminWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;

    private JMenuItem adminExit;

    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;
    private JMenuItem flightsEdit;
    private JMenuItem passengerList;

    private JMenuItem bookingsAdd;
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;
    private JMenuItem bookingsCustomer;

    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;
    private JMenuItem custEdit;

    private FlightBookingSystem fbs;

    /**
     * Constructs an AdminWindow with a given FlightBookingSystem instance.
     *
     * @param fbs The FlightBookingSystem instance to manage.
     */
    public AdminWindow(FlightBookingSystem fbs) {
        this.fbs = fbs;
        initialize();
    }

    /**
     * Retrieves the FlightBookingSystem instance associated with this AdminWindow.
     *
     * @return The FlightBookingSystem instance.
     */
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    /**
     * Initializes the contents of the AdminWindow.
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("Admin Flight Booking Management System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Adding Admin menu and menu items
        adminMenu = new JMenu("Admin");
        ImageIcon adminIcon = new ImageIcon("admin_icon.png"); // Replace with your icon path
        adminMenu.setIcon(adminIcon); // Set the icon for Admin menu
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // Adding Flights menu and menu items
        flightsMenu = new JMenu("Flights");
        menuBar.add(flightsMenu);

        flightsView = new JMenuItem("View");
        flightsAdd = new JMenuItem("Add");
        flightsDel = new JMenuItem("Delete");
        flightsEdit = new JMenuItem("Edit");
        passengerList = new JMenuItem("Flight Passengers");

        flightsMenu.add(flightsView);
        flightsMenu.add(flightsAdd);
        flightsMenu.add(flightsDel);
        flightsMenu.add(flightsEdit);
        flightsMenu.add(passengerList);

        // Adding action listener for Flights menu items
        flightsView.addActionListener(this);
        flightsAdd.addActionListener(this);
        flightsDel.addActionListener(this);
        flightsEdit.addActionListener(this);
        passengerList.addActionListener(this);

        // Adding Bookings menu and menu items
        bookingsMenu = new JMenu("Bookings");
        menuBar.add(bookingsMenu);

        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        bookingsAdd = new JMenuItem("Add");
        bookingsCustomer = new JMenuItem("Customer Booking list");
        bookingsMenu.add(bookingsAdd);
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        bookingsMenu.add(bookingsCustomer);

        // Adding action listener for Bookings menu items
        bookingsIssue.addActionListener(this);
        bookingsUpdate.addActionListener(this);
        bookingsCancel.addActionListener(this);
        bookingsAdd.addActionListener(this);
        bookingsCustomer.addActionListener(this);

        // Adding Customers menu and menu items
        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        custView = new JMenuItem("View");
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");
        custEdit = new JMenuItem("Edit");

        customersMenu.add(custView);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);
        customersMenu.add(custEdit);

        // Adding action listener for Customers menu items
        custView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);
        custEdit.addActionListener(this);

        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Handles action events from menu items.
     *
     * @param ae The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == adminExit) {
            try {
                FlightBookingSystemData.store(fbs);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        } else if (ae.getSource() == flightsView) {
            displayFlights();
        } else if (ae.getSource() == flightsAdd) {
            new AddFlightWindow(this);
        } else if (ae.getSource() == flightsDel) {
            new DeleteFlightWindow(this);
        } else if (ae.getSource() == flightsEdit) {
            new EditFlightWindow(this);
        } else if (ae.getSource() == passengerList) {
            new PassengerListWindow(this, fbs);
        } else if (ae.getSource() == bookingsAdd) {
            new AddBookingWindow(this);
        } else if (ae.getSource() == bookingsIssue) {
            // Handle bookings issue action
        } else if (ae.getSource() == bookingsUpdate) {
            new EditBookingWindow(this);
        } else if (ae.getSource() == bookingsCancel) {
            new DeleteBookingWindow(this);
        } else if (ae.getSource() == bookingsCustomer) {
            new CustomerBookListWindow(this, fbs);
        } else if (ae.getSource() == custView) {
            try {
                displayCustomers();
            } catch (FlightBookingSystemException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == custAdd) {
            new AddCustomerWindow(this);
        } else if (ae.getSource() == custDel) {
            new DeleteCustomerWindow(this);
        } else if (ae.getSource() == custEdit) {
            new EditCustomerWindow(this);
        }
    }

    /**
     * Displays the list of flights in a table format.
     */
    public void displayFlights() {
        List<Flight> flightsList = fbs.getFlights();
        // Headers for the table
        String[] columns = new String[]{"Flight No", "Origin", "Destination", "Departure Date", "Seats", "Price"};

        Object[][] data = new Object[flightsList.size()][6];
        for (int i = 0; i < flightsList.size(); i++) {
            Flight flight = flightsList.get(i);
            data[i][0] = flight.getFlightNumber();
            data[i][1] = flight.getOrigin();
            data[i][2] = flight.getDestination();
            data[i][3] = flight.getDepartureDate();
            data[i][4] = flight.getNumberOfSeats();
            data[i][5] = flight.getPrice();
        }

        JTable table = new JTable(data, columns);
        displayTableInFrame(table);
    }

    /**
     * Displays the list of customers in a table format.
     *
     * @throws FlightBookingSystemException If an error occurs while retrieving customer data.
     */
    public void displayCustomers() throws FlightBookingSystemException {
        List<Customer> customersList = fbs.getCustomers();
        // Headers for the table
        String[] columns = new String[]{"Name", "Phone Number", "Email", "No of Bookings"};

        Object[][] data = new Object[customersList.size()][4];
        for (int i = 0; i < customersList.size(); i++) {
            Customer customer = customersList.get(i);
            int custId = customer.getId();
            List<Booking> custBooking = fbs.bookingListByCustomer(custId);
            int numOfBook = custBooking.size();
            data[i][0] = customer.getName();
            data[i][1] = customer.getPhone();
            data[i][2] = customer.getEmail();
            data[i][3] = numOfBook;
        }

        JTable table = new JTable(data, columns);
        displayTableInFrame(table);
    }

    /**
     * Displays a given JTable within a new JFrame.
     *
     * @param table The JTable to display.
     */
    private void displayTableInFrame(JTable table) {
        JFrame frame = new JFrame("Table");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);
        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
}
