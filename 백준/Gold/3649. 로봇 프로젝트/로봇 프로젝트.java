import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while(input(in)) {
          solve();
        }
    }
    
    static int hole;
    static int legoCnt;
    static int[] lego;
    static boolean input(BufferedReader in) throws IOException {
        String input = in.readLine();
        if(input == null) return false;
      
        hole = Integer.parseInt(input) * 10_000_000;
        legoCnt = Integer.parseInt(in.readLine());
        
        lego = new int[legoCnt];
        for(int i = 0; i < legoCnt; i++) {
            lego[i] = Integer.parseInt(in.readLine());
        }
        
        Arrays.sort(lego);
        
        return true;
    }
    
    static void solve() {
        int min = 0;
        int max = -1;
        for(int i = 0; i < legoCnt-1; i++) {
            int match = getMatch(lego, i+1, legoCnt-1, lego[i], hole);
            
            // no match
            if(match < 0) continue;
            
            int diff = max - min;
            int curDiff = match - lego[i];
            
            if(curDiff > diff) {
                min = lego[i];
                max = match;
            }
        }
        
        if(min == 0) {
            System.out.println("danger");
        } else {
            System.out.printf("yes %d %d\n", min, max);
        }
    }
    
    static int getMatch(int[] arr, int leftIdx, int rightIdx, int pair, int goal) {
        while(leftIdx < rightIdx) {
            int midIdx = (leftIdx + rightIdx) / 2;
            
            int sum = pair + arr[midIdx];
            
            if(sum < goal) leftIdx = midIdx + 1;
            else rightIdx = midIdx;
        }
        
        //System.out.printf("%d %d %d\n", pair, arr[rightIdx], goal);
        
        if(arr[rightIdx] + pair == goal) return arr[rightIdx];
        else return -1;
    }
}