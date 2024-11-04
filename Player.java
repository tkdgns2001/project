package project;

import java.util.Arrays;

public class Player {
    private String [] player;
    private String name;
    public int number;

    public Player(String name) {
        this.name = name;
    }
    public Player(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void print() {
        System.out.println(name);
    }
}
