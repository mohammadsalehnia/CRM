package service;

import model.Contact;
import model.Customer;

import java.util.List;

public interface CustomerService {

    void safeDeleteCustomer(int id);

    List<Customer> searchByBrand(String brand);

    List<Customer> searchByWebsite(String website);

    List<Customer> searchIdenticalCode(String identicalCode);

    List<Customer> searchByAddress(String address);

    List<Customer> searchByLastName(String lastName);

    List<Customer> searchByNumber(String number);

    List<Customer> searchByEmail(String email);

    List<Customer> searchByName(String name);

    List<Customer> searchById(int id);

    List<Customer> getActiveCustomers();

    List<Customer> getDeletedCustomers();

    void addCustomer(Customer customer);

    Customer getCustomerById(int id);

    Contact getContentById(Customer customer, int id);

}
