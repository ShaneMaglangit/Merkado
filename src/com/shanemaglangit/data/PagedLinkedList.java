package com.shanemaglangit.data;

public class PagedLinkedList<T extends Comparable<T>> extends SinglyLinkedList<T> {
    private int pageSize;

    public PagedLinkedList(int pageSize) {
        super();
        this.pageSize = pageSize;
    }

    public SinglyLinkedList<T> getPage(int page) {
        if(getSize() == 0) return new SinglyLinkedList<T>();
        if(page >= getPagesCount()) throw new IndexOutOfBoundsException();

        SinglyLinkedList<T> subList = new SinglyLinkedList<>();
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;

        if(endIndex >= getSize()) endIndex = getSize();

        for(int i = startIndex; i < endIndex; i++) {
            subList.add(get(i));
        }

        return subList;
    }

    public int getPageSize(){
        return pageSize;
    }

    public int getPagesCount() {
        return (int) Math.ceil((double) getSize() / (double) pageSize);
    }
}