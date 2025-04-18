import java.util.Random;

public class RandomId {
    private Random rand;

    public RandomId() {
        rand = new Random();
    }
    
    public String generateId() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int randomNum = rand.nextInt(36);
            if (randomNum < 10) {
                id.append(randomNum);
            } else {
                id.append((char) ('A' + randomNum - 10));
            }
        }
        return id.toString();
    }
}
