package model;

public class IndividualCustomer extends Customer {
    private String lastName;
    private String identicalCode;

    public IndividualCustomer(String name) {
        super(name, CustomerType.INDIVIDUAL);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdenticalCode() {
        return identicalCode;
    }

    public void setIdenticalCode(String identicalCode) {
        this.identicalCode = identicalCode;
    }

    @Override
    public String toString() {
        return "IndividualCustomer{" + "id='" + this.getId() + '\'' + ", name='" + this.getName() + '\'' + ", lastName='" + this.getLastName() + '\'' + ", identicalCode='" + this.getIdenticalCode() + '\'' + ", address='" + this.getAddress() + '\'' + ", contacts='" + this.getContacts() + '\'' + '}';
    }
}
