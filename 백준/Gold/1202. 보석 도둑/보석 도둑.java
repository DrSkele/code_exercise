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
	
	static int nJewel;
	static int nBag;
	static PriorityQueue<Jewel> jewels;
	static int[] bags;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nJewel = Integer.parseInt(tokens.nextToken());
		nBag = Integer.parseInt(tokens.nextToken());
		jewels = new PriorityQueue<>(new Comparator<Jewel>() {
			public int compare(Jewel j1, Jewel j2) {
				if(j1.weight < j2.weight) return -1;
				else if(j1.weight > j2.weight) return 1;
				else return 0;
			}
		});
		for(int i = 0; i < nJewel; i++) {
			tokens = new StringTokenizer(in.readLine());
			int weight = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			jewels.add(new Jewel(weight, value));
		}
		bags = new int[nBag];
		for(int i = 0; i < nBag; i++) {
			bags[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(bags);
	}
	
	static void solve() {
		long sum = 0;
		PriorityQueue<Jewel> temp = new PriorityQueue<>(new Comparator<Jewel>() {
			public int compare(Jewel j1, Jewel j2) {
				if(j1.value > j2.value) return -1;
				else if(j1.value < j2.value) return 1;
				else return 0;
			}
		});
		for(int i = 0; i < nBag; i++) {
			int bag = bags[i];
			
			while(!jewels.isEmpty() && jewels.peek().weight <= bag) {
				Jewel jewel = jewels.poll();
				temp.add(jewel);
			}
			
			if(!temp.isEmpty()) sum += temp.poll().value;
		}
		
		System.out.println(sum);
	}
	
	static class Jewel{
		int weight;
		int value;
		public Jewel(int w, int v) {
			weight = w;
			value = v;
		}
	}
}