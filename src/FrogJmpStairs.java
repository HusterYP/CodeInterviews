/**
 * 题目:
 * <<剑指Offer>> 面试题 P77(77页): 青蛙跳台阶
 * 一次可以跳一级, 也可以跳两级 , 求该青蛙跳上一个n级台阶共有多少种跳发
 * 还是Fibonacci数列: F(n) = F(n-1) + F(n-2)
 * 解法参加Fibonacci数列求第N项, 区别在于考虑n的实际取值
 */
public class FrogJmpStairs {
    public int wayToJmpNStairs(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n < 0");
        int result[] = {0, 1};
        if (n == 1)
            return result[n];
        int fibMinOne = result[0];
        int fibMinTwo = result[1];
        int fibN = 0;
        for (int i = 2; i <= n; i++) {
            fibN = fibMinOne + fibMinTwo;
            fibMinOne = fibMinTwo;
            fibMinTwo = fibN;
        }
        return fibN;
    }

    public static void main(String[] args) {
        System.out.println(new FrogJmpStairs().wayToJmpNStairs(10));
    }
}
