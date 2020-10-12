package com.shanemaglangit.util;

public class ItemOverflowException extends Exception {
    public ItemOverflowException() {
        super("Item size exceeded the allotted space");
    }

    public ItemOverflowException(String message) {
        super(message);
    }
}
