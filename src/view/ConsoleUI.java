package view;

import model.*;
import service.CustomerService;
import util.ScannerWrapper;
import view.component.AbstractCustomerUI;

import java.util.List;

public class ConsoleUI implements AutoCloseable {
    private final ScannerWrapper scannerWrapper;
    private final CustomerService customerService;

    public ConsoleUI() {
        this.scannerWrapper = ScannerWrapper.getInstance();
        this.customerService = CustomerService.getInstance();
    }

    public void startMenu() {

        System.out.println("CRM Application Started");
        String selectedOption = "";

        while (!selectedOption.equals("0")) {

            printMenu();

            selectedOption = scannerWrapper.getUserInput("Select a menu item: ");

            switch (selectedOption) {
                case "1": {
                    addCustomer();
                    break;
                }
                case "2": {
                    printAllCustomers();
                    break;
                }
                case "3": {
                    searchCustomer();
                    break;
                }
                case "4": {
                    editCustomer();
                    break;
                }
                case "5": {
                    deleteCustomer();
                    break;
                }
                case "6": {
                    printAllDeletedCustomers();
                    break;
                }
                case "0": {
                    System.out.println("You closed the application");
                    break;
                }
                default: {
                    System.out.println("Invalid Item!");
                    break;
                }
            }
        }

        System.out.println("Bye");
    }

    private void printMenu() {
        System.out.println("---------- Menu ---------");
        System.out.println("1. Add new customer");
        System.out.println("2. Display all customers");
        System.out.println("3. Search customer");
        System.out.println("4. Edit customer");
        System.out.println("5. Delete customer");
        System.out.println("6. Print all deleted customers");
        System.out.println("0. Exit");
        System.out.println("--------------------------");
    }


    @Override
    public void close() {
        scannerWrapper.close();
    }

    private void addCustomer() {

        System.out.println("Customer Types:");
        System.out.println("1. Individual");
        System.out.println("2. Legal");
        String selectedOption = scannerWrapper.getUserInput("Select a menu item: ");

//        CustomerType type = CustomerType.fromValue(Integer.parseInt(selectedOption));
//        AbstractCustomerUI customerUI = AbstractCustomerUI.fromCustomerType(type);
//        Customer customer = customerUI.generateCustomer();

        Customer customer = AbstractCustomerUI
                .fromCustomerType(CustomerType.fromValue(Integer.parseInt(selectedOption)))
                .generateCustomer();

        String addContactQuestion = scannerWrapper.getUserInput("Do you want add contact? (enter y for yes and other char for no): ");

        while (addContactQuestion.equalsIgnoreCase("y")) {
            addContactTo(customer);
            addContactQuestion = scannerWrapper.getUserInput("Do you want add contact? (enter y for yes and other char for no): ");
        }
        customerService.addCustomer(customer);
    }


    private void addContactTo(Customer customer) {

        System.out.println("Contact Types:");
        System.out.println("1. Phone Number");
        System.out.println("2. Mobile");
        System.out.println("3. Fax");
        System.out.println("4. Email");
        String selectedOption = scannerWrapper.getUserInput("Select a menu item: ");


        switch (selectedOption) {
            case "1": {
                addContactToCustomer("Enter Phone Number: ", ContactType.PHONE_NUMBER, customer);
                break;
            }
            case "2": {
                addContactToCustomer("Enter Mobile: ", ContactType.MOBILE, customer);
                break;
            }
            case "3": {
                addContactToCustomer("Enter Fax: ", ContactType.FAX, customer);
                break;
            }
            case "4": {
                addContactToCustomer("Enter Email: ", ContactType.EMAIL, customer);
                break;
            }
            default: {
                System.out.println("Invalid Item!");
                break;
            }
        }
    }


  

    private void addContactToCustomer(String message, ContactType phoneNumberType, Customer customer) {
        String value = scannerWrapper.getUserInput(message);
        Contact contact = new Contact(value, phoneNumberType);
        customer.getContacts().add(contact);
        System.out.println("Contact added successfully");
    }

    private void printAllCustomers() {
        List<Customer> allCustomers = customerService.getActiveCustomers();
        if (allCustomers.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            System.out.println("----------------- Customers List -------------");
            for (Customer customer : allCustomers) {
                System.out.println(customer.toString());
                System.out.println("-------------------------------------------");
            }
            System.out.println("------------------ End List ------------------");
        }
    }

