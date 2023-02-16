package edu.kh.array.practice;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayPractice {
    public void practice1() {
        int[] arr = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = i + 1;
            if (i % 2 == 0)
                sum += arr[i];
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n짝수 번째 인덱스 합 : " + sum);
    }

    public void practice2() {
        int[] arr = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = i + 1;
            if (i % 2 == 1)
                sum += arr[i];
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n홀수 번째 인덱스 합 : " + sum);
    }

    public void practice3() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("양의 정수 : ");
        int input = scanner.nextInt();
        int[] arr = new int[input];
        for (int i = 0; i < input; i++) {
            arr[i] = i + 1;
            System.out.print(arr[i] + " ");
        }
    }

    public void practice4() {
        int[] arr = new int[5];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.printf("입력 %d : ", i);
            arr[i] = sc.nextInt();
        }
        System.out.print("검색할 값 : ");
        int search = sc.nextInt();
        for (int i = 0; i < 5; i++) {
            if (arr[i] == search) {
                System.out.println("인덱스 : " + i);
                return;
            }
        }
        System.out.println("일치하는 값이 없습니다.");
    }

    public void practice5() {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자열 : ");
        String input = sc.next();
        System.out.print("문자 : ");
        char search = sc.next().charAt(0);

        char[] arr = new char[input.length()];
        String str = "";
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (arr[i] == search) {
                count++;
                str += i + " ";
            }
            arr[i] = input.charAt(i);
        }

        if (count != 0) {
            System.out.println("application에 " + search + "가 존재하는 위치(인덱스) : " + str);
            System.out.println("i 개수 : " + count);
        } else {
            System.out.print("존재하지 않는 문자입니다.");
        }
    }

    public void practice6() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 : ");
        int input = sc.nextInt();

        int[] arr = new int[input];
        for (int i = 0; i < input; i++) {
            System.out.printf("배열 %d번째 인덱스에 넣을 값 : ", i);
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            sum += arr[i];
        }
        System.out.println("\n총 합 : " + sum);
    }

    public void practice7() {
        Scanner sc = new Scanner(System.in);
        System.out.print("주민등록번호(-포함) : ");
        String input = sc.next();
        char[] arr = input.toCharArray();
        for (int i = 8; i < arr.length; i++)
            arr[i] = '*';
        System.out.println(arr);
    }

    public void practice8() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        while (true) {
            System.out.print("정수(3이상인 홀수) : ");
            input = scanner.nextInt();
            if (input >= 3 && input % 2 == 1) {
                break;
            }
            System.out.println("다시 입력하세요.");
        }

        int[] arr = new int[input];
        int num = 0;
        for (int i = 0; i < input; i++) {
            if (i <= input / 2)
                arr[i] = ++num;
            else
                arr[i] = --num;

            if (i == input - 1)
                System.out.println(arr[i]);
            else
                System.out.print(arr[i] + ", ");
        }
    }

    public void practice9() {
        int[] arr = new int[10];
        System.out.print("발생한 난수 : ");
        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 10 + 1);
            System.out.print(arr[i] + " ");
        }
    }

    public void practice10() {
        int[] arr = new int[10];
        System.out.print("발생한 난수 : ");
        int min = 99, max = -1;
        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 10 + 1);
            System.out.print(arr[i] + " ");
            if (min > arr[i]) min = arr[i];
            if (max < arr[i]) max = arr[i];
        }
        System.out.println("\n최댓값 : " + max);
        System.out.println("최솟값 : " + min);
    }

    public void practice11() {
        boolean[] check = new boolean[11]; // false로 자동 초기화
        int[] arr = new int[10];
        System.out.print("발생한 난수 : ");
        for (int i = 0; i < 10; i++) {
            while (true) {
                arr[i] = (int) (Math.random() * 10 + 1);
                if (check[arr[i]] == false) {
                    check[arr[i]] = true;
                    break;
                }
            }
            System.out.print(arr[i] + " ");
        }
    }

    public void practice12() {
        boolean[] check = new boolean[46]; // false로 자동 초기화
        int[] arr = new int[6];
        for (int i = 0; i < 6; i++) {
            while (true) {
                arr[i] = (int) (Math.random() * 45 + 1);
                if (check[arr[i]] == false) {
                    check[arr[i]] = true;
                    break;
                }
            }
        }
        Arrays.sort(arr);
        for (int i = 0; i < 6; i++) {
            if (i == 5) System.out.println(arr[i]);
            else System.out.print(arr[i] + ", ");
        }
    }

    public void practice13() {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자열 : ");
        String input = sc.next();

        boolean[] check = new boolean[26]; // 초기값: false
        char[] arr = new char[input.length()];
        int count = 0;

        // String to char[] + 중복제거
        input.toLowerCase(); // 대소문자 구분 없음
        for (int i = 0; i < input.length(); i++) {
            char cur = input.charAt(i);
            if (check[cur - 'a']) continue;
            check[cur - 'a'] = true;
            arr[count++] = cur;
        }

        // 결과 출력
        System.out.print("문자열에 있는 문자 : ");
        for (int i = 0; i < count - 1; i++)
            System.out.print(arr[i] + ", ");
        System.out.print(arr[count - 1] + "\n");
        System.out.println("문자 갯수 : " + count);
    }

    public void practice14() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("배열의 크기를 입력하세요 : ");
        int sizeOfArray = scanner.nextInt();
        String[] arr = new String[sizeOfArray];

        int curIndex = 0;
        while (true) {
            for (int i = curIndex; i < sizeOfArray; i++) {
                System.out.printf("%d번째 문자열 : ", curIndex + 1);
                arr[curIndex++] = scanner.next();
            }

            String check = "";
            while (true) {
                System.out.print("더 값을 입력하시겠습니까?(Y/N) : ");
                check = scanner.next().toLowerCase();
                if (check.equals("y") || check.equals("n")) break;
                else System.out.println("Y/y 또는 N/n만 입력해주세요.");
            }
            if (check.equals("n")) break;

            // 배열 확장
            System.out.print("더 입력하고 싶은 개수 : ");
            sizeOfArray += scanner.nextInt();
            String[] newArr = new String[sizeOfArray];
            System.arraycopy(arr, 0, newArr, 0, curIndex);
            arr = newArr;
        }

        // 결과 출력
        System.out.print("[");
        for (int i = 0; i < sizeOfArray - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[sizeOfArray - 1] + "]");
    }

    public void practice15() {
        String[][] arr = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = "(" + i + ", " + j + ")";
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public void practice16() {
        int[][] arr = new int[4][4];
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = count++;
                System.out.printf("%3d", arr[i][j]);
            }
            System.out.println();
        }
    }

    public void practice17() {
        int[][] arr = new int[4][4];
        int count = 16;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = count--;
                System.out.printf("%3d", arr[i][j]);
            }
            System.out.println();
        }
    }

    public void practice18() {
        int[][] arr = new int[4][4];
        int count = 1;

        // 랜덤 값 지정 + 행 합계 연산
        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                arr[i][j] = (int) (Math.random() * 10 + 1);
                System.out.printf("%4d", arr[i][j]);
                sum += arr[i][j];
            }
            arr[i][3] = sum;
            System.out.printf("%4d", arr[i][3]);
            System.out.println();
        }

        // 열 합계 연산
        for (int j = 0; j < 4; j++) {
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                sum += arr[i][j];
            }
            arr[3][j] = sum;
            System.out.printf("%4d", arr[3][j]);
        }
    }

    public void practice19() {
        Scanner scanner = new Scanner(System.in);
        int rowSize, colSize;
        while (true) {
            System.out.print("행 크기 : ");
            rowSize = scanner.nextInt();
            System.out.print("열 크기 : ");
            colSize = scanner.nextInt();

            if (rowSize < 1 || rowSize > 10 || colSize < 1 || colSize > 10) {
                System.out.println("반드시 1~10 사이의 정수를 입력해야 합니다.");
            } else {
                break;
            }
        }

        char[][] arr = new char[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                arr[i][j] = (char) (Math.random() * 26 + 'A');
                System.out.printf("%2c", arr[i][j]);
            }
            System.out.println();
        }
    }

    public void practice20() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("행의 크기 : ");
        int rowSize = scanner.nextInt();
        char[][] arr = new char[rowSize][];
        for (int i = 0; i < rowSize; i++) {
            System.out.print(i + "열의 크기 : ");
            int colSize = scanner.nextInt();
            arr[i] = new char[colSize];
        }

        char alphabet = 'a';
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = alphabet++;
                System.out.printf("%2c", arr[i][j]);
            }
            System.out.println();
        }
    }

    public void practice21() {
        String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배",
                "송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하"};
        String[][][] seat = new String[2][3][2];
        boolean[] check = new boolean[students.length];

        for (int i = 0; i < seat.length; i++) {
            System.out.printf("== %d분단 ==\n", i + 1);
            for (int j = 0; j < seat[i].length; j++) {
                for (int k = 0; k < seat[i][j].length; k++) {
                    int pick = -1;
                    while (true) {
                        pick = (int) (Math.random() * 12);
                        if (check[pick] == false) {
                            check[pick] = true;
                            break;
                        }
                    }
                    seat[i][j][k] = students[pick];
                    System.out.print(seat[i][j][k] + "  ");
                }
                System.out.println();
            }
        }
    }

    public void practice22() {
        String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배",
                "송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하"};
        String[][][] seat = new String[2][3][2];
        boolean[] check = new boolean[students.length];

        for (int i = 0; i < seat.length; i++) {
            System.out.printf("== %d분단 ==\n", i + 1);
            for (int j = 0; j < seat[i].length; j++) {
                for (int k = 0; k < seat[i][j].length; k++) {
                    int pick = -1;
                    while (true) {
                        pick = (int) (Math.random() * 12);
                        if (check[pick] == false) {
                            check[pick] = true;
                            break;
                        }
                    }
                    seat[i][j][k] = students[pick];
                    System.out.print(seat[i][j][k] + "  ");
                }
                System.out.println();
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("=========");
        System.out.print("검색할 학생 이름을 입력하세요 : ");
        String target = scanner.next();

        for (int i = 0; i < seat.length; i++)
        for (int j = 0; j < seat[i].length; j++)
        for (int k = 0; k < seat[i][j].length; k++) {
            if (seat[i][j][k].equals(target)) {
                if (k == 0) System.out.printf("검색하신 %s 학생은 %d분단 %d번째 줄 왼쪽에 있습니다.\n", target, i + 1, j + 1);
                else System.out.printf("검색하신 %s 학생은 %d분단 %d번째 줄 오른쪽에 있습니다.\n", target, i + 1, j + 1);
                return;
            }
        }
    }

    public void practice23() {
        Scanner scanner = new Scanner(System.in);
        int targetRow, targetCol;

        while(true) {
            System.out.print("행 인덱스 입력 : ");
            targetRow = scanner.nextInt();
            System.out.print("열 인덱스 입력 : ");
            targetCol = scanner.nextInt();

            if (targetCol < 0 || targetCol > 4 || targetRow < 0 || targetRow > 4) {
                System.out.println("0~4 사이의 숫자만 입력 가능합니다.");
            } else {
                break;
            }
        }

        // 배열에 데이터 저장
        String[][] arr = new String[6][6];
        for(int i=1; i<6; i++) {
            arr[i][0] = arr[0][i] = i-1 + "";
        }
        arr[targetRow+1][targetCol+1] = "X";

        // 결과 출력
        for(int i=0; i<6; i++) {
            for(int j=0; j<6; j++) {
                if(arr[i][j] == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(arr[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    public void practice24() {
        Scanner scanner = new Scanner(System.in);
        int targetRow, targetCol;

        while(true) {
            System.out.print("행 인덱스 입력 : ");
            targetRow = scanner.nextInt();
            if (targetRow == 99) {
                System.out.println("프로그램 종료");
                break;
            } else if (targetRow < 0 || targetRow > 4) {
                System.out.println("0~4 사이의 숫자만 입력 가능합니다.");
                continue;
            }

            System.out.print("열 인덱스 입력 : ");
            targetCol = scanner.nextInt();
            if (targetCol == 99) {
                System.out.println("프로그램 종료");
                break;
            } else if (targetCol < 0 || targetCol > 4) {
                System.out.println("0~4 사이의 숫자만 입력 가능합니다.");
                continue;
            }

            // 배열에 데이터 저장
            String[][] arr = new String[6][6];
            for (int i = 1; i < 6; i++) {
                arr[i][0] = arr[0][i] = i - 1 + "";
            }
            arr[targetRow + 1][targetCol + 1] = "X";

            // 결과 출력
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (arr[i][j] == null) {
                        System.out.print("  ");
                    } else {
                        System.out.print(arr[i][j] + " ");
                    }

                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
