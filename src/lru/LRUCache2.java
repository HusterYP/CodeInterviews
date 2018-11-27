package lru;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存的实现: 基于HashMap
 * https://blog.csdn.net/wangxilong1991/article/details/70172302
 * 注意:
 * 1. 加锁
 * 2. 相当于自己实现一个按照访问顺序排序的LinkedHashMap
 */
public class LRUCache2<K, V> {
    private int maxCapacity = 8;
    private CacheNode firstNode = null;
    private CacheNode lastNode = null;
    private Map<K, CacheNode> map = new HashMap<>();

    public synchronized V get(K key) {
        CacheNode node = map.get(key);
        if (node == null)
            return null;
        moveToFirst(node);
        return node.value;
    }

    public synchronized void put(K key, V value) {
        CacheNode node = map.get(key);
        if (node == null) {
            node = new CacheNode();
            node.key = key;
            node.value = value;
            checkRemove();
            map.put(key, node);
            moveToFirst(node);
            return;
        }
        if (node.value.equals(value)) {
            moveToFirst(node);
            return;
        }
        checkRemove();
        CacheNode hashNode = new CacheNode();
        hashNode.key = key;
        hashNode.value = value;
        map.put(key, hashNode);
        moveToFirst(hashNode);
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized Collection<Map.Entry<K, CacheNode>> getAll() {
        return new ArrayList<>(map.entrySet());
    }

    private void checkRemove() {
        if (map.size() >= maxCapacity) {
            map.remove(lastNode.key);
            removeLast();
        }
    }

    private void moveToFirst(CacheNode node) {
        System.out.println("MoveToFirst: " + node);
        if (firstNode == null && lastNode == null) {
            node.next = null;
            node.pre = null;
            firstNode = node;
            lastNode = node;
            return;
        }
        CacheNode old = firstNode;
        firstNode = node;
        firstNode.next = old;
        firstNode.pre = null;
        old.pre = firstNode;
    }

    private void removeLast() {
        CacheNode node = lastNode;
        lastNode = node.pre;
        lastNode.next = null;
        System.out.println("RemoveLast: " + lastNode);
    }

    class CacheNode {
        CacheNode pre;
        CacheNode next;
        K key;
        V value;

        @Override
        public String toString() {
            return "CacheNode{" +
                    " key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        LRUCache2<Integer, String> cache2 = new LRUCache2<>();
        for (int i = 0; i < 24; i++) {
            cache2.put(i, "String " + i);
        }

        System.out.println("========================");
        System.out.println(cache2.getAll());
    }
}
