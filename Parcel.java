public class Parcel implements ParcelInterface {
    private String senderName, receiverName, source, destination, id;
    private double weight, height, width, length;

    public Parcel(
        String senderName,
        String receiverName,
        String source,
        String destination,
        String id,
        double weight,
        double height,
        double width,
        double length
    ) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.source = source;
        this.destination = destination;
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public void getInfo() {
        System.out.println(String.format("From: %s (%s), To: %s (%s)", senderName, source, receiverName, destination));
        System.out.println(String.format("WxLxH: %sx%sx%s cm", width, length, height));
        System.out.println(String.format("Weight: %s kg", weight));
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return weight * height * width * length * 0.05;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return width * length * height;
    }

    public String getServices() {
        return "";
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
