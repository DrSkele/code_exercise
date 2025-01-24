import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int holes;
	static int nDevice;
	static int[] devices;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		holes = Integer.parseInt(tokens.nextToken());
		nDevice = Integer.parseInt(tokens.nextToken());
		devices = new int[nDevice];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nDevice; i++) {
			devices[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve() {
		int cnt = 0;
		int[] dist = new int[nDevice+1];
		Set<Integer> pluged = new HashSet<>();
		for(int i = 0; i < nDevice; i++) {
			int device = devices[i];
			if(pluged.contains(device)) continue;
			
			if(pluged.size() < holes) {
				pluged.add(device);
			} else {
				for(int n : pluged) {
					dist[n] = 0;
				}
				for(int j = nDevice-1; j > i; j--) {
					dist[devices[j]] = j-i;
				}
				int replace = 0;
				for(int n : pluged) {
					if(dist[n] == 0) {
						replace = n; break;
					} else if(dist[n] > dist[replace]) {
						replace = n;
					}
				}
				pluged.remove(replace);
				pluged.add(device);
				//System.out.println(replace);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}