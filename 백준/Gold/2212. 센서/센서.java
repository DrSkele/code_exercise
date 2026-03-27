import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int length;
    static int maxNum;
    static int[] location;
    static void input(BufferedReader in) throws IOException {
        length = Integer.parseInt(in.readLine());
        maxNum = Integer.parseInt(in.readLine());
        location = new int[length];
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < length; i++) {
            location[i] = Integer.parseInt(tokens.nextToken());
        }
    }
    
    static void solve() {
        // station on each sensor
        if(length <= maxNum) {
            System.out.println(0);
            return;
        }
        
        Arrays.sort(location);
        
        int[] diff = new int[length-1];
        
        for(int i = 0; i < length-1; i++) {
            diff[i] = location[i+1] - location[i];
        }
        Arrays.sort(diff);
        
        int sum = 0;
        for(int i = 0; i < (length-1) - (maxNum-1); i++) {
            sum += diff[i];
        }
        
        System.out.println(sum);
    }
}