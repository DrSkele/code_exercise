import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int length = Integer.parseInt(in.readLine());
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int[] arr = new int[length];
        long sum = 0;
        for(int i = 0; i < length; i++) {
            int req = Integer.parseInt(tokens.nextToken());
            arr[i] = req;
            sum += req;
        }
        long budget = Long.parseLong(in.readLine());
        
        Arrays.sort(arr);
        
        // within budget
        if(sum <= budget) {
            System.out.println(arr[length-1]);
            return;
        }
        
        int max = findMax(arr, 1, arr[length-1], budget);
        
        System.out.println(max);
    }
    
    public static int findMax(int[] arr, int left, int right, long limit) {
        
        while(left < right) {
            int mid = (int) Math.ceil((left + right) / 2f);
            
            long sum = 0;
            for(int i = 0; i < arr.length; i++) {
                int cur = arr[i];
                if(cur <= mid) sum += cur;
                else sum += mid;
            }
            
            if(sum == limit) return mid;
            else if(sum > limit) right = mid - 1;
            else left = mid;
        }
        
        return right;
    }
}