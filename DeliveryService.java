import java.util.ArrayList;
import java.util.Set;

abstract public class DeliveryService {
    private ArrayList<ParcelInterface> parcels;
    private String location;
    private double totalProfit;

    public DeliveryService(String location) {
        parcels = new ArrayList<ParcelInterface>();
        this.location = location;
    }

    abstract public String getInfo();

    public String getLocation() {
        return location;
    }

    public void registerParcel(ParcelInterface parcel) {
        parcels.add(parcel);
    }
    
    public void listParcels() {
        for (ParcelInterface parcel: parcels) {
            System.out.println(String.format("Parcel ID: %s", parcel.getId()));
            parcel.getInfo();
            System.out.println(String.format("Calculated price: %f, services: %s", parcel.getPrice(), parcel.getServices()));
        }
    }

    public int getParcelSize() {
        return parcels.size();
    }

    public void listTotalShippingCost() {
        TransportationManager transportationManager = TransportationManager.getInstance();
        CountryDeliveryService countryDeliveryService = CountryDeliveryService.getInstance();
        ShippingService shippingService = new ShippingService();
        for (ParcelInterface parcel: parcels) {
            String source = parcel.getSource();
            String destination = parcel.getDestination();
            String tag;
            double distance = 0;
            if (!source.equals(destination)){
                distance = transportationManager.getTransportationDistance(Set.of(source, destination));
                shippingService.setShippingStrategy(new ShippingCrossProvince());
                tag = "cross";
            } else {
                shippingService.setShippingStrategy(new ShippingInProvince());
                tag = "in";
            }
            double cost = shippingService.calculateCost(distance);
            System.out.println(String.format("Parcel ID: %s", parcel.getId()));
            parcel.getInfo();

            boolean isFound = false;
            for (DeliveryService station: countryDeliveryService.getStations()) {
                if (station.getCompany().equals(this.getCompany()) && destination.equals(station.location)) isFound = true;
            }
            if (!isFound) System.out.println(String.format("There is no %s delivery service registered at %s", this.getCompany(), destination));
            else System.out.println(String.format("Extra shipping cost (%s province): %f", tag, cost));
        }
    }

    public void deliverAllParcels() {
        CountryDeliveryService countryDeliveryService = CountryDeliveryService.getInstance();
        TransportationManager transportationManager = TransportationManager.getInstance();
        ShippingService shippingService = new ShippingService();
        ArrayList<ParcelInterface> toRemove = new ArrayList<ParcelInterface>();
        for (ParcelInterface parcel: parcels) {
            String destination = parcel.getDestination();
            boolean isFound = false;
            for (DeliveryService otherStation: countryDeliveryService.getStations()) {
                if (otherStation.getCompany().equals(this.getCompany()) && destination.equals(otherStation.location)) {
                    isFound = true;
                    double distance = transportationManager.getTransportationDistance(Set.of(this.location, destination));
                    if (!this.location.equals(destination)) {
                        shippingService.setShippingStrategy(new ShippingCrossProvince());
                        double cost = shippingService.calculateCost(distance);
                        System.out.println(String.format("Parcel ID: %s has been sent from %s to %s (Cross province) with shipping cost: %f", parcel.getId(), location, destination, cost));
                        totalProfit += cost;
                    } else {
                        shippingService.setShippingStrategy(new ShippingInProvince());
                        double cost = shippingService.calculateCost(distance);
                        System.out.println(String.format("Parcel ID: %s has been sent from %s to %s (In province) with shipping cost: %f", parcel.getId(), location, destination, cost));
                        totalProfit += cost;
                    }
                    break;
                }
            }
            if (isFound) toRemove.add(parcel);
            else System.out.println(String.format("Parcel ID: %s has not been sent because there is no %s delivery service registered at %s", parcel.getId(), this.getCompany(), destination));
        }
        parcels.removeAll(toRemove);
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    abstract public String getCompany();
}

class FlashDeliveryService extends DeliveryService {
    public FlashDeliveryService(String location) {
        super(location);
    }

    public String getInfo() {
        return String.format("Flash, Location: %s, Number of parcels: %d, Total profit: %f", getLocation(), getParcelSize(), getTotalProfit());
    }

    public String getCompany() {
        return "Flash";
    }
}

class KurryDeliveryService extends DeliveryService {
    public KurryDeliveryService(String location) {
        super(location);
    }

    public String getInfo() {
        return String.format("Kurry, Location: %s, Number of parcels: %d, Total profit: %f", getLocation(), getParcelSize(), getTotalProfit());
    }

    public String getCompany() {
        return "Kurry";
    }
}