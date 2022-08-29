package com.surowski.analitycsapp.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NNCore {
    List<Double> errors;
    Matrix weights_ih, weights_ho, bias_h, bias_o;
    double learning_rate=0.01;

    public NNCore(int i, int h, int o){
        this.weights_ih = new Matrix(h, i);
        this.weights_ho = new Matrix(o, h);
        this.bias_h = new Matrix(h, 1);
        this.bias_o = new Matrix(o, 1);
        errors = new ArrayList<>();
    }

    public List<Double> predict(double[] x){
        Matrix input = Matrix.fromArray(x);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        return output.toArray();
    }

    public void train(double[] x, double[] y){
        Matrix input = Matrix.fromArray(x);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        Matrix target = Matrix.fromArray(y);

        Matrix error = Matrix.substract(target, output);
        createInstance(hidden, output, error, weights_ho, bias_o);

        errors.add(error.toArray().get(0));
        Matrix who_T = Matrix.transpose(weights_ho);
        Matrix hidden_errors = Matrix.multiply(who_T, error);

        createInstance(input, hidden, hidden_errors, weights_ih, bias_h);
    }

    private void createInstance(Matrix input, Matrix hidden, Matrix hidden_errors, Matrix weights_ih, Matrix bias_h) {
        Matrix h_gradient = hidden.dsigmoid();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(learning_rate);

        Matrix i_T = Matrix.transpose(input);
        Matrix wih_delta = Matrix.multiply(h_gradient, i_T);

        weights_ih.add(wih_delta);
        bias_h.add(h_gradient);
    }

    public List<Double> fit(double[][]X,double[][]Y,int epochs)
    {
        for(int i=0;i<epochs;i++)
        {
            int sampleN =  (int)(Math.random() * X.length );
            this.train(X[sampleN], Y[sampleN]);
        }
        return errors;
    }
}
