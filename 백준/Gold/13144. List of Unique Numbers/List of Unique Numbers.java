import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int length = Integer.parseInt(in.readLine());
        
        int[] arr = new int[length];
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < length; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }
        
        long cnt = 0;
        int start = 0;
        int end = 0;
        HashSet<Integer> set = new HashSet<>();
        
        while(start < length) {
            while(end < length) {
                if(set.contains(arr[end])) {
                    break;
                }
              
                set.add(arr[end]);
                end++;
            }
            
            cnt += end - start;
            
            set.remove(arr[start]);
            start++;
        }
        
        System.out.println(cnt);
    }
}