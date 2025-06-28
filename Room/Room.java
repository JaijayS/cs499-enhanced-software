package com.CS499.TextBasedGame.Room;

import com.CS499.TextBasedGame.Game.Item;

public enum Room {

    //    G
    //    K M
    //  C BR BU
    //    A


    CONTROL_ROOM("Control Room", 0, 2, new Item("Syringe"),
            "Sparks fly from broken panels. You sift through a first aid box hidden behind a console and find a syringe.",
            true),

    ARMORY("Armory", 1, 3, new Item("KeyCard"),
            "Rusted lockers line the walls. You discover a keycard half-covered in dust.",
            true),

    KITCHEN("Kitchen", 1, 1, new Item("Z-Boost"),
            "The fridge hums weakly. You spot a sealed can labeled 'Z-Boost'. Rumored to help with zombie infection symptoms.",
            true),

    BUNK_ROOM("Bunk Room", 2, 2, new Item("NanoMed"),
            "Underneath the torn sheets and clothes, You find a vial labeled 'NanoMed' hidden under a mattress.",
            true),

    BREAK_ROOM("Break Room", 1, 2, null,
            "Nothing but silence and dust. Nothing useful here. Look somewhere else",
            false),

    GARAGE("Garage", 1, 0, null,
            "**Oh no! A zombie lunges from behind a wrecked van!**",
            true),

    MEDICAL_ROOM("Medical", 2, 1, new Item("Mixer Bottle"),
            "You uncover a mixer bottle behind loose tiles. Perfect for mixing up the vaccine!",
            true);

    private final String name;
    private final int x;
    private final int y;
    private final String description;
    private Item item;
    private boolean hasItem;

    Room(String name, int x, int y, Item item, String description, boolean hasItem) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.item = item;
        this.description = description;
        this.hasItem = hasItem;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Item getItem() {
        return item;
    }

    public boolean hasItem() {
        return hasItem;
    }

    public String getDescription() {
        return description;
    }
}
