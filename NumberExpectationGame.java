package project;

import java.util.Random;
import java.util.Scanner;

public class NumberExpectationGame {

    private boolean result;
    private final Player[] players;
    private int number;
    private int count;
    private int MaxCount;
    private int survivor; // 생존자 수
    private int size;
    private boolean[] isWinner; // 승자인지 여부를 저장
    private boolean isResult = true;
    private int WinnerIndex = -1; // 현재 라운드 승자 인덱스
    private String Loser = ""; // 최종 패자 이름
    Random random = new Random();
    int[] numbers = new int[15];

    public NumberExpectationGame(Player[] players) {
        this.players = players;
        size = players.length;
        isWinner = new boolean[size];
    }

    public void GameMain(Player[] players) {
        Scanner scanner = new Scanner(System.in);

        // 플레이어의 숫자 선택
        for (int i = 0; i < players.length; i++) {
            System.out.print("[" + players[i].getName() + "]" + " 정수 선택 (1~10) >> ");
            players[i].number = Integer.parseInt(scanner.nextLine());
        }

        // 게임 진행
        while (isResult) {
            RandomNumber(); // 무작위 숫자 배열 생성
            PlayerCount(players); // 플레이어별 숫자 일치 개수 확인
        }
        scanner.close();
    }

    private void PlayerCount(Player[] players) {
        MaxCount = 0;
        WinnerIndex = -1;

        // 모든 플레이어의 숫자 맞춘 개수를 확인
        for (int j = 0; j < players.length; j++) {
            if (isWinner[j]) continue; // 이미 승자인 경우 스킵

            count = 0;
            for (int num : numbers) {
                if (players[j].number == num) {
                    count++;
                }
            }

            System.out.println("[" + players[j].getName() + "] 맞춘 개수: " + count);

            // 최다 일치 개수를 찾고, 현재 라운드의 승자 인덱스 업데이트
            if (count > MaxCount) {
                MaxCount = count;
                WinnerIndex = j;
            }
        }

        if (WinnerIndex != -1) {
            System.out.println("라운드 승자: " + players[WinnerIndex].getName());
            isWinner[WinnerIndex] = true; // 승자 설정
        }

        FinalMatch(players); // 최종 패자 확인
    }

    private void FinalMatch(Player[] players) {
        survivor = 0;
        Loser = "";

        System.out.print("현재 패자들: ");
        for (int i = 0; i < players.length; i++) {
            if (!isWinner[i]) { // 패자인 경우 출력
                System.out.print(players[i].getName() + " ");
                survivor++;
                Loser = players[i].getName();
            }
        }
        System.out.println();

        if (survivor == 1) { // 최종 패자가 한 명 남은 경우
            System.out.println("최종 패자는 " + Loser + "입니다.");
            isResult = false; // 게임 종료
        }
    }

    private void RandomNumber() {
        System.out.print("Enter키 입력 >> ");
        Scanner s = new Scanner(System.in);
        s.nextLine(); // Enter 입력 대기

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(10) + 1; // 1부터 10까지의 무작위 숫자 생성
        }

        System.out.print("무작위 숫자: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
