package util;

import java.util.Scanner;

public class ScannerWrapper implements AutoCloseable {

    private static final Scanner INSTANCE;

    public static Scanner getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE = new Scanner(System.in);
    }

    private ScannerWrapper() {
    }

    @Override
    public void close() {
        INSTANCE.close();
    }
}
