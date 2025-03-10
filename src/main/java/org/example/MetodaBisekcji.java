package org.example;

public class MetodaBisekcji {
    public static double bisekcja(Funkcja funkcja, double a, double b, double e, int maxIter) {
        double fa = funkcja.wartosc(a);
        double fb = funkcja.wartosc(b);

        if (fa * fb > 0) {
            throw new IllegalArgumentException("Brak miejsca zerowego w tym przedziale" + a +  ", " + b);
        }

        double xS = a; //poprzednie przyblizenie
        double xMid = 0.0; //biezacy srodek

        for (int i = 0; i < maxIter; i++) {
            xMid = (a + b) / 2.0; // obliczamy srodek

            if (Math.abs(xMid - xS) < e) { // sprawdzamy roznice
                return xMid;
            }
            double fMid = funkcja.wartosc(xMid);
            if (fa * fMid < 0) {
                b = xMid; // przesuwamy prawa granice [a, xMId]
                fb = fMid;
            } else {
                a = xMid; //tutaj w [xMid. b]
                fa = fMid;
            }
            xS = xMid;

        }
        return xMid;


    }
}