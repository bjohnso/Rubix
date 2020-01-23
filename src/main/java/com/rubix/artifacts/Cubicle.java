package com.rubix.artifacts;

import java.awt.*;

public class Cubicle {
    private Node3D node3D;
    private String position;

    public Cubicle(String position) {
        this.position = position;
        initNode();
    }

    private void initNode() {
        this.node3D = new Node3D(this.position);
        if (position.charAt(1) == '1')
            node3D.addFace("F", Color.RED);
        if (position.charAt(1) == '3')
            node3D.addFace("B", Color.MAGENTA);
        if (position.charAt(0) == '1')
            node3D.addFace("D", Color.WHITE);
        if (position.charAt(0) == '3')
            node3D.addFace("U", Color.YELLOW);
        if (position.charAt(2) == '1')
            node3D.addFace("L", Color.BLUE);
        if (position.charAt(2) == '3')
            node3D.addFace("R", Color.GREEN);
        this.node3D.setCurrentCubicle(position);
    }

    public Node3D getNode3D() {
        return node3D;
    }

    public String getPosition() {
        return position;
    }

    public void setNode3D(Node3D node3D) {
        this.node3D = node3D;
        this.node3D.setCurrentCubicle(position);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Cubicle clone() {
        Cubicle clone = new Cubicle(position);
        clone.setNode3D(node3D);
        return clone;
    }
}