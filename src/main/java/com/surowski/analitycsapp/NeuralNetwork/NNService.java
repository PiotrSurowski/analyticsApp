package com.surowski.analitycsapp.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NNService {
    public NNCore nn;

    public NNService(){
        initNetwork(2,10,1);
    }

    public void initNetwork(int inputNeurons, int hiddenNeurons, int outputNeurons){
        this.nn = new NNCore(inputNeurons,hiddenNeurons,outputNeurons);
    }

    public List<Double> train(int epochs){
        double [][] X= {
                {0,0},
                {1,0},
                {0,1},
                {1,1}
        };
        double [][] Y= {
                {0},{1},{1},{0}
        };

        return nn.fit(X, Y, epochs);
    }

    public List<Result> clasify(){
        train(50000);
        List<Result> output = new ArrayList<>();
        double [][] input ={{1,0},{0,1},{1,1},{1,1},{1,1},{0,0}};
        for(double d[]:input)
        {
            output.add(new Result(nn.predict(d).get(0), d));
        }
        return output;
    }
}
