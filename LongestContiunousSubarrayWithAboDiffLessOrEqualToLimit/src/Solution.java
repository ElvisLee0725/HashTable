// Given an array of integers nums and an integer limit, return the size of the longest
// non-empty subarray such that the absolute difference between any two elements of this
// subarray is less than or equal to limit.

import java.util.TreeMap;

// Sliding Window + TreeMap
// Use i and j pointing at the range of sliding window
// Create a TreeMap <Value, Frequency of that Value>
// While j is within the input array range, add nums[j] to the TreeMap
// Check if the max and min value in TreeMap is within limit? If so, update the max subarray length if possible
// Else, reduce the frequency of value at index i and move index i by 1
// Time: O(n), Space: O(n)
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().longestSubarray(new int[]{10,1,2,4,7,2}, 5));
    }
    public int longestSubarray(int[] nums, int limit) {
        int i = 0;
        int j = 0;
        TreeMap<Integer, Integer> map = new TreeMap();
        int max = 0;
        while(j < nums.length) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            if(map.lastKey() - map.firstKey() <= limit) {
                max = Math.max(max, j - i + 1);
            }
            else {
                if(map.get(nums[i]) > 1) {
                    map.put(nums[i], map.get(nums[i]) - 1);
                }
                else {
                    map.remove(nums[i]);
                }
                i++;
            }
            j++;
        }
        return max;
    }
}
