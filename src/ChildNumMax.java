/**
 * 题目:
 * 输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)
 * 解法: https://blog.csdn.net/DERRANTCM/article/details/46736967
 */
public class ChildNumMax {
//    private static int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
    private static int[] data = {-2, -8, -1, -5, -9};

    public static void main(String[] args) {
        if (data.length <= 0)
            throw new IllegalArgumentException("data length can not less than 0");

        int curMax = data[0];
        int lastMax = data[0];
        for (int i = 0; i < data.length; i++) {
            curMax += data[i];
            if (curMax > lastMax)
                lastMax = curMax;
            else if (curMax <= 0)
                curMax = 0;
        }
        System.out.println("max: " + lastMax);
    }
}
