public abstract class ParcelDecorator implements ParcelInterface {
    protected ParcelInterface decoratedParcel;

    public ParcelDecorator(ParcelInterface parcel) {
        decoratedParcel = parcel;
    }

    @Override
    public void getInfo() {
        decoratedParcel.getInfo();
    }

    @Override
    public String getId() {
        return decoratedParcel.getId();
    }
    
    @Override
    public double getWeight() {
        return decoratedParcel.getWeight();
    }

    @Override
    public double getVolume() {
        return decoratedParcel.getVolume();
    }

    @Override
    public double getPrice() {
        return decoratedParcel.getPrice();
    }

    @Override
    public String getServices() {
        return decoratedParcel.getServices();
    }

    @Override
    public String getSource() {
        return decoratedParcel.getSource();
    }

    @Override
    public String getDestination() {
        return decoratedParcel.getDestination();
    }
}