    private void printAllDeletedCustomers() {
        List<Customer> allCustomers = customerService.getDeletedCustomers();
        if (allCustomers.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            System.out.println("----------------- Deleted Customers List -------------");
            for (Customer customer : allCustomers) {
                System.out.println(customer.toString());
                System.out.println("-------------------------------------------");
            }
            System.out.println("------------------ End List ------------------");
        }
    }

    private void searchCustomer() {

        System.out.println("Search Options:");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Last Name");
        System.out.println("4. Search by Address");
        System.out.println("5. Search by Identical Code");
        System.out.println("6. Search by Phone Number");
        System.out.println("7. Search by Email");
        System.out.println("8. Search by Brand");
        System.out.println("9. Search by Website");

        String searchOption  = scannerWrapper.getUserInput("Select a search option: ");

        switch (searchOption) {
            case "1": {
                searchById();
                break;
            }
            case "2": {
                searchByName();
                break;
            }
            case "3": {
                searchByLastName();
                break;
            }
            case "4": {
                searchByAddress();
                break;
            }
            case "5": {
                searchIdenticalCode();
                break;
            }
            case "6": {
                searchByNumber();
                break;
            }
            case "7": {
                searchByEmail();
                break;
            }
            case "8": {
                searchByBrand();
                break;
            }
            case "9": {
                searchByWebsite();
                break;
            }
            default: {
                System.out.println("Invalid search option!");
                break;
            }
        }
    }

    private void searchById() {
        int searchId = scannerWrapper.getUserInputInt("Enter ID to search: ");
        List<Customer> customers = customerService.searchById(searchId);
        customers.forEach(System.out::println);
    }

    private void searchByName() {
        String searchName = scannerWrapper.getUserInput("Enter Name to search: ").trim();
        List<Customer> customers = customerService.searchByName(searchName);
        customers.forEach(System.out::println);
    }

    private void searchByLastName() {
        String searchLastName = scannerWrapper.getUserInput("Enter Last Name to search: ").trim();
        List<Customer> customers = customerService.searchByLastName(searchLastName);
        customers.forEach(System.out::println);
    }

    private void searchByAddress() {
        String searchAddress = scannerWrapper.getUserInput("Enter Address to search: ").trim();
        List<Customer> customers = customerService.searchByAddress(searchAddress);
        customers.forEach(System.out::println);
    }

    private void searchIdenticalCode() {
        String searchIdenticalCode = scannerWrapper.getUserInput("Enter Identical Code to search: ").trim();
        List<Customer> customers = customerService.searchIdenticalCode(searchIdenticalCode);
        customers.forEach(System.out::println);
    }

    private void searchByEmail() {
        String searchEmail = scannerWrapper.getUserInput("Enter Email to search: ").trim();
        List<Customer> customers = customerService.searchByEmail(searchEmail);
        customers.forEach(System.out::println);
    }

    private void searchByNumber() {
        String searchNumber = scannerWrapper.getUserInput("Enter Phone Number to search: ").trim();
        List<Customer> customers = customerService.searchByNumber(searchNumber);
        customers.forEach(System.out::println);
    }

    private void searchByBrand() {
        String searchBrand = scannerWrapper.getUserInput("Enter Brand to search: ").trim();
        List<Customer> customers = customerService.searchByBrand(searchBrand);
        customers.forEach(System.out::println);
    }

    private void searchByWebsite() {
        String searchWebsite = scannerWrapper.getUserInput("Enter Website to search: ").trim();
        List<Customer> customers = customerService.searchByWebsite(searchWebsite);
        customers.forEach(System.out::println);
    }

    private void editCustomer() {
        int customerIdToEdit = scannerWrapper.getUserInputInt("Enter the ID of the customer you want to edit: ");
        Customer customerToEdit = customerService.getCustomerById(customerIdToEdit);
        // factory method
//        AbstractCustomerUI customerUI = AbstractCustomerUI.createCustomerUI(customerToEdit.getType());

        AbstractCustomerUI.
                fromCustomerType(customerToEdit.getType())
                .editCustomer(customerToEdit, customerService);

    }

    private void deleteCustomer() {
        int customerIdToDelete = scannerWrapper.getUserInputInt("Enter the ID of the customer you want to delete: ");
//        customerService.deleteCustomer(customerIdToDelete);
        customerService.safeDeleteCustomer(customerIdToDelete);
    }

}
