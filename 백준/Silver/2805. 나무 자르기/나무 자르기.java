import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int givenN = Integer.parseInt(tokens.nextToken());
        long needM = Integer.parseInt(tokens.nextToken());
        int[] trees = new int[givenN];
        int tallest = 0;
        tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < givenN; i++){
            int tree = Integer.parseInt(tokens.nextToken());
            trees[i] = tree;
            if(tree > tallest) tallest = tree;
        }
        
        int bottom = 0;
        int top = tallest;
        
        while(bottom <= top){
            int mid = (bottom+top)/2;
            
            long sum = 0;
            for(int i =0; i< givenN; i++){
                if(trees[i] <= mid) continue;
                sum += trees[i]-mid;
            }
            if(sum < needM){
                top = mid-1;
            } else {
                bottom = mid+1;
            }
        }
        System.out.print(top);
    }
}