import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int length;
    static int variant;
    static int streak;
    static int coupon;
    static int[] belt;
    public static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        length = Integer.parseInt(tokens.nextToken());
        variant = Integer.parseInt(tokens.nextToken());
        streak = Integer.parseInt(tokens.nextToken());
        coupon = Integer.parseInt(tokens.nextToken());
        
        belt = new int[length*2];
        for(int i = 0; i < length; i++) {
            int dish = Integer.parseInt(in.readLine());
            belt[i] = dish;
            belt[length+i] = dish;
        }
    }
    
    static int[] eaten;
    static int num;
    public static void solve() {
        int max = 0;
        eaten = new int[variant+1];
        num = 0;
        
        int tail = 0;
        int head = 1;
        
        addDish(tail);
        addDish(head);
        
        while(tail < length) {
            max = Math.max(max, maxDish());
            int next = head + 1;
            
            // head out of bound
            if(next >= length * 2) {
                removeDish(tail);
                tail++;
                continue;
            }
            
            int curStreak = head - tail + 1;
            
            // streak in range
            if(curStreak < streak) {
                head = next;
                addDish(head);
            } else {
                // able to use coupon
                if(belt[tail] == coupon && curStreak < streak + 1) {
                    head = next;
                    addDish(head);
                } else {
                    // max streak reached
                    removeDish(tail);
                    tail++;
                }
            }
        }
        
        System.out.println(max);
    }
    
    static int maxDish() {
        if(eaten[coupon] > 0) return num;
        else return num + 1;
    }
    
    static void addDish(int idx) {
        eaten[belt[idx]]++;
        if(eaten[belt[idx]] == 1) {
          num++;
        }
    }
    
    static void removeDish(int idx) {
        eaten[belt[idx]]--;
        if(eaten[belt[idx]] == 0) {
          num--;
        }
    }
}