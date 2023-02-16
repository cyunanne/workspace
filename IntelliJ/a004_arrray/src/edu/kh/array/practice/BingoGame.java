package edu.kh.array.practice;

import java.util.Scanner;

public class BingoGame {

    private String[][] board;
    private int size;

    public BingoGame(int size) {
        this.board = new String[size][size];
        this.size = size;
    }

    public void start() {

        // 보드판 생성
        generateBoard();

        // 빙고게임 시작
        System.out.println("\n===== 빙고게임 시작 =====");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n정수를 입력하세요 : ");
            selectNumber(scanner.next());
            printBoard();

            // 3빙고 확인
            if( checkBingo() == true ) {
                System.out.println("***Bingo!***");
                break;
            }
        }
    }

    private void generateBoard() {
        boolean[] dupCheck = new boolean[size * size + 1];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                while (true) {
                    board[i][j] = (int) (Math.random() * size * size + 1) + "";
                    if (dupCheck[Integer.valueOf(board[i][j])] == false) {
                        dupCheck[Integer.valueOf(board[i][j])] = true;
                        break;
                    }
                }
            }
        }
        printBoard();
    }

    private void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%3s", board[i][j]);
            }
            System.out.println();
        }
    }

    private void selectNumber(String num) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].equals(num)) {
                    board[i][j] = "★";
                }
            }
        }
    }

    private boolean checkBingo() {
        int bingoCount = 0;

        // 가로세로 빙고 확인
        for(int i=0; i<size; i++) {
            boolean verticalBingo = true;
            boolean horizentalBingo = true;

            for(int j=0; j<size; j++) {
                if(board[i][j].equals("★") == false)
                    verticalBingo = false;
                if(board[j][i].equals("★") == false)
                    horizentalBingo = false;
            }
            if( verticalBingo == true ) bingoCount++;
            if( horizentalBingo == true ) bingoCount++;
        }

        // 대각선 빙고 확인
        boolean diagonalBingo1 = true;
        boolean diagonalBingo2 = true;

        for(int i=0; i<size; i++) {
            if(board[i][i].equals("★") == false)
                diagonalBingo1 = false;
            if(board[i][size-i-1].equals("★") == false)
                diagonalBingo2 = false;
        }
        if( diagonalBingo1 == true ) bingoCount++;
        if( diagonalBingo2 == true ) bingoCount++;

        // 총 빙고 개수 확인
        if( bingoCount >= 3 )
            return true;
        return false;
    }
}
