package edu.kh.polymorphism.ex2.dto;

public class YNCalculrator implements Calculator {

    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int multiple(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }

    @Override
    public double divide2(int a, int b) {
        return (double)a / b;
    }

    @Override
    public double areaOfCircle(double r) {
        return Calculator.PI * r * r;
    }

    @Override
    public int square(int a, int x) {
        return (int)Math.pow(a, x);
    }
}
