import java.util.*;

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();
        double[] weights = new double[numItems];
        System.out.print("Enter the weights of the items: ");
        for (int i = 0; i < numItems; i++) {
            weights[i] = scanner.nextDouble();
        }

        Arrays.sort(weights);
        reverseArray(weights);
        ArrayList<ArrayList<Double>> containers = new ArrayList<>();
        containers.add(new ArrayList<>());
        for (double weight : weights) {
            boolean placed = false;
            for (ArrayList<Double> container : containers) {
                double currentWeight = calculateCurrentWeight(container);
                if (currentWeight + weight <= 10.0) { // Assuming max capacity is 10.0
                    container.add(weight);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                ArrayList<Double> newContainer = new ArrayList<>();
                newContainer.add(weight);
                containers.add(newContainer);
            }
        }

        System.out.println("Number of containers needed: " + containers.size());
        for (int i = 0; i < containers.size(); i++) {
            System.out.println("Container " + (i + 1) + ": " + containers.get(i));
        }
    }

    private static void reverseArray(double[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            double temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    private static double calculateCurrentWeight(ArrayList<Double> container) {
        double sum = 0.0;
        for (double weight : container) {
            sum += weight;
        }
        return sum;
    }
}