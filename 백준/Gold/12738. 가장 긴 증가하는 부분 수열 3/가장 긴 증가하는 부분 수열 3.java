import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  
    input(in);
    solve();
    
  }
  
  static int size;
  static int[] nums;
  public static void input(BufferedReader in) throws IOException {
    size = Integer.parseInt(in.readLine());
    StringTokenizer tokens = new StringTokenizer(in.readLine());
    nums = new int[size];
    for(int i = 0; i < size; i++) {
      nums[i] = Integer.parseInt(tokens.nextToken());
    }
  }
  
  public static void solve() {
    List<Integer> asc = new ArrayList<>();
    
    asc.add(nums[0]);
    
    for(int i = 1; i < size; i++) {
      int cur = nums[i];
      
      if(asc.get(asc.size() - 1) < cur) {
        asc.add(cur);
      } else {
        int result = 0;
        int left = 0;
        int right = asc.size() - 1;
        
        while(left <= right) {
          int mid = left + (right - left) / 2;
          
          if(asc.get(mid) >= cur) {
            result = mid;
            right = mid - 1;
          } else {
            left = mid + 1;
          }
        }
        
        asc.set(result, cur);
      }
    }
    
    System.out.println(asc.size());
  }
}