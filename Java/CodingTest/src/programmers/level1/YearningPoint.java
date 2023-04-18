package programmers.level1;

import java.util.Map;
import java.util.HashMap;

/**
 * 프로그래머스 코딩테스트 연습 Lv.1 추억 점수
 * https://school.programmers.co.kr/learn/courses/30/lessons/176963
 * @author yuna
 * @date 2023.04.18.
 */
public class YearningPoint {
	public static void main(String[] args) {
		String[][] name = {
				{"may", "kein", "kain", "radi"},
				{"kali", "mari", "don"},
				{"may", "kein", "kain", "radi"}
		};
		int[][] yearning = {
				{5, 10, 1, 3},
				{11, 1, 55},
				{5, 10, 1, 3}
		};
		String[][][] photo = {
				{{"may", "kein", "kain", "radi"},{"may", "kein", "brin", "deny"},{"kon", "kain", "may", "coni"}},
				{{"kali", "mari", "don"},{"pony", "tom", "teddy"},{"con", "mona", "don"}},
				{{"may"},{"kein", "deny", "may"},{"kon", "coni"}}
		};
		
		// 19, 15, 6
		// 67, 0, 55
		// 5, 15, 0
		for(int i=0; i<3; i++) {
			for(int j : solution2(name[i], yearning[i], photo[i]))
				System.out.print(j + " ");
			System.out.println();
		}
	}
	
	/* 빠름
	 * 테스트 1 〉		통과 (0.02ms, 74.6MB)
	 * 테스트 2 〉		통과 (0.03ms, 74.5MB)
	 * 테스트 3 〉		통과 (0.27ms, 86.2MB)
	 * 테스트 4 〉		통과 (0.17ms, 85.6MB)
	 * 테스트 5 〉		통과 (0.41ms, 73.2MB)
	 * 테스트 6 〉		통과 (1.18ms, 87.9MB)
	 * 테스트 7 〉		통과 (0.78ms, 78.4MB)
	 * 테스트 8 〉		통과 (1.15ms, 90.2MB)
	 * 테스트 9 〉		통과 (1.19ms, 90.5MB)
	 * 테스트 10 〉	통과 (2.19ms, 94.6MB)
	 * 테스트 11 〉	통과 (2.18ms, 98.5MB)
	 * 테스트 12 〉	통과 (1.45ms, 82.2MB)
	 * 테스트 13 〉	통과 (0.15ms, 79.1MB)
	 * 테스트 14 〉	통과 (0.03ms, 74.1MB)
	 */
	public static int[] solution(String[] name, int[] yearning, String[][] photo) {
		int[] answer = new int[photo.length];
        Map<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<name.length; i++) {
        	map.put(name[i], yearning[i]);
        }
        
        for(int i=0; i<photo.length; i++) {
        	for(String p : photo[i]) {
        		if( map.containsKey(p) ) {
        			answer[i] += map.get(p);
        		}
        	}
        }
        
        return answer;
	}
	
	/* 느림
	 * 테스트 1 〉		통과 (0.01ms, 78.4MB)
	 * 테스트 2 〉		통과 (0.03ms, 77MB)
	 * 테스트 3 〉		통과 (0.55ms, 83.4MB)
	 * 테스트 4 〉		통과 (0.26ms, 79.1MB)
	 * 테스트 5 〉		통과 (3.51ms, 79.5MB)
	 * 테스트 6 〉		통과 (8.70ms, 82.1MB)
	 * 테스트 7 〉		통과 (5.23ms, 83.9MB)
	 * 테스트 8 〉		통과 (6.68ms, 73.9MB)
	 * 테스트 9 〉		통과 (9.55ms, 82.4MB)
	 * 테스트 10 〉	통과 (20.49ms, 93.2MB)
	 * 테스트 11 〉	통과 (25.94ms, 104MB)
	 * 테스트 12 〉	통과 (6.45ms, 83.6MB)
	 * 테스트 13 〉	통과 (0.09ms, 76.7MB)
	 * 테스트 14 〉	통과 (0.03ms, 73.1MB)
	 */
	public static int[] solution2(String[] name, int[] yearning, String[][] photo) {
		int[] answer = new int[photo.length];
        
        for(int i=0; i<photo.length; i++) {
        	for(String p : photo[i]) {
        		for(int j=0; j<name.length; j++) {
        			if(p.equals(name[j])) {
        				answer[i] += yearning[j];
        			}
        		}
        	}
        }
        
        return answer;
	}
}
