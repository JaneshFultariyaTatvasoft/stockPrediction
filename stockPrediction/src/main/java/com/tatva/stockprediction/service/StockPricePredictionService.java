package com.tatva.stockprediction.service;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPricePredictionService {

    private MultiLayerNetwork model;

    public void trainModel(List<INDArray> data) {
        int numInputs = data.get(0).columns();
        // Ensure correct output layer configuration based on expected output
        int numOutputs = 10;  // Adjust if multiple outputs are needed

        // Create the model configuration
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(123)
                .updater(new Adam())
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(10).activation("tanh").build())
                .layer(1, new OutputLayer.Builder().nIn(10).nOut(numOutputs).activation("identity").build())
                .build();

        // Create the model
        model = new MultiLayerNetwork(conf);
        model.init();

        // Prepare training data
        INDArray features = Nd4j.vstack(data.subList(0, data.size() - 1));
        INDArray labels = Nd4j.create(new double[data.size() - 1][10]);  // Adjust for 10 output values
//        INDArray labels = Nd4j.create(new double[][] {data.get(data.size() - 1).data().asDouble()}); // Ensure correct single-value extraction
        DataSet trainingData = new DataSet(features, labels);

        // Train the model
        for (int i = 0; i < 10000; i++) {
            model.fit(trainingData);
        }
    }

    public double predictPrice(INDArray inputData) {
        INDArray output = model.output(inputData);
        return output.getDouble(0);
    }
}
