package util;

import java.util.Scanner;

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

    public String getUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public Integer getUserInputInt(String message) {
        System.out.print(message);
        int value =  scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
