package com.github.marinkay;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Data
public class Cluster {
    List<Point> points = new ArrayList<>();
    double centroidX, centroidY;
    String clusterIndex;

    Cluster(String index, Point point) {
        clusterIndex = index;
        points.add(point);
        updateCentroid();
    }

    public void updateCluster(Point point) {
        points.add(point);
        updateCentroid();
    }

    void updateCentroid() {
        double sumX = centroidX, sumY = centroidY;
        for (Point p : points) {
            sumX += p.x;
            sumY += p.y;
        }
        centroidX = sumX / points.size();
        centroidY = sumY / points.size();
    }

    double distance(Cluster other) {
        return new Point(centroidX, centroidY).distance(new Point(other.centroidX, other.centroidY));
    }

    double averageIntraClusterDistance() {
        double totalDistance = 0;
        int count = 0;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                totalDistance += points.get(i).distance(points.get(j));
                count++;
            }
        }
        return count > 0 ? totalDistance / count : 0;
    }
}