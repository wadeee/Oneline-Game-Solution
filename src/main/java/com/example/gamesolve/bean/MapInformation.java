package com.example.gamesolve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MapInformation {

    private List<List<Integer>> status;

    private List<List<Integer>> inDegree;

    private static int MAP_WIDTH = 12;

    public MapInformation() {
        status = new ArrayList<>();
        inDegree = new ArrayList<>();
        for (int i = 0; i<MAP_WIDTH; i ++ ) {
            List<Integer> statusRow = new ArrayList<>();
            List<Integer> inDegreeRow = new ArrayList<>();
            for (int j = 0; j<MAP_WIDTH; j++) {
                statusRow.add(0);
                inDegreeRow.add(0);
            }
            status.add(statusRow);
            inDegree.add(inDegreeRow);
        }
    }
}
