package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * The UserWindow class represents the main window for user operations in the Flight Booking System GUI.
 * It includes menus for managing flights, customers, and bookings, as well as functionalities to display
 * flight and customer information in tables.
 * 
 * @author Sandip Magar, Sailesh Aryal
 */
public class UserWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu customerMenu;
    private JMenu bookingsMenu;

    private JMenuItem adminExit;
    private JMenuItem flightsView;
    private JMenuItem customerView;
    private JMenuItem custAdd;
    private JMenuItem custDel;
    private JMenuItem custEdit;
    private JMenuItem bookingsAdd;
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;
    private JMenuItem bookingsCustomer;

    private FlightBookingSystem fbs;

    /**
     * Constructs a UserWindow with the specified FlightBookingSystem instance.
     *
     * @param fbs The FlightBookingSystem instance used to access data and perform operations.
     */
    public UserWindow(FlightBookingSystem fbs) {
        this.fbs = fbs;
        initialize();
    }

    /**
     * Retrieves the FlightBookingSystem instance associated with this UserWindow.
     *
     * @return The FlightBookingSystem instance used by this UserWindow.
     */
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    /**
     * Initializes the contents of the UserWindow, setting up menus and event listeners.
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("User Flight Booking Management System");

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
        flightsMenu.add(flightsView);
        flightsView.addActionListener(this);

        // Adding Customers menu and menu items
        customerMenu = new JMenu("Customers");
        menuBar.add(customerMenu);

        customerView = new JMenuItem("View");
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");
        custEdit = new JMenuItem("Edit");

        customerMenu.add(customerView);
        customerMenu.add(custAdd);
        customerMenu.add(custDel);
        customerMenu.add(custEdit);

        customerView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);
        custEdit.addActionListener(this);

        // Adding Bookings menu and menu items
        bookingsMenu = new JMenu("Bookings");
        menuBar.add(bookingsMenu);

        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        bookingsAdd = new JMenuItem("Add");
        bookingsCustomer = new JMenuItem("Customer Booking List");

        bookingsMenu.add(bookingsAdd);
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        bookingsMenu.add(bookingsCustomer);

        bookingsIssue.addActionListener(this);
        bookingsUpdate.addActionListener(this);
        bookingsCancel.addActionListener(this);
        bookingsAdd.addActionListener(this);
        bookingsCustomer.addActionListener(this);

        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Handles actions performed on the menu items in the UserWindow.
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
        } else if (ae.getSource() == customerView) {
            displayCustomers();
        } else if (ae.getSource() == custAdd) {
            new AddCustomerWindow(this);
        } else if (ae.getSource() == custDel) {
            new DeleteCustomerWindow(this);
        } else if (ae.getSource() == custEdit) {
            new EditCustomerWindow(this);
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
        }
    }

    /**
     * Displays a table of flights in a new window.
     */
    public void displayFlights() {
        List<Flight> flightsList = fbs.getFlights();
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
     * Displays a table of customers in a new window.
     */
    public void displayCustomers() {
        List<Customer> customersList = fbs.getCustomers();
        String[] columns = new String[]{"Name", "Phone Number", "Email"};
        Object[][] data = new Object[customersList.size()][3];

        for (int i = 0; i < customersList.size(); i++) {
            Customer customer = customersList.get(i);
            data[i][0] = customer.getName();
            data[i][1] = customer.getPhone();
            data[i][2] = customer.getEmail();
        }

        JTable table = new JTable(data, columns);
        displayTableInFrame(table);
    }

    /**
     * Displays the provided JTable in a new JFrame.
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
