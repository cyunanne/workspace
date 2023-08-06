package real.toss;

import java.util.*;

public class no3 {

    public static void main(String[] args) {
        String[] merchantNames = {"비바리퍼블리", "토스커피사일로 베이커리",
                "비바리퍼블리카 식당", "토스커피사일", "토스커피사일로 베이커",
                "비바리퍼블리카식", "토스커피사일로 베이", "토스커피사일로&베이커리",
                "토스커피사일로&베이커리", "비바리퍼블리카 식당"};
        System.out.println(solution(merchantNames));
    }

    static public String[] solution(String[] merchantNames) {
        String[] answer = {};
        List<String> list = new ArrayList<>();

        list.add(merchantNames[0].replaceAll(" ", ""));
        for (int i = 1; i < merchantNames.length; i++) {
            String cur = merchantNames[i].replaceAll(" ", "");
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).indexOf(cur) != -1) {
                    continue;
                } else if (merchantNames[i].indexOf(cur) != -1) {
                    list.remove(j);
                    list.add(merchantNames[i]);
                } else {
                    list.add(merchantNames[i]);
                }
            }
        }

        return (String[])list.toArray();
    }
}
