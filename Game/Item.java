package com.CS499.TextBasedGame.Game;

public record Item(String name) {

    public static final Item SYRINGE = new Item("Syringe");
    public static final Item KEYCARD = new Item("KeyCard");
    public static final Item Z_BOOST = new Item("Z-Boost");
    public static final Item NANOMED = new Item("NanoMed");
    public static final Item MIXER_BOTTLE = new Item("Mixer Bottle");

    @Override
    public String toString() {
        return name;
    }
}