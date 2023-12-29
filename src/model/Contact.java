package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Contact {
    private static final AtomicInteger nextId = new AtomicInteger(1);
    private int id;
    private String value;
    private final ContactType type;

    public Contact(String value, ContactType type) {
        this.id = nextId.getAndIncrement();
        this.setValue(value);
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public ContactType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
