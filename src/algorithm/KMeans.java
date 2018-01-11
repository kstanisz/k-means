package algorithm;

import model.Cluster;
import model.Point;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KMeans {

    private List<Point> points;
    private List<Cluster> clusters = new ArrayList<>();

    public KMeans(List<Point> initPoints, int nrOfClusters) {
        this.points = initPoints;
        initClusters(nrOfClusters);
    }

    private void initClusters(int nrOfClusters) {
        if (points.size() < nrOfClusters) {
            nrOfClusters = points.size();
        }

        Collections.shuffle(points);

        for (int i = 0; i < nrOfClusters; i++) {
            Cluster cluster = new Cluster();
            cluster.setId(i);
            cluster.setCenter(points.get(i));
            clusters.add(cluster);
        }
        assignPointsForClusters();
        assignNamesForClusters();
    }

    public List<Cluster> run(int nrOfIterations) {
        for (int i = 0; i < nrOfIterations; i++) {
            singleRun();
        }
        return clusters;
    }

    private void singleRun() {
        findNewCentersForClusters();
        assignPointsForClusters();
        assignNamesForClusters();
    }

    private void findNewCentersForClusters() {
        clusters.stream()
                .filter(cluster -> !cluster.getPoints().isEmpty())
                .forEach(cluster -> cluster.setCenter(CoordinatesCalculator.getCenter(cluster.getPoints())));
    }

    private void assignPointsForClusters() {
        clusters.forEach(cluster -> cluster.setPoints(new ArrayList<>()));
        for (Point point : points) {
            Map<Cluster, Double> clustersWithDistance = clusters.stream()
                    .collect(Collectors.toMap(Function.identity(), x -> CoordinatesCalculator.getDistance(point, x.getCenter())));

            Cluster nearestCluster = clustersWithDistance.entrySet().stream()
                    .min(Comparator.comparing(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .orElse(null);

            point.setSelectedCluster(nearestCluster.getName());
            clusters.get(nearestCluster.getId()).getPoints().add(point);
        }
    }

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