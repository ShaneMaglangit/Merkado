package com.shanemaglangit.components;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.ItemOverflowException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ProductList extends JPanel {
    private ProductListItem[] productListItems;

    private ProductListClickListener clickListener;

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
            ProductListItem productListItem = new ProductListItem();
            productListItem.addMouseListener(new MouseListener() {
                @Override public void mouseClicked(MouseEvent e) {
                    Product product = productListItem.getProduct();
                    if(clickListener != null && product != null) {
                        clickListener.onClick(product);
                    }
                }

                @Override public void mousePressed(MouseEvent e) { }
                @Override public void mouseReleased(MouseEvent e) { }
                @Override public void mouseEntered(MouseEvent e) { }
                @Override public void mouseExited(MouseEvent e) { }
            });
            productListItems[i] = productListItem;
            this.add(productListItem);
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
        for(int i = 0; i < productListItems.length; i++) {
            if(i >= products.length) productListItems[i].setProduct(null);
            else productListItems[i].setProduct(products[i]);
        }
    }

    public void setClickListener(ProductListClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
