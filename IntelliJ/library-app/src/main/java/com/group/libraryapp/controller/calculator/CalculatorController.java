package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @@RestController: 해당 클래스를 API 진입지점으로 지정하고, 내부 메소드 반환값을 JSON으로 자동 변환해준다.
 */
@RestController // 해당 클래스를 API 진입 지점으로 설정
public class CalculatorController {

    /**
     * [ GET API 생성 시 query로 전달받은 데이터 사용하는 방법 1: @RequestParam ]
     * @@GetMapping("/add"): 해당 함수를 HTTP_Method=GET 이고 HTTP_Path=/add 인 API로 지정
     * @@RequestParam: query를 통해 넘어온 데이터를 함수에 연결하기 위한 어노테이션
     */
    //@GetMapping("/add")
    public int addTwoNumbers(@RequestParam int number1, @RequestParam int number2) {
        return number1 + number2;
    }

    /**
     * [ GET API 생성 시 query로 전달받은 데이터 사용하는 방법 2: DTO 활용 ]
     * @DTO: Data Transfer Object,
     *
     * 위 코드는 쿼리로 전달받는 매개변수가 2개 뿐이지만 매개변수를 수십 개 전달받는 함수가 필요할 수도 있다.
     * 그럴 떄 쿼리를 받는 전용 클래스(DTO, CalculatorAddRequest)를 사용하면
     * 실수를 줄이고 함수를 깔끔하게 유지할 수 있다.
     */
    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    /**
     * @@PostMapping("/multiply"): 해당 함수를 HTTP_Method=POST 이고 HTTP_Path=/multiply 인 API로 지정
     * @@RequestBody: HTTP body 안에 담긴 JSON 데이터를 DTO 객체로 변환시키기 위한 annotation. 단, DTO 필드명과 JSON 키값이 같아야 한다.
     * @JSON: JavaScript Object Notation. POST에서 body를 통해 데이터를 전달할 때 사용하는 데이터 형식이다. "key": value 형식으로 작성한다.
     */
    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }
}