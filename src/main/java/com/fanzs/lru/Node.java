package com.fanzs.lru;

public class Node {
    public int key, value;
    public Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
