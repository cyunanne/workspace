package edu.kh.collection.list.dto;

import java.util.Objects;

public class Student {

    private String name;
    private int grade;
    private int classRoom;
    private int number;
    private String address;
    private char gender;
    private int score;

    public Student() {
    }

    public Student(String name, int grade, int classRoom, int number, String address, char gender, int score) {
        this.name = name;
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
        this.address = address;
        this.gender = gender;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return grade == student.grade
                && classRoom == student.classRoom
                && number == student.number
                && gender == student.gender
                && score == student.score
                && name.equals(student.name)
                && address.equals(student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, grade, classRoom, number, address, gender, score);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", classRoom=" + classRoom +
                ", number=" + number +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", score=" + score +
                '}';
    }
}