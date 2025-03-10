package org.example;
import java.sql.SQLOutput;
import java.util.Scanner;
public class Main {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz Funkcje");
        System.out.println("1. f(x) = x^2 - 4 ");
        System.out.println("2. f(x) = sin(x) - 0.5");
        System.out.println("Twoj wybor:");
        int wyborFunkcji = scanner.nextInt();
        scanner.nextLine();

        String opisFunkcji = "";
        Funkcja funkcja = null;
        switch ((wyborFunkcji)) {
            case 1:
                funkcja = new Funkcja() {
                    @Override
                    public double wartosc(double x) {
                        return x * x - 4;
                    }
                };
                opisFunkcji = "f(x) = x^2 - 4";
                break;
            case 2:
                funkcja = new Funkcja() {
                    @Override
                    public double wartosc(double x) {
                        return Math.sin(x) - 0.5;
                    }
                };
                opisFunkcji = "f(x) = sin(x) - 0.5";
                break; // nie chce mi sie wiecej


    }

        System.out.println("Podaj przedzial: ");
        System.out.println("Podaj wartosc a: ");
        double a = scanner.nextDouble();
        System.out.println("Podaj wartosc b: ");
        double b = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Wybierz kryterium zatrzymania");
        System.out.println("1. Dokladnosc epsilon");
        System.out.println("2. Maksymalna liczba iteracji");
        System.out.println("Twoj wybor: ");
        int wyborKryterium = scanner.nextInt();

        scanner.nextLine();

        double e = 0.0;
        int maxIter = 0;


        if (wyborKryterium == 1) {
            System.out.println("Podaj wartosc: ");
            String eStr = scanner.nextLine();
            e = Double.parseDouble(eStr);

            maxIter = Integer.MAX_VALUE;

        } else if (wyborKryterium == 2) {
            System.out.println("Podaj wartosc: ");
            maxIter = scanner.nextInt();

            e = 0;
        } else {
            System.out.println("Nieprawidlowy wybor...... mozna jakies domyslne ustawic");
        }

        Wynik wynikBisekcji = MetodaBisekcji.bisekcja(funkcja, a, b, e, maxIter);
        Wynik wynikRegulaFalsi = MetodaRegulaFalsi.regulaFalsi(funkcja, a, b, e, maxIter);

        double rootB = wynikBisekcji.getRoot();
        int iterB = wynikBisekcji.liczbaIter();

        double rootRF = wynikRegulaFalsi.getRoot();
        int iterRF = wynikRegulaFalsi.liczbaIter();
        System.out.println("Wynik metody Bisekcji: " + rootB + " (iteracji: " + iterB + ")");
        System.out.println("Wynik metody Regula Falsi: " + rootRF + " (iteracji: " + iterRF + ")");

        Wykres wykres = new Wykres("Wykres funkcji", opisFunkcji, funkcja, a, b, rootB, rootRF );
        wykres.setVisible(true);
        scanner.close();



}}