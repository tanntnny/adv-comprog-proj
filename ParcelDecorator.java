public abstract class ParcelDecorator implements ParcelInterface {
    protected ParcelInterface decoratedParcel;

    public ParcelDecorator(Parcel parcel) {
        this.decoratedParcel = parcel;
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
}

class ExpressDeliveryDecorator extends ParcelDecorator {
    public ExpressDeliveryDecorator(Parcel parcel) {
        super(parcel);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + super.getWeight() * 10;
    }
}

class ProtectionDecorator extends ParcelDecorator {
    public ProtectionDecorator(Parcel parcel) {
        super(parcel);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + super.getVolume() * 0.05;
    }
}

class WarrantyDeliveryDecorator extends ParcelDecorator {
    public WarrantyDeliveryDecorator(Parcel parcel) {
        super(parcel);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + super.getWeight() * getVolume() * 0.3;
    }
}