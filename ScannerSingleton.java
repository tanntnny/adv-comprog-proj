import java.util.Scanner;

public class ScannerSingleton {
    Scanner scanner;
    private static ScannerSingleton instance;
    
    public ScannerSingleton() {
        scanner = new Scanner(System.in);
    }
    
    public static ScannerSingleton getInstance() {
        if (instance == null) {
            instance = new ScannerSingleton();
        }
        return instance;
    }
    
    public String nextLine() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
