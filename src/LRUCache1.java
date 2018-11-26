import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LRU缓存的实现: 基于LinkedHashMap
 * https://blog.csdn.net/wangxilong1991/article/details/70172302
 * 注意:
 * 1. 重写removeEldestEntry()
 * 2. 加锁
 */
public class LRUCache1<K, V> extends LinkedHashMap<K, V> {
    private int maxCapacity = 8;
    private static final float loadFactor = 0.75f;
    private final Lock lock = new ReentrantLock();

    public LRUCache1(int capacity) {
        super(capacity, loadFactor, true);
        this.maxCapacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }

    @Override
    public V get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            return super.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsValue(Object value) {
        try {
            lock.lock();
            return super.containsValue(value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.lock();
            return super.size();
        } finally {
            lock.unlock();
        }
    }

    public Collection<Map.Entry<K, V>> getAll() {
        try {
            lock.lock();
            return new ArrayList<>(super.entrySet());
        } finally {
            lock.unlock();
        }
    }
}
