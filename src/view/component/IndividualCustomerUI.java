package view.component;

import model.Customer;
import model.IndividualCustomer;
import service.CustomerService;

import java.util.Scanner;

public class IndividualCustomerUI extends AbstractCustomerUI {
    public IndividualCustomerUI() {
        super();
    }

    @Override
    public Customer generateCustomer() {
        String name = getUserInput("Enter Name: ");
        String lastName = getUserInput("Enter Last Name: ");
        String identicalCode = getUserInput("Enter IdenticalCode: ");
        String address = getUserInput("Enter Address: ");
        IndividualCustomer individualCustomer = new IndividualCustomer(name);
        individualCustomer.setLastName(lastName);
        individualCustomer.setIdenticalCode(identicalCode);
        individualCustomer.setAddress(address);
        return individualCustomer;
    }

    @Override
    public void editCustomer(Customer customer,CustomerService customerService) {

        IndividualCustomer individualCustomer = (IndividualCustomer) customer;

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
                editContact(individualCustomer, customerService);
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


}
