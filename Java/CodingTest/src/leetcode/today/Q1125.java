package leetcode.today;

import java.util.*;

public class Q1125 {

    public static void main(String[] args) {
        String[] req_skills = {"java","nodejs","reactjs"};
        List<List<String>> people = new ArrayList<>();
        for(int i=0; i<3; i++)
            people.add(new ArrayList<>());
        people.get(0).add("java");
        people.get(1).add("nodejs");
        people.get(2).add("nodejs");
        people.get(2).add("reactjs");

        smallestSufficientTeam(req_skills, people);
    }

    public static int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Set<Integer> set = null;
        Set<String> skill = new HashSet<>();
        Map<String, List<Integer>> map = new HashMap<>();
        Map<String, Integer> visited = new HashMap<>();
//        int[] visited = new int[req_skills.length];

        for(String s : req_skills) {
            map.put(s, new ArrayList<Integer>());
            visited.put(s, 0);
            skill.add(s);
        }

        for(int i=0; i<people.size(); i++) {
            for(String s : people.get(i)) {
                map.get(s).add(i);
            }
        }

        Stack<Integer> stack = new Stack<>();
//        for(int who : map.get(req_skills[0])) {


            // 모자란 스킬 보유자 찾기
            for(String s : req_skills) {
                if(!skill.contains(s)) continue; // 해당 스킬 마감

                Set<Integer> curSet = new HashSet<>();
                for(int newbie : map.get(s)) {
//                    if(newbie < who) continue; // 이전 사람은 고려 안함

                    stack.push(newbie);
                    for(String ss : people.get(newbie)) {
                        visited.put(ss, visited.get(ss) + 1);
                    }


                }

                int pop = stack.pop();
                curSet.add(pop);
                for(String ss : people.get(pop)) {
                    visited.put(ss, visited.get(ss) - 1);
                }
                pop = stack.pop();
                curSet.add(pop);
                for(String ss : people.get(pop)) {
                    visited.put(ss, visited.get(ss) - 1);
                }

                while(!stack.empty()) {
                    curSet.add(stack.pop());
                }

                if(set == null || set.size() > curSet.size()) {
                    set = curSet;
                }
            }
//        }

        // if( curSet.size() < set.size() ) set = curSet;

        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
