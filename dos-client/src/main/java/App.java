import java.util.Scanner;

public class App {

    /**
     * Application entry point.
     *
     * @param args application command line arguments
     */
    public static void main(String[] args) {

        clientRunner clientRunner = new clientRunner();
        Scanner scanner;
        int numberOfClients;

        numberOfClients = getNumOfClientsInput();
        clientRunner.startClients(numberOfClients);
        System.out.println("Started " + numberOfClients + " clients");
        scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            clientRunner.shutDownClients();
            return;
        }
        System.exit(0);
    }

    private static int getNumOfClientsInput() {
        Scanner scanner;
        while (true) {
            System.out.println("Please enter the number of clients to run: ");
            scanner = new Scanner(System.in);
            String clients = scanner.next();

            try {
                return Integer.parseInt(clients);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. ");
            }
        }
    }

}