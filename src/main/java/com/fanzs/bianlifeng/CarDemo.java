package com.fanzs.bianlifeng;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CarDemo {

    public static void main(String[] args) {
        int target = 12;
        int[] positions = new int[]{10, 8, 0, 5, 3};
        int[] speeds = new int[]{2, 4, 1, 1, 3};
        System.out.println(countCar(target, positions, speeds));
    }

    public static int countCar(int target, int[] positions, int[] speeds) {
        if (positions == null || positions.length < 1 || speeds == null || speeds.length < 1) {
            return 0;
        }
        if (target < 1 || positions.length == 1 || speeds.length == 1) {
            return 1;
        }

        for (int i : speeds) {
            if (i == 0) {
                throw new RuntimeException("speed is 0");
            }
        }

        Map<Integer, Double> position2TimeMap = new HashMap<>();
        for (int i = 0; i < positions.length; ++i) {
            double time = 1.0 * (target - positions[i]) / speeds[i];
            if (position2TimeMap.get(positions[i]) == null) {
                position2TimeMap.put(positions[i], time);
            } else if (position2TimeMap.get(positions[i]) < time) {
                position2TimeMap.put(positions[i], time);
            }
        }

        int[] sortPositions = Arrays.copyOf(positions, positions.length);
        Arrays.sort(sortPositions);
        int count = 1;
        double lastMaxTime = position2TimeMap.get(sortPositions[sortPositions.length - 1]);
        for (int i = sortPositions.length - 2; i >= 0; --i) {
            if (position2TimeMap.get(sortPositions[i]) > lastMaxTime) {
                ++count;
                lastMaxTime = position2TimeMap.get(sortPositions[i]);
            }
        }
        return count;
    }
}
