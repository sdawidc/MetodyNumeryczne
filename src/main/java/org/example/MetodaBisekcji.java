package org.example;

public class MetodaBisekcji {
    public static Wynik bisekcja(Funkcja funkcja, double a, double b, double e, int maxIter) {
        double fa = funkcja.wartosc(a);
        double fb = funkcja.wartosc(b);

        if (fa * fb >= 0) {
            throw new IllegalArgumentException("Przedział [" + a +  ", " + b + "] jest nieprawidłowy");
        }

        double xS = a; //poprzednie przyblizenie
        double xMid = 0.0; //biezacy srodek

        for (int i = 0; i < maxIter; i++) {
            xMid = (a + b) / 2.0; // obliczamy srodek

            if (Math.abs(xMid - xS) < e) { // sprawdzamy roznice
                return new Wynik(xMid, i + 1);
            }
            double fMid = funkcja.wartosc(xMid);
            if (fa * fMid < 0) {
                b = xMid; // przesuwamy prawa granice [a, xMId]
                fb = fMid;
            } else if (fb * fMid < 0) {
                a = xMid; //tutaj w [xMid. b]
                fa = fMid;
            }
            xS = xMid;
        }
      return new Wynik(xMid, maxIter);


    }

}