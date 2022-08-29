package com.surowski.analitycsapp.NeuralNetwork;

public class Result {
    Double clasificatorOutput;
    double[] input;

    public Result(Double clasificatorOutput, double[] input) {
        this.clasificatorOutput = clasificatorOutput;
        this.input = input;
    }

    public Double getClasificatorOutput() {
        return clasificatorOutput;
    }

    public void setClasificatorOutput(Double clasificatorOutput) {
        this.clasificatorOutput = clasificatorOutput;
    }

    public double[] getInput() {
        return input;
    }

    public void setInput(double[] input) {
        this.input = input;
    }
}
