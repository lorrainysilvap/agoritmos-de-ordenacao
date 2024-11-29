//Implemente o Quick Sort utilizando diferentes critérios para escolha do pivô (ex.: primeiro elemento, último elemento, elemento do meio).
//Analise o desempenho do Quick Sort em listas quase ordenadas e completamente desordenadas.

public class QuickSort {

    public static void quickSortFirstPivot(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionFirstPivot(arr, low, high);
            quickSortFirstPivot(arr, low, pi - 1);
            quickSortFirstPivot(arr, pi + 1, high);
        }
    }

    public static int partitionFirstPivot(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low + 1;
        for (int j = low + 1; j <= high; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, low, i - 1);
        return i - 1;
    }

    public static void quickSortLastPivot(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionLastPivot(arr, low, high);
            quickSortLastPivot(arr, low, pi - 1);
            quickSortLastPivot(arr, pi + 1, high);
        }
    }

    public static int partitionLastPivot(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void quickSortMiddlePivot(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionMiddlePivot(arr, low, high);
            quickSortMiddlePivot(arr, low, pi - 1);
            quickSortMiddlePivot(arr, pi + 1, high);
        }
    }

    public static int partitionMiddlePivot(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivot = arr[mid];
        swap(arr, mid, high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        // Testando Quick Sort com pivô como primeiro elemento
        int[] arr1 = arr.clone();
        System.out.println("Array Original" );
        for (int num : arr1) System.out.print(num + " ");
        System.out.println();
        quickSortFirstPivot(arr1, 0, n - 1);
        System.out.println("Array Ordenado (Primeiro Pivô):");
        for (int num : arr1) System.out.print(num + " ");
        System.out.println();

        // Testando Quick Sort com pivô como último elemento
        int[] arr2 = arr.clone();
        System.out.println();
        quickSortLastPivot(arr2, 0, n - 1);
        System.out.println("Array Ordenado (Último Pivô):");
        for (int num : arr2) System.out.print(num + " ");
        System.out.println();

        // Testando Quick Sort com pivô como elemento do meio
        int[] arr3 = arr.clone();
        System.out.println();
        quickSortMiddlePivot(arr3, 0, n - 1);
        System.out.println("Array Ordenado (Meio Pivô):");
        for (int num : arr3) System.out.print(num + " ");
    }
}

//Análise de Desempenho

//Listas Quase Ordenadas:
//Primeiro Elemento como Pivô: Pode resultar em partições desbalanceadas e ter desempenho O(n^2).
//Último Elemento como Pivô: Semelhante ao primeiro elemento, pode resultar em partições desbalanceadas e ter desempenho O(n^2).
//Elemento do Meio como Pivô: Melhor escolha, tende a manter o desempenho em O(n log n).

//Listas Completamente Desordenadas:
//Desempenho médio para todas as estratégias tende a ser O(n log n).
//Elemento do Meio como Pivô geralmente fornece partições mais balanceadas.