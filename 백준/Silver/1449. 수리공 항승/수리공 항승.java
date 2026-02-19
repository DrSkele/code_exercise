import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        int[] locations = new int[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            locations[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(locations);
        
        int tapeCount = 0;
        int currentCoverageRange = 0;

        for (int pos : locations) {
            if (pos > currentCoverageRange) {
                tapeCount++;
                currentCoverageRange = pos + L - 1;
            }
        }
        
        System.out.println(tapeCount);
    }
}