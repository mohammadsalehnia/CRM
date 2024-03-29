package view.component;

import model.Customer;
import model.IndividualCustomer;
import service.impl.CustomerServiceImpl;
import java.util.function.Function;

public class IndividualCustomerUI extends AbstractCustomerUI {
    public IndividualCustomerUI() {
        super();
    }

    @Override
    public Customer additionalGenerateCustomer(String name, String address) {
        String lastName = scannerWrapper.getUserInput("Enter Last Name: ", Function.identity());
        String identicalCode = scannerWrapper.getUserInput("Enter IdenticalCode: ", Function.identity());
        IndividualCustomer individualCustomer = new IndividualCustomer(name);
        individualCustomer.setLastName(lastName);
        individualCustomer.setIdenticalCode(identicalCode);
        individualCustomer.setAddress(address);
        return individualCustomer;
    }

    @Override
    public void editCustomer(Customer customer, CustomerServiceImpl customerService) {

        IndividualCustomer individualCustomer = (IndividualCustomer) customer;

        System.out.println("It's a individual customer");
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Last Name");
        System.out.println("3. Contacts");
        System.out.println("4. Identical Code");
        System.out.println("5. Address");

        String selectedOption = scannerWrapper.getUserInput("Select a menu item: ", Function.identity());

        switch (selectedOption) {
            case "1": {
                String name = scannerWrapper.getUserInput("Enter new Name: ", Function.identity());
                individualCustomer.setName(name);
                System.out.println("Name updated");
                break;
            }
            case "2": {
                String lastName = scannerWrapper.getUserInput("Enter new Last Name: ", Function.identity());
                individualCustomer.setLastName(lastName);
                System.out.println("Last Name updated");
                break;
            }
            case "3": {
                editContact(individualCustomer, customerService);
                break;
            }
            case "4": {
                String userInput = scannerWrapper.getUserInput("Enter new IdenticalCode: ", Function.identity());
                individualCustomer.setIdenticalCode(userInput);
                System.out.println("Identical Code updated");
                break;
            }
            case "5": {
                String userInput = scannerWrapper.getUserInput("Enter new Address: ", Function.identity());
                individualCustomer.setAddress(userInput);
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

}
