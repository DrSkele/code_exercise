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
	
	static int num;
	static Node root;
	static void input(BufferedReader in) throws IOException {
		num = Integer.parseInt(in.readLine());
		root = new Node();
		
		for(int i= 0; i < num; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			
			int size = Integer.parseInt(tokens.nextToken());
			String[] arr = new String[size];
			for(int j = 0; j < size; j++) {
				arr[j] = tokens.nextToken();
			}
			
			root.insert(arr);
		}
	}
	
	
	static void solve() {
		StringBuilder str = new StringBuilder();
		root.print(str, 0);
		System.out.println(str.toString());
	}
	
	static class Node{
		Map<String, Node> branch = new HashMap<>();
		
		public Node() {};
		
		public void insert(String[] strings) {
			Node node = this;
			
			for(int i = 0; i < strings.length; i++) {
				String cur = strings[i];
				node.branch.putIfAbsent(cur, new Node());
				node = node.branch.get(cur);
			}
		}
		
		public void print(StringBuilder str, int depth) {
			
			ArrayList<Entry<String, Node>> list = new ArrayList<>(branch.entrySet());
			
			Collections.sort(list, new Comparator<Entry<String, Node>>(){
				@Override
				public int compare(Entry<String, Node> o1, Entry<String, Node> o2) {
					return o1.getKey().compareTo(o2.getKey());
				}
			});
			
			for(Entry<String, Node> entry : list) {
				for(int i = 0; i < depth; i++) {
					str.append("--");
				}
				str.append(entry.getKey()).append("\n");
				entry.getValue().print(str, depth+1);
			}
		}
	}
}