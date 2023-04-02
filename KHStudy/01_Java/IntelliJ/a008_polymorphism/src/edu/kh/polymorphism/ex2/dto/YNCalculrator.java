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

    @Override
    public int square_recursive(int a, int x) {
        if(x==1) return a;
        return a * square(a, x-1);
    }

    @Override
    public int square_for(int a, int x) {
        int result = 1;
        for(int i=0; i<x ;i++) result *= a;
        return result;
    }
}
