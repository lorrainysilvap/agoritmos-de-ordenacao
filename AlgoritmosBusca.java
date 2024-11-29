//Construa uma tabela comparativa dos tempos de execução de Binary Search, Interpolation Search, 
//Jump Search e Exponential Search em listas de tamanhos diferentes.

import java.util.Arrays;

public class AlgoritmosBusca {

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000}; // Tamanhos diferentes de listas

        // Imprime o cabeçalho da tabela
        System.out.printf("%-15s %-20s %-25s %-20s %-25s\n", "Tamanho da Lista", "Binary Search (ns)", "Interpolation Search (ns)", "Jump Search (ns)", "Exponential Search (ns)");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        for (int size : sizes) {
            int[] arr = generateSortedArray(size);
            int x = arr[size / 2]; // Elemento a ser procurado (valor do meio)

            // Binary Search
            long startTime = System.nanoTime();
            binarySearch(arr, 0, size - 1, x);
            long endTime = System.nanoTime();
            long binarySearchTime = endTime - startTime;

            // Interpolation Search
            startTime = System.nanoTime();
            interpolationSearch(arr, x);
            endTime = System.nanoTime();
            long interpolationSearchTime = endTime - startTime;

            // Jump Search
            startTime = System.nanoTime();
            jumpSearch(arr, x);
            endTime = System.nanoTime();
            long jumpSearchTime = endTime - startTime;

            // Exponential Search
            startTime = System.nanoTime();
            exponentialSearch(arr, x);
            endTime = System.nanoTime();
            long exponentialSearchTime = endTime - startTime;

            // Imprime os resultados na forma de tabela
            System.out.printf("%-15d %-20d %-25d %-20d %-25d\n", size, binarySearchTime, interpolationSearchTime, jumpSearchTime, exponentialSearchTime);
        }
    }

    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int binarySearch(int[] arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            if (arr[mid] == x) {
                return mid;
            }

            if (arr[mid] > x) {
                return binarySearch(arr, l, mid - 1, x);
            }

            return binarySearch(arr, mid + 1, r, x);
        }

        return -1;
    }

    public static int interpolationSearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && x >= arr[low] && x <= arr[high]) {
            if (low == high) {
                if (arr[low] == x) return low;
                return -1;
            }

            int pos = low + (((high - low) / (arr[high] - arr[low])) * (x - arr[low]));

            if (arr[pos] == x) {
                return pos;
            }

            if (arr[pos] < x) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return -1;
    }

    public static int jumpSearch(int[] arr, int x) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (arr[Math.min(step, n) - 1] < x) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) {
                return -1;
            }
        }

        while (arr[prev] < x) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }

        if (arr[prev] == x) {
            return prev;
        }

        return -1;
    }

    public static int exponentialSearch(int[] arr, int x) {
        if (arr[0] == x) {
            return 0;
        }

        int n = arr.length;
        int i = 1;

        while (i < n && arr[i] <= x) {
            i *= 2;
        }

        return Arrays.binarySearch(arr, i / 2, Math.min(i, n), x);
    }
}
