import java.util.ArrayList;
import java.util.Random;

public class Problem1 {

    public static void main(String[] args) {
        ArrayList<Integer> intList = generateRandomIntArrayList(100, 0, 9);
        System.out.println("Original list of integers:");
        System.out.println(intList);

        ArrayList<Integer> uniqueIntList = removeDuplicates(intList);
        System.out.println("List of unique integers:");
        System.out.println(uniqueIntList);
        System.out.println();

        ArrayList<Character> charList = generateRandomCharArrayList(100);
        System.out.println("Original list of characters:");
        System.out.println(charList);

        ArrayList<Character> uniqueCharList = removeDuplicates(charList);
        System.out.println("List of unique characters:");
        System.out.println(uniqueCharList);
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> uniqueList = new ArrayList<>();

        for (E element : list) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }
        return uniqueList;
    }

    public static ArrayList<Integer> generateRandomIntArrayList(int size, int min, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int num = random.nextInt(max - min + 1) + min;
            list.add(num);
        }
        return list;
    }

    public static ArrayList<Character> generateRandomCharArrayList(int size) {
        ArrayList<Character> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            char c = (char) (random.nextInt(26) + 'a'); // Generates 'a' to 'z'
            list.add(c);
        }
        return list;
    }
}
