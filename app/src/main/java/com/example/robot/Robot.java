package com.example.robot;

import com.example.robot.enums.Direction;

public class Robot {
    private int x, y;
    private Direction direction;
    private final int gridMaxX, gridMaxY;

    public Robot(int x, int y, Direction direction, int gridMaxX, int gridMaxY) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.gridMaxX = gridMaxX;
        this.gridMaxY = gridMaxY;
    }

    public void executeCommand(char command) {
        switch (command) {
            case 'L':
                turnLeft();
                break;
            case 'R':
                turnRight();
                break;
            case 'M':
                moveForward();
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

    private void turnLeft() {
        direction = direction.turnLeft();
    }

    private void turnRight() {
        direction = direction.turnRight();
    }

    private void moveForward() {
        switch (direction) {
            case N:
                if (y + 1 <= gridMaxY) y++;
                break;
            case E:
                if (x + 1 <= gridMaxX) x++;
                break;
            case S:
                if (y - 1 >= 0) y--;
                break;
            case W:
                if (x - 1 >= 0) x--;
                break;
        }
    }

    public String getPosition() {
        return x + " " + y + " " + direction;
    }
}