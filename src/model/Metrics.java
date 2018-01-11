package model;

public class Metrics {

    private double accuracy;
    private double precision;
    private double recall;

    public Metrics(){}

    public Metrics(double accuracy, double precision, double recall) {
        this.accuracy = accuracy;
        this.precision = precision;
        this.recall = recall;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "accuracy=" + accuracy +
                ", precision=" + precision +
                ", recall=" + recall +
                '}';
    }
}
