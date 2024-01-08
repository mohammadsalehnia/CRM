package view;

import model.*;
import service.CustomerService;
import util.ScannerWrapper;
import view.component.AbstractCustomerUI;
import view.component.IndividualCustomerUI;
import view.component.LegalCustomerUI;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements AutoCloseable {
    private final Scanner scanner;
    private final CustomerService customerService;

    public ConsoleUI() {
        this.scanner = ScannerWrapper.getInstance();
        this.customerService = CustomerService.getInstance();
    }

    public void startMenu() {

        System.out.println("CRM Application Started");
        String selectedOption = "";

        while (!selectedOption.equals("0")) {

            printMenu();

            selectedOption = getUserInput("Select a menu item: ");

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

        scanner.close();
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
        scanner.close();
    }

    private void addCustomer() {

        System.out.println("Customer Types:");
        System.out.println("1. Individual");
        System.out.println("2. Legal");
        String selectedOption = getUserInput("Select a menu item: ");

        AbstractCustomerUI customerUI;

        if (selectedOption.equals("1")) {
            customerUI = new IndividualCustomerUI();
        } else {
            customerUI = new LegalCustomerUI();
        }

        Customer customer = customerUI.generateCustomer();

        String addContactQuestion = getUserInput("Do you want add contact? (enter y for yes and other char for no): ");

        while (addContactQuestion.equalsIgnoreCase("y")) {
            addContactTo(customer);
            addContactQuestion = getUserInput("Do you want add contact? (enter y for yes and other char for no): ");
        }
        customerService.addCustomer(customer);
    }


    private void addContactTo(Customer customer) {

        System.out.println("Contact Types:");
        System.out.println("1. Phone Number");
        System.out.println("2. Mobile");
        System.out.println("3. Fax");
        System.out.println("4. Email");
        String selectedOption = getUserInput("Select a menu item: ");


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


    private String getUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private void addContactToCustomer(String message, ContactType phoneNumberType, Customer customer) {
        String value = getUserInput(message);
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
        System.out.print("Select a search option: ");

        String searchOption = scanner.nextLine();

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
        System.out.print("Enter ID to search: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();
        List<Customer> customers = customerService.searchById(searchId);
        customers.forEach(System.out::println);
    }

    private void searchByName() {
        System.out.print("Enter Name to search: ");
        String searchName = scanner.nextLine().trim();
        List<Customer> customers = customerService.searchByName(searchName);
        customers.forEach(System.out::println);
    }

    private void searchByLastName() {
        System.out.print("Enter Last Name to search: ");
        String searchLastName = scanner.nextLine().trim();
        List<Customer> customers = customerService.searchByLastName(searchLastName);
        customers.forEach(System.out::println);
    }

    private void searchByAddress() {
        System.out.print("Enter Address to search: ");
        String searchAddress = scanner.nextLine().trim();
        List<Customer> customers = customerService.searchByAddress(searchAddress);
        customers.forEach(System.out::println);
    }

    private void searchIdenticalCode() {
        System.out.print("Enter Identical Code to search: ");
        String searchIdenticalCode = scanner.nextLine().trim();
        List<Customer> customers = customerService.searchIdenticalCode(searchIdenticalCode);
        customers.forEach(System.out::println);
    }

    private void searchByEmail() {
        System.out.print("Enter Email to search: ");
        String searchEmail = scanner.nextLine().trim();
        List<Customer> customers = customerService.searchByEmail(searchEmail);
        customers.forEach(System.out::println);
    }

    private void searchByNumber() {
        System.out.print("Enter Phone Number to search: ");
        String searchNumber = scanner.nextLine().trim();
        List<Customer> customers = customerService.searchByNumber(searchNumber);
        customers.forEach(System.out::println);
    }

    private void searchByBrand() {
        System.out.print("Enter Brand to search: ");
        String searchBrand = scanner.nextLine().trim();
        List<Customer> customers = customerService.searchByBrand(searchBrand);
        customers.forEach(System.out::println);
    }

    private void searchByWebsite() {
        System.out.print("Enter Website to search: ");
        String searchWebsite = scanner.nextLine().trim();
        List<Customer> customers = customerService.searchByWebsite(searchWebsite);
        customers.forEach(System.out::println);
    }

    private void editCustomer() {
        System.out.print("Enter the ID of the customer you want to edit: ");
        int customerIdToEdit = scanner.nextInt();
        scanner.nextLine();

        Customer customerToEdit = customerService.getCustomerById(customerIdToEdit);

        AbstractCustomerUI customerUI;

        if (customerToEdit instanceof IndividualCustomer) {
            customerUI = new IndividualCustomerUI();
        } else {
            customerUI = new LegalCustomerUI();
        }

        customerUI.editCustomer(customerToEdit,customerService);

    }

    private void deleteCustomer() {
        System.out.print("Enter the ID of the customer you want to delete: ");
        int customerIdToDelete = scanner.nextInt();
        scanner.nextLine();
//        customerService.deleteCustomer(customerIdToDelete);
        customerService.safeDeleteCustomer(customerIdToDelete);
    }

}
