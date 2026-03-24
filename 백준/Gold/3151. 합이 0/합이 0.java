import java.io.*;
import java.util.*;

public class Main {
    static int length;
    static ArrayList<Integer> student;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        length = Integer.parseInt(in.readLine());
        student = new ArrayList<>(length);
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for (int i = 0; i < length; i++) {
            student.add(Integer.parseInt(tokens.nextToken()));
        }
        
        Collections.sort(student);
        solve();
    }
    
    static void solve() {
        long cnt = 0;
        
        for(int i = 0; i < length - 2; i++) {
            for(int j = i + 1; j < length - 1; j++) {
                
                int target = -(student.get(i) + student.get(j));
                
                int lowerBound = lowerBound(j + 1, length, target);
                int upperBound = upperBound(j + 1, length, target);
                
                cnt += (upperBound - lowerBound);
            }
        }
        System.out.println(cnt);
    }
    
    static int lowerBound(int left, int right, int target) {
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(student.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    static int upperBound(int left, int right, int target) {
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(student.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}