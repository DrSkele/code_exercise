class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] nums = new int[n];
        // for(int i = 0; i < n; i++){
        //     nums[i] = 1;
        // }
        for(int i = 0; i < lost.length; i++){
            nums[lost[i]-1]--;
        }
        for(int i = 0; i < reserve.length; i++){
            nums[reserve[i]-1]++;
        }
        for(int i = 0; i < n; i++){
            if(nums[i] >= 0) {
                answer++;
            } else if(i > 0 && nums[i-1] > 0) {
                answer++;
                nums[i-1]--;
            } else if(i < n-1 && nums[i+1] > 0){
                answer++;
                nums[i+1]--;
            }
        }
        
        return answer;
    }
}