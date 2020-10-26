package com.shanemaglangit.data;

import java.util.UUID;

public class Product extends CSVEntity implements Comparable<Product> {
    private UUID productId;
    private String name;
    private double price;
    private String market;
    private String imagePath;
    private String category;

    public Product(String CSV) {
        this(CSV.split(","));
    }

    public Product(String[] values) {
        this.productId = UUID.fromString(values[0]);
        this.name = values[1];
        this.price = Double.parseDouble(values[2]);
        this.market = values[3];
        this.imagePath = values[4];
        this.category = values[5];
    }

    public Product(String name, double price, String market, String imagePath, String category) {
        this(UUID.randomUUID(), name, price, market, imagePath, category);
    }

    public Product(UUID productId, String name, double price, String market, String imagePath, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.market = market;
        this.imagePath = imagePath;
        this.category = category;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getMarket() {
        return market;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public int compareTo(Product obj) {
        return Double.compare(price, obj.getPrice());
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%.2f,%s,%s,%S", productId.toString(), name, price, market, imagePath, category);
    }
}
