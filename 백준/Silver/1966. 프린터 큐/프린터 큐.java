import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int testCase = Integer.parseInt(tokens.nextToken());
        for(int t = 0; t < testCase; t++){
            tokens = new StringTokenizer(in.readLine());
            int length = Integer.parseInt(tokens.nextToken());
            int idx = Integer.parseInt(tokens.nextToken());
            int[] archive = new int[10];
            
            Queue<Doc> docs = new LinkedList<>();
            
            tokens = new StringTokenizer(in.readLine());
            for(int i = 0; i < length; i++){
                int priority = Integer.parseInt(tokens.nextToken());
                Doc doc = new Doc(priority, i == idx);
                docs.add(doc);
                archive[priority]++;
            }
            
            int cnt = 0;
            while(!docs.isEmpty()){
                Doc curDoc = docs.poll();
                boolean hasHigher = false;
                for(int i = 9; i > 0; i--){
                    if(i > curDoc.priority && archive[i] > 0){
                        hasHigher = true;
                        break;
                    }
                }
                if(hasHigher){
                    docs.add(curDoc);
                } else {
                    archive[curDoc.priority]--;
                    cnt++;
                    if(curDoc.marked){
                        System.out.println(cnt);
                        break;
                    }
                }
            }
        }
    }
}

class Doc{
    int priority;
    boolean marked;
    
    public Doc(int value, boolean marked){
        this.priority = value;
        this.marked = marked;
    }
}