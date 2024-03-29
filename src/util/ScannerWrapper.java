package util;

import java.util.Scanner;
import java.util.function.Function;

public class ScannerWrapper implements AutoCloseable {

    private static final ScannerWrapper INSTANCE;
    private final Scanner scanner;

    public static ScannerWrapper getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE = new ScannerWrapper();
    }

    private ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void close() {
        scanner.close();
    }

    public <T> T getUserInput(String message, Function<String, T> convertor) {
        System.out.print(message);
        T input = null;
        try {
            input = convertor.apply(scanner.nextLine());
        } catch (Exception ex) {
            System.out.println("Wrong input!");
            input = getUserInput(message, convertor);
        }
        return input;
    }

}
