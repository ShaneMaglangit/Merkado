package com.shanemaglangit.ui.listing.productlist;

import com.shanemaglangit.data.PagedLinkedList;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.data.SinglyLinkedList;
import com.shanemaglangit.util.ItemOverflowException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductList extends JPanel {
    private ProductListClickListener clickListener;

    private int row;
    private int col;

    public ProductList(int row, int col) {
        this.row = row;
        this.col = col;

        // Set the layout
        GridLayout layout = new GridLayout(row, col);
        layout.setHgap(6);
        layout.setVgap(6);
        setLayout(layout);
    }

    public void setProducts(SinglyLinkedList<Product> products) throws ItemOverflowException {
        int maxSize = row * col;
        if(products.getSize() > maxSize) {
            throw new ItemOverflowException("Product size cannot be greater than " + maxSize);
        }
        attachItemsToContainer(products);
    }

    private void attachItemsToContainer(SinglyLinkedList<Product> products) {
        // Create the containers
        SwingUtilities.invokeLater(() -> {
            this.removeAll();

            for(int i = 0; i < products.getSize(); i++) {
                Product product = products.get(i);
                ProductListItem productListItem = new ProductListItem(product);
                productListItem.addMouseListener(new MouseListener() {
                    @Override public void mouseClicked(MouseEvent e) {
                        if(clickListener != null) clickListener.onClick(product);
                    }

                    @Override public void mousePressed(MouseEvent e) { }
                    @Override public void mouseReleased(MouseEvent e) { }
                    @Override public void mouseEntered(MouseEvent e) { }
                    @Override public void mouseExited(MouseEvent e) { }
                });
                add(productListItem);
            }
        });
    }

    public void setClickListener(ProductListClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
