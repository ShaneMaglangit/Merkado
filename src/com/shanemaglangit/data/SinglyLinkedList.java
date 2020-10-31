package com.shanemaglangit.data;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public void add(E value) {
        if(head == null) insertAtHead(value);
        else insertAtTail(head, value);
    }

    public void add(E value, int pos) {
        if(pos == 0) insertAtHead(value);
        else insertAtPos(head, value, pos);
    }

    public void addAll(SinglyLinkedList<E> list) {
        for(int i = 0; i < list.getSize(); i++) {
            add(list.get(i));
        }
    }

    public void set(int pos, E value) {
        getNode(head, pos).setValue(value);
    }

    public E get(int pos) {
        return getNode(head, pos).getValue();
    }
    
    public void remove(int pos) {
        if(pos == 0) removeHead();
        else remove(head, pos);
    }

    public void remove(E value) { remove(head, value); }

    public void removeAll() { head = null; }

    public boolean contains(E value) {
        return find(head, value);
    }

    public void sort(boolean isAscending) {
        quickSort(0, getSize() - 1, isAscending);
    }

    public int getSize() {
        Node<E> currentNode = head;
        int size = 0;

        while(currentNode != null) {
            currentNode = currentNode.getNext();
            size++;
        }

        return size;
    }

    private void removeHead() {
        if(head != null) head = head.getNext();
    }

    private void remove(Node<E> node, int pos) {
        if(node == null || node.getNext() == null) throw new IndexOutOfBoundsException();
        else if(pos == 1) node.setNext(node.getNext().getNext());
        else remove(node.getNext(), pos - 1);
    }

    private void remove(Node<E> node, E value) {
        if(node == null || node.getNext() == null) throw new IndexOutOfBoundsException();
        else if(node.getNext().getValue().equals(value)) node.setNext(node.getNext().getNext());
        else remove(node.getNext(), value);
    }

    private Node<E> getNode(Node<E> node, int pos) {
        if(node == null) throw new IndexOutOfBoundsException();
        else if(pos == 0) return node;
        else return getNode(node.getNext(), pos - 1);
    }

    private void insertAtPos(Node<E> node, E value, int pos) {
        if(node.getNext() != null) insertAtPos(node.getNext(), value, pos - 1);
        else {
            if(pos == 1) node.setNext(new Node<>(value));
            else throw new IndexOutOfBoundsException();
        }
    }

    private void insertAtTail(Node<E> node, E value) {
        if(node.getNext() == null) node.setNext(new Node<>(value));
        else insertAtTail(node.getNext(), value);
    }

    private void insertAtHead(E value) {
        Node<E> node = new Node<>(value);
        if(head != null) node.setNext(head);
        head = node;
    }

    private boolean find(Node<E> node, E value) {
        if(node == null) return false;
        else if (node.getValue().equals(value)) return true;
        else return find(node.getNext(), value);
    }

    private void quickSort(int low, int high, boolean isAscending) {
        if(low < high) {
            int partitionIndex = partition(low, high, isAscending);

            quickSort(low, partitionIndex - 1, isAscending);
            quickSort(partitionIndex + 1, high, isAscending);
        }
    }

    private int partition(int low, int high, boolean isAscending) {
        E pivot = get(high);
        int pointer = low;
        int comparisonValue;

        if(isAscending) comparisonValue = -1;
        else comparisonValue = 1;

        for(int i = low; i < high; i++) {
            if(get(i).compareTo(pivot) == comparisonValue) {
                swap(pointer++, i);
            }
        }

        swap(pointer, high);

        return pointer;
    }

    private void swap(int x, int y) {
        E temp = get(x);
        set(x, get(y));
        set(y, temp);
    }
}

