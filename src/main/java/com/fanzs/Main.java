package com.fanzs;

public class Main {

    public static class Node {
        private int value;
        private Node prev, next;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }

    public static Node reverseNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node curr = head;
        Node temp = null;
        while (curr != null) {
            Node node = curr;
            curr = curr.next;
            node.next = temp;
            temp = node;
        }
        return temp;
    }
}
