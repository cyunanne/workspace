package programmers.level0;

import java.util.Arrays;
import java.util.Stack;

/**
 * 프로그래머스 코딩테스트 연습 Lv.1 햄버거 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/133502
 * @author yuna
 * @date 2023.04.12.
 */
public class Hamberger {
	public static void main(String[] args) {
		
		int[][] ingredients = {
				{2, 1, 1, 2, 3, 1, 2, 3, 1}, //2
				{1, 3, 2, 1, 2, 1, 3, 1, }}; //0

		for(int i=0; i<2; i++) {
			int result = solution2(ingredients[i]);
			System.out.println(result);
		}
	}
	
	public static int solution(int[] ingredient) {
		int answer = 0;
		Stack<Integer> stack = new Stack<>(); 
		
		for(int i : ingredient) {
			String hamberger = "";
			
			stack.push(i);
			
			if(i==1) { // 이번에 넣은 재료가 빵이면
				while(!stack.isEmpty() && hamberger.length()<4) { // 일단 재료 4개 꺼내봄
					hamberger += stack.pop().toString();
				}
				
				if(hamberger.equals("1321")) { // 햄버거임
					answer++;
				} else { // 햄버거 아님 -> 재료 다시 넣기
					for(int j=hamberger.length()-1; j>=0; j--) {
						stack.push(hamberger.charAt(j) - '0');
					}
				}
			}
		}
		return answer;
	}
	
	public static int solution2(int[] ingredient) {
		int answer = 0;
		String strIngredient = Arrays.toString(ingredient).replaceAll("/^[1-3]/", "");
		
		while(strIngredient.contains("1231")) {
			answer++;
			strIngredient = strIngredient.replace("1231", "");
		}

		return answer;
	}
}