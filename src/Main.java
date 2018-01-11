import algorithm.KMeans;
import algorithm.MetricsCalculator;
import model.Cluster;
import model.Metrics;
import model.Point;
import service.DataLoader;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        List<Point> points = null;
        try {
            points = dataLoader.readPoints("data.csv");
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku wejściowego");
            System.exit(1);
        }

        KMeans kMeans = new KMeans(points, 3);
        List<Cluster> clusters = kMeans.run(10);

        MetricsCalculator metricsCalculator = new MetricsCalculator();
        Metrics metrics = metricsCalculator.calculate(clusters);

        System.out.println(metrics);
        System.out.println("This is the end");
    }
}
