package search;

/**
 * 二分搜索:
 * 不允许key为null, 存放key相同的时候视为更新
 * 支持自动扩容
 */
public class BinarySearch<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N = 0;

    public BinarySearch(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("key = null");
        if (N <= 0) {
            keys[0] = key;
            values[0] = value;
            N++;
            return;
        }
        int mid = rank(key);
        if (key.compareTo(keys[mid]) == 0) {
            values[mid] = value;
            return;
        }
        // 检查扩容
        if (N >= keys.length) {
            resize();
        }
        for (int i = N - 1; i >= mid; i--) {
            keys[i + 1] = keys[i];
            values[i + 1] = values[i];
        }
        keys[mid] = key;
        values[mid] = value;
        N++;
    }

    private void resize() {
        Key[] resizeKeys = (Key[]) new Comparable[keys.length * 2];
        Value[] resizeValues = (Value[]) new Object[values.length * 2];
        System.arraycopy(keys, 0, resizeKeys, 0, keys.length);
        System.arraycopy(values, 0, resizeValues, 0, values.length);
        keys = resizeKeys;
        values = resizeValues;
    }

    private int rank(Key key) {
        int low = 0;
        int high = N - 1;
        int mid = (low + high) / 2;
        while (low <= high) {
            if (key.compareTo(keys[mid]) < 0) {
                low = mid + 1;
                mid = (low + high) / 2;
            } else {
                high = mid - 1;
                mid = (low + high) / 2;
            }
        }
        return N > 0 && key.compareTo(keys[mid]) == 0 ? mid : low;
    }

    public Value get(Key key) {
        int mid = rank(key);
        if (key.compareTo(keys[mid]) != 0)
            return null;
        else
            return values[mid];
    }
}
