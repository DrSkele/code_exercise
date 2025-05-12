import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for(int i = 0; i < T; i++) {
            input(in);
            solve();
        }
    }
    
    static double[] password;
    
    static void input(BufferedReader in) throws IOException {
        password = new double[4];
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < 4; i++) {
            password[i] = Double.parseDouble(tokens.nextToken());
        }
    }
    
    static boolean hasFound;
    
    static void solve() {
        hasFound = false;
        List<Double> nums = new ArrayList<>();
        for(double num : password) {
            nums.add(num);
        }
        hasFound = tryMake24(nums);
        System.out.println(hasFound ? "YES" : "NO");
    }
    
    static boolean tryMake24(List<Double> nums) {
        if(nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) < 1e-9;
        }
        
        for(int i = 0; i < nums.size(); i++) {
            for(int j = i + 1; j < nums.size(); j++) {
                double a = nums.get(i);
                double b = nums.get(j);
                
                List<Double> nextNums = new ArrayList<>();
                for(int k = 0; k < nums.size(); k++) {
                    if(k != i && k != j) {
                        nextNums.add(nums.get(k));
                    }
                }
                
                nextNums.add(a + b);
                if(tryMake24(nextNums)) return true;
                nextNums.remove(nextNums.size() - 1);
                
                nextNums.add(a - b);
                if(tryMake24(nextNums)) return true;
                nextNums.remove(nextNums.size() - 1);
                
                nextNums.add(b - a);
                if(tryMake24(nextNums)) return true;
                nextNums.remove(nextNums.size() - 1);
                
                nextNums.add(a * b);
                if(tryMake24(nextNums)) return true;
                nextNums.remove(nextNums.size() - 1);
                
                if(Math.abs(b) > 1e-9) {
                    nextNums.add(a / b);
                    if(tryMake24(nextNums)) return true;
                    nextNums.remove(nextNums.size() - 1);
                }
                
                if(Math.abs(a) > 1e-9) {
                    nextNums.add(b / a);
                    if(tryMake24(nextNums)) return true;
                    nextNums.remove(nextNums.size() - 1);
                }
            }
        }
        
        return false;
    }
}