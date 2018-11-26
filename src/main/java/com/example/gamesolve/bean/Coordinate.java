package com.example.gamesolve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate implements Comparable<Coordinate> {

    private int x;

    private int y;

    public final static Coordinate[] fourSides = {new Coordinate(-1,0), new Coordinate(0, 1), new Coordinate(1, 0), new Coordinate(0, -1) };

    @Override
    public int compareTo(Coordinate o) {
        return this.x + this.y - o.x - o.y;
    }

}
