// Ordene a mesma lista utilizando Shell Sort, Merge Sort, Selection Sort, Quick Sort, Bucket Sort e Radix Sort. 
//Registre os tempos de execução e número de comparações realizadas.

import java.util.*;

public class AlgoritmosOrden {

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000}; // Tamanhos diferentes de listas
        for (int size : sizes) {
            int[] arr = generateRandomArray(size);

            System.out.println("Tamanho da Lista: " + size);

            // Shell Sort
            int[] shellArr = arr.clone();
            long startTime = System.nanoTime();
            int shellComparisons = shellSort(shellArr);
            long endTime = System.nanoTime();
            long shellTime = endTime - startTime;
            System.out.printf("Shell Sort -> Tempo: %d ns, Comparações: %d\n", shellTime, shellComparisons);

            // Merge Sort
            int[] mergeArr = arr.clone();
            int[] mergeComparisons = {0};
            startTime = System.nanoTime();
            mergeSort(mergeArr, 0, size - 1, mergeComparisons);
            endTime = System.nanoTime();
            long mergeTime = endTime - startTime;
            System.out.printf("Merge Sort -> Tempo: %d ns, Comparações: %d\n", mergeTime, mergeComparisons[0]);

            // Selection Sort
            int[] selectionArr = arr.clone();
            startTime = System.nanoTime();
            int selectionComparisons = selectionSort(selectionArr);
            endTime = System.nanoTime();
            long selectionTime = endTime - startTime;
            System.out.printf("Selection Sort -> Tempo: %d ns, Comparações: %d\n", selectionTime, selectionComparisons);

            // Quick Sort
            int[] quickArr = arr.clone();
            int[] quickComparisons = {0};
            startTime = System.nanoTime();
            quickSort(quickArr, 0, size - 1, quickComparisons);
            endTime = System.nanoTime();
            long quickTime = endTime - startTime;
            System.out.printf("Quick Sort -> Tempo: %d ns, Comparações: %d\n", quickTime, quickComparisons[0]);

            // Bucket Sort
            int[] bucketArr = arr.clone();
            startTime = System.nanoTime();
            int bucketComparisons = bucketSort(bucketArr, size);
            endTime = System.nanoTime();
            long bucketTime = endTime - startTime;
            System.out.printf("Bucket Sort -> Tempo: %d ns, Comparações: %d\n", bucketTime, bucketComparisons);

            // Radix Sort
            int[] radixArr = arr.clone();
            startTime = System.nanoTime();
            int radixComparisons = radixSort(radixArr, size);
            endTime = System.nanoTime();
            long radixTime = endTime - startTime;
            System.out.printf("Radix Sort -> Tempo: %d ns, Comparações: %d\n", radixTime, radixComparisons);

            System.out.println();
        }
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size);
        }
        return arr;
    }

    public static int shellSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                    comparisons++;
                }
                arr[j] = temp;
                comparisons++;
            }
        }
        return comparisons;
    }

    public static void mergeSort(int[] arr, int left, int right, int[] comparisons) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, comparisons);
            mergeSort(arr, mid + 1, right, comparisons);
            merge(arr, left, mid, right, comparisons);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] comparisons) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) leftArray[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) rightArray[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            comparisons[0]++;
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
            comparisons[0]++;
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
            comparisons[0]++;
        }
    }

    public static int selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            comparisons++;
        }
        return comparisons;
    }

    public static void quickSort(int[] arr, int low, int high, int[] comparisons) {
        if (low < high) {
            int pi = partition(arr, low, high, comparisons);
            quickSort(arr, low, pi - 1, comparisons);
            quickSort(arr, pi + 1, high, comparisons);
        }
    }

    public static int partition(int[] arr, int low, int high, int[] comparisons) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons[0]++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        comparisons[0]++;
        return i + 1;
    }

    public static int bucketSort(int[] arr, int n) {
        if (n <= 0) return 0;

        List<Integer>[] buckets = new List[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int num : arr) {
            int bucketIndex = num / (n / 10);
            buckets[bucketIndex].add(num);
        }

        int comparisons = 0;
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
                comparisons++;
            }
        }
        return comparisons;
    }

    public static int radixSort(int[] arr, int n) {
        int m = getMax(arr, n);
        int comparisons = 0;

        for (int exp = 1; m / exp > 0; exp *= 10) {
            comparisons += countSort(arr, n, exp);
        }
        return comparisons;
    }

    public static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int countSort(int[] arr, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];
        int comparisons = 0;

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
            comparisons++;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
        return comparisons;
    }
}