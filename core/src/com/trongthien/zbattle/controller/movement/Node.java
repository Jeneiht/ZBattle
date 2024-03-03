package com.trongthien.zbattle.controller.movement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private int x;
    private int y;
    private Node parent;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
