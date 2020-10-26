package com.shanemaglangit.data;

import java.util.List;

public class SinglyLinkedList<T extends Comparable<T>> {
    private Node<T> head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public void add(T value) {
        if(head == null) insertAtHead(value);
        else insertAtTail(head, value);
    }

    public void add(T value, int pos) {
        if(pos == 0) insertAtHead(value);
        else insertAtPos(head, value, pos);
    }

    public T get(int pos) {
        return getNode(head, pos).getValue();
    }

    private Node<T> getNode(Node<T> node, int pos) {
        if(node == null) throw new IndexOutOfBoundsException();
        else if(pos == 0) return node;
        else return getNode(node.getNext(), pos - 1);
    }
    
    public void remove(int pos) { }

    public void remove(Node<T> node) { }

    public void removeAll() { head = null; }

    private void insertAtPos(Node<T> node, T value, int pos) {
        if(node.getNext() != null) insertAtPos(node.getNext(), value, pos - 1);
        else {
            if(pos == 1) node.setNext(new Node<>(value));
            else throw new IndexOutOfBoundsException();
        }
    }

    private void insertAtTail(Node<T> node, T value) {
        if(node.getNext() == null) node.setNext(new Node<>(value));
        else insertAtTail(node.getNext(), value);
    }

    private void insertAtHead(T value) {
        Node<T> node = new Node<>(value);
        if(head != null) node.setNext(head);
        head = node;
    }

    public int getSize() {
        Node<T> currentNode = head;
        int size = 0;

        while(currentNode != null) {
            currentNode = currentNode.getNext();
            size++;
        }

        return size;
    }

    // TODO: Add a sort() which sorts the list in ascending by default.
    // TODO: Add a sort(Boolean isAscending) that sorts the list based on the parameters.
    //  Tip -> `Order` and `Product` should implement `Comparable`. This would allow us to
    //          perform comparisons between these objects. E.g "order1 >= order2" will be
    //          possible. This would help with the sorting of these nodes.
}

