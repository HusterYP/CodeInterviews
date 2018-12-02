/**
 * 题目:
 * <<剑指Offer>> 面试题 12: 矩阵中的路径
 */
public class PathInMatrix {
    public boolean isPathExistInMatrix(char matrix[], int row, int col, String target) {
        if (target == null || matrix == null || row <= 0 || col <= 0)
            return false;
        char[] arr = target.toCharArray();
        boolean isVisit[] = new boolean[row * col];
        for (int i = 0; i < isVisit.length; i++)
            isVisit[i] = false;
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isPathExistCore(matrix, row, col, i, j, arr, index, isVisit))
                    return true;
            }
        }
        return false;
    }

    public boolean isPathExistCore(char matrix[], int row, int col, int curRow, int curCol,
                                   char target[], int index, boolean isVisit[]) {
        if (index >= target.length)
            return true;
        boolean pathExist = false;
        if (curRow >= 0 && curRow < row && curCol >= 0 && curCol < col && matrix[curRow * col + curCol] == target[index]
                && !isVisit[curRow * col + curCol]) {
            isVisit[curRow * col + curCol] = true;
            pathExist = isPathExistCore(matrix, row, col, curRow + 1, curCol, target, index + 1, isVisit)
                    || isPathExistCore(matrix, row, col, curRow - 1, curCol, target, index + 1, isVisit)
                    || isPathExistCore(matrix, row, col, curRow, curCol + 1, target, index + 1, isVisit)
                    || isPathExistCore(matrix, row, col, curRow, curCol - 1, target, index + 1, isVisit);
            if (!pathExist)
                isVisit[curRow * col + curCol] = false;
        }
        return pathExist;
    }

    public static void main(String[] args) {
        char matrix[] = {'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e', 'h'};
        String target = "bfce";
        System.out.println(new PathInMatrix().isPathExistInMatrix(matrix, 3, 4, target));
    }
}
