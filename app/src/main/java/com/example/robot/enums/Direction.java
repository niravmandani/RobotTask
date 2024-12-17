package com.example.robot.enums;

public enum Direction {
    N, E, S, W;

    private static final Direction[] VALUES = values();
    private static final int SIZE = VALUES.length;

    public Direction turnLeft() {
        return VALUES[(this.ordinal() - 1 + SIZE) % SIZE];
    }

    public Direction turnRight() {
        return VALUES[(this.ordinal() + 1) % SIZE];
    }
}