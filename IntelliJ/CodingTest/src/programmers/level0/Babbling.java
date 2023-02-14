package programmers.level0;

public class Babbling {

    public static void main(String[] args) {
        Babbling babbling = new Babbling();
        String[] babblings1 = {"aya", "yee", "u", "maa", "wyeoo"};
        String[] babblings2 = {"ayaye", "uuuma", "ye", "yemawoo", "ayaa"};
        babbling.solution(babblings1); // answer: 1
        babbling.solution(babblings2); // answer: 3
    }

    public int solution(String[] babbling) {
        int answer = 0;
        String[] says = {"aya", "ye", "woo", "ma"};

        for(String b : babbling) {
            for(String s : says)
                if(b.contains(s)) b = b.replace(s, " ");
            if( b.trim().isEmpty() ) answer++;
        }
        return answer;
    }

    public int solution2(String[] babbling) {
        int answer = 0;
        String[] says = {"aya", "ye", "woo", "ma"};

        for(String b : babbling) {

            for(String s : says) {
                int startIndex = b.indexOf(s);

                if(startIndex != -1) {
                    int endIndex = startIndex + s.length();
                    String buffer = b.substring(0, startIndex);
                    buffer += " " + b.substring(endIndex);
                    b = buffer;
                }

                if( b.trim().isEmpty() ) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}
