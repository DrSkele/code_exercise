import java.io.*;
import java.util.*;

class Main{
    
    public static void main(String args[]) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(reader.readLine());
        
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int[] arrayA = new int[length];
        
        for(int i = 0; i < length; i++){
          arrayA[i] = Integer.parseInt(tokens.nextToken());
        }
        
        int[] sortedA = arrayA.clone();
        Arrays.sort(sortedA);
        int[] arrayIdx = new int[1001];
        arrayIdx[sortedA[0]] = 0;
        
        for(int i = 1; i < length; i++){
          if(sortedA[i] != sortedA[i-1]) arrayIdx[sortedA[i]] = i;
        }
        
        for(int i = 0; i < length; i++){
          System.out.print(arrayIdx[arrayA[i]] + " ");
          arrayIdx[arrayA[i]] += 1;
        }
    }
}