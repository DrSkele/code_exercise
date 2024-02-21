import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		int tab = Integer.parseInt(tokens.nextToken());
		int device = Integer.parseInt(tokens.nextToken());
		
		HashSet<Integer> pluged = new HashSet<>();
		LinkedList<Device> plugedUntil = new LinkedList<>();
		
		Device[] own = new Device[device+1];
		
		int[] order = new int[device];
		tokens = new StringTokenizer(in.readLine());
		
		for(int i = 0; i < device; i++) {
			int curDevice = Integer.parseInt(tokens.nextToken());
			order[i] = curDevice;
			if(own[curDevice] == null) {
				own[curDevice] = new Device(curDevice);
			}
			own[curDevice].use.add(i);
		}
		
		int cnt = 0;
		for(int i = 0; i < device; i++) {
			Device curDevice = own[order[i]];
			curDevice.use.poll(); //다음 사용기간으로 갱신
			if(!pluged.contains(curDevice.serialNum)) { //꽂혀있지 않다면
				if(pluged.size() >= tab) { //탭이 포화상태라면
					Collections.sort(plugedUntil);
					Device toRemove = plugedUntil.getFirst();
					pluged.remove(toRemove.serialNum);
					plugedUntil.remove(toRemove);
					cnt++;
				}
				pluged.add(curDevice.serialNum);
				plugedUntil.add(curDevice);
			} 
		}
		System.out.println(cnt);
	}
	
	static class Device implements Comparable<Device>{
		int serialNum;
		Queue<Integer> use = new LinkedList<>();
		
		public Device(int serial) {
			this.serialNum = serial;
		}

		@Override
		public int compareTo(Device d) {
			int result = 0;
			int thisUntil = this.use.isEmpty()? Integer.MAX_VALUE : this.use.peek();
			int otherUntil = d.use.isEmpty()? Integer.MAX_VALUE : d.use.peek();
			if(thisUntil > otherUntil) result = -1; //다음 사용까지 남은 기간이 제일 긴 것을 먼저
			else if(thisUntil < otherUntil) result = 1;
			return result;
		}
	}
}