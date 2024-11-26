//Adapte o Bucket Sort para ordenar números inteiros positivos em intervalos maiores.

import java.util.*;

public class BucketSortIP {

    public static void bucketSort(int[] arr, int maxValue) {
        int n = arr.length;
        int numBuckets = (int) Math.sqrt(n);

        // Criar baldes
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[numBuckets];

        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Distribuir os elementos nos baldes
        for (int num : arr) {
            int bucketIndex = (int) ((float) num / maxValue * (numBuckets - 1));
            buckets[bucketIndex].add(num);
        }

        // Ordenar cada balde e juntar todos os elementos
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {78, 17, 39, 26, 72, 94, 21, 12, 23, 68};

        System.out.println("Array Original:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        int maxValue = Arrays.stream(arr).max().getAsInt(); // Valor máximo no array
        bucketSort(arr, maxValue);

        System.out.println("Array Ordenado:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

