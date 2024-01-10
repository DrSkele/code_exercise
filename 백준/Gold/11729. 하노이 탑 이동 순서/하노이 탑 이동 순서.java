import java.io.*;
import java.util.*;

class Main{
    static int cnt = 0;
    static StringBuilder answer;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        answer = new StringBuilder();
        hanoi(N, 1, 2, 3);
        System.out.println(cnt);
        System.out.print(answer.toString());
    }
    
    static void hanoi(int n, int from, int middle, int to){
        cnt++;
        if(n == 1){
            answer.append(from);
            answer.append(" ");
            answer.append(to);
            answer.append("\n");
        }
        else{
            hanoi(n-1, from, to, middle);
            answer.append(from);
            answer.append(" ");
            answer.append(to);
            answer.append("\n");
            hanoi(n-1, middle, from, to);
        }
    }
}