package edu.kh.array.ex;

public class ArrayEx2 {

    /*
     * [ 2차원 배열 ]
     * : 1차원 배열의 참조 변수로 이루어진 배열
     *   2차원 배열 참조변수에는 1차원 배열 참조 변수 묶음의 시작 주소가 저장됨
     *
     * int[][] arr = new int[2][3];
     * 1) int[][] arr    : stack 영역에 생성
     * 2) new int[2]     : heap, int[](참조형) 배열 2칸 생성
     * 3) [3]            : heap, int(기본자료형) 배열 3칸 생성
     * 4) =              : heap에 생성된 int[][] 배열 참조값 전달
     *
     * 행의 길이 : arr.length -> 2
     * 열의 길이 : arr[n].length -> 3
     * */

    // 2차원배열 사용법1
    public void ex1() {
        // 2차원배열 선언 및 할당
        int[][] arr = new int[2][3];
        System.out.println("행의 길이 : " + arr.length);
        System.out.println("열의 길이 : " + arr[0].length);

        // 2차원배열 초기화
        // 1) 인덱스 이용
        arr[0][0] = 7;
        arr[0][1] = 14;
        arr[0][2] = 21;

        arr[1][0] = 28;
        arr[1][1] = 35;
        arr[1][2] = 42;

        System.out.println("==========");

        // 2) 2중 for문 이용
        int number = 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                arr[i][j] = (number++) * 5;

        System.out.println("==========");
    }
}
