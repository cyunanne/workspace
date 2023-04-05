package programmers.level0;

/**
 * 프로그래머스 코딩테스트 연습 Lv.0 로그인 성공?
 * https://school.programmers.co.kr/learn/courses/30/lessons/120883
 * @author yuna
 * @date 2023.04.05.
 */
public class login {
	public static void main(String[] args) {
		
		String[][] db1 = {{"rardss", "123"}, {"yyoom", "1234"}, {"meosseugi", "1234"}};
		String[][] db2 = {{"programmer02", "111111"}, {"programmer00", "134"}, {"programmer01", "1145"}};
		String[][] db3 = {{"jaja11", "98761"}, {"krong0313", "29440"}, {"rabbit00", "111333"}};
		
		String[] id_pw1 = {"meosseugi", "1234"};
		String[] id_pw2 = {"programmer01", "15789"};
		String[] id_pw3 = {"rabbit04", "98761"};
		
		System.out.println(solution(id_pw1, db1)); // login
		System.out.println(solution(id_pw2, db2)); // wrong pw
		System.out.println(solution(id_pw3, db3)); // fail
	}
	
	public static String solution(String[] id_pw, String[][] db) {
        for(String[] d : db) {
            if(id_pw[0].equals(d[0])) {
                if(id_pw[1].equals(d[1])) {
                    return "login";
                } else {
                    return "wrong pw";
                }
            }   
        }
        return "fail";
    }
}