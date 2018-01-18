import algorithm.KMeans;
import algorithm.MetricsCalculator;
import graphics.ClusteringVisualization;
import model.Cluster;
import model.Metrics;
import model.Point;
import service.DataLoader;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Arguments: fileName, nrOfClusters, maxIterations[optional]");
            return;
        }
        String fileName = args[0];
        int nrOfClusters = Integer.parseInt(args[1]);
        Integer maxIterations = args.length == 3 ? Integer.parseInt(args[2]) : null;

        DataLoader dataLoader = new DataLoader();
        List<Point> points = null;
        try {
            points = dataLoader.readPoints("dataset/" + fileName);
        } catch (IOException e) {
            System.out.println("Could not read input file: " + fileName);
            System.exit(1);
        }

        System.out.println("Number of points: " + points.size());

        // Run K-means algorithm
        KMeans kMeans = new KMeans(points, nrOfClusters, maxIterations);
        List<Cluster> clusters = kMeans.run();

        // Compute metrics
        MetricsCalculator metricsCalculator = new MetricsCalculator();
        Metrics metrics = metricsCalculator.calculate(clusters);

        // Visualize results
        ClusteringVisualization visualization = new ClusteringVisualization(clusters);
        visualization.drawImage(fileName.replace(".csv", ".png"));

        // Print metrics
        System.out.println("Metrics: ");
        System.out.println(metrics);
        clusters.forEach(x -> System.out.println(x.printMetrics()));
    }
}