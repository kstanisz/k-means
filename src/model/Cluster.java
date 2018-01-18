package model;

import java.util.List;

public class Cluster {
    private String name; // Cluster name
    private Point center; // Center of the cluster
    private List<Point> points; // List clustered points
    private Metrics metrics; // Clustering metrics

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public String printMetrics() {
        return "Cluster: " + name + ", " + metrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cluster cluster = (Cluster) o;

        if (name != null ? !name.equals(cluster.name) : cluster.name != null) return false;
        if (center != null ? !center.equals(cluster.center) : cluster.center != null) return false;
        if (points != null ? !points.equals(cluster.points) : cluster.points != null) return false;
        return metrics != null ? metrics.equals(cluster.metrics) : cluster.metrics == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (center != null ? center.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + (metrics != null ? metrics.hashCode() : 0);
        return result;
    }
}