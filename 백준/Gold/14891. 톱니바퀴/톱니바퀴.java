import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	init(in);
    	solve(in);    		
    }
    
    static Gear[] gears;
    static int cnt;
    static void init(BufferedReader in) throws IOException {
    	gears = new Gear[4];
    	for(int i = 0; i < gears.length; i++) {
    		char[] teeth = in.readLine().toCharArray();
    		gears[i] = new Gear(new int[teeth.length]);
    		for(int j = 0; j < teeth.length; j++) {
    			gears[i].teeth[j] = Character.getNumericValue(teeth[j]);
    		}
    	}
    	cnt = Integer.parseInt(in.readLine());
    }

    static void solve(BufferedReader in) throws IOException {
    	
    	
    	
    	for(int i = 0; i < cnt; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		int idx = Integer.parseInt(tokens.nextToken())-1;
    		int dir = Integer.parseInt(tokens.nextToken());
    		
    		int[] rotation = new int[4];
    		rotation[idx] = dir;
    		
    		int leftDir = -dir;
    		int left = gears[idx].getLeft();
    		for(int j = idx - 1; j >= 0; j--) {
    			if(gears[j].getRight() != left) {
    				rotation[j] = leftDir;
    				leftDir = -leftDir;
    				left = gears[j].getLeft();
    			} else break;
    		}
    		
    		int rightDir = -dir;
    		int right = gears[idx].getRight();
    		for(int j = idx + 1; j < gears.length; j++) {
    			if(gears[j].getLeft() != right) {
    				rotation[j] = rightDir;
    				rightDir = -rightDir;
    				right = gears[j].getRight();
    			} else break;
    		}
    		
    		for(int j = 0; j < gears.length; j++) {
    			gears[j].turn(rotation[j]);
    		}
    		
//    		for(int j = 0; j < gears.length; j++) {
//    			System.out.print(gears[j].pivot);
//    		}
//    		System.out.println();
    	}
    	
    	int score = 0;
    	for(int i = 0; i < gears.length; i++) {
//    		System.out.println(gears[i].getTop());
    		if(gears[i].getTop() == 1) {
    			score += Math.pow(2, i);
    		}
    	}
    	System.out.println(score);
    }
    
    static class Gear{
    	int[] teeth;
    	int pivot;
    	
    	public Gear(int[] arr) {
    		teeth = arr;
    		pivot = 0;
    	}
    	
    	public void turn(int dir) {
    		if(dir < 0) {
    			pivot = (pivot + 1) % 8;
    		} else if(dir > 0) {
    			pivot = (pivot + 7) % 8;
    		}
    	}
    	
    	public int getTop() {
    		return teeth[pivot];
    	}
    	
    	public int getLeft() {
    		return teeth[(pivot + 6) % 8];
    	}
    	
    	public int getRight() {
    		return teeth[(pivot + 2) % 8];
    	}
    }
}


