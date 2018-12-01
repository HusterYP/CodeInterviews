/**
 * 题目:
 * <<剑指Offer>> 面试题 11: 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾, 我们称之为数组的旋转;
 * 输入一个递增排序的数组的一个旋转, 输出旋转数组的最小元素;
 * 如: 数组{3, 4, 5, 1, 2}是{1, 2, 3, 4, 5}的一个旋转, 该数组的最小值为1
 */
public class RotateMinNum {
    public int getRotateMinNum(int[] arr) {
        if (arr == null || arr.length <= 0)
            throw new IllegalArgumentException();
        if (arr.length == 1)
            return arr[0];
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;
        while (low < high) {
            if (arr[mid] > arr[mid + 1])
                break;
            else if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else if (arr[mid] < arr[high]) {
                high = mid - 1;
            }
        }
        return arr[mid+1];
    }

    public static void main(String[] args) {
        int arr[] = {3, 4, 5, 1, 2};
        System.out.println(new RotateMinNum().getRotateMinNum(arr));
    }
}
