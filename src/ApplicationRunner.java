import service.CustomerService;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (CustomerService customerService = new CustomerService()) {
            customerService.run();
        }
    }
}