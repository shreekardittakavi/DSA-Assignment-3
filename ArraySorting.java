
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ArraySorting {

    public static Integer[] generateNumbers(int size) {
        Random rand = new Random();
        Integer[] randNums = new Integer[size];
        for (int i = 0; i < randNums.length; i++) {
            randNums[i] = rand.nextInt(100);
        }
        return randNums;
    }

    public static ArrayList<Double> timeSorting(int size) {
        Integer[] nums;
        long start, end;
        SortingAlgorithms<Integer> algos = new SortingAlgorithms<>();
        ArrayList<Double> times = new ArrayList<>();

        System.out.println("Insertion sort:");
        nums = generateNumbers(size);
        start = System.nanoTime();
        algos.insertionSort(nums);
        end = System.nanoTime();
        times.add((end - start) / 1_000_000.0);
        System.out.println(times.getLast());

        System.out.println("Heap sort:");
        nums = generateNumbers(size);
        start = System.nanoTime();
        algos.heapSort(nums);
        end = System.nanoTime();
        times.add((end - start) / 1_000_000.0);
        System.out.println(times.getLast());

        System.out.println("Merge sort:");
        nums = generateNumbers(size);
        start = System.nanoTime();
        algos.mergeSort(nums);
        end = System.nanoTime();
        times.add((end - start) / 1_000_000.0);
        System.out.println(times.getLast());

        System.out.println("Quick sort (no cutoff):");
        nums = generateNumbers(size);
        start = System.nanoTime();
        algos.quickSort(nums, 1);
        end = System.nanoTime();
        times.add((end - start) / 1_000_000.0);
        System.out.println(times.getLast());

        System.out.println("Quick sort (cutoff 10):");
        nums = generateNumbers(size);
        start = System.nanoTime();
        algos.quickSort(nums, 10);
        end = System.nanoTime();
        times.add((end - start) / 1_000_000.0);
        System.out.println(times.getLast());

        System.out.println("Quick sort (cutoff 50):");
        nums = generateNumbers(size);
        start = System.nanoTime();
        algos.quickSort(nums, 50);
        end = System.nanoTime();
        times.add((end - start) / 1_000_000.0);
        System.out.println(times.getLast());

        System.out.println("Quick sort (cutoff 200):");
        nums = generateNumbers(size);
        start = System.nanoTime();
        algos.quickSort(nums, 200);
        end = System.nanoTime();
        times.add((end - start) / 1_000_000.0);
        System.out.println(times.getLast());

        return times;
    }

    public static void warmup() {
        int j = 0;
        for (int i = 0; i < 1_000_000; i++)
            j -= (++j);
    }

    public static void logResults(ArrayList<Double> times, FileWriter fW) throws IOException {
        for (Double time: times)
            fW.append(String.format("%.4f,", time));
        fW.append("\n");
        fW.flush();
    }

    public static void main(String[] args) throws IOException {
        try (FileWriter log = new FileWriter("results.csv", false)) {
            log.write("Insertion,Heap,Merge,Quick,Quick10,Quick50,Quick200,\n");

            System.out.println("Warming up...");
            warmup();
            System.out.println("50 elements:");
            logResults(timeSorting(50), log);
            System.out.println("\n500 elements:");
            logResults(timeSorting(500), log);
            System.out.println("\n1000 elements:");
            logResults(timeSorting(1000), log);
            System.out.println("\n2000 elements:");
            logResults(timeSorting(2000), log);
            System.out.println("\n5000 elements:");
            logResults(timeSorting(5000), log);
            System.out.println("\n10000 elements:");
            logResults(timeSorting(10000), log);
        }
    }
}
