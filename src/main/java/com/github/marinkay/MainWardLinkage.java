package com.github.marinkay;

import org.apache.commons.csv.CSVFormat;
import smile.clustering.HierarchicalClustering;
import smile.clustering.linkage.CompleteLinkage;
import smile.clustering.linkage.WardLinkage;
import smile.io.Read;
import smile.math.distance.EuclideanDistance;
import smile.plot.swing.Dendrogram;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.marinkay.Util.qualityClusteringAssessment;

public class MainWardLinkage {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException, IOException, URISyntaxException {
        double[][] x = Read.csv("C:\\Users\\user\\Desktop\\projects\\hierarchical-clustering\\src\\main\\resources\\data\\clustering\\rem.txt", CSVFormat.DEFAULT.withDelimiter(' ')).toArray();

        // Выполнение иерархической кластеризации
        HierarchicalClustering clusters = HierarchicalClustering.fit(WardLinkage.of(x));

        // Построение дендрограммы
        Dendrogram plot = new Dendrogram(clusters.tree(), clusters.height());
        plot.canvas().window();
        //оценка качества кластеризации
        qualityClusteringAssessment(x, 3, clusters);
    }

}
