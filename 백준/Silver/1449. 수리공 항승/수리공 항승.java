import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int holes = Integer.parseInt(tokens.nextToken());
        int length = Integer.parseInt(tokens.nextToken());
        
        ArrayList<Integer> arr = new ArrayList<>();
        
        tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < holes; i++) {
            arr.add(Integer.parseInt(tokens.nextToken()));
        }
        Collections.sort(arr);
        
        int cnt = 0;
        int start = -1;
        
        for(int i = 0; i < arr.size(); i++) {
            if(start == -1) {
              start = arr.get(i);
              continue;
            }
          
            int gap = arr.get(i) - start + 1;
            if(gap == length) {
              cnt++;
              start = -1;
            } else if(gap > length) {
              cnt++;
              start = arr.get(i);
            }
        }
        
        if(start != -1) cnt++;
        
        System.out.println(cnt);
    }
}