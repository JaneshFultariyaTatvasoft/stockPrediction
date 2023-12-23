package com.tatva.stockprediction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String symbol;
    private String series;
    private double prevClose;
    private double open;
    private double high;
    private double low;
    private double last;
    private double close;
    private double vwap;
    private double volume;
    private double turnover;
    private int trades;
    private double deliverableVolume;
    private double percentDeliverable;

}
