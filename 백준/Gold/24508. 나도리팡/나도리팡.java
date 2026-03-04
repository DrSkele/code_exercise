import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        int basketCnt = Integer.parseInt(tokens.nextToken());
        int maxDori = Integer.parseInt(tokens.nextToken());
        int maxMove = Integer.parseInt(tokens.nextToken());
        
        tokens = new StringTokenizer(in.readLine());
        
        Integer[] basket = new Integer[basketCnt];
        long total = 0;
        for(int i = 0; i < basket.length; i++) {
            int dori = Integer.parseInt(tokens.nextToken());
            total += dori;
            basket[i] = dori;
        }
        
        if(total == 0) {
          System.out.println("YES");
          return;
        }
        
        Arrays.sort(basket, Collections.reverseOrder());
        
        boolean success = true;
        int sum = 0;
        for(int i = 0; i < basket.length; i++) {
            int need = maxDori - basket[i];
            
            total -= maxDori;
            sum += need;
            
            if(sum > maxMove || total < 0) {
                success = false;
                break;
            }
            
            if(total == 0) break;
        }
        
        if(success) System.out.println("YES");
        else System.out.println("NO");
    }
}