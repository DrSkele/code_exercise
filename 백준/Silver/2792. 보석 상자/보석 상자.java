import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        long kidsCnt = Long.parseLong(tokens.nextToken());
        int colorCnt = Integer.parseInt(tokens.nextToken());
        
        long[] gem = new long[colorCnt];
        long maxGem = 0;
        
        for(int i = 0; i < colorCnt; i++) {
            gem[i] = Long.parseLong(in.readLine());
            if(gem[i] > maxGem) maxGem = gem[i];
        }
        
        long min = minEnvy(gem, 1, maxGem, kidsCnt);
        
        System.out.println(min);
    }
    
    static long minEnvy(long[] arr, long left, long right, long limit) {
        while(left < right) {
            long mid = (left + right)/2;
            
            long sum = 0;
            for(int i = 0; i < arr.length; i++) {
                sum += arr[i]/mid;
                if(arr[i]%mid > 0) sum++;
                
                if(sum > limit) break;
            }
            
            if(sum <= limit) right = mid;
            else left = mid + 1;
        }
        
        return left;
    }
}