import java.io.*;
import java.util.*;

class Main{
    static int pubCnt;
    static ArrayList<Coord> pubs;
    static ArrayList<Coord> home;
    static Coord[] open;
    static int minVal;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int size = Integer.parseInt(tokens.nextToken());
        pubCnt = Integer.parseInt(tokens.nextToken());
        pubs = new ArrayList<Coord>();
        home = new ArrayList<Coord>();
        open = new Coord[pubCnt];
        minVal = Integer.MAX_VALUE;
        
        int homeId = 0;
        int pubId = 0;
        for(int y = 0; y < size; y++){
            tokens = new StringTokenizer(in.readLine());
            for(int x = 0; x < size; x++){
                String tile = tokens.nextToken();
                if("1".equals(tile)) home.add(new Coord(x, y));
                else if("2".equals(tile)) pubs.add( new Coord(x, y));
            }
        }
        selectPub(0, 0);
        
        System.out.print(minVal);
    }
    static void selectPub(int start, int cnt){
        if(cnt == pubCnt){
            int distSum = 0;
            for(Coord dep : home){
                int dist = Integer.MAX_VALUE;
                for(Coord des : open){
                    dist = Math.min(dist, Math.abs(dep.x-des.x) + Math.abs(dep.y-des.y));
                }
                distSum += dist;
                if(distSum > minVal) break;
            }
            minVal = Math.min(minVal, distSum);
        } else {
            for(int i = start; i < pubs.size(); i++){
                open[cnt] = pubs.get(i);
                selectPub(i+1, cnt+1);
            }
        }
    }
}
class Coord{
    public int x;
    public int y;
    
    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }
}