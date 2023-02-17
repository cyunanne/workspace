package com.group.libraryapp.dto.calculator.request;

public class CalculatorMultiplyRequest {

    // 필드명은 JSON의 키값과 같아야한다.
    private int number1;
    private int number2;

    public CalculatorMultiplyRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}
