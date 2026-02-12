import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int length = Integer.parseInt(in.readLine());
        int[] sums = new int[length];
        
        for(int i = 0; i < length; i++) {
            sums[i] = Integer.parseInt(in.readLine());
        }
        // input finished
        
        int candy = sums[0];
        for(int i = 1; i < length - 1; i++) {
            if(i % 2 == 1) candy -= sums[i];
            else candy += sums[i];
        }
        candy = (candy + sums[length - 1]) / 2;
        
        StringBuilder str = new StringBuilder();
        str.append(candy).append("\n");
        
        for(int i = 0; i < length - 1; i++) {
            candy = sums[i] - candy;
            str.append(candy).append("\n");
        }
        
        System.out.println(str.toString());
    }
}