package org.example;

public class Wynik {
    private double root;
    private int liczbaIter;

    public Wynik(double root, int liczbaIter) {
        this.root = root;
        this.liczbaIter = liczbaIter;
    }
    public double getRoot() {
        return root;
    }

    public int liczbaIter() {
        return liczbaIter;
    }


}
