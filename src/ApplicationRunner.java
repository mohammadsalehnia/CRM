import view.ConsoleUI;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (ConsoleUI consoleUI = new ConsoleUI()) {
            consoleUI.startMenu();
        }
    }
}