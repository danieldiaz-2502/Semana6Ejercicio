package com.example.ejercicios6;

public class Circulo {
    private int movx,movy,r,g,b;

    public Circulo(int movx,int movy,int r,int g,int b) {
        this.movx = movx;
        this.movy = movy;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getMovx() {
        return movx;
    }

    public void setMovx(int movx) {
        this.movx = movx;
    }

    public int getMovy() {
        return movy;
    }

    public void setMovy(int movy) {
        this.movy = movy;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
