package edu.kh.polymorphism.ex2.dto;

// 추상 클래스 : 추상 메서드가 포함된 클래스 (단, 추상 메서드가 없어도 추사 클래스가 될 수 있다.)
public abstract class Animal {

    private String type; // 종
    private String eatType; // 식성

    public Animal() {
    }

    public Animal(String type, String eatType) {
        this.type = type;
        this.eatType = eatType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEatType() {
        return eatType;
    }

    public void setEatType(String eatType) {
        this.eatType = eatType;
    }

    // 객체가 가지고 있는 필드를 하나의 문자열로 반환하는 메서드 (오버라이딩해서 사용 권장)
    // 오버라이딩안하면 "패키지명+클래스명@해시코드" 문자열 반환
    @Override
    public String toString() {
        return type + " / " + eatType;
    }

    // 추상 메서드 : 상속받은 자식 클래스에서 반드시 오버라이딩 해야한다. (오버라이딩 강제화)
    //            추상메서드를 포함한 클래스는 반드시 abstract 클래스여야 한다.
    public abstract void breath();
}
