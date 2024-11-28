package com.github.marinkay;

import org.apache.commons.csv.CSVFormat;
import smile.clustering.HierarchicalClustering;
import smile.clustering.linkage.CompleteLinkage;
import smile.io.Read;
import smile.plot.swing.Dendrogram;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException, IOException, URISyntaxException {
        double[][] x = Read.csv("C:\\Users\\user\\Desktop\\projects\\hierarchical-clustering\\src\\main\\resources\\data\\clustering\\rem.txt", CSVFormat.DEFAULT.withDelimiter(' ')).toArray();

        // Выполнение иерархической кластеризации
        HierarchicalClustering clusters = HierarchicalClustering.fit(CompleteLinkage.of(x));

        // Построение дендрограммы
        Dendrogram plot = new Dendrogram(clusters.tree(), clusters.height());
        plot.canvas().window();

        // Для оценки F-меры нам нужны истинные метки классов
        // Предположим, что у вас есть истинные метки классов в виде массива
        int[] trueLabels = {0, 1, 1, 0, 0, 1, 2, 2, 0, 1}; // Пример истинных меток классов

        // Получение предсказанных меток классов из кластеризации
        int[] predictedLabels = new int[x.length];
        for (int i = 0; i < clusters.size(); i++) {
            for (int j : clusters.get(i)) {
                predictedLabels[j] = i;
            }
        }

        // Оценка качества кластеризации с помощью F-меры
        var f = new FMeasure

        // Вывод результата
        System.out.println("F-мера: " + fMeasure);
    }
}
