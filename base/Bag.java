package base;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {
    private int size;
    private Node first;

    private class Node {
        private T data;
        private Node next;
    }

    public void add(T t) {
        Node oldFirst = first;
        first = new Node();
        first.data = t;
        first.next = oldFirst;
        ++size;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node pre = null;
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public T next() {
            if(current == null) 
                throw new NullPointerException();
            pre = current;
            current = current.next;
            return pre.data;
        }
        @Override
        public void remove() {
            if(current == null) 
                throw new NullPointerException();
            if(current == first) {
                first = first.next;
                pre = null;
                current = first;
            } else {
                pre.next = current.next;
                current = current.next;
            }
            --size;
        }
    }
}