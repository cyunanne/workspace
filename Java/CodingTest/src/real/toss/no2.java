package real.toss;

import java.net.StandardSocketOptions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class no2 {

    static Set<Integer> friendSet = new HashSet<>();
    static Set<Integer> newFriendSet = new HashSet<>();

    public static void main(String[] args) {
        int[][] relationships = {{1,2},{2,3},{2,6},{3,4},{4,5}};
        int target = 2;
        int limit = 3;

        System.out.println(solution(relationships, 1, 2));
    }

    static public int solution(int[][] relationships, int target, int limit) {
        if(limit <= 0) return 0;


        List<int[]> list = Arrays.stream(relationships).toList();

        for(int i=0; i<list.size(); i++) {

            if(list.get(i)[0] == target || list.get(i)[1] == target) {
                int friend = list.get(i)[0] == target ? list.get(i)[1] : list.get(i)[0];
                friendSet.add(friend);
                list.remove(list.get(i));
                findFriend(list, friend, limit-1);
            }
        }

        newFriendSet.remove(target);
        newFriendSet.removeAll(friendSet);

        return friendSet.size() * 5 + newFriendSet.size() * 11;
    }

    static private void findFriend(List<int[]> list, int target, int limit) {
        if(limit <= 0) return;

        for(int i=0; i<list.size(); i++) {

            if(list.get(i)[0] == target || list.get(i)[1] == target) {
                int friend = list.get(i)[0] == target ? list.get(i)[1] : list.get(i)[0];
                newFriendSet.add(friend);
                list.remove(i);
                findFriend(list, friend, limit-1);
            }
        }
    }
}
