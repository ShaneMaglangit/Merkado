package com.shanemaglangit.util;

import com.shanemaglangit.data.SinglyLinkedList;

import javax.swing.text.View;
import java.util.ArrayList;

public abstract class QuickSort {
    public static void sort(SinglyLinkedList<?> values, int low, int high, boolean isAscending) {
        if(low < high) {
            int partitionIndex = partition(values, low, high, isAscending);

            sort(values, low, partitionIndex - 1, isAscending);
            sort(values, partitionIndex + 1, high, isAscending);
        }
    }

    private static int partition(SinglyLinkedList values, int low, int high, boolean isAscending) {
        Comparable pivot = values.get(high);
        int pointer = low;
        int comparisonValue;

        if(isAscending) comparisonValue = -1;
        else comparisonValue = 1;

        for(int i = low; i < high; i++) {
            if(values.get(i).compareTo(pivot) == comparisonValue) {
                swap(values, pointer++, i);
            }
        }

        swap(values, pointer, high);

        return pointer;
    }

    private static void swap(SinglyLinkedList values, int x, int y) {
        Comparable temp = values.get(x);
        values.set(x, values.get(y));
        values.set(y, temp);
    }
}
