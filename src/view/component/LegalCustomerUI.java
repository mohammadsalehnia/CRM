package view.component;

import model.Customer;
import model.LegalCustomer;
import service.CustomerService;

import java.util.Scanner;

public class LegalCustomerUI extends AbstractCustomerUI {
    public LegalCustomerUI() {
        super();
    }

    @Override
    public Customer generateCustomer() {
        String name = getUserInput("Enter Name: ");
        String brand = getUserInput("Enter Brand: ");
        String website = getUserInput("Enter Website: ");
        String address = getUserInput("Enter Address: ");
        LegalCustomer legalCustomer = new LegalCustomer(name);
        legalCustomer.setBrand(brand);
        legalCustomer.setWebsite(website);
        legalCustomer.setAddress(address);
        return legalCustomer;
    }

    @Override
    public void editCustomer(Customer customer, CustomerService customerService) {

        LegalCustomer legalCustomer = (LegalCustomer) customer;

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
                editContact(legalCustomer,customerService);
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
}
