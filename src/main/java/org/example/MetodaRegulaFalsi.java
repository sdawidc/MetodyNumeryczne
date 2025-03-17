package org.example;

public class MetodaRegulaFalsi {
    public static Wynik regulaFalsi(Funkcja funkcja, double a, double b, double e, int maxIter) {
        double fa = funkcja.wartosc(a);
        double fb = funkcja.wartosc(b);

        if (fa * fb >= 0) {
            throw new IllegalArgumentException("Przedział [" + a +  ", " + b + "] jest nieprawidłowy");
        }

        double xS = a;
        double xN = 0.0;
        for (int i = 0; i < maxIter; i++) {
            //xN = b - fb * (b - a) / (fb - fa);
            xN = a - (fa*(b-a))/(fb-fa);

            if (Math.abs(xN - xS) < e) {
                return new Wynik(xN, i + 1);
            }

            double fN = funkcja.wartosc(xN);

            if (fN == 0) {
                return new Wynik(xN, i+1);
            }

            if (fa * fN < 0) {
                //[a, xN]
                b = xN;
                fb = fN;
            } else if (fb * fN < 0) {
                //[xN, b]
                a = xN;
                fa = fN;

            }
            xS = xN;
        }
        return new Wynik(xN, maxIter);
    }

}