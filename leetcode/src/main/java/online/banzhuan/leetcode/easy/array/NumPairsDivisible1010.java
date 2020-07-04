package online.banzhuan.leetcode.easy.array;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1010. 总持续时间可被 60 整除的歌曲
 *
 * @author haisenbao
 * @date 2020/7/4 12:25
 */
@Slf4j
public class NumPairsDivisible1010 {

    /**
     * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
     * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
     * <p>
     * 示例 1：
     * 输入：[30,20,150,100,40]
     * 输出：3
     * 解释：这三对的总持续时间可被 60 整数：
     * (time[0] = 30, time[2] = 150): 总持续时间 180
     * (time[1] = 20, time[3] = 100): 总持续时间 120
     * (time[1] = 20, time[4] = 40): 总持续时间 60
     * <p>
     * 示例 2：
     * 输入：[60,60,60]
     * 输出：3
     * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
     */
    public int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length < 2) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < time.length - 1; i++) {
            for (int j = i + 1; j < time.length; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    count++;
                }
            }
        }
        return count;
    }


    public int numPairsDivisibleBy60_2(int[] time) {
        if (time == null || time.length < 2) {
            return 0;
        }

        int divisibleLength = 0;
        int lessThan30Length = 0;
        int notLessThan30Length = 0;
        for (int value : time) {
            if (value % 60 == 0) {
                divisibleLength++;
            } else if (value < 30) {
                lessThan30Length++;
            } else {
                notLessThan30Length++;
            }
        }

        int[] divisible = new int[divisibleLength];
        int[] lessThan30 = new int[lessThan30Length];
        int[] notLessThan30 = new int[notLessThan30Length];
        int divisibleIndex = 0;
        int lessThan30Index = 0;
        int notLessThan30Index = 0;
        for (int value : time) {
            if (value % 60 == 0) {
                divisible[divisibleIndex++] = value;
            } else if (value < 30) {
                lessThan30[lessThan30Index++] = value;
            } else {
                notLessThan30[notLessThan30Index++] = value;
            }
        }


        int count = 0;

        if (divisibleLength == 1) {
            count += 1;
        } else if (divisibleLength >= 2) {
            count += divisibleLength * (divisibleLength - 1) / 2;
        }

        for (int i = 0; i < notLessThan30.length - 1; i++) {
            for (int j = i + 1; j < notLessThan30.length; j++) {
                if ((notLessThan30[i] + notLessThan30[j]) % 60 == 0) {
                    count++;
                }
            }
        }

        for (int i = 0; i < lessThan30Length; i++) {
            for (int j = 0; j < notLessThan30Length; j++) {
                if ((lessThan30[i] + notLessThan30[j]) % 60 == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public int numPairsDivisibleBy60_3(int[] time) {
        Map<Integer, Long> map = Arrays.stream(time).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        List<Integer> keyList = new ArrayList<>(map.keySet());
        int[] keys = new int[keyList.size()];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = keyList.get(i);
        }

        int count = 0;
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                if ((keys[i] + keys[j]) % 60 == 0) {
                    count += map.get(keys[i]) * map.get(keys[j]);
                }
            }
        }
        for (int key : keys) {
            if (key * 2 % 60 == 0) {
                Long c = map.get(key);
                count += c * (c - 1) / 2;
            }
        }
        return count;
    }

    public int numPairsDivisibleBy60_LeetCode(int[] time) {
        int[] mods = new int[60];
        for (int value : time) {
            mods[value % 60]++;
        }

        int count = mods[0] * (mods[0] - 1) / 2;

        for (int i = 1; i < 30; i++) {
            count += mods[i] * mods[60 - i];
        }
        count += mods[30] * (mods[30] - 1) / 2;

        return count;
    }

    public int numPairsDivisibleBy60_LeetCode2(int[] time) {
        int[] bucket = new int[60];
        int cnt = 0;
        for (int t : time) {
            int mod = t % 60;
            int remain = mod == 0 ? 0 : 60 - mod;
            cnt += bucket[remain];
            bucket[mod]++;
        }
        return cnt;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\86150\\IdeaProjects\\banzhuan\\leetcode\\src\\main\\java\\online\\banzhuan\\leetcode\\easy\\array\\times")));
        String[] strings = reader.readLine().split(",");
        List<Integer> collect = Arrays.stream(strings).map(Integer::parseInt).collect(Collectors.toList());

        int[] time = new int[collect.size()];
        for (int i = 0; i < collect.size(); i++) {
            time[i] = collect.get(i);
        }

        long start = System.currentTimeMillis();
        int count = new NumPairsDivisible1010().numPairsDivisibleBy60(time);
        long spendMillis = System.currentTimeMillis() - start;
        log.info("time.length={}, 歌曲对的数量count={}, spendMillis={}", time.length, count, spendMillis);

        start = System.currentTimeMillis();
        count = new NumPairsDivisible1010().numPairsDivisibleBy60_2(time);
        spendMillis = System.currentTimeMillis() - start;
        log.info("time.length={}, 歌曲对的数量count={}, spendMillis={}", time.length, count, spendMillis);

        start = System.currentTimeMillis();
        count = new NumPairsDivisible1010().numPairsDivisibleBy60_3(time);
        spendMillis = System.currentTimeMillis() - start;
        log.info("time.length={}, 歌曲对的数量count={}, spendMillis={}", time.length, count, spendMillis);

        start = System.currentTimeMillis();
        count = new NumPairsDivisible1010().numPairsDivisibleBy60_LeetCode(time);
        spendMillis = System.currentTimeMillis() - start;
        log.info("time.length={}, 歌曲对的数量count={}, spendMillis={}", time.length, count, spendMillis);

        start = System.currentTimeMillis();
        count = new NumPairsDivisible1010().numPairsDivisibleBy60_LeetCode2(time);
        spendMillis = System.currentTimeMillis() - start;
        log.info("time.length={}, 歌曲对的数量count={}, spendMillis={}", time.length, count, spendMillis);
    }
}
