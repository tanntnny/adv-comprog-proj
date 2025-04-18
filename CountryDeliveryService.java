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

    public void registerStation() {
        DeliveryService station;

        System.out.println("===================================");
        System.out.println("What delivery service do you want to register: ");
        System.out.println("(1) Flash");
        System.out.println("(2) Kurry");
        System.out.print("Your command: ");
        String in1 = scanner.nextLine();

        System.out.print("Input the location: ");
        String in2 = scanner.nextLine();

        if (in1.equals("1"))
            station = new FlashDeliveryService(in2);
        else if (in1.equals("2"))
            station = new KurryDeliveryService(in2);
        else
            return;

        countryDeliveryServices.add(station);
        System.out.println(String.format("Station %s has been registered.", station.getInfo()));
    }

    public void registerStation(DeliveryService station) {
        countryDeliveryServices.add(station);
        System.out.println(String.format("Station %s has been registered.", station.getInfo()));
    }

    public void registerParcel() {
        DeliveryService station;
        ParcelInterface parcel;

        listStations();
        System.out.print("What station do you want to register the parcel (Enter station number): ");
        String in = scanner.nextLine();

        station = countryDeliveryServices.get(Integer.parseInt(in));

        System.out.println("Input the parcel information...");
        System.out.print("Enter the sender name: ");
        String senderName = scanner.nextLine();
        System.out.print("Enter the receiver name: ");
        String receiverName = scanner.nextLine();
        String source = station.getLocation();
        System.out.print("Enter the destination address: ");
        String destination = scanner.nextLine();
        RandomId randomId = new RandomId();
        String id = randomId.generateId();
        System.out.print("Enter the length of the parcel [cm]: ");
        double length = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter the width of the parcel [cm]: ");
        double width = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter the height of the parcel [cm]: ");
        double height = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter the weight of the parcel [kg]: ");
        double weight = Double.parseDouble(scanner.nextLine());

        parcel = new Parcel(senderName, receiverName, source, destination, id, weight, height, width, length);

        System.out.print("Do you want to add express delivery? (Y/N): ");
        in = scanner.nextLine();
        if (in.equals("Y")) parcel = new ExpressDeliveryDecorator(parcel);

        System.out.print("Do you want to add protection delivery? (Y/N): ");
        in = scanner.nextLine();
        if (in.equals("Y")) parcel = new ProtectionDecorator(parcel);

        System.out.print("Do you want to add warranty delivery? (Y/N): ");
        in = scanner.nextLine();
        if (in.equals("Y")) parcel = new WarrantyDeliveryDecorator(parcel);

        station.registerParcel(parcel);
        System.out.println(String.format("Parcel id: %s has been registered.", parcel.getId()));
    }

    public void listParcels() {
        int countStation = 0;
        System.out.println("===================================");
        for (DeliveryService station: countryDeliveryServices) {
            System.out.println(String.format("Station number: (%d) -> %s", countStation++, station.getInfo()));
            station.listParcels();
        }
    }

    public void listStations() {
        int count = 0;
        System.out.println("===================================");
        for(DeliveryService station: countryDeliveryServices) {
            System.out.println(String.format("Station number: (%d) -> %s", count++, station.getInfo()));
        }
    }

    public void listShippingCosts() {
        int countStation = 0;
        System.out.println("===================================");
        for (DeliveryService station: countryDeliveryServices) {
            System.out.println(String.format("Station number: (%d) -> %s", countStation++, station.getInfo()));
            station.listTotalShippingCost();
        }
    }
    
    public ArrayList<DeliveryService> getStations() {
        return countryDeliveryServices;
    }
}
