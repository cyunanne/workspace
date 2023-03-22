package programmers.level0;

import java.util.Arrays;

/**
 * 최빈값 구하기
 *
 * @Date : 2023.2.16.(목)
 * */
public class ModeNumber {

    public static void main(String[] args) {
        ModeNumber mn = new ModeNumber();
        int[] array = {1};
        System.out.println(mn.solution(array));
    }

    public int solution(int[] array) {
        int answer = array[0];
        int count = 1;

        Arrays.sort(array);

        int curAnswer = array[0], curCount = 0;
        for(int i=0; i<array.length; i++) {
            if( curAnswer == array[i] ) {
                curCount++;
            } else {
                if(curCount == count) {
                    answer = -1;
                } else if( curCount > count) {
                    answer = curAnswer;
                    count = curCount;
                }
                curCount = 1;
                curAnswer = array[i];
            }
        }

        if( answer != array[array.length-1] && curCount == count )
            return -1;
        else if ( curCount > count )
            return curAnswer;

        return answer;
    }
}
