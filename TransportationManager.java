import java.util.HashMap;
import java.util.Set;

public class TransportationManager {
    private static TransportationManager instance;
    private HashMap<Set<String>, Double> transportationDistance;
    
    public TransportationManager() {
        transportationDistance = new HashMap<>();
    }

    public static TransportationManager getInstance() {
        if (instance == null) {
            instance = new TransportationManager();
        }
        return instance;
    }
    
    public void addTransportationRoute(Set<String> route, double cost) {
        transportationDistance.put(route, cost);
    }

    public double getTransportationDistance(Set<String> route) {
        return transportationDistance.getOrDefault(route, 0.0);
    }

    public void printTransportationRoutes() {
        System.out.println("===================================");
        for (Set<String> route : transportationDistance.keySet()) {
            System.out.println("Route: " + route + ", Cost: " + transportationDistance.get(route));
        }
    }
}