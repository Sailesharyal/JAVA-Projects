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
 * The MainWindow class represents the main window of the Flight Booking System GUI.
 * It provides a menu-based interface for administrators to manage flights, customers,
 * and user registrations.
 */
public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu customersMenu;

    private JMenuItem adminLogin;
    private JMenuItem userLogin;
    private JMenuItem adminExit;
    private JMenuItem registerUser;

    private FlightBookingSystem fbs;

    private LoginDialog loginDialog;
    private AdminWindow adminWindow;
    private UserLoginDialog userLoginDialog;
    private UserWindow userWindow;

    /**
     * Constructs a MainWindow with a given FlightBookingSystem instance.
     *
     * @param fbs The FlightBookingSystem instance associated with this window.
     */
    public MainWindow(FlightBookingSystem fbs) {
        this.fbs = fbs;
        initialize();
    }

    /**
     * Retrieves the FlightBookingSystem instance associated with this window.
     *
     * @return The FlightBookingSystem instance.
     */
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    /**
     * Initializes the contents of the main window.
     * Sets up menus, menu items, and other UI components.
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("Flight Booking System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Adding Admin menu and menu items
        adminMenu = new JMenu("Admin");
        ImageIcon adminIcon = new ImageIcon("admin_icon.png"); // Replace with your icon path
        adminMenu.setIcon(adminIcon); // Set the icon for Admin menu
        menuBar.add(adminMenu);

        adminLogin = new JMenuItem("Admin Login");
        adminMenu.add(adminLogin);
        adminLogin.addActionListener(this);

        userLogin = new JMenuItem("User Login");
        adminMenu.add(userLogin);
        userLogin.addActionListener(this);

        registerUser = new JMenuItem("Register User");
        adminMenu.add(registerUser);
        registerUser.addActionListener(this);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // Adding Flights menu and menu items
        flightsMenu = new JMenu("Flights");
        menuBar.add(flightsMenu);

        JMenuItem flightsView = new JMenuItem("View");
        flightsMenu.add(flightsView);
        flightsView.addActionListener(this);

        // Adding Customers menu and menu items
        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        JMenuItem custView = new JMenuItem("View");
        customersMenu.add(custView);
        custView.addActionListener(this);

        setSize(800, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Handles action events from various menu items.
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
        } else if (ae.getSource() == adminLogin) {
            showLoginDialog();
        } else if (ae.getSource() == userLogin) {
            showUserLoginDialog();
        } else if (ae.getSource() == registerUser) {
            new AddCustomerWindow(this);
        } else if (ae.getSource() == flightsMenu.getItem(0)) { // View Flights
            displayFlights();
        } else if (ae.getSource() == customersMenu.getItem(0)) { // View Customers
            displayCustomers();
        }
    }

    /**
     * Displays the login dialog for admin authentication.
     * If authenticated, opens the admin window.
     */
    private void showLoginDialog() {
        loginDialog = new LoginDialog(this);
        loginDialog.setVisible(true); // Blocks until dialog is closed

        if (loginDialog.isAuthenticated()) {
            openAdminWindow();
        } else {
            JOptionPane.showMessageDialog(this, "Access denied", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens the admin window if authentication is successful.
     * If the window is not yet created, initializes and displays it.
     */
    private void openAdminWindow() {
        if (adminWindow == null) {
            adminWindow = new AdminWindow(fbs);
            adminWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose admin window on close
            adminWindow.setLocationRelativeTo(this); // Center admin window relative to main window
        }
        adminWindow.setVisible(true);
    }

    /**
     * Displays the user login dialog for user authentication.
     * If authenticated, opens the user window.
     */
    private void showUserLoginDialog() {
        if (userLoginDialog == null) {
            userLoginDialog = new UserLoginDialog(this);
        }
        userLoginDialog.setVisible(true); // Blocks until dialog is closed

        if (userLoginDialog.isAuthenticated()) {
            openUserWindow(userLoginDialog.getEmail(), userLoginDialog.getPhoneNumber());
        } else {
            JOptionPane.showMessageDialog(this, "Access denied", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens the user window if authentication is successful.
     * If the window is not yet created, initializes and displays it.
     *
     * @param email      The email used for authentication.
     * @param phoneNumber The phone number used for authentication.
     */
    private void openUserWindow(String email, String phoneNumber) {
        if (userWindow == null) {
            // Perform authentication against customer list
            List<Customer> customers = fbs.getCustomers();
            boolean authenticated = false;
            for (Customer customer : customers) {
                if (customer.getEmail().equals(email) && customer.getPhone().equals(phoneNumber)) {
                    authenticated = true;
                    break;
                }
            }

            if (authenticated) {
                userWindow = new UserWindow(fbs);
                userWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                userWindow.setLocationRelativeTo(this);
                userWindow.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Access denied", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            userWindow.setVisible(true);
        }
    }

    /**
     * Displays a table with the list of flights from the FlightBookingSystem.
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
     * Displays a table with the list of customers from the FlightBookingSystem.
     */
    public void displayCustomers() {
        List<Customer> customersList = fbs.getCustomers();
        // Headers for the table
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
     * Displays a table within a new JFrame with scrollable functionality.
     *
     * @param table The JTable to be displayed.
     */
    private void displayTableInFrame(JTable table) {
        JFrame frame = new JFrame("Table");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);
        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
}
