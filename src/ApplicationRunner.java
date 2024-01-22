import view.ConsoleUI;

public class ApplicationRunner {
    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        do {
            try {
                if (consoleUI.startMenu() == 0) {
                    break;
                }
            } catch (Throwable throwable) {
                System.out.println("System throwable!");
            } finally {
                consoleUI.close();
            }
        }
        while (true);

    }
}