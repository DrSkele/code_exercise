import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve();
    }

    static int cnt;
    static int[] cables;

    static void input(BufferedReader in) throws IOException {
        cnt = Integer.parseInt(in.readLine());
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        cables = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            cables[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    static void solve() {
        int[] asc = new int[cnt];
        int len = 0;

        for (int i = 0; i < cnt; i++) {
            int cable = cables[i];

            int idx = Arrays.binarySearch(asc, 0, len, cable);
            if (idx < 0) idx = -(idx + 1); // 삽입 위치

            asc[idx] = cable;
            if (idx == len) len++; // 새로운 길이 증가
        }

        System.out.println(cnt - len); // 잘라야 할 전선 수 = 전체 - LIS 길이
    }
}
