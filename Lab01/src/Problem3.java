import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {
        Integer[] randomIntegers = generateRandomIntArray(100, 0, 99);
        Arrays.sort(randomIntegers); // Binary search requires sorted array
        System.out.println("Sorted array of integers:");
        printArray(randomIntegers);
        int intKey = promptForInteger(); // Prompt user for integer key
        int intIndex = binarySearch(randomIntegers, intKey);
        printSearchResult(intKey, intIndex);
        System.out.println();

        String[] randomStrings = generateRandomStringArray(10, 1, 10);
        Arrays.sort(randomStrings); // Binary search requires sorted array

        System.out.println("Sorted array of strings:");
        printArray(randomStrings);

        String strKey = promptForString(); // Prompt user for string key
        int strIndex = binarySearch(randomStrings, strKey);
        printSearchResult(strKey, strIndex);

        System.out.println();

        Employee[] randomEmployees = generateRandomEmployees(5);
        Arrays.sort(randomEmployees); // Binary search requires sorted array

        System.out.println("Sorted array of employees:");
        printArray(randomEmployees);

        Employee empKey = promptForEmployee(); // Prompt user for employee key
        int empIndex = binarySearch(randomEmployees, empKey);
        printSearchResult(empKey, empIndex);
    }

    // Generic binarySearch method
    public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = key.compareTo(list[mid]);

            if (comparison == 0) {
                return mid; // Found
            } else if (comparison < 0) {
                high = mid - 1; // Search in left half
            } else {
                low = mid + 1; // Search in right half
            }
        }

        return -1; // Not found
    }

    public static Integer[] generateRandomIntArray(int size, int min, int max) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

    public static String[] generateRandomStringArray(int size, int minLength, int maxLength) {
        String[] array = new String[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int length = random.nextInt(maxLength - minLength + 1) + minLength;
            array[i] = generateRandomString(length);
        }
        return array;
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }

    public static Employee[] generateRandomEmployees(int size) {
        Employee[] employees = new Employee[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            employees[i] = new Employee(generateRandomString(5), generateRandomString(5), random.nextDouble() * 99000 + 1000);
        }
        return employees;
    }

    // Method to prompt user for an integer
    public static int promptForInteger() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer to search for in the array: ");
        return scanner.nextInt();
    }

    // Method to prompt user for a string
    public static String promptForString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to search for in the array: ");
        return scanner.nextLine();
    }

    // Method to prompt user for an Employee
    public static Employee promptForEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name of employee to search for: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name of employee to search for: ");
        String lastName = scanner.nextLine();
        return new Employee(firstName, lastName, 0); // Salary not needed for comparison
    }

    // Method to print elements of an array
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Method to print search result
    public static <E> void printSearchResult(E key, int index) {
        if (index != -1) {
            System.out.println(key + " found at index " + index);
        } else {
            System.out.println(key + " not found in the array");
        }
    }

    // Employee class implementing Comparable interface
    public static class Employee implements Comparable<Employee> {
        private String firstName;
        private String lastName;
        private double salary;

        public Employee(String firstName, String lastName, double salary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
        }

        @Override
        public int compareTo(Employee other) {
            // Compare first by last name, then by first name
            int lastNameComparison = this.lastName.compareTo(other.lastName);
            if (lastNameComparison == 0) {
                return this.firstName.compareTo(other.firstName);
            }
            return lastNameComparison;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}