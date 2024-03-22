import java.util.*;

class Solution {
    static final String enter = "님이 들어왔습니다.";
    static final String exit = "님이 나갔습니다.";
    public String[] solution(String[] record) {
        
        Map<String, String> map = new HashMap<>();
        ArrayList<Text> list = new ArrayList<>();
        
        for(int i = 0; i < record.length; i++){
            StringTokenizer tokens = new StringTokenizer(record[i]);
            String cmd = tokens.nextToken();
            String uid = tokens.nextToken();
            String name = "";
            switch(cmd){
                case "Enter" : 
                    list.add(new Text(uid, enter));
                    name = tokens.nextToken();
                    map.put(uid, name);
                    break;
                case "Leave" :
                    list.add(new Text(uid, exit));
                    break;
                default :
                    name = tokens.nextToken();
                    map.put(uid, name);
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i = 0; i < answer.length; i++){
            Text txt = list.get(i);
            answer[i] = map.get(txt.uid)+txt.msg;
        }
        
        return answer;
    }
    
    static class Text{
        String uid;
        String msg;
        Text(String uid, String msg){
            this.uid = uid;
            this.msg = msg;
        }
    }
}