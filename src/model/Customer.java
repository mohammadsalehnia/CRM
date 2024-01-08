package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Customer {
    private final CustomerType type;
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1);
    private Integer id;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private String name;
    private String address;

    private Boolean deleted;

    protected Customer(String name, CustomerType type) {
        this.id = ID_COUNTER.getAndIncrement();
        this.name = name;
        this.type = type;
        this.deleted = false;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerType getType() {
        return type;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                " type=" + type +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                "\n contacts=" + contacts +
                '}';
    }

}
