import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        double ladA = Double.parseDouble(tokens.nextToken());
        double ladB = Double.parseDouble(tokens.nextToken());
        double height = Double.parseDouble(tokens.nextToken());
        
        double answer = getDist(ladA, ladB, height);
        
        System.out.printf("%.3f", answer);
    }
    
    public static double getDist(double x, double y, double c) {
        double left = 0.0;
        double right = Math.min(x, y);

        for (int i = 0; i < 100; i++) {
            double mid = (left + right) / 2.0;
            double est = getEstHeight(mid, x, y);
    
            if (est >= c) left = mid;
            else right = mid;
        }
        return (left + right) / 2.0;
    }
    
    public static double getEstHeight(double w, double x, double y) {
        double h1 = Math.sqrt(x * x - w * w);
        double h2 = Math.sqrt(y * y - w * w);
        return (h1 * h2) / (h1 + h2);
    }
}