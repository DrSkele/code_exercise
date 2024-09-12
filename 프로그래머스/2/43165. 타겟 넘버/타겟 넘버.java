class Solution {
    
    static int cnt = 0;
    static int[] arr;
    static int goal;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        arr = numbers;
        goal = target;
        
        dfs(0, 0);
        
        return cnt;
    }
    
    public void dfs(int idx, int val) {
        if(idx >= arr.length) {
            if(val == goal) cnt++;
            return;
        }
        
        dfs(idx+1, val + arr[idx]);
        dfs(idx+1, val - arr[idx]);
    }
}

