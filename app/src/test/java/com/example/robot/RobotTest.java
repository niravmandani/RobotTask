package com.example.robot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.robot.enums.Direction;

public class RobotTest {
    private Robot robot;

    @Before
    public void setUp() {
        robot = new Robot(1, 2, Direction.N, 5, 5);
    }

    @Test
    public void testInitialPosition() {
        assertEquals("1 2 N", robot.getPosition());
    }

    @Test
    public void testTurnLeft() {
        robot.executeCommand('L');
        assertEquals("1 2 W", robot.getPosition());
    }

    @Test
    public void testTurnRight() {
        robot.executeCommand('R');
        assertEquals("1 2 E", robot.getPosition());
    }

    @Test
    public void testMoveForwardNorth() {
        robot.executeCommand('M');
        assertEquals("1 3 N", robot.getPosition());
    }

    @Test
    public void testMoveForwardEast() {
        robot.executeCommand('R');
        robot.executeCommand('M');
        assertEquals("2 2 E", robot.getPosition());
    }

    @Test
    public void testMoveOutOfBounds() {
        robot.executeCommand('M'); // Move to (1, 3)
        robot.executeCommand('M'); // Move to (1, 4)
        robot.executeCommand('M'); // Move to (1, 5)
        robot.executeCommand('M'); // Attempt to move out of bounds
        assertEquals("1 5 N", robot.getPosition()); // Should not exceed the boundary
    }

    @Test
    public void testComplexNavigation() {
        // Test a sequence of commands.
        robot = new Robot(1, 2, Direction.N, 5, 5);
        String commands = "LMLMLMLMM";
        for (char command : commands.toCharArray()) {
            robot.executeCommand(command);
        }
        assertEquals("1 3 N", robot.getPosition());
    }

    @Test
    public void testDifferentStartingPosition() {
        robot = new Robot(3, 3, Direction.E, 5, 5);
        String commands = "MMRMMRMRRM";
        for (char command : commands.toCharArray()) {
            robot.executeCommand(command);
        }
        assertEquals("5 1 E", robot.getPosition());
    }

    @Test
    public void testInvalidCommand() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> robot.executeCommand('X')
        );
        assertEquals("Invalid command: X", exception.getMessage());
    }
}
