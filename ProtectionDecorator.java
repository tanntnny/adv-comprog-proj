public class ProtectionDecorator extends ParcelDecorator {
    public ProtectionDecorator(ParcelInterface parcel) {
        super(parcel);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + super.getVolume() * 0.05;
    }

    @Override
    public String getServices() {
        return super.getServices() + String.format("ProtectionDelivery(+%f) ", super.getVolume() * 0.05);
    }
}