package com.CS499.TextBasedGame.Game;

import com.CS499.TextBasedGame.Room.Room;

import java.util.List;

public class GameMap {
    private List<Room> rooms;

    public GameMap() {
        rooms = List.of(Room.CONTROL_ROOM, Room.ARMORY, Room.KITCHEN, Room.BUNK_ROOM,
                Room.BREAK_ROOM, Room.GARAGE, Room.MEDICAL_ROOM);
    }

    /**
     * Retrieves the room at the specified coordinates.
     *
     * @param x the x-coordinate of the room
     * @param y the y-coordinate of the room
     * @return the .Room at the coordinates or null if none exists
     */
    public Room getRoomByCoordinates(int x, int y) {
        for (Room r : rooms) {
            if (r.getX() == x && r.getY() == y) {
                return r;
            }
        }
        return null;
    }

    /**
     * Calculates and returns the room adjacent to the current room
     * in the specified direction.
     *
     * @param currentRoom the current room of Player
     * @param direction the direction to move
     * @return the adjacent room or null if no room exists in that direction
     */
    public Room moveRoom(Room currentRoom, Direction direction) {
        int x = currentRoom.getX();
        int y = currentRoom.getY();

        switch (direction) {
            case NORTH -> y -= 1;
            case EAST -> x += 1;
            case SOUTH -> y += 1;
            case WEST -> x -= 1;
        }

        return getRoomByCoordinates(x, y);
    }
}
