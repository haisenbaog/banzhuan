package online.banzhuan.leetcode.self;

import lombok.extern.slf4j.Slf4j;

/**
 * @author haisenbao
 * @date 2020/7/3
 */
@Slf4j
public class GuiBingPaiXu {

    public int[] sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        if (nums.length > 2) {
            int[] sub1 = new int[nums.length / 2];
            int[] sub2 = new int[nums.length - sub1.length];
            System.arraycopy(nums, 0, sub1, 0, sub1.length);
            System.arraycopy(nums, sub1.length, sub2, 0, sub2.length);
            sub1 = sort(sub1);
            sub2 = sort(sub2);

            int index1 = 0;
            int index2 = 0;
            for (int i = 0; i < nums.length; i++) {
                if (index1 < sub1.length && index2 < sub2.length) {
                    nums[i] = sub1[index1] < sub2[index2] ? sub1[index1++] : sub2[index2++];
                } else if (index1 < sub1.length && index2 == sub2.length) {
                    nums[i] = sub1[index1++];
                } else if (index1 == sub1.length && index2 < sub2.length) {
                    nums[i] = sub2[index2++];
                }
            }
        }

        if (nums.length == 2 && nums[0] > nums[1]) {
            int temp = nums[0];
            nums[0] = nums[1];
            nums[1] = temp;
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {8, 4, 5, 7, 1, 3, 6, 2, 12, 15, 6};
        log.info(nums.toString());

        int[] result = new GuiBingPaiXu().sort(nums);
        log.info(result.toString());

        log.info("result={}", result);
    }
}
