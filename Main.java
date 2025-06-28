package com.CS499.TextBasedGame;

import com.CS499.TextBasedGame.Game.GameMap;
import com.CS499.TextBasedGame.Game.GameOverException;
import com.CS499.TextBasedGame.Player.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            GameMap gamemap = new GameMap();
            Player player = new Player(0, 2);
            player.setCurrentRoom(gamemap.getRoomByCoordinates(0, 2));

            System.out.println("Welcome to the text-based survival game!");
            System.out.println("Mission: Collect all items before reaching the zombie in the Garage.");
            System.out.println("Start the game? (Y/N)");

            String begin = sc.nextLine().trim().toLowerCase();
            if (!begin.equals("y") && !begin.equals("yes")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                while (true) {
                    player.displayCurrentRoom(gamemap);
                }
            } catch (GameOverException e) {
                System.out.println("Restarting game...");
            }
        }

        sc.close();
    }
}
