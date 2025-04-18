public class WarrantyDeliveryDecorator extends ParcelDecorator {
    public WarrantyDeliveryDecorator(ParcelInterface parcel) {
        super(parcel);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + super.getWeight() * getVolume() * 0.3;
    }

    @Override
    public String getServices() {
        return super.getServices() + String.format("WarrantyDelivery(+%f) ", super.getWeight() * getVolume() * 0.3);
    }
}