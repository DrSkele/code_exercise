import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        int[] arr = new int[n];
        tokens = new StringTokenizer(in.readLine());

        int globalMin = Integer.MAX_VALUE;
        int globalMax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
            globalMin = Math.min(globalMin, arr[i]);
            globalMax = Math.max(globalMax, arr[i]);
        }

        int left = 0;
        int right = globalMax - globalMin;
        int ans = findMin(arr, left, right, m);

        System.out.println(ans);
    }

    static int findMin(int[] arr, int left, int right, int maxSegments) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (evaluate(arr, mid, maxSegments)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    static boolean evaluate(int[] arr, int goal, int maxSegments) {
        int segments = 1;

        int curMin = arr[0];
        int curMax = arr[0];

        for (int i = 1; i < arr.length; i++) {
            curMin = Math.min(curMin, arr[i]);
            curMax = Math.max(curMax, arr[i]);

            if (curMax - curMin > goal) {
                segments++;
                curMin = arr[i];
                curMax = arr[i];

                if (segments > maxSegments) return false;
            }
        }

        return true;
    }
}