package view.component;

import model.Contact;
import model.Customer;
import model.CustomerType;
import service.impl.CustomerServiceImpl;
import util.ScannerWrapper;

import java.util.function.Function;

public abstract class AbstractCustomerUI {
    protected final ScannerWrapper scannerWrapper;

    public AbstractCustomerUI() {
        this.scannerWrapper = ScannerWrapper.getInstance();
    }

    public static AbstractCustomerUI fromCustomerType(CustomerType type) {
        return switch (type) {
            case CustomerType.INDIVIDUAL -> new IndividualCustomerUI();
            case CustomerType.LEGAL -> new LegalCustomerUI();
        };
    }

    public Customer generateCustomer() {

        String name = scannerWrapper.getUserInput("Enter Name: ", Function.identity());
        String address = scannerWrapper.getUserInput("Enter Address: ",Function.identity());

        return additionalGenerateCustomer(name, address);
    }


    protected abstract Customer additionalGenerateCustomer(String name, String address);

    public abstract void editCustomer(Customer customer, CustomerServiceImpl customerService);

    protected void editContact(Customer customer, CustomerServiceImpl customerService) {
        System.out.println("List of this customer contacts: ");
        System.out.println(customer.getContacts());
        System.out.println("End of contacts");

        int contactIdToEdit = scannerWrapper
                .getUserInput("Enter the ID of the contact you want to edit: ", Integer::valueOf);
        Contact contactToEdit = customerService.getContentById(customer, contactIdToEdit);
        System.out.println(contactToEdit);
        if (contactToEdit != null) {

            System.out.println("Update contact options: ");
            System.out.println("1. Edit contact value");
            System.out.println("2. Delete contact");

            String updateContactOption = scannerWrapper
                    .getUserInput("Enter update option: ",Function.identity());

            switch (updateContactOption) {
                case "1":
                    String newValue = scannerWrapper
                            .getUserInput("Enter new value: ",Function.identity());
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
