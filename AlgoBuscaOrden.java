import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AlgoBuscaOrden {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
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
        return i + 1;
    }

    public static int linearSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] < x) {
                low = mid + 1;
            } else if (arr[mid] > x) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void compareSortingAlgorithms(int[] arr) {
        int[] arrCopy;
        long startTime, endTime;

        arrCopy = Arrays.copyOf(arr, arr.length);
        startTime = System.nanoTime();
        bubbleSort(arrCopy);
        endTime = System.nanoTime();
        System.out.println("Bubble Sort: " + (endTime - startTime) + " ns");

        arrCopy = Arrays.copyOf(arr, arr.length);
        startTime = System.nanoTime();
        selectionSort(arrCopy);
        endTime = System.nanoTime();
        System.out.println("Selection Sort: " + (endTime - startTime) + " ns");

        arrCopy = Arrays.copyOf(arr, arr.length);
        startTime = System.nanoTime();
        quickSort(arrCopy, 0, arr.length - 1);
        endTime = System.nanoTime();
        System.out.println("Quick Sort: " + (endTime - startTime) + " ns");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Escolha uma operação: \n1. Ordenação\n2. Busca");
        int choice = scanner.nextInt();

        if (choice == 1) {
            int[] arr = random.ints(10, 0, 100).toArray();
            System.out.println("Lista original: " + Arrays.toString(arr));
            System.out.println("Escolha um algoritmo de ordenação: \n1. Bubble Sort\n2. Selection Sort\n3. Quick Sort");
            int sortChoice = scanner.nextInt();
            switch (sortChoice) {
                case 1 -> bubbleSort(arr);
                case 2 -> selectionSort(arr);
                case 3 -> quickSort(arr, 0, arr.length - 1);
                default -> System.out.println("Opção inválida!");
            }
            System.out.println("Lista ordenada: " + Arrays.toString(arr));
            compareSortingAlgorithms(random.ints(10, 0, 100).toArray());

        } else if (choice == 2) {
            int[] arr = random.ints(10, 0, 100).sorted().toArray();
            System.out.println("Lista: " + Arrays.toString(arr));
            System.out.println("Escolha um algoritmo de busca: \n1. Busca Linear\n2. Busca Binária");
            int searchChoice = scanner.nextInt();
            System.out.print("Digite o elemento a ser buscado: ");
            int x = scanner.nextInt();
            int index = switch (searchChoice) {
                case 1 -> linearSearch(arr, x);
                case 2 -> binarySearch(arr, x);
                default -> {
                    System.out.println("Opção inválida!");
                    yield -1;
                }
            };
            if (index != -1) {
                System.out.println("Elemento encontrado no índice " + index);
            } else {
                System.out.println("Elemento não encontrado");
            }
        } else {
            System.out.println("Opção inválida!");
        }

        scanner.close();
    }
}
