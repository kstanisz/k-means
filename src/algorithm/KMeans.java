package algorithm;

import model.Cluster;
import model.Point;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KMeans {

    private List<Point> points; // List of points for clustering
    private int nrOfClusters; // Expected number of clusters at the end of the algorithm
    private Integer maxIterations; // Maximum number of iterations
    private List<Cluster> clusters = new ArrayList<>(); // List of result clusters

    public KMeans(List<Point> initPoints, int nrOfClusters, Integer maxIterations) {
        this.points = initPoints;
        this.nrOfClusters = nrOfClusters;
        this.maxIterations = maxIterations;
    }

    /*
        Main method of k-means algorithm
     */
    public List<Cluster> run() {
        initClusters();

        int iterations;
        for (iterations = 0; ; iterations++) {
            if (maxIterations != null && maxIterations <= iterations) {
                break;
            }

            boolean newCenters = findNewCentersForClusters();
            if (!newCenters) {
                break;
            }

            assignPointsForClusters();
            assignNamesForClusters();
        }

        System.out.println("Number of iterations: " + iterations);
        return clusters;
    }

    /*
        Method to initialize clusters.
        It draws initial center of each cluster and then assigns points for clusters.
     */
    private void initClusters() {
        if (points.size() < nrOfClusters) {
            nrOfClusters = points.size();
        }

        Collections.shuffle(points);

        for (int i = 0; i < nrOfClusters; i++) {
            Cluster cluster = new Cluster();
            cluster.setCenter(points.get(i));
            clusters.add(cluster);
        }
        assignPointsForClusters();
        assignNamesForClusters();
    }

    /*
        Method to find new center for each cluster
        Returns true if the algorithm found new centers
     */
    private boolean findNewCentersForClusters() {
        boolean newCenters = false;
        for (Cluster cluster : clusters) {
            Point previousCenter = cluster.getCenter();
            Point newCenter = CoordinatesCalculator.getCenter(cluster.getPoints());
            cluster.setCenter(newCenter);

            newCenters |= !previousCenter.equals(newCenter);
        }

        return newCenters;
    }

    /*
        Method to assign points for clusters.
        For each point it computes the distance to center of each cluster and chooses the nearest one.
     */
    private void assignPointsForClusters() {
        clusters.forEach(cluster -> cluster.setPoints(new ArrayList<>()));
        for (Point point : points) {
            Map<Cluster, Double> clustersWithDistance = clusters.stream()
                    .collect(Collectors.toMap(Function.identity(), x -> CoordinatesCalculator.getDistance(point, x.getCenter())));

            Cluster nearestCluster = clustersWithDistance.entrySet().stream()
                    .min(Comparator.comparing(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .orElse(null);

            clusters.get(clusters.indexOf(nearestCluster)).getPoints().add(point);
        }
    }

    /*
        Method to assign names for clusters.
        Name of the cluster is the most common original cluster name form list of clustered points
     */
    private void assignNamesForClusters() {
        for (Cluster cluster : clusters) {
            Map<String, Long> originalClusterNamesFrequencies = cluster.getPoints().stream()
                    .map(Point::getOriginalCluster)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            String mostFrequentName = originalClusterNamesFrequencies.entrySet().stream()
                    .max(Comparator.comparing(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .orElse(null);

            cluster.setName(mostFrequentName);
        }
    }
}