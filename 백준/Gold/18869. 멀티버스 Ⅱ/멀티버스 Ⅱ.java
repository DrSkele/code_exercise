import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve();
    }
    
    static int nUniverse;
    static int nPlanets;
    static int[][] compressedSizes;
    static List<Integer>[] uniqueSizes;
    
    static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        nUniverse = Integer.parseInt(tokens.nextToken());
        nPlanets = Integer.parseInt(tokens.nextToken());
        
        compressedSizes = new int[nUniverse][nPlanets];
        uniqueSizes = new ArrayList[nUniverse];
        
        for (int i = 0; i < nUniverse; i++) {
            tokens = new StringTokenizer(in.readLine());
            Set<Integer> uniqueSet = new HashSet<>();
            
            // 각 행성의 크기를 저장하고 유니크한 값을 Set에 추가
            for (int j = 0; j < nPlanets; j++) {
                int size = Integer.parseInt(tokens.nextToken());
                compressedSizes[i][j] = size;
                uniqueSet.add(size);
            }
            
            // Set을 정렬된 List로 변환
            uniqueSizes[i] = new ArrayList<>(uniqueSet);
            Collections.sort(uniqueSizes[i]);
            
            // 좌표 압축 적용
            for (int j = 0; j < nPlanets; j++) {
                compressedSizes[i][j] = Collections.binarySearch(uniqueSizes[i], compressedSizes[i][j]);
            }
        }
    }
    
    static void solve() {
        int count = 0;
        
        // 모든 우주 쌍에 대해 비교
        for (int i = 0; i < nUniverse; i++) {
            for (int j = i + 1; j < nUniverse; j++) {
                if (Arrays.equals(compressedSizes[i], compressedSizes[j])) {
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
}