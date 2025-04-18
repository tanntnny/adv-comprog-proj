public class ShippingService {
    private ShippingStrategyInterface shippingStrategy;
    
    public ShippingService() {
        shippingStrategy = new ShippingInProvince();
    }

    public void setShippingStrategy(ShippingStrategyInterface shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public double calculateCost(double distance) {
        return shippingStrategy.calculateCost(distance);
    }
}