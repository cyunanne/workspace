package programmers.level0;

/**
 * 프로그래머스 코딩테스트 연습 Lv.0 외계어 사전
 * https://school.programmers.co.kr/learn/courses/30/lessons/120869
 * @author yuna
 * @date 2023.04.12.
 */
public class AlienLanguage {
	public static void main(String[] args) {
		
		String[][] spells = {{"p", "o", "s"}, {"z", "d", "x"}, {"s", "o", "m", "d"}};
		String[][] dics =  {{"sod", "eocd", "qixm", "adio", "soo"},   // 2
							{"def", "dww", "dzx", "loveaw"}, 		  // 1
							{"moos", "dzx", "smm", "sunmmo", "som"}}; // 2

		for(int i=0; i<3; i++) {
			int result = solution(spells[i], dics[i]);
			System.out.println(result);
		}
	}
	
	public static int solution(String[] spell, String[] dic) {
        for(String word : dic) {
        	boolean flag = true;
        	for(String s : spell) {
        		if(!word.contains(s) || (word.indexOf(s) != word.lastIndexOf(s))) {
        			flag = false;
        			break;
        		}
        	}
        	if(flag) return 1;
        }
        return 2;
    }
}