package com.shanemaglangit.data;

public abstract class CSVEntity {
    public CSVEntity() {}
    public CSVEntity(String CSV) {}
    public CSVEntity(String[] values) {}
    public abstract String toCSV();
}
