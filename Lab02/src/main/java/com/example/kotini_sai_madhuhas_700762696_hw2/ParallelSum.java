package com.example.kotini_sai_madhuhas_700762696_hw2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSum {

    public static void main(String[] args) {
        final int N = 9000000;
        double[] list = new double[N];
        for (int i = 0; i < list.length; i++)
            list[i] = Math.random() * 2;

        System.out.println("Printing first 100 elements since the array consists of 9000000 double elements..");
        for (int i = 0; i < 100; i++) System.out.print(list[i] + " ");
        System.out.println();
        long startTime = System.currentTimeMillis();

        System.out.println("\nThe parallel sum is " + parallelSum(list));

        long endTime = System.currentTimeMillis();
        System.out.println("Parallel time with "
                + Runtime.getRuntime().availableProcessors() +
                " processors is " + (endTime - startTime) + " milliseconds");

        long startTime1 = System.currentTimeMillis();

        System.out.println("\nThe sequential sum is " + sequentialSum(list));

        long endTime1 = System.currentTimeMillis();
        System.out.println("Sequential time is " +
                (endTime1 - startTime1) + " milliseconds");
    }

    public static double sequentialSum(double[] list) {
        double sum = 0;
        for (int i = 0; i < list.length; i++) sum += list[i];
        return sum;
    }

    public static double parallelSum(double[] list) {
        RecursiveTask<Double> task = new SumTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    private static class SumTask extends RecursiveTask<Double> {
        private final static int THRESHOLD = 1000;
        private double[] list;
        private int low, high;

        public SumTask(double[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        public Double compute() {
            if (high - low <= THRESHOLD) {
                double sum = 0;
                for (int i = low; i < high; i++) {
                    sum += list[i];
                }
                return sum;
            } else {
                int mid = (low + high) / 2;
                RecursiveTask<Double> left = new SumTask(list, low, mid);
                RecursiveTask<Double> right = new SumTask(list, mid, high);

                right.fork();
                left.fork();
                return left.join() + right.join();
            }
        }
    }
}
