package com.shanemaglangit.ui.listing;

import com.shanemaglangit.data.PagedLinkedList;
import com.shanemaglangit.data.Product;

public class ProductFilter {
    private PagedLinkedList<Product> productList;

    public ProductFilter(PagedLinkedList<Product> productList) {
        this.productList = productList;
    }

    public ProductFilter title(String title) {
        if(title == null || title.isEmpty()) return this;
        PagedLinkedList<Product> temp = new PagedLinkedList<>(productList.getPageSize());
        for(int i = 0; i < productList.getSize(); i++) {
            Product product = productList.get(i);
            if(product.getName().toLowerCase().contains(title.toLowerCase())) {
                temp.add(product);
            }
        }
        this.productList = temp;
        return this;
    }

    public ProductFilter category(String category) {
        if(category == null || category.isEmpty()) return this;
        PagedLinkedList<Product> temp = new PagedLinkedList<>(productList.getPageSize());
        for(int i = 0; i < productList.getSize(); i++) {
            Product product = productList.get(i);
            if(product.getCategory().toLowerCase().contains(category.toLowerCase())) {
                temp.add(product);
            }
        }
        this.productList = temp;
        return this;
    }

    public ProductFilter market(String market) {
        if(market == null || market.isEmpty()) return this;
        PagedLinkedList<Product> temp = new PagedLinkedList<>(productList.getPageSize());
        for(int i = 0; i < productList.getSize(); i++) {
            Product product = productList.get(i);
            if(product.getMarket().toLowerCase().contains(market.toLowerCase())) {
                temp.add(product);
            }
        }
        this.productList = temp;
        return this;
    }

    public ProductFilter minPrice(int minPrice) {
        PagedLinkedList<Product> temp = new PagedLinkedList<>(productList.getPageSize());
        for(int i = 0; i < productList.getSize(); i++) {
            Product product = productList.get(i);
            if(product.getPrice() >= minPrice) {
                temp.add(product);
            }
        }
        this.productList = temp;
        return this;
    }

    public ProductFilter maxPrice(int maxPrice) {
        PagedLinkedList<Product> temp = new PagedLinkedList<>(productList.getPageSize());
        for(int i = 0; i < productList.getSize(); i++) {
            Product product = productList.get(i);
            if(product.getPrice() <= maxPrice) {
                temp.add(product);
            }
        }
        this.productList = temp;
        return this;
    }

    public ProductFilter sort(boolean isAscending) {
        this.productList.sort(isAscending);
        return this;
    }

    public PagedLinkedList<Product> get() {
        return productList;
    }
}
