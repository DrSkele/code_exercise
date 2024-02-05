import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(in.readLine());
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < K; i++){
            int input = Integer.parseInt(in.readLine());
            if(input == 0) stack.pop();
            else stack.push(input);
        }
        
        int sum = 0;
        while(!stack.isEmpty()){
            sum += stack.pop();
        }
        System.out.println(sum);
    }
}