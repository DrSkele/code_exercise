import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i< N; i++){
            arr[i] = Integer.parseInt(tokens.nextToken());
        }
        Arrays.sort(arr);
        
        int sum = 0;
        int prev = 0;
        for(int i = 0; i < N; i++){
            int cur = prev + arr[i];
            sum += cur;
            prev = cur;
        }
        System.out.println(sum);
    }
}