import java.util.Random;

public class Problem2 {

    public static void main(String[] args) {
        Integer[] intArray = generateRandomIntArray(100, 0, 10000);
        System.out.println("Original array of integers:");
        printArray(intArray);
        Integer maxInteger = maxElement(intArray);
        System.out.println("Maximum element in the array of integers: " + maxInteger);
        System.out.println();
        Double[] doubleArray = generateRandomDoubleArray(100, 0.0, 1.0);
        System.out.println("Original array of doubles:");
        printArray(doubleArray);
        Double maxDouble = maxElement(doubleArray);
        System.out.println("Maximum element in the array of doubles: " + maxDouble);
    }

    public static <E extends Comparable<E>> E maxElement(E[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        E max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public static Integer[] generateRandomIntArray(int size, int min, int max) {
        Integer[] array = new Integer[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

    public static Double[] generateRandomDoubleArray(int size, double min, double max) {
        Double[] array = new Double[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextDouble() * (max - min) + min;
        }

        return array;
    }

    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
