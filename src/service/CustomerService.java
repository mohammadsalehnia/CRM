package service;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private ArrayList<Customer> customers = new ArrayList<>();


    public void deleteCustomer(int id) {
        customers.removeIf(customer -> customer.getId() == id);
    }

    public List<Customer> searchByBrand(String brand) {
        return customers.stream()
                .filter(customer -> customer instanceof LegalCustomer).map(customer -> (LegalCustomer) customer)
                .filter(legalCustomer -> legalCustomer.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Customer> searchByWebsite(String website) {
        return customers.stream()
                .filter(customer -> customer instanceof LegalCustomer).map(customer -> (LegalCustomer) customer)
                .filter(legalCustomer -> legalCustomer.getWebsite().equalsIgnoreCase(website))
                .collect(Collectors.toList());
    }

    public List<Customer> searchIdenticalCode(String identicalCode) {
        return customers.stream()
                .filter(customer -> customer instanceof IndividualCustomer)
                .map(customer -> (IndividualCustomer) customer).filter(individualCustomer -> individualCustomer.getIdenticalCode().equalsIgnoreCase(identicalCode))
                .collect(Collectors.toList());
    }

    public List<Customer> searchByAddress(String address) {
        return customers.stream()
                .filter(customer -> customer.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toList());
    }

    public List<Customer> searchByLastName(String lastName) {
        return customers.stream().
                filter(customer -> customer instanceof IndividualCustomer).
                map(customer -> (IndividualCustomer) customer).filter(individualCustomer -> individualCustomer.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public List<Customer> searchByNumber(String number) {
        return customers.stream().filter(customer -> customer.getContacts().stream().
                        anyMatch(contact -> ((contact.getType().equals(ContactType.PHONE_NUMBER) ||
                                contact.getType().equals(ContactType.FAX) ||
                                contact.getType().equals(ContactType.MOBILE)) &&
                                contact.getValue().equals(number))))
                .collect(Collectors.toList());
    }


    public List<Customer> searchByEmail(String email) {
        return customers.stream().
                filter(customer -> customer.getContacts().stream().anyMatch(contact -> (contact.getType().equals(ContactType.EMAIL) && contact.getValue().equals(email))))
                .collect(Collectors.toList());
    }

    public List<Customer> searchByName(String name) {
        return customers.stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Customer> searchById(int id) {

        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .collect(Collectors.toList());
    }


    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addContactToList(Customer customer, Contact contact) {
        customer.getContacts().add(contact);
    }

    public Customer getCustomerById(int id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    public Contact getContentById(Customer customer, int id) {
        return customer.getContacts().stream().filter(contact -> contact.getId() == id).findFirst().orElse(null);
    }


}
