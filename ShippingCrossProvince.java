public class ShippingCrossProvince implements ShippingStrategyInterface {
    public double calculateCost(double distance) {
        return distance * 5;
    }
}
