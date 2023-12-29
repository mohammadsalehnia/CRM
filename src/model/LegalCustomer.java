package model;

public class LegalCustomer extends Customer {
    private String brand;
    private String website;

    public LegalCustomer(String name) {
        super(name, CustomerType.LEGAL);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "LegalCustomer{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", brand='" + this.getBrand() + '\'' +
                ", website='" + this.getWebsite() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                ", contacts='" + this.getContacts() + '\'' +
                '}';
    }
}
