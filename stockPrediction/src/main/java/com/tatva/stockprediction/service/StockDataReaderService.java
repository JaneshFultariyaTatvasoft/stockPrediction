package com.tatva.stockprediction.service;

import ch.qos.logback.core.testUtil.RandomUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockDataReaderService {
    public List<INDArray> loadAndPreprocessData() throws IOException {
        List<INDArray> features = new ArrayList<>();
        for (int i = 0 ; i<10000; i++){
            double[] data = new double[] {
                    Double.parseDouble(String.valueOf(RandomUtil.getPositiveInt())),
                    Double.parseDouble(String.valueOf(RandomUtil.getPositiveInt())),
            };
            INDArray featureVector = Nd4j.create(data);
            features.add(featureVector);

        }
        return features;
    }
}

