import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int num;
	static int menu;
	static int streak;
	static int coupon;
	static StringTokenizer tokens;
	
	static int[] plates;
	static HashSet<Integer> variety;
	static int[] eaten;
	static int max;
	static void init(BufferedReader in) throws IOException{
		tokens = new StringTokenizer(in.readLine());
		num = Integer.parseInt(tokens.nextToken());
		menu = Integer.parseInt(tokens.nextToken());
		streak = Integer.parseInt(tokens.nextToken());
		coupon = Integer.parseInt(tokens.nextToken());
		max = 0;
		
		plates = new int[num];
		variety = new HashSet<>();
		eaten = new int[menu+1];
		for(int i = 0; i < num; i++) {
			plates[i] = Integer.parseInt(in.readLine());
		}
	}
	
	static void solve() {
		int start = 0;
		int end = streak - 1;
		
		while(start <= end) {
			int topPlate = plates[start];
			int lastPlate = plates[end];
			eaten[topPlate]++;
			eaten[lastPlate]++;
			if(!variety.contains(topPlate)) variety.add(topPlate);
			if(!variety.contains(lastPlate)) variety.add(lastPlate);
			start++;
			end--;
		}
		if(streak % 2 == 1) {
			eaten[plates[end+1]]--;
		}
		
//		for(Object n : variety.toArray()) {
//			System.out.print((int) n);
//		}
//		System.out.println();
		
		max = variety.contains(coupon) ? variety.size() : variety.size() + 1;
		
		for(int i = 0; i < num; i++) {
			int topPlate = plates[i];
			int bottomIdx = i + streak >= num ? (i + streak) - num : i + streak;
			int lastPlate = plates[bottomIdx];
			
			if(topPlate == coupon && eaten[topPlate] == 1) {
				eaten[lastPlate]++;
				if(!variety.contains(lastPlate)) variety.add(lastPlate);
				
				max = Math.max(max, variety.size());
				
				eaten[topPlate]--;
				variety.remove(topPlate);
				
			} else if(lastPlate == coupon && !variety.contains(lastPlate)) {
				eaten[lastPlate]++;
				variety.add(lastPlate);
				
				max = Math.max(max, variety.size());
				
				eaten[topPlate]--;
				if(eaten[topPlate] == 0) variety.remove(topPlate);
			} else {
				eaten[lastPlate]++;
				if(!variety.contains(lastPlate)) variety.add(lastPlate);
				
				eaten[topPlate]--;
				if(eaten[topPlate] == 0) variety.remove(topPlate);
				
				int curSize = variety.contains(coupon) ? variety.size() : variety.size() + 1;
				
				if(curSize > max) max = curSize;
			}
				
		}
		
		System.out.println(max);
	}
	
}


