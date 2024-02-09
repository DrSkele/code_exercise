import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        
        Stack<Integer> stack = new Stack<>();
        int last = 0;
        int top = 0;
        StringBuilder str = new StringBuilder();
        
        boolean possible = true;
        for(int i = 0; i < n; i++){
            int cur = Integer.parseInt(in.readLine());
            if(cur > top){
                for(int j = last + 1; j <= cur; j++){
                    stack.push(j);
                    str.append('+').append("\n");
                }
                last = Math.max(last, stack.pop());
                str.append('-').append("\n");
                top = stack.isEmpty() ? 0 : stack.peek();
                
            } else if (cur == top){
                stack.pop();
                top = stack.isEmpty() ? 0 : stack.peek();
                str.append('-').append("\n");
            } else {
                possible = false;
                break;
            }
        }
        System.out.print(possible ? str.toString() : "NO");
    }
}