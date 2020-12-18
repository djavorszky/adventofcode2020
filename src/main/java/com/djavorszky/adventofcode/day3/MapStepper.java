package com.djavorszky.adventofcode.day3;


import java.util.List;


public class MapStepper {

    private final int stepRight;
    private final int stepDown;
    private final List<String> map;
    private final int mapWidth;

    public MapStepper(int stepRight, int stepDown, List<String> map) {
        this.stepRight = stepRight;
        this.stepDown = stepDown;
        this.map = map;

        this.mapWidth = map.get(0).length();
    }

    public long countTrees() {
        int numberOfTrees = 0;

        int currentRow = 0;
        int currentCol = 0;

        while (currentRow < map.size()) {
            String currentLine = map.get(currentRow);

            if (currentLine.charAt(currentCol) == '#') {
                numberOfTrees++;
            }

            currentRow += stepDown;
            currentCol = (currentCol + stepRight) % mapWidth;
        }

        return numberOfTrees;
    }
}
