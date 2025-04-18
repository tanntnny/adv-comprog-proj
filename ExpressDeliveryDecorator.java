public class ExpressDeliveryDecorator extends ParcelDecorator {
    public ExpressDeliveryDecorator(ParcelInterface parcel) {
        super(parcel);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + super.getWeight() * 10;
    }

    @Override
    public String getServices() {
        return super.getServices() + String.format("ExpressDelivery(+%f) ", super.getWeight() * 10);
    }
}
