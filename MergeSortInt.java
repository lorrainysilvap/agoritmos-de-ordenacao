//Implemente o Merge Sort para ordenar uma lista de números inteiros. Explique o conceito de "dividir para conquistar" usado nesse algoritmo.

public class MergeSortInt {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Dividir a lista em duas metades
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Conquistar e combinar
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i)
            leftArray[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
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
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};

        System.out.println("Array Original:");
        for (int num : arr) System.out.print(num + " ");

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("\nArray Ordenado:");
        for (int num : arr) System.out.print(num + " ");
    }
}
//A lista é dividida repetidamente em duas metades até que cada sublista contenha apenas um elemento ou esteja vazia.
//No código, isso é feito pelas chamadas recursivas para mergeSort(arr, left, mid) e mergeSort(arr, mid + 1, right).
//As sublistas menores são combinadas (ou mescladas) de forma ordenada para formar listas maiores.
//No código, isso é realizado pela função merge(arr, left, mid, right), que combina duas sublistas ordenadas em uma única sublista ordenada.
