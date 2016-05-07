package ru.nsu.fit.g13205.fink.raytracing;

import java.util.ArrayList;
import java.util.List;

public class Sphere extends Shape {

    private final int n = 2;
    private final int m = 10;
    private final int k = 2;
    private Coordinate3D center;
    private double radius;

    public Sphere(Coordinate3D center, double radius, double kdr, double kdg, double kdb, double ksr, double ksg, double ksb, double power) {
        super(kdr, kdg, kdb, ksr, ksg, ksb, power, ShapeType.SPHERE);
        this.center = center;
        this.radius = radius;
    }

    public Coordinate3D getCenter() {
        return center;
    }

    public void setCenter(Coordinate3D center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getMin() {
        return Math.min(Math.min(center.x, center.y), center.z) - radius;
    }

    @Override
    public double getMax() {
        return Math.max(Math.max(center.x, center.y), center.z) + radius;
    }

    @Override
    public List<Segment> getCoordinate() {
        List<Segment> segmentList = new ArrayList<>();
        double stepNK = Math.PI / n / k;
        double stepN = Math.PI / n;
        double stepMK = 2 * Math.PI / m / k;
        double stepM = 2 * Math.PI / m;
        Coordinate3D last;
        Coordinate3D c;
        for (int i = 0; i < n; i++) {
            last = new Coordinate3D(center.x, center.y + Math.sin(i * stepN) * radius, center.z + Math.cos(i * stepN) * radius);
            for (int j = 0; j <= k; j++) {
                c = new Coordinate3D(center.x, center.y + Math.sin(stepNK * j + i * stepN) * radius, center.z + Math.cos(stepNK * j + i * stepN) * radius);
                segmentList.add(new Segment(c, last));
                last = c;
            }
            /*double z = center.z + Math.cos((i + 1) * stepN) * radius;
            double rad = center.y + Math.sin((i + 1) * stepN) * radius;
            for (int j = 0; j < m; j++) {
                for (int l = 0; l <= k; l++) {
                    c = new Coordinate3D(center.x + Math.sin(stepMK * j + i * stepM) * rad, center.y + Math.cos(stepMK * j + i * stepM) * rad, z);
                    segmentList.add(new Segment(c, last));
                    last = c;
                }
            }*/
        }
        return segmentList;
    }

}