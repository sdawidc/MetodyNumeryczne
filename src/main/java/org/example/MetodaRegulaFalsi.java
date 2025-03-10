package org.example;

public class MetodaRegulaFalsi {
    public static Wynik regulaFalsi(Funkcja funkcja, double a, double b, double e, int maxIter) {
        double fa = funkcja.wartosc(a);
        double fb = funkcja.wartosc(b);

        if (fa * fb > 0) {
            throw new IllegalArgumentException("Brak miejsca zerowego w tym przedziale" + a + ", " + b);
        }

        double xS = a;
        double xN = 0.0;
        for (int i = 0; i < maxIter; i++) {
            xN = b - fb * (b - a) / (fb - fa);

            if (Math.abs(xN - xS) < e) {
                return new Wynik(xN, i + 1);
            }

            double fN = funkcja.wartosc(xN);

            if (fa * fN < 0) {
                //[a, xN]
                b = xN;
                fb = fN;
            } else {
                //[xN, b]
                a = xN;
                fa = fN;

            }
            xS = xN;
        }
        return new Wynik(xN, maxIter);
    }
}