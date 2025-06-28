package com.CS499.TextBasedGame.Player;

import com.CS499.TextBasedGame.Room.Room;
import com.CS499.TextBasedGame.Game.Direction;
import com.CS499.TextBasedGame.Game.GameMap;
import com.CS499.TextBasedGame.Game.GameOverException;
import com.CS499.TextBasedGame.Game.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    public final ArrayList<Item> backpack;
    private Room currentRoom;

    public Player(int x, int y) {
        this.backpack = new ArrayList<>();
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Displays information about the current room, allows item pickup,
     * and handles player movement.
     *
     * @param gamemap the game map to navigate
     * @throws GameOverException if game-ending conditions are met
     */
    public void displayCurrentRoom(GameMap gamemap) throws GameOverException {
        System.out.printf("You are in the %s\n", currentRoom.getName());
        System.out.println(currentRoom.getDescription());

        pickUpItem(currentRoom, backpack);

        move(gamemap);
    }

    /**
     * Prompts the player to pick up an item in the current room if available.
     *
     * @param currentRoom the room the player is currently in
     * @param backpack the player's inventory list
     * @throws GameOverException thrown when all items have been collected
     */
    public void pickUpItem(Room currentRoom, List<Item> backpack) throws GameOverException {
        Scanner sc = new Scanner(System.in);

        if (currentRoom.hasItem() && currentRoom.getItem() != null) {
            Item roomItem = currentRoom.getItem();

            if (backpack.contains(roomItem)) {
                System.out.println("You already have the " + roomItem.name() + ".");
                return;
            }

            System.out.printf("Pick up the %s? (Y/N): ", roomItem.name());
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                backpack.add(roomItem);
                System.out.printf("You have picked up the %s.%n", roomItem.name());

                if (hasAllRequiredItems()) {
                    System.out.println("Congratulations! You have collected all the required items!");
                    throw new GameOverException("You have collected all the required items and won the game!");
                }
            }
        }
    }

    /**
     * Picks up the given item directly, adding it to the backpack.
     * This method is used for testing
     *
     * @param item the item to pick up
     */
    public void pickUpItem(Item item) {
        if (item != null && !backpack.contains(item)) {
            backpack.add(item);
            System.out.printf("You have picked up the %s.%n", item.name());
        }
    }

    /**
     * Prompts player for a direction to move, updates the current room,
     * and checks for game-ending conditions.
     *
     * @param gamemap the game map to navigate
     * @throws GameOverException if game-ending conditions are met
     */
    public void move(GameMap gamemap) throws GameOverException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Which direction would you like to move? (NORTH, EAST, SOUTH, WEST)");
            String input = sc.nextLine().toUpperCase();

            try {
                Direction direction = Direction.valueOf(input);
                Room newRoom = gamemap.moveRoom(currentRoom, direction);

                if (newRoom == null) {
                    System.out.println("You can't move that way!");
                    continue;
                }

                setCurrentRoom(newRoom);

                // If the player reaches the Garage without all items, game over
                if ("Garage".equals(newRoom.getName())) {
                    if (!hasAllRequiredItems()) {
                        System.out.println(newRoom.getDescription());
                        throw new GameOverException("You haven't collected all necessary items! Game over!");
                    } else {
                        System.out.println("You've collected all the items and defeated the zombie!");
                        System.out.println("You inject the cure and escape through the garage tunnel.");
                        throw new GameOverException("You have collected all the items and defeated the zombie infestation!");
                    }
                }

                break;

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid direction. Try: NORTH, EAST, SOUTH, WEST.");
            }
        }
    }

    /**
     * Checks whether the player's backpack contains all required items.
     *
     * @return true if all required items are present, false otherwise
     */
    public boolean hasAllRequiredItems() {
        String[] requiredItems = {"Syringe", "KeyCard", "Z-Boost", "NanoMed", "Mixer Bottle"};

        for (String Name : requiredItems) {
            boolean found = false;
            for (Item item : backpack) {
                if (item.name().equals(Name)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
}
