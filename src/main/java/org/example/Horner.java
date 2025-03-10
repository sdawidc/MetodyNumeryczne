package org.example;

public class Horner {
    public static double obliczWielomian(double x, double[] wspolczynniki) {
        int n = wspolczynniki.length - 1;
        double wynik = wspolczynniki[n];

        for (int i = n - 1; i >= 0; i--) {
            wynik = wynik * x + wspolczynniki[i];
        }
        return wynik;
    }
}
