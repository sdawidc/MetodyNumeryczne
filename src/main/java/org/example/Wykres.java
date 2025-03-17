package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Wykres extends JFrame {
    public Wykres(String tytulOkna, String rownanieFunkcji, Funkcja f, double a, double b, double wynikB, double wynikRF) {
        super(tytulOkna);


        XYSeries seriaFunkcji = new XYSeries("f(x)");
        int N = 100;
        double krok = (b - a) / (N - 1);
        double xx = a;

        for(int i = 0; i < N; i++) {
            double yy = f.wartosc(xx);
            seriaFunkcji.add(xx, yy);
            xx += krok;
        }

        XYSeries seriaB = new XYSeries("Bisekcja");
        seriaB.add(wynikB, 0.0);

        XYSeries seriaRF = new XYSeries("Regula Falsi");
        seriaRF.add(wynikRF, 0.0);

        XYSeriesCollection zbior = new XYSeriesCollection();
        zbior.addSeries(seriaFunkcji);
        zbior.addSeries(seriaB);
        zbior.addSeries(seriaRF);


        JFreeChart wykres = ChartFactory.createXYLineChart(
                rownanieFunkcji,
                "x",
                "f(x)",
                zbior
        );


        XYPlot plot = (XYPlot) wykres.getPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();

        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);


        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, true);
        Shape kolo = new Ellipse2D.Double(-4, -4, 8, 8);
        renderer.setSeriesShape(1, kolo);

        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShapesVisible(2, true);
        Shape kwadrat = new Rectangle2D.Double(-4, -4, 8, 8);
        renderer.setSeriesShape(2, kwadrat);

        ChartPanel panel = new ChartPanel(wykres);
        panel.setPreferredSize(new Dimension(800, 600));

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }


}