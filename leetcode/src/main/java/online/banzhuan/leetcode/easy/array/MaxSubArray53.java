package online.banzhuan.leetcode.easy.array;

import lombok.extern.slf4j.Slf4j;

/**
 * 53. 最大子序和
 *
 * @author haisenbao
 * @date 2020/7/3
 */
@Slf4j
public class MaxSubArray53 {

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶: 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("无有效元素！");
        }

        int startIndex = 0;
        int endIndex = 0;
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum > maxSum) {
                maxSum = sum;
                startIndex = i;
                endIndex = i;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }
        log.info("startIndex={}, endIndex={}", startIndex, endIndex);
        return maxSum;
    }

    public static void main(String[] args) {
        int maxSum = new MaxSubArray53().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        log.info("maxSum={}", maxSum);
    }
}
