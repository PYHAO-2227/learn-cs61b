package deque;

public class LinkedListDeque<T> {
    private Node<T> sentinel;
    private int size;
    private static class Node<T> {
        public T item;
        public Node<T> next;
        public Node<T> prev;
        /** The constructor of Node, a nested class used by myself not user. */
        public Node(T i) {
            item = i;
            next = null;
            prev = null;
        }
        public Node() {
            item = null;
            next = null;
            prev = null;
        }
    }

    /** 2 kinds of constructor, use sentinel to create a circular structure */
    public LinkedListDeque() {
        sentinel = new Node<>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T i) {
        sentinel = new Node<>();
        sentinel.next = new Node<>(i);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T i) {
        Node<T> newNode = new Node<>(i);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T i) {
        Node<T> newNode = new Node<>(i);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while(p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return temp;
    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = null;
        size -= 1;
        return  temp;
    }

    public T get(int index) {
        if (sentinel.next == null || index < 0 || index > size - 1) {
            return null;
        }
        Node<T> cur = sentinel.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    public T getRecursive(int index) {
        if (sentinel.next == null || index < 0 || index > size - 1) {
            return null;
        }
        Node<T> cur = sentinel.next;
        return checkNode(cur, index);
    }

    private T checkNode(Node<T> cur, int index) {
        if (cur == null || index < 0) {
            return null;
        }
        if (index == 0) {
            return cur.item;
        } else {
            return checkNode(cur.next, index - 1);
        }
    }
}
