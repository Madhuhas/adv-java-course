package com.example.kotini_sai_madhuhas_700762696_hw2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort {

    public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
        RecursiveAction mainTask = new SortTask<>(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask<E extends Comparable<E>> extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private E[] list;

        SortTask(E[] list) {
            this.list = list;
        }

        @Override
        protected void compute() {
            if (list.length > 1) {
                // Split the array into halves
                int mid = list.length / 2;
                E[] firstHalf = (E[]) (new Comparable[mid]);
                System.arraycopy(list, 0, firstHalf, 0, mid);

                E[] secondHalf = (E[]) (new Comparable[list.length - mid]);
                System.arraycopy(list, mid, secondHalf, 0, list.length - mid);

                // Recursively sort each half
                invokeAll(new SortTask<>(firstHalf), new SortTask<>(secondHalf));

                // Merge the sorted halves
                merge(firstHalf, secondHalf, list);
            }
        }

        private void merge(E[] list1, E[] list2, E[] temp) {
            int current1 = 0; // Current index in list1
            int current2 = 0; // Current index in list2
            int current3 = 0; // Current index in temp

            while (current1 < list1.length && current2 < list2.length) {
                if (list1[current1].compareTo(list2[current2]) < 0)
                    temp[current3++] = list1[current1++];
                else
                    temp[current3++] = list2[current2++];
            }

            while (current1 < list1.length)
                temp[current3++] = list1[current1++];

            while (current2 < list2.length)
                temp[current3++] = list2[current2++];
        }
    }

    public static void main(String[] args) {
        // Test with an array of integers
        Integer[] intArray = { 3, 2, 5, 4, 1, 6, 8, 7 };
        System.out.println("Original integer array:");
        printArray(intArray);

        parallelMergeSort(intArray);

        System.out.println("Sorted integer array:");
        printArray(intArray);

        // Test with an array of strings
        String[] stringArray = { "apple", "orange", "banana", "pear", "grape", "kiwi" };
        System.out.println("Original string array:");
        printArray(stringArray);

        parallelMergeSort(stringArray);

        System.out.println("Sorted string array:");
        printArray(stringArray);
    }

    public static <E> void printArray(E[] array) {
        for (E e : array) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}
