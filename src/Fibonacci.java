/**
 * 题目:
 * <<剑指Offer>> 面试题 10: 求Fibonacci数列的第n项
 */
public class Fibonacci {
    public int getNFibonacci(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        return getNFibonacci(n - 1) + getNFibonacci(n - 2);
    }

    public int getNFibonacciImp(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n < 0");
        int result[] = {0, 1};
        if (n <= 1)
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
        Fibonacci fibonacci = new Fibonacci();
        long time = System.currentTimeMillis();
        System.out.println(fibonacci.getNFibonacci(40));
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        System.out.println(fibonacci.getNFibonacciImp(40));
        System.out.println(System.currentTimeMillis() - time);
    }
}
