package com.github.marinkay;

import smile.clustering.HierarchicalClustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static double averageInterClusterDistance(List<Cluster> clusters) {
        double totalDistance = 0;
        int count = 0;
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = i + 1; j < clusters.size(); j++) {
                totalDistance += clusters.get(i).distance(clusters.get(j));
                count++;
            }
        }
        return count > 0 ? totalDistance / count : 0;
    }

    // numberOfClusters задается на основе дендограммы
    public static void qualityClusteringAssessment(double[][] data, int numberOfClusters, HierarchicalClustering clusters) {
        Map<String, Cluster> clusterMap = new HashMap();
        int[] labels = clusters.partition(numberOfClusters);
        for (int i = 0; i < data.length; i++) {
            double[] pointsCoordinates = data[i];
            Point point = new Point(pointsCoordinates[0], pointsCoordinates[1]);
            String index = String.valueOf(labels[i]);
            Cluster cluster = clusterMap.get(index);
            if (cluster == null) {
                cluster = new Cluster(index, point);
                clusterMap.put(index, cluster);
            } else {
                cluster.updateCluster(point);
            }
        }
        for (Map.Entry<String, Cluster> clusterEntry : clusterMap.entrySet()) {
            Cluster cluster = clusterEntry.getValue();
            System.out.println("---- Cluster INDEX - " + clusterEntry.getKey() + " ----");
            System.out.printf("Centroid: (%.2f, %.2f)\n", cluster.getCentroidX(), cluster.getCentroidY());
            System.out.printf("Average Intra-Cluster Distance: %.2f\n", cluster.averageIntraClusterDistance());
        }
        List<Cluster> finalClusters = new ArrayList<>(clusterMap.values());
        double averageInterClusterDistance = averageInterClusterDistance(finalClusters);
        System.out.println("-------------------------------");
        System.out.printf("Average Inter-Cluster Distance: %.2f\n", averageInterClusterDistance);
    }
}
