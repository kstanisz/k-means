import algorithm.KMeans;
import algorithm.MetricsCalculator;
import gui.Gui;
import model.Cluster;
import model.Metrics;
import model.Point;
import service.DataLoader;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        List<Point> points = null;
        try {
            points = dataLoader.readPoints("dataset/allUSCities.csv");
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku wejściowego");
            System.exit(1);
        }

        KMeans kMeans = new KMeans(points, 50);
        List<Cluster> clusters = kMeans.run(10);

        MetricsCalculator metricsCalculator = new MetricsCalculator();
        Metrics metrics = metricsCalculator.calculate(clusters);

        EventQueue.invokeLater(() -> {
            Gui gui = new Gui();
            gui.setVisible(true);
        });

        System.out.println("Ocena jakości grupowania:");
        System.out.println(metrics);
        clusters.forEach(x -> System.out.println(x.printMetrics()));
    }
}
