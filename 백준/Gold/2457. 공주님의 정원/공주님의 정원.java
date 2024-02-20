import java.io.*;
import java.util.*;

public class Main {
	
	static int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		
		int [][] flower = new int[N][2];
		int totalDays = 0;
		for(int day : days) {
			totalDays += day;
		}
		
		int start = 1;
		int end = 30;
		for(int month = 0;  month < 2; month++) {
			start += days[month];
		}
		
		for(int month = 0; month < 10; month++) {
			end += days[month];
		}
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(in.readLine());
			int bloomMonth = Integer.parseInt(tokens.nextToken());
			int bloomDay = Integer.parseInt(tokens.nextToken());
			
			int bloomDate = 0;
			for(int m = 0; m < bloomMonth-1; m++) {
				bloomDate += days[m];
			}
			bloomDate += bloomDay;
			
			int witherMonth = Integer.parseInt(tokens.nextToken());
			int witherDay = Integer.parseInt(tokens.nextToken());
			
			int witherDate = 0;
			for(int m = 0; m < witherMonth-1; m++) {
				witherDate += days[m];
			}
			witherDate += witherDay;
			
			flower[i][0] = bloomDate;
			flower[i][1] = witherDate;
			// length = witherDate - bloomDate + 1;
		}
		
		Arrays.sort(flower, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int result = 0;
				if(o1[0] < o2[0]) result = -1;
				else if(o1[0] > o2[0]) result = 1;
				return result;
			}
		});
		
//		System.out.println(start + ", " + end);
//		for(int[] date : flower) {
//			System.out.println(date[0] + ", " + date[1]);
//		}
		
		int cnt = 0;
		int witherDate = start;
		int tempDate = 0;
		int idx = 0;
		if(flower[0][0] <= start) {			
			while(witherDate <= end) {
				if(idx < N) {				
					int[] curFlower = flower[idx];
					if(curFlower[0] <= witherDate) {
						idx++;
						if(witherDate < curFlower[1]) {
							tempDate = Math.max(tempDate, curFlower[1]);
						}
					} else {
						cnt++;
						witherDate = tempDate;
						if(witherDate < curFlower[0]) break;
					}
				} else {
					cnt++;
					witherDate = tempDate;
					break;
				}
			}
		}
		if(witherDate <= end) cnt = 0;
		
		System.out.println(cnt);
		
	}
}




