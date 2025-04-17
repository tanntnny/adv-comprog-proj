public class Main {
    public static void main(String[] args) {
        ScannerSingleton scanner = ScannerSingleton.getInstance();
        CountryDeliveryService countryDeliveryService = CountryDeliveryService.getInstance();

        // Initial delivery service station
        countryDeliveryService.registerStation(new FlashDeliveryService("Bangkok"));
        countryDeliveryService.registerStation(new KurryDeliveryService("Chonburi"));

        while (true) {
            System.out.println("===================================");
            System.out.println("Command list:");
            System.out.println("(RS) Register a new delivery staion.");
            System.out.println("(RP) Register a new parcel.");
            System.out.println("(LS) List all the delivery stations.");
            System.out.println("(LP) List all the parcels in the specified stations.");
            System.out.println("(Q) End the program.");
            System.out.print("Your command: ");

            String in = scanner.nextLine();
            if (in.equals("Q"))
                break;

            else if (in.equals("LS")) {
                countryDeliveryService.listStations();
            }

            else if (in.equals("RS")) {
                countryDeliveryService.registerStation(null);
            }

            else if (in.equals("RP")) {
                countryDeliveryService.registerParcel(null);
            }

            else
                System.out.println("Unknown command.");

        }
        System.out.println("===================================");
        scanner.close();
    }
}
