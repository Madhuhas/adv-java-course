package com.example.kotini_sai_madhuhas_700762696_hw2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Synchronized_threads {
    public static void main(String[] args) {
        System.out.println("Running Unsynchronized Version:");
        runUnsynchronizedVersion();

        System.out.println("Running Synchronized Version:");
        runSynchronizedVersion();
    }

    // Unsynchronized version
    private static void runUnsynchronizedVersion() {
        SumClass sum = new SumClass(0);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Create and launch 1000 threads
        for (int i = 0; i < 1000; i++) {
            executor.execute(new IncrementSumTask(sum));
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("Unsynchronized version: " + sum);
    }

    // Synchronized version
    private static void runSynchronizedVersion() {
        SumClassSynchronized sum = new SumClassSynchronized(0);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Create and launch 1000 threads
        for (int i = 0; i < 1000; i++) {
            executor.execute(new IncrementSumTaskSynchronized(sum));
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("Synchronized version: " + sum);
    }

    // Task for unsynchronized version
    private static class IncrementSumTask implements Runnable {
        private final SumClass sum;

        IncrementSumTask(SumClass sum) {
            this.sum = sum;
        }

        @Override
        public void run() {
            sum.incrementSum(1);
        }
    }

    // Task for synchronized version
    private static class IncrementSumTaskSynchronized implements Runnable {
        private final SumClassSynchronized sum;

        IncrementSumTaskSynchronized(SumClassSynchronized sum) {
            this.sum = sum;
        }

        @Override
        public void run() {
            sum.incrementSum(1);
        }
    }

    // Unsynchronized SumClass
    private static class SumClass {
        private Integer sum;

        SumClass(Integer sum) {
            this.sum = sum;
        }

        public Integer getSum() {
            return this.sum;
        }

        public void incrementSum(Integer val) {
            sum += val;
        }

        @Override
        public String toString() {
            return "Sum is " + sum;
        }
    }

    // Synchronized SumClass
    private static class SumClassSynchronized {
        private Integer sum;

        SumClassSynchronized(Integer sum) {
            this.sum = sum;
        }

        public Integer getSum() {
            return this.sum;
        }

        public synchronized void incrementSum(Integer val) {
            sum += val;
        }

        @Override
        public String toString() {
            return "Sum is " + sum;
        }
    }
}
