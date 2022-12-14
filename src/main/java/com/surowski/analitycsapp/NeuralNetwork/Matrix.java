package com.surowski.analitycsapp.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    double [][]data;
    int rows, cols;

    public Matrix(int rows, int cols) {
        this.data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                data[i][j] = Math.random()*2-1;
            }
        }
    }

    public void add(double scaller){
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                data[i][j] += scaller;
            }
        }
    }

    public void add(Matrix matrix){
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                data[i][j] += matrix.data[i][j];
            }
        }
    }

    public static Matrix substract(Matrix a, Matrix b){
        Matrix temp = new Matrix(a.rows, a.cols);

        for (int i = 0; i < a.rows; i++){
            for (int j = 0; j < a.cols; j++){
                temp.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return temp;
    }

    public static Matrix transpose(Matrix matrix){
        Matrix temp = new Matrix(matrix.cols, matrix.rows);
        for (int i = 0; i < matrix.rows; i++){
            for (int j = 0; j < matrix.cols; j++){
                temp.data[j][i] = matrix.data[i][j];
            }
        }
        return temp;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix temp=new Matrix(a.rows,b.cols);
        for(int i=0;i<temp.rows;i++)
        {
            for(int j=0;j<temp.cols;j++)
            {
                double sum=0;
                for(int k=0;k<a.cols;k++)
                {
                    sum+=a.data[i][k]*b.data[k][j];
                }
                temp.data[i][j]=sum;
            }
        }
        return temp;
    }

    public void multiply(Matrix a) {
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                this.data[i][j]*=a.data[i][j];
            }
        }

    }

    public void multiply(double a) {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.data[i][j]*=a;
            }
        }

    }

    public void sigmoid() {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
        }

    }

    public Matrix dsigmoid() {
        Matrix temp=new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
                temp.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
        }
        return temp;
    }

    public static Matrix fromArray(double[]x)
    {
        Matrix temp = new Matrix(x.length,1);
        for(int i =0;i<x.length;i++)
            temp.data[i][0]=x[i];
        return temp;

    }

    public List<Double> toArray() {
        List<Double> temp= new ArrayList<Double>();

        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                temp.add(data[i][j]);
            }
        }
        return temp;
    }
}
