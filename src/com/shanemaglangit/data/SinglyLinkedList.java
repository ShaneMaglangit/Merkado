package com.shanemaglangit.data;

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

    public void addAll(SinglyLinkedList<T> list) {
        for(int i = 0; i < list.getSize(); i++) {
            add(list.get(i));
        }
    }

    public void set(int pos, T value) {
        getNode(head, pos).setValue(value);
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

    public boolean contains(T value) {
        return find(head, value);
    }

    private boolean find(Node<T> node, T value) {
        if(node == null) return false;
        else if (node.getValue().equals(value)) return true;
        else return find(node.getNext(), value);
    }

    public void sort(boolean isAscending) {
        quickSort(0, getSize() - 1, isAscending);
    }

    private void quickSort(int low, int high, boolean isAscending) {
        if(low < high) {
            int partitionIndex = partition(low, high, isAscending);

            quickSort(low, partitionIndex - 1, isAscending);
            quickSort(partitionIndex + 1, high, isAscending);
        }
    }

    private int partition(int low, int high, boolean isAscending) {
        T pivot = get(high);
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
        T temp = get(x);
        set(x, get(y));
        set(y, temp);
    }
}

