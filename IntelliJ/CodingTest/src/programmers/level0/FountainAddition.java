package programmers.level0;

import java.math.BigInteger;

public class FountainAddition {

    public static void main(String[] args) {
        FountainAddition add = new FountainAddition();
        add.solution(1, 2, 3, 4); // answer: [5,4]
    }

    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];

        answer[0] = numer1 * denom2 + numer2 * denom1;
        answer[1] = denom1 * denom2;

        BigInteger b0 = BigInteger.valueOf(answer[0]);
        BigInteger b1 = BigInteger.valueOf(answer[1]);
        BigInteger gcd = b0.gcd(b1);

        int gcdInt = gcd.intValue();
        if(gcdInt != 0) {
            answer[0] /= gcdInt;
            answer[1] /= gcdInt;
        }

        return answer;
    }
}
