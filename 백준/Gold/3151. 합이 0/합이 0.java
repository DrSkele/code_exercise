import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int length;
    static ArrayList<Integer> student;
    
    static void input(BufferedReader in) throws IOException {
        length = Integer.parseInt(in.readLine());
        
        student = new ArrayList<>(length);
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        while(tokens.hasMoreTokens()) {
            int ability = Integer.parseInt(tokens.nextToken());
            student.add(ability);
        }
        
        Collections.sort(student);
    }
    
    static void solve() {
        long cnt = 0;
        
        
        for(int i = 0; i < length - 2; i++) {
            int left = i + 1;
            int right = length - 1;
            
            while(left < right) {
                int leftVal = student.get(left);
                int rightVal = student.get(right);
                int sum = student.get(i) + leftVal + rightVal;
                
                if(sum == 0) {
                    if(leftVal == rightVal) {
                        long n = right - left + 1;
                        cnt += (n * (n - 1)) / 2;
                        break;
                    }
                    
                    long leftCount = 1;
                    long rightCount = 1;
                    
                    while(left + 1 < right && student.get(left + 1) == leftVal) {
                        leftCount++;
                        left++;
                    }
                    
                    while(right - 1 > left && student.get(right - 1) == rightVal) {
                        rightCount++;
                        right--;
                    }
                    
                    cnt += leftCount * rightCount;
                    
                    left++;
                    right--;
                    
                } else if(sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        System.out.println(cnt);
    }
}