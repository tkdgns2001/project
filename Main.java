package project;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("게임에 참여할 선수들 이름>>");
        String input = scanner.nextLine();

        String[] names = input.split(" ");
        Player[] players = new Player[names.length];
        NumberExpectationGame game = new NumberExpectationGame(players);


        for (int i = 0; i < names.length; i++) {
            players[i] = new Player(names[i]);
        }

        game.GameMain(players);

        scanner.close();
    }


}
