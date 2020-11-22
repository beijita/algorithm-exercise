package com.fanzs.lru;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache2<K, V> {

    static class Node<K, V> {
        public K key;
        public V value;
        public Node prev, next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    static class DoubleList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public DoubleList() {
            head = null;
            tail = null;
        }

        public void addNode(Node<K, V> newNode) {
            if (head == null) {
                head = newNode;
                tail = newNode;
                return;
            }
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail = newNode;
        }

        public Node<K, V> removeHead() {
            if (head == null) {
                return null;
            }
            Node node = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail.next = head.next;
                head.next.prev = tail;
                head = head.next;
            }
            node.next = null;
            node.prev = null;
            return node;
        }

        public void moveNodeToTail(Node<K, V> node) {
            if (node == null) {
                return;
            }
            node.next.prev = node.prev;
            node.prev.next = node.next;
            tail.next = node;
            node.prev = tail;
            node.next = tail.next;
            tail.next.prev = node;
            tail = node;
        }
    }

    public Map<K, Node<K, V>> map;
    public DoubleList<K, V> kvDoubleList;
    public int capacity;

    public LRUCache2(int capacity) {
        if (capacity < 1) {
            throw new RuntimeException("capacity can not less 1");
        }
        kvDoubleList = new DoubleList<>();
        map = new ConcurrentHashMap<>();
        this.capacity = capacity;
    }

    public V get(K key) {
        if (!this.map.containsKey(key)) {
            return null;
        }
        Node<K, V> kvNode = this.map.get(key);
        this.kvDoubleList.moveNodeToTail(kvNode);
        return kvNode.value;
    }

    public void put(K key, V value) {
        if (this.map.containsKey(key)) {
            Node<K, V> kvNode = this.map.get(key);
            kvNode.value = value;
            this.kvDoubleList.moveNodeToTail(kvNode);
        } else {
            Node<K, V> newNode = new Node<>(key, value);
            this.map.put(key, newNode);
            this.kvDoubleList.addNode(newNode);
            if (this.map.size() > this.capacity) {
                Node<K, V> kvNode = this.kvDoubleList.removeHead();
                this.map.remove(kvNode.key);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache2<Integer, String> lruCache2 = new LRUCache2<>(3);
        lruCache2.put(1, "1");
        lruCache2.put(2, "2");
        lruCache2.put(3, "3");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(4, "4");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(5, "5");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(3, "3");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(3, "3");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(3, "3");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(4, "6");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(6, "6");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(7, "7");
        System.out.println(lruCache2.map.keySet());

        lruCache2.put(8, "8");
        System.out.println(lruCache2.map.keySet());
    }
}
