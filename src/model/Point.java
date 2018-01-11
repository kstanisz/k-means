package model;

public class Point {
    private String name;
    private String originalCluster;
    private String selectedCluster;
    private double latitude;
    private double longitude;

    public Point(){}

    public Point(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalCluster() {
        return originalCluster;
    }

    public void setOriginalCluster(String originalCluster) {
        this.originalCluster = originalCluster;
    }

    public String getSelectedCluster() {
        return selectedCluster;
    }

    public void setSelectedCluster(String selectedCluster) {
        this.selectedCluster = selectedCluster;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Point{" +
                "name='" + name + '\'' +
                ", originalCluster='" + originalCluster + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
