package com.shanemaglangit.ui.listing.productlist;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.data.SinglyLinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductList extends JPanel {
    private JPanel pnlInner;
    private ProductListClickListener clickListener;

    public ProductList() {
        // Set the layout
        GridLayout layout = new GridLayout(0, 5);
        layout.setHgap(6);
        layout.setVgap(6);
        setLayout(layout);
    }

    public void setProducts(SinglyLinkedList<Product> products) {
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

            for(int i = 0; i < Config.PRODUCT_PER_SCREEN - products.getSize(); i++) add(new JPanel());
            this.revalidate();
        });
    }

    public void setClickListener(ProductListClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
