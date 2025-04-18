import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ScannerSingleton scanner = ScannerSingleton.getInstance();
        CountryDeliveryService countryDeliveryService = CountryDeliveryService.getInstance();
        TransportationManager transportationManager = TransportationManager.getInstance();

        // Initial delivery service station
        countryDeliveryService.registerStation(new FlashDeliveryService("Bangkok"));
        countryDeliveryService.registerStation(new KurryDeliveryService("Lopburi"));

        // Initial transportation routes
        transportationManager.addTransportationRoute(Set.of("Bangkok", "Lopburi"), 150);
        transportationManager.addTransportationRoute(Set.of("Bangkok", "Rayong"), 180);
        transportationManager.addTransportationRoute(Set.of("Bangkok", "Chiangmai"), 700);
        transportationManager.addTransportationRoute(Set.of("Lopburi", "Chiangmai"), 550);
        transportationManager.addTransportationRoute(Set.of("Lopburi", "Rayong"), 330);
        transportationManager.addTransportationRoute(Set.of("Chiangmai", "Rayong"), 850);

        while (true) {
            System.out.println("===================================");
            System.out.println("Command list:");
            System.out.println("> (RS) Register a new delivery staion.");
            System.out.println("> (RP) Register a new parcel.");
            System.out.println("> (LS) List all the delivery stations.");
            System.out.println("> (LP) List all the parcels in the system.");
            System.out.println("> (LSH) List all the extra shipping costs.");
            System.out.println("> (Q) End the program.");
            System.out.print("Your command: ");

            String in = scanner.nextLine();
            if (in.equals("Q"))
                break;

            else if (in.equals("LS")) {
                countryDeliveryService.listStations();
            }

            else if (in.equals("RS")) {
                countryDeliveryService.registerStation();
            }

            else if (in.equals("RP")) {
                countryDeliveryService.registerParcel();
            }

            else if (in.equals("LP")) {
                countryDeliveryService.listParcels();
            }

            else if(in.equals("LSH")) {
                countryDeliveryService.listShippingCosts();
            }

            else
                System.out.println("Unknown command.");

        }
        System.out.println("===================================");
        scanner.close();
    }
}
