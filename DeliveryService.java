import java.util.ArrayList;

abstract public class DeliveryService {
    private ArrayList<Parcel> parcels;
    private String location;

    public DeliveryService(String location) {
        parcels = new ArrayList<Parcel>();
        this.location = location;
    }

    abstract public String getInfo();

    public String getLocation() {
        return location;
    }

    public void registerParcel(Parcel parcel) {
        parcels.add(parcel);
        System.out.println(String.format("Parcel id: %s has been registered.", parcel.getId()));
    }
}

class FlashDeliveryService extends DeliveryService {
    public FlashDeliveryService(String location) {
        super(location);
    }

    public String getInfo() {
        return String.format("This is Flash. Location: %s", getLocation());
    }
}

class KurryDeliveryService extends DeliveryService {
    public KurryDeliveryService(String location) {
        super(location);
    }

    public String getInfo() {
        return String.format("This is Kurry. Location: %s", getLocation());
    }
}