package edu.kh.polymorphism.ex1.service;

import edu.kh.polymorphism.ex1.dto.Car;
import edu.kh.polymorphism.ex1.dto.LightCar;
import edu.kh.polymorphism.ex1.dto.Truck;

import java.util.Scanner;

/**
 * @다형성: 객체가 다양한 형태를 지 상속을 이용한 기술로 부모 타입의 참조 변수 하나로 여러 타입의 자식 객체를 참조할 수 있다.
 */
public class CarService {

    /**
     * UpCasting
     *
     * @- 자식 객체 -> 부모 객체
     * @- 자식 객체 내부에 있는 부모 객체를 참조하도록 변함
     * @- 부모 타입 참조 변수로 참조 시 부모 부분만 참조 가능
     */
    public void ex1() {

        Car c2 = new Truck();

        // Truck 객체가 Car에서 상속받은 메서드 사용
        c2.setWheel(10);
        c2.setSeat(3);
        c2.setFuel("경유");

        // c2로 접근 불가능하여 에러
        // c2의 자료형이 Car -> Truck 객체를 참조하더라도 Car 부분만 볼 수 있음
//        c2.setWeight(2.3);
    }

    /**
     * DownCasting
     *
     * @- 부모 객체 -> 자식 객체
     * @- 강제형변환 이용
     */
    public void ex2() {

        Car c1 = new LightCar();

        // 업캐스팅에 의해 LightCar의 필드/메소드 접근 불가
//        c1.print();
//        c1.getDiscountOffer();

        // LightCar로 강제 형변환1(다운캐스팅)
        ((LightCar) c1).print();
        ((LightCar) c1).setDiscountOffer(0.56);
        System.out.println(((LightCar) c1).toString());

        // LightCar로 강제 형변환2(다운캐스팅)
        LightCar lightCar = (LightCar) c1;
        lightCar.print();
    }

    public void ex3() {

        Car[] carList = new Car[3];

        carList[0] = new Truck(6, 4, "경유", 1); // UpCasting
        carList[1] = new LightCar(4, 4, "휘발유", 0.3); // UpCasting
        carList[2] = new Car(4, 2, "전기");

        for (Car car : carList) {
            System.out.println("바퀴 수 : " + car.getWheel());
            System.out.println("좌석 수 : " + car.getSeat());
            System.out.println("연료 : " + car.getFuel());

            // 다운캐스팅이 잘못될 경우 ClassCastException 발생 가능
            // 해결방법 : instanceof 를 통해 참조하는 객체의 자료형을 판단하여 적절한 형식으로 다운캐스팅한다.
            if(car instanceof Truck) {
                System.out.println("최대 적재 하중 : " + ((Truck)car).getWeight() + "t\n"); // DownCasting
            } else if(car instanceof LightCar) {
                System.out.println("할인율 : " + (((LightCar)car).getDiscountOffer() * 100) + "%\n"); // DownCasting
            } else {
                System.out.println("차종이 등록되어있지 않습니다.\n");
            }
        }
    }

    /**
     * 객체배열 + 다형성(업캐스팅/다운캐스팅/instanceof) + 매개변수의 다형성 + 바인딩(정적/동적)
     */
    public void ex4() {

        Car[] carList = new Car[3];

        carList[0] = new Truck(6, 4, "경유", 1); // UpCasting
        carList[1] = new LightCar(4, 4, "휘발유", 0.3); // UpCasting
        carList[2] = new Car(4, 2, "전기");

        for(Car c : carList) {
            printCar(c);
        }
    }

    public void printCar(Car c) {
        String type = "Car";
        if( c instanceof Truck ) type = "Truck";
        else if( c instanceof LightCar) type = "LightCar";
        System.out.println("["+type+"]에 대한 정보입니다.");
        System.out.println(c.toString()); // binding
    }

    /**
     * 반환형의 다형성
     */
    public void ex5() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("생성할 자동차 종류를 선택하세요.");
        System.out.println("1. 트럭");
        System.out.println("2. 경차");
        System.out.println("3. 차");
        System.out.print("입력 : ");
        Car c = createCar(scanner.nextInt());
        System.out.println("차가 생성되었습니다.");
    }

    public Car createCar(int num) {
        switch (num) {
            case 1: return new Truck();
            case 2: return new LightCar();
            case 3: return new Car();
        }
        return null;
    }
}
