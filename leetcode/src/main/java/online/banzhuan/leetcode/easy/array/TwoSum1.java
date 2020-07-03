package online.banzhuan.leetcode.easy.array;

import lombok.extern.slf4j.Slf4j;

/**
 * 1. 两数之和
 *
 * @author haisenbao
 * @date 2020/7/3
 */
@Slf4j
public class TwoSum1 {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            log.warn("输入数组不合法！");
            return new int[0];
        }

        int maxIndex = nums.length - 1;
        for (int i = 0; i < maxIndex; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        log.info("没找到");
        return new int[0];
    }

    public static void main(String[] args) {
        int[] result = new TwoSum1().twoSum(new int[]{1, 3, 5, 7, 5, 8}, 19);
        log.info("result:{}", result);
    }

}
