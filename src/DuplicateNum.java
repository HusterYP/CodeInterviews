/**
 * 题目:
 * <<剑指Offer>> 面试题3: 数组中的重复数字
 * 在一个长度为n的数组中, 所有数字都在0 ~ n-1之间, 数组中有些数字是重复的
 * 找出数组中任意一个重复数字
 * 如: {2, 3, 1, 0, 2, 5, 3} 输出: 2 或 3
 */
public class DuplicateNum {
    public static int getDuplicateNum(int data[]) {
        if (data == null || data.length <= 0)
            throw new IllegalArgumentException("length of data can not less than 0");
        for (int i = 0; i < data.length; i++) {
            if (data[i] != i) {
                if (data[data[i]] != data[i]) {
                    int temp = data[i];
                    data[i] = data[temp];
                    data[temp] = temp;
                    i--;
                } else {
                    return data[data[i]];
                }
            }
        }
        return data.length;
    }

    public static void main(String[] args) {
        int data[] = {2, 3, 1, 0, 2, 5, 3};
        int duplicate = DuplicateNum.getDuplicateNum(data);
        System.out.println(duplicate);
    }
}