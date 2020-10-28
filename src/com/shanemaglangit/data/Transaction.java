package com.shanemaglangit.data;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.util.Util;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.UUID;

public class Transaction extends CSVEntity implements Comparable<Transaction> {
    private UUID transactionId;
    private ZonedDateTime dateTime;
    private String address;
    private String phoneNumber;
    private boolean isCOD;
    private String cardNumber;
    private String expiration;
    private String CVV;
    private Order order;
    private double total;

    public Transaction(String CSV) {
        this(CSV.split(","));
    }

    public Transaction(String[] values) {
        this.transactionId = UUID.fromString(values[0]);
        this.dateTime = ZonedDateTime.parse(values[1], Util.dateTimeFormatter);
        this.address = Util.cipher(values[2]);
        this.phoneNumber = Util.cipher(values[3]);
        this.isCOD = Boolean.parseBoolean(values[4]);
        this.cardNumber = Util.cipher(values[5]);
        this.expiration = Util.cipher(values[6]);
        this.CVV = Util.cipher(values[7]);
        this.order = new Order(Arrays.copyOfRange(values, 8, values.length - 1));
        this.total = Double.parseDouble(values[values.length - 1]);
    }

    public Transaction(String address, String phoneNumber, boolean isCOD, String cardNumber, String expiration, String CVV, Order order) {
        this.transactionId = UUID.randomUUID();
        this.dateTime = ZonedDateTime.now();
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isCOD = isCOD;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.CVV = CVV;
        this.order = order;
        this.total = order.getTotal();
    }

    @Override
    public String toCSV() {
        return String.format(
            "%s,%s,%s,%s,%b,%s,%s,%s,%s,%.2f",
            transactionId.toString(),
            dateTime.format(Util.dateTimeFormatter),
            Util.cipher(address),
            Util.cipher(phoneNumber),
            isCOD,
            Util.cipher(cardNumber),
            Util.cipher(expiration),
            Util.cipher(CVV),
            order.toCSV(),
            order.getTotal()
        );
    }

    @Override
    public String getCSVHeader() {
        return "transactionId,dateTime,address,phoneNumber,isCOD,cardNumber,expiration,CVV," + order.getCSVHeader() + ",total";
    }

    @Override
    public int compareTo(@NotNull Transaction o) {
        return 0;
    }
}
