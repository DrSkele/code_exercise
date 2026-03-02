import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int M = 2 * N - 2;

        String[] arr = new String[M];
        ArrayList<String> longest = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            arr[i] = br.readLine().trim();
            if (arr[i].length() == N - 1) longest.add(arr[i]);
        }

        String a = longest.get(0);
        String b = longest.get(1);

        String S1 = a + b.charAt(N - 2);
        String res1 = tryBuild(S1, arr, N);
        if (res1 != null) {
            System.out.println(S1);
            System.out.println(res1);
            return;
        }

        String S2 = b + a.charAt(N - 2);
        String res2 = tryBuild(S2, arr, N);
        System.out.println(S2);
        System.out.println(res2);
    }
    
    static String tryBuild(String S, String[] arr, int N) {
        HashMap<String, Integer> prefNeed = new HashMap<>();
        HashMap<String, Integer> suffNeed = new HashMap<>();

        for (int len = 1; len <= N - 1; len++) {
            String p = S.substring(0, len);
            String s = S.substring(N - len);
            prefNeed.put(p, prefNeed.getOrDefault(p, 0) + 1);
            suffNeed.put(s, suffNeed.getOrDefault(s, 0) + 1);
        }

        char[] label = new char[arr.length];

        for (int i = 0; i < arr.length; i++) {
            String t = arr[i];

            int pc = prefNeed.getOrDefault(t, 0);
            if (pc > 0) {
                label[i] = 'P';
                prefNeed.put(t, pc - 1);
                continue;
            }

            int sc = suffNeed.getOrDefault(t, 0);
            if (sc > 0) {
                label[i] = 'S';
                suffNeed.put(t, sc - 1);
                continue;
            }

            // no candidate
            return null;
        }

        return new String(label);
    }
}