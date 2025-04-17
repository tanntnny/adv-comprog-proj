import java.util.ArrayList;

public class CountryDeliveryService {
    private static CountryDeliveryService instance;
    private static ScannerSingleton scanner;

    private ArrayList<DeliveryService> countryDeliveryServices;

    public CountryDeliveryService() {
        countryDeliveryServices = new ArrayList<DeliveryService>();
        scanner = ScannerSingleton.getInstance();
    }
    
    public static CountryDeliveryService getInstance() {
        if (instance == null) {
            instance = new CountryDeliveryService();
        }
        return instance;
    }

    public void registerStation(DeliveryService station) {
        System.out.println("===================================");
        if (station == null) {
            System.out.println("What delivery service do you want to register: ");
            System.out.println("(1) Flash");
            System.out.println("(2) Kurry");
            System.out.print("Your command: ");
            String in1 = scanner.nextLine();

            System.out.print("Input the location: ");
            String in2 = scanner.nextLine();

            if (in1.equals("1")) station = new FlashDeliveryService(in2);
            else if (in1.equals("2")) station = new KurryDeliveryService(in2);
        }
        countryDeliveryServices.add(station);
        System.out.println(String.format("Station %s has been registered.", station.getInfo()));
    }

    public void registerParcel(Parcel parcel, DeliveryService station) {
        System.out.println("===================================");
        if (station == null) {
            listStations();
            System.out.println("What station do you want to register the parcel (Enter station number): ");
            String in = scanner.nextLine();
            station = countryDeliveryServices.get(Integer.parseInt(in));
        }
    }

    public void listStations() {
        int count = 0;
        System.out.println("===================================");
        for(DeliveryService station: countryDeliveryServices) {
            System.out.println(String.format("Station number: %d -> %s", count++, station.getInfo()));
        }
    }
}
