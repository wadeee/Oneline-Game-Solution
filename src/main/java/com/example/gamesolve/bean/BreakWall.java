package com.example.gamesolve.bean;

import lombok.Data;

@Data
public class BreakWall {
    private boolean rightBreak;
    private boolean bottomBreak;

    public BreakWall() {
        rightBreak = false;
        bottomBreak = false;
    }
}
