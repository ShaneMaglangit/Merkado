package com.shanemaglangit.components;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.ItemOverflowException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class ProductList extends JPanel {
    private ProductListItem[] productListItems;

    private int row;
    private int col;

    public ProductList(int row, int col) {
        this.productListItems = new ProductListItem[row * col];
        this.row = row;
        this.col = col;

        // Set the layout
        GridLayout layout = new GridLayout(row, col);
        layout.setHgap(6);
        layout.setVgap(6);
        setLayout(layout);

        // Create the containers
        for(int i = 0; i < productListItems.length; i++) {
            productListItems[i] = new ProductListItem();
            add(productListItems[i]);
        }
    }

    public void setProducts(Product[] products) throws ItemOverflowException {
        int maxSize = row * col;
        if(products.length > maxSize) {
            throw new ItemOverflowException("Product size cannot be greater than " + maxSize);
        }
        attachItemsToContainer(products);
    }

    private void attachItemsToContainer(Product[] products) {
        for(int i = 0; i < products.length; i++) {
            productListItems[i].setProduct(products[i]);
        }
    }
}
