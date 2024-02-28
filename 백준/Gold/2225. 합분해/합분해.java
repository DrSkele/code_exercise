import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int K;
	static long dp[][];
	static final long mod = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);

		solve();

		System.out.println(dp[N][K] % mod);
	}

	static void init(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		dp = new long[N + 1][K + 1];
	}

	static void solve() {
		dp[0][0] = 1;
		for (int i = 1; i <= K; i++) {
			dp[0][i] = 1;
		}

		for (int k = 1; k <= K; k++) {
			for (int n = 1; n <= N; n++) {
				for (int m = 0; m <= n; m++) {
					dp[n][k] =( dp[n][k] % mod )+( dp[n - m][k - 1] % mod );
				}
			}
		}

	}
}
