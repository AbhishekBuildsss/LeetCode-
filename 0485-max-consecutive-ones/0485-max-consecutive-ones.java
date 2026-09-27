class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max_count=0;
        int current_count=0;
        int n=nums.length;

        for(int j=0;j<n;j++){
            if(nums[j]==1){
                current_count++;

            }
            else{
                max_count=Math.max(max_count,current_count);
                current_count=0;
            }
        }
        return Math.max(max_count,current_count);
        
    }
}