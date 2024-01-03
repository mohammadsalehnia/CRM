package view;

import model.*;
import service.CustomerService;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements AutoCloseable {
    private final Scanner scanner;
    private final CustomerService customerService;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
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


    private String getUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
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

        switch (selectedOption) {
            case "1": {
                addIndividualCustomer();
                break;
            }
            case "2": {
                addLegalCustomer();
                break;
            }
            default: {
                System.out.println("Invalid Item!");
                break;
            }
        }
    }


    private void addLegalCustomer() {

        String name = getUserInput("Enter Name: ");
        String brand = getUserInput("Enter Brand: ");
        String website = getUserInput("Enter Website: ");
        String address = getUserInput("Enter Address: ");

        LegalCustomer legalCustomer = new LegalCustomer(name);
        legalCustomer.setBrand(brand);
        legalCustomer.setWebsite(website);
        legalCustomer.setAddress(address);

        String addContactQuestion = getUserInput("Do you want add contact? (enter y for yes and other char for no): ");

        while (addContactQuestion.equalsIgnoreCase("y")) {
            addContactTo(legalCustomer);
            addContactQuestion = getUserInput("Do you want add contact? (enter y for yes and other char for no): ");
        }
        customerService.addCustomer(legalCustomer);

    }

    private void addIndividualCustomer() {

        String name = getUserInput("Enter Name: ");
        String lastName = getUserInput("Enter Last Name: ");
        String identicalCode = getUserInput("Enter IdenticalCode: ");
        String address = getUserInput("Enter Address: ");

        IndividualCustomer individualCustomer = new IndividualCustomer(name);
        individualCustomer.setLastName(lastName);
        individualCustomer.setIdenticalCode(identicalCode);
        individualCustomer.setAddress(address);

        String addContactQuestion = getUserInput("Do you want add contact? (enter y for yes and other char for no): ");

        while (addContactQuestion.equalsIgnoreCase("y")) {
            addContactTo(individualCustomer);
            addContactQuestion = getUserInput("Do you want add contact? (enter y for yes and other char for no): ");
        }
        customerService.addCustomer(individualCustomer);
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
                addContactToList("Enter Phone Number: ", ContactType.PHONE_NUMBER, customer);
                break;
            }
            case "2": {
                addContactToList("Enter Mobile: ", ContactType.MOBILE, customer);
                break;
            }
            case "3": {
                addContactToList("Enter Fax: ", ContactType.FAX, customer);
                break;
            }
            case "4": {
                addContactToList("Enter Email: ", ContactType.EMAIL, customer);
                break;
            }
            default: {
                System.out.println("Invalid Item!");
                break;
            }
        }
    }

    private void addContactToList(String message, ContactType phoneNumber, Customer customer) {
        String value = getUserInput(message);
        Contact contact = new Contact(value, phoneNumber);
        customer.getContacts().add(contact);
        customerService.addContactToList(customer, contact);
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


        if (customerToEdit instanceof IndividualCustomer) {
            editIndividualCustomer((IndividualCustomer) customerToEdit);
        } else if (customerToEdit instanceof LegalCustomer) {
            editLegalCustomer((LegalCustomer) customerToEdit);
        } else {
            System.out.println("Invalid Item");
        }
    }

    private void editLegalCustomer(LegalCustomer legalCustomer) {

        System.out.println("It's a legal customer");
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Brand");
        System.out.println("3. Contacts");
        System.out.println("4. Website");
        System.out.println("5. Address");
        System.out.print("Select a menu item: ");
        String selectedOption = scanner.nextLine();

        switch (selectedOption) {
            case "1": {
                System.out.print("Enter new Name: ");
                legalCustomer.setName(scanner.nextLine());
                System.out.println("Name updated");
                break;
            }
            case "2": {
                System.out.print("Enter new Brand: ");
                legalCustomer.setBrand(scanner.nextLine());
                System.out.println("Last Brand updated");
                break;
            }
            case "3": {
                editContact(legalCustomer);
                break;
            }
            case "4": {
                System.out.print("Enter new Website: ");
                legalCustomer.setWebsite(scanner.nextLine());
                System.out.println("Website updated");
                break;
            }
            case "5": {
                System.out.print("Enter new Address: ");
                legalCustomer.setAddress(scanner.nextLine());
                System.out.println("Address updated");
                break;
            }
            default: {
                System.out.println("Invalid Item!");
                break;
            }
        }

        System.out.println("Contact with ID " + legalCustomer.getId() + " edited successfully.");

    }

    private void editIndividualCustomer(IndividualCustomer individualCustomer) {
        System.out.println("It's a individual customer");
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Last Name");
        System.out.println("3. Contacts");
        System.out.println("4. Identical Code");
        System.out.println("5. Address");
        System.out.print("Select a menu item: ");
        String selectedOption = scanner.nextLine();

        switch (selectedOption) {
            case "1": {
                System.out.print("Enter new Name: ");
                individualCustomer.setName(scanner.nextLine());
                System.out.println("Name updated");
                break;
            }
            case "2": {
                System.out.print("Enter new Last Name: ");
                individualCustomer.setLastName(scanner.nextLine());
                System.out.println("Last Name updated");
                break;
            }
            case "3": {
                editContact(individualCustomer);
                break;
            }
            case "4": {
                System.out.print("Enter new IdenticalCode: ");
                individualCustomer.setIdenticalCode(scanner.nextLine());
                System.out.println("Identical Code updated");
                break;
            }
            case "5": {
                System.out.print("Enter new Address: ");
                individualCustomer.setAddress(scanner.nextLine());
                System.out.println("Address updated");
                break;
            }
            default: {
                System.out.println("Invalid Item!");
                break;
            }
        }

        System.out.println("Contact with ID " + individualCustomer.getId() + " edited successfully.");
    }

    private void editContact(Customer customer) {
        System.out.println("List of this customer contacts: ");
        System.out.println(customer.getContacts());
        System.out.println("End of contacts");

        System.out.print("Enter the ID of the contact you want to edit: ");
        int contactIdToEdit = scanner.nextInt();
        scanner.nextLine();
        Contact contactToEdit = customerService.getContentById(customer, contactIdToEdit);
        System.out.println(contactToEdit);
        if (contactToEdit != null) {

            System.out.println("Update contact options: ");
            System.out.println("1. Edit contact value");
            System.out.println("2. Delete contact");
            System.out.print("Enter update option: ");
            String updateContactOption = scanner.nextLine();

            switch (updateContactOption) {
                case "1":
                    System.out.print("Enter new value: ");
                    String newValue = scanner.nextLine();
                    contactToEdit.setValue(newValue);
                    System.out.println("Contact updated");
                    break;
                case "2":
                    if (customer.getContacts().removeIf(contact -> contact.getId() == contactIdToEdit)) {
                        System.out.println("Contact with ID " + contactIdToEdit + " deleted successfully.");
                    } else {
                        System.out.println("Contact with ID " + contactIdToEdit + " not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid Item!");
                    return;
            }
        } else {
            System.out.println("Contact with ID " + contactIdToEdit + " not found.");
        }
    }

    private void deleteCustomer() {
        System.out.print("Enter the ID of the customer you want to delete: ");
        int customerIdToDelete = scanner.nextInt();
        scanner.nextLine();
//        customerService.deleteCustomer(customerIdToDelete);
        customerService.safeDeleteCustomer(customerIdToDelete);
    }

}
