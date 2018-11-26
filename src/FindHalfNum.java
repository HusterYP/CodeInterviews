/**
 * 数组中出现次数超过一半的数字
 * https://wizardforcel.gitbooks.io/the-art-of-programming-by-july/content/04.03.html
 */
public class FindHalfNum {

    public int findHalfNum(int[] data) {
        int candidate = data[0];
        int count = 1;
        for (int i = 1; i < data.length; i++) {
            if (candidate != data[i]) {
                if (--count <= 0) {
                    candidate = data[i];
                    count = 0;
                }
            } else count++;
        }
        return candidate;
    }

    public static void main(String[] args) {
        int data[] = {1, 1, 3, 4, 5, 1, 1, 1, 2, 2, 1, 3, 1, 1};
        FindHalfNum halfNum = new FindHalfNum();
        System.out.println(halfNum.findHalfNum(data));
    }
}
