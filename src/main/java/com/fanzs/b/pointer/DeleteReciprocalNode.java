package com.fanzs.b.pointer;

public class DeleteReciprocalNode {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 19. 删除链表的倒数第N个节点
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     *
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * 给定的 n 保证是有效的。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return head;
        }
        ListNode fast = head, slow = head;

        int len = 0 ;
        while(fast != null){
            ++len;
            fast = fast.next;
        }
        fast = head;
        if(0 == n % len){
            return head.next;
        }
        for(int i=0;i<n%len;++i){
            fast = fast.next;
        }
        while(fast.next != null){
            fast=fast.next;
            slow=slow.next;
        }
        if(slow.next != null){
            slow.next = slow.next.next;
        }
        return head;
    }
}
