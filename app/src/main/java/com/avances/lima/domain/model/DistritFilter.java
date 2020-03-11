package com.avances.lima.domain.model;

import java.io.Serializable;

public class DistritFilter implements Serializable {


    private DistritNeighborhood distritNeighborhood;
    private boolean pressed;

    public DistritFilter() {
    }

    public DistritFilter(DistritNeighborhood distritNeighborhood, boolean pressed) {
        this.distritNeighborhood = distritNeighborhood;
        this.pressed = pressed;
    }


    public DistritNeighborhood getDistritNeighborhood() {
        return distritNeighborhood;
    }

    public void setDistritNeighborhood(DistritNeighborhood distritNeighborhood) {
        this.distritNeighborhood = distritNeighborhood;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
