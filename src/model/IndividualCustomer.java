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
        return "IndividualCustomer{" +
                super.toString() +
                ", lastName='" + this.getLastName() + '\'' +
                ", identicalCode='" + this.getIdenticalCode();
    }
}
