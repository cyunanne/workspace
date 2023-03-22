package edu.kh.polymorphism.ex1.dto;

public class Car {

    private int wheel; // 바퀴 개수
    private int seat; // 인승
    private String fuel; // 연료

    public Car() {}

    public Car(int wheel, int seat, String fuel) {
        this.wheel = wheel;
        this.seat = seat;
        this.fuel = fuel;
    }

    public int getWheel() {
        return wheel;
    }

    public void setWheel(int wheel) {
        this.wheel = wheel;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return String.format("%d / %d / %s", wheel, seat, fuel);
    }
}
