package edu.kh.array.practice;

import java.util.Scanner;

public class BingoGameRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("빙고판 크기 지정 : ");
        BingoGame game = new BingoGame(scanner.nextInt());
        game.start();
    }
}
