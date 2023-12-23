package com.tatva.stockprediction.controller;

import com.tatva.stockprediction.service.StockDataReaderService;
import com.tatva.stockprediction.service.StockPricePredictionService;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class StockPriceController {
    @Autowired
    private StockDataReaderService dataReaderService;
    @Autowired
    private StockPricePredictionService predictionService;

    @GetMapping("/predict/{symbol}")
    public double predictStockPrice(@PathVariable String symbol) throws IOException {
        List<INDArray> newData = dataReaderService.loadAndPreprocessData();
        predictionService.trainModel(newData);
        double predictedPrice = predictionService.predictPrice(newData.get(newData.size() - 1));
        return predictedPrice;
    }
}
