import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder string = new StringBuilder();
        
        Stack<Integer> stack = new Stack<>();
        
        int cnt = sc.nextInt();
        
        for(int i = 0; i < cnt; i++){
            String cmd = sc.next();
            switch(cmd){
                case "push" :
                    int element = sc.nextInt();
                    stack.push(element);
                    break;
                case "pop" :
                    string.append(stack.isEmpty()? -1 : stack.pop());
                    string.append("\n");
                    break;
                case "size" :
                    string.append(stack.size());
                    string.append("\n");
                    break;
                case "empty" :
                    string.append(stack.isEmpty()? 1 : 0);
                    string.append("\n");
                    break;
                case "top" :
                    string.append(stack.isEmpty()? -1 : stack.peek());
                    string.append("\n");
                    break;
            }
        }
        System.out.print(string.toString());
    }
}