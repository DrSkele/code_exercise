import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int[] arrayA = new int[N];
        
        tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++){
            arrayA[i] = Integer.parseInt(tokens.nextToken());
        }
        Arrays.sort(arrayA);
        
        tokens = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(tokens.nextToken());
        int[] arrayB = new int[M];
        
        tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < M; i++){
            arrayB[i] = Integer.parseInt(tokens.nextToken());
        }
        
        for(int i = 0; i < M; i++){
            int target = arrayB[i];
            
            int bottom = 0;
            int top = N;
            
            boolean hasValue = false;
            while(bottom < top){
                int mid = (bottom+top)/2;
                if(arrayA[mid] < target){
                    bottom = mid+1;
                } else if (target < arrayA[mid]){
                    top = mid;
                } else {
                    hasValue = true;
                    //System.out.println(arrayA[mid]);
                    break;
                }
            }
            System.out.println(hasValue?1:0);
        }
        
        
        
        
    }
}