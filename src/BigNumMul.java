import java.util.Arrays;

/**
 * 题目:
 * 大数相乘
 * https://blog.csdn.net/chhuach2005/article/details/21168179
 */
public class BigNumMul {
    public String bigNumMul(String factor1, String factor2) {
        if (factor1 == null || factor2 == null)
            throw new NullPointerException();
        char fac1[] = factor1.toCharArray();
        char fac2[] = factor2.toCharArray();
        int facOne[] = new int[fac1.length];
        int facTwo[] = new int[fac2.length];
        int result[] = new int[fac1.length + fac2.length];
        for (int i = 0; i < fac1.length; i++) {
            facOne[fac1.length - i - 1] = charToInt(fac1[i]); // 记住先进行reverse
        }
        for (int i = 0; i < fac2.length; i++) {
            facTwo[fac2.length - i - 1] = charToInt(fac2[i]);
        }
        for (int i = 0; i < result.length; i++)
            result[i] = 0;
        bigNumMulCore(facOne, facTwo, result);
        // 得到的result也需要reserve, 并去除高位的0
        int count = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] == 0)
                count++;
            else break;
        }
        char resultStr[] = new char[result.length - count];
        for (int i = resultStr.length - 1; i >= 0; i--) {
            resultStr[i] = (char) (result[resultStr.length - i - 1] + '0');
        }
        return String.valueOf(resultStr);
    }

    private void bigNumMulCore(int fac1[], int fac2[], int result[]) {
        int index = 0;
        int oldIndex = 0;
        for (int i = 0; i < fac1.length; i++) {
            oldIndex = index;
            for (int j = 0; j < fac2.length; j++) {
                result[index] += fac1[i] * fac2[j]; // 先不处理进位
                index++;
            }
            index = oldIndex + 1;
        }
        // 处理进位
        int carry = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] += carry;
            carry = result[i] / 10;
            result[i] %= 10;
        }
    }

    private int charToInt(char target) {
        if (target > '9' || target < '0')
            throw new IllegalArgumentException();
        return target - '0';
    }

    public static void main(String[] args) {
        String fac1 = "1234567";
        String fac2 = "987654321";
        System.out.println(new BigNumMul().bigNumMul(fac1, fac2));
    }
}
