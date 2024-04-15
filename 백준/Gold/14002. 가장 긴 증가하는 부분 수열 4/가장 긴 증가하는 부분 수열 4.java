
import java.io.*;
import java.util.*;

// 다익스트라로 먹을 수 있는 가장 가까운 물고기 탐색
// 상단 좌측 우선이므로 dx dy를 상단 좌측부터 시작
// 먹은 갯수 카운트 -> 그거 따라 크기 업데이트


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int[] arr;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve() {
		
		int[] max = new int[N];
		String[] str = new String[N];
		for(int i = 0; i < N; i++) {
			String val = String.valueOf(arr[i]);
			max[i] = 1;
			str[i] = val;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && max[i] < max[j] + 1) {
					max[i] = max[j] + 1;
					str[i] = str[j] + " " + val;
				}
			}
		}
		
		int result = 0;
		int idx = 0;
		for(int i = 0; i < N; i++) {
			if(result < max[i]) {
				result = max[i];
				idx = i;
			}
		}
		
		System.out.println(max[idx]);
		System.out.print(str[idx]);
	}
	
}


