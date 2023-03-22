package edu.kh.polymorphism.ex2.dto;

public class Person extends Animal {
    // 추상클래스 상속 시 abstract 메서드도 상속되는데, 이를 오버라이딩해서 구현하지 않으면 에러 발생

    private String name;
    private int age;

    public Person() {}

    public Person(String type, String eatType, String name, int age) {
        super(type, eatType); // 부모의 매개변수 생성자 호출
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void breath() {
        System.out.println("코와 입으로 숨을 쉰다.");
    }

    @Override
    public String toString() {
        return super.toString() + " / 이름 : " + name + " / 나이 : " + age;
    }
}
