package real.toss;

public class no1 {

    public static void main(String[] args) {
        String s = "1451232125";
        int N = 2;

        System.out.println(solution(s, N));
    }

    public static int solution(String s, int N) {
        int answer = -1;

        if(s.length() < N) return -1;

        for(int i=0; i<=s.length()-N; i++) {
            String cur = s.substring(i, i+N);
            for(int j=1; j<=N; j++) {
                String strJ = String.valueOf(j);

                if(cur.indexOf(strJ) == -1) break;
                int intCur = Integer.parseInt(cur);
                if(j==N && answer < intCur)
                    answer = intCur;
            }
        }

        return answer;
    }
}
