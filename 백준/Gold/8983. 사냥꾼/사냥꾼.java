import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nGun;
	static int nAnimal;
	static int range;
	static int[] guns;
	static Animal[] animals;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		nGun = Integer.parseInt(tokens.nextToken());
		nAnimal = Integer.parseInt(tokens.nextToken());
		range = Integer.parseInt(tokens.nextToken());
		
		guns = new int[nGun];
		animals = new Animal[nAnimal];
		
		
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nGun; i++) {
			guns[i] = Integer.parseInt(tokens.nextToken());
		}
		
		
		for(int i = 0; i < nAnimal; i++) {
			tokens = new StringTokenizer(in.readLine());
			
			animals[i] = new Animal(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		}
	}
	
	static void solve(){
		
		int cnt = 0;
		
		for(int i = 0; i < nAnimal; i++) {
			Animal cur = animals[i];
			
			if(inRange(cur.x, cur.y)) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static boolean inRange(int x, int y) {
		
		int left = 0;
		int right = guns.length-1;
		
		while(left <= right) {
			int mid = left + (right - left)/2;
			
			int gun = guns[mid];
			int dist = Math.abs(x - gun) + y;
			
			if(dist <= range) {
				return true;
			} else {
				if(gun < x) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		
		return false;
	}
	
	static class Animal {
		int x;
		int y;
		public Animal(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}