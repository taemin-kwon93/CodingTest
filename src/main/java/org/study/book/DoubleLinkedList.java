package org.study.book;

import java.util.Comparator;

public class DoubleLinkedList<E> {
    class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        Node() {
            data = null;
            prev = next = this;
        }

        Node(E obj, Node<E> prev, Node<E> next) {
            data = obj;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> crnt;

    public DoubleLinkedList() {
        head = crnt = new Node<E>();
    }

    public boolean isEmpty() {
        return head.next == head;
    }

    public E search(E obj, Comparator<? super E> c) {
        Node<E> ptr = head.next;

        while (ptr != head) {
            if (c.compare(obj, ptr.data) == 0) {
                crnt = ptr;
                return ptr.data;
            }
            ptr = ptr.next;
        }
        return null;
    }

    public void printCurrentNode() {
        if (isEmpty())
            System.out.println("선택 노드가 없습니다.");
        else
            System.out.println(crnt.data);
    }

    public void dump() {
        Node<E> ptr = head.next;

        while (ptr != head) {
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }

    public void dumpReserve() {
        Node<E> ptr = head.prev;

        while (ptr != head) {
            System.out.println(ptr.data);
            ptr = ptr.prev;
        }
    }

    public boolean next() {
        if(isEmpty() || crnt.next == head)
            return false;
        crnt = crnt.next;
        return true;
    }

    public boolean prev() {
        if (isEmpty() || crnt.prev == head)
            return false;
        crnt = crnt.prev;
        return true;
    }
}
