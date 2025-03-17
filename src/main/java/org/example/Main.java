package org.example;
import java.sql.SQLOutput;
import java.util.Scanner;
public class Main {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz Funkcje");
        System.out.println("1. f(x) = -2x^3 + x^2 - 6x + 3");
        System.out.println("2. f(x) = sin(x) - 0.5");
        System.out.println("3. f(x) = exp(x) - 2");
        System.out.println("4. f(x) = sin(exp(x^2-4)+cos(-exp(x))");
        System.out.println("5. Wielomian (użytkownik podaje stopień i współczynniki)");

        System.out.println("Twoj wybor:");
        int wyborFunkcji = scanner.nextInt();
        scanner.nextLine();

        String opisFunkcji = "";
        Funkcja funkcja = null;
        switch ((wyborFunkcji)) {
            case 1:
                double[] wspS = new double[4];
                wspS[0] = 3;
                wspS[1] = -6;
                wspS[2] = 1;
                wspS[3] = -2;
                funkcja = new Funkcja() {
                    @Override
                    public double wartosc(double x) {
                        return Horner.obliczWielomian(x, wspS);
                    }
                };
                opisFunkcji = "f(x) = -2x^3 + x^2 - 6x + 3";
                break;
            case 2:
                funkcja = new Funkcja() {
                    @Override
                    public double wartosc(double x) {
                        return Math.sin(x) - 0.5;
                    }
                };
                opisFunkcji = "f(x) = sin(x) - 0.5";
                break;

            case 3:
                funkcja = new Funkcja() {
                    @Override
                    public double wartosc(double x) {
                        return Math.exp(x) - 2;
                    }
                };
                opisFunkcji = "f(x) = exp(x) - 2";
                break;
            case 4:
                double[] wspZ = new double[3];
                wspZ[0] = -4;
                wspZ[1] = 0;
                wspZ[2] = 1;
                funkcja = new Funkcja() {
                    @Override
                    public double wartosc(double x) {
                        return Math.sin(Math.exp(Horner.obliczWielomian(x, wspZ))) + Math.cos(-Math.exp(x));
                    }
                };
                opisFunkcji = "f(x) = sin(exp(x^2-4))+cos(-exp(x))";
                break;
            case 5:
                System.out.println("Podaj stopien wielomianu: ");
                int n = scanner.nextInt();
                scanner.nextLine();

                double [] wsp = new double[n+1];
                for (int i =0; i <= n; i++) {
                    System.out.println("Podaj wspolczynnik a" + i+ ": ");
                    wsp[i] = scanner.nextDouble();
                }
                scanner.nextLine();
                funkcja = new Funkcja() {
                    @Override
                    public double wartosc(double x) {
                        return Horner.obliczWielomian(x, wsp);
                    }
                };
                String wielomianString = "";
                for(int i = wsp.length - 1; i >= 0; i--){
                    if (wsp[i] == (int)wsp[i]) {
                        wielomianString += (int)wsp[i] + "x^" + i;
                    } else {
                        wielomianString += wsp[i] + "x^" + i;
                    }
                    if (i>0) {
                        if (wsp[i-1] >= 0){
                            wielomianString += "+";
                        }
                    }
                }
                opisFunkcji = "Wielomian stopnia " + n + ": "+ wielomianString +" (wczytany przez użytkownika)";
                break;

            default:
                System.out.println("Nieprawidłowy wybór funkcji");
                return;


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
            e = scanner.nextDouble();

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