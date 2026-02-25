import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int deskCnt = Integer.parseInt(tokens.nextToken());
        long friendCnt = Integer.parseInt(tokens.nextToken());
        long[] time = new long[deskCnt];
        for(int i = 0; i < time.length; i++) {
            time[i] = Long.parseLong(in.readLine());
        }
        
        Arrays.sort(time);
        
        long min = minTime(time, time[0], time[time.length-1] * friendCnt, friendCnt);
        
        System.out.println(min);
    }
    
    static long minTime(long[] arr, long left, long right, long limit) {
        while(left < right) {
            long mid = left + (right - left) / 2;
            long sum = 0;
            for(int i = 0; i < arr.length; i++) {
                sum += mid / arr[i];
                if(sum > limit) break;
            }
            
            if(sum >= limit) right = mid;
            else left = mid+1;
        }
        return right;
    }
}