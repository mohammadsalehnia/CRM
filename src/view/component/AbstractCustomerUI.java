package view.component;

import model.Contact;
import model.Customer;
import service.CustomerService;
import util.ScannerWrapper;

import java.util.Scanner;

public abstract class AbstractCustomerUI {
    protected final Scanner scanner;

    public AbstractCustomerUI() {
        this.scanner = ScannerWrapper.getInstance();
    }

    public abstract Customer generateCustomer();

    public abstract void editCustomer(Customer customer,CustomerService customerService);
    protected String getUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    protected void editContact(Customer customer, CustomerService customerService) {
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

}
