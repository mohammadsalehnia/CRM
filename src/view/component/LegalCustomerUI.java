package view.component;

import model.Customer;
import model.LegalCustomer;
import service.impl.CustomerServiceImpl;
import java.util.function.Function;

public class LegalCustomerUI extends AbstractCustomerUI {
    public LegalCustomerUI() {
        super();
    }

    @Override
    public Customer additionalGenerateCustomer(String name, String address) {
        String brand = scannerWrapper.getUserInput("Enter Brand: ", Function.identity());
        String website = scannerWrapper.getUserInput("Enter Website: ", Function.identity());

        LegalCustomer legalCustomer = new LegalCustomer(name);
        legalCustomer.setBrand(brand);
        legalCustomer.setWebsite(website);
        legalCustomer.setAddress(address);
        return legalCustomer;
    }

    @Override
    public void editCustomer(Customer customer, CustomerServiceImpl customerService) {

        LegalCustomer legalCustomer = (LegalCustomer) customer;

        System.out.println("It's a legal customer");
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Brand");
        System.out.println("3. Contacts");
        System.out.println("4. Website");
        System.out.println("5. Address");

        String selectedOption = scannerWrapper.getUserInput("Select a menu item: ", Function.identity());

        switch (selectedOption) {
            case "1": {
                String userInput = scannerWrapper.getUserInput("Enter new Name: ", Function.identity());
                legalCustomer.setName(userInput);
                System.out.println("Name updated");
                break;
            }
            case "2": {
                String userInput = scannerWrapper.getUserInput("Enter new Brand: ", Function.identity());
                legalCustomer.setBrand(userInput);
                System.out.println("Last Brand updated");
                break;
            }
            case "3": {
                editContact(legalCustomer, customerService);
                break;
            }
            case "4": {
                String userInput = scannerWrapper.getUserInput("Enter new Website: ", Function.identity());
                legalCustomer.setWebsite(userInput);
                System.out.println("Website updated");
                break;
            }
            case "5": {
                String userInput = scannerWrapper.getUserInput("Enter new Address: ", Function.identity());
                legalCustomer.setAddress(userInput);
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
}
