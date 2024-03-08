import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(arr);
        for(int i = 0; i < N; i++){
            str.append(arr[i]).append("\n");
        }
        System.out.println(str.toString());
    }
}