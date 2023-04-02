package edu.kh.polymorphism.ex2.dto;

public class Fish extends Animal {

    private int fin; // 지느러미 개수

    public Fish() {}

    public Fish(String type, String eatType, int fin) {
        super(type, eatType);
        this.fin = fin;
    }

    public int getFin() {
        return this.fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    // 상속받은 추상메서드 오버라이드 강제화
    @Override
    public void breath() {
        System.out.println("아가미 호흡을 한다.");
    }

    @Override
    public String toString() {
        return super.toString() + " / 지느러미 갯수 : " + fin;
    }
}
