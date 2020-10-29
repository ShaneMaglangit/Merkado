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
        if(values[0].isEmpty()) this.productId = UUID.randomUUID();
        else this.productId = UUID.fromString(values[0]);
        this.name = values[1];
        this.price = Double.parseDouble(values[2]);
        this.market = values[3];
        this.imagePath = values[4];
        this.category = values[5];
    }

    public Product(String name, double price, String market, String imagePath, String category) {
        this.productId = UUID.randomUUID();
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

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public int compareTo(Product obj) {
        return Double.compare(price, obj.getPrice());
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%.2f,%s,%s,%s", productId.toString(), name, price, market, imagePath, category);
    }

    @Override
    public String getCSVHeader() {
        return "productId,name,price,market,imagePath,category";
    }
}
