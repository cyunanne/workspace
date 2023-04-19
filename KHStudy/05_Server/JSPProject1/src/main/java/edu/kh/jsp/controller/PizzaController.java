package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.model.dto.Pizza;

@WebServlet("/pizzaOrder")
public class PizzaController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// DB에서 가져온 피자 정
		List<Pizza> pizzaList = new ArrayList<>();
		pizzaList.add(new Pizza("치즈피자", 8000));
		pizzaList.add(new Pizza("콤비네이션피자", 9000));
		pizzaList.add(new Pizza("핫치킨피자", 10000));
		
		// POST 방식 문제점 : 문자 인코딩 불일치(한글 깨짐)
		req.setCharacterEncoding("UTF-8");
		
		// 요청과 함께 전달받은 값 확인
		String pizza = req.getParameter("pizza"); // [1, 2, 3] 중 하나
		String size = req.getParameter("size"); // [R, L] 중 하나
		String amount = req.getParameter("amount"); // [0-n] 숫자, input type="hidden"을 통해 넘어온 값
		
		// 피자 종류 : 핫치킨 피자(L) - L사이즈는 2000원 추가
		// 수량 : 2
		// 총 가격 : 240000원
		
		// 피자 종류 파악
		// - pizza는 "1" 처럼 문자열 -> 정수로 형변환(parsing) 필요
		Pizza p = pizzaList.get(Integer.parseInt(pizza) - 1);
		String pizzaName = p.getName();
		int price = p.getPrice();
		if(size.equals("L")) price += 2000; // L사이즈는 2000원 추가
		
		// 총 가격 계산 : price * amount
		price *= Integer.parseInt(amount);
		
		// req에 pizzaName, price를 속성으로 추가
		req.setAttribute("pizzaName", pizzaName);
		req.setAttribute("price", price);
		
		// JSP로 요청 위임
		// JSP 경로 작성 시 webapp 을 root-path 로 두고 작성
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pizzaResult.jsp");
		dispatcher.forward(req, resp);
	}
}