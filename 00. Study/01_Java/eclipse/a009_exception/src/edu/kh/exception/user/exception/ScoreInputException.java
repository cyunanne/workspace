package edu.kh.exception.user.exception;

// << 사용자 정의 예외를 만드는 방법 >>
// 이미 존재하는 자바의 Exception 클래스 중 하나를 상속 받는다.
// 관련성 있는 예외가 있으면 해당 예외 클래스를, 없으면 Exception 또는 RuntimeException을 상속
//  - Checked Exception을 원할 경우 -> Exception 상속
//  - Unchecked Exception을 원할 경우 -> RuntimeException 상속
public class ScoreInputException extends Exception {
	
	public ScoreInputException() {
		super("입력된 점수가 범위를 초과하였습니다.");
	}
	
	public ScoreInputException(String message) {
		super(message);
	}

}