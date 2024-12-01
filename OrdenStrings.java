//Adapte os algoritmos de ordenação (Merge Sort e Quick Sort) para ordenar palavras em ordem alfabética.
//Utilize Binary Search para verificar se uma palavra específica está presente em uma lista de palavras ordenadas.

import java.util.Arrays;

public class OrdenStrings {

    // Merge Sort para ordenar strings em ordem alfabética
    public static void mergeSort(String[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergeSort(array, esquerda, meio);
            mergeSort(array, meio + 1, direita);
            merge(array, esquerda, meio, direita);
        }
    }

    public static void merge(String[] array, int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = array[esquerda + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[meio + 1 + j];
        }

        int i = 0, j = 0;
        int k = esquerda;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    // Quicksort para ordenar strings em ordem alfabética
    public static void quicksort(String[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int pi = particionar(array, esquerda, direita);
            quicksort(array, esquerda, pi - 1);
            quicksort(array, pi + 1, direita);
        }
    }

    public static int particionar(String[] array, int esquerda, int direita) {
        String pivo = array[direita];
        int i = (esquerda - 1);
        for (int j = esquerda; j < direita; j++) {
            if (array[j].compareTo(pivo) <= 0) {
                i++;
                String temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        String temp = array[i + 1];
        array[i + 1] = array[direita];
        array[direita] = temp;
        return i + 1;
    }

    // Busca Binária para strings
    public static int buscaBinaria(String[] array, String chave) {
        int esquerda = 0;
        int direita = array.length - 1;
        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            int comparacao = array[meio].compareTo(chave);
            if (comparacao == 0) {
                return meio;  // Elemento encontrado
            } else if (comparacao < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return -1;  // Não encontrado
    }

    // Função principal
    public static void main(String[] args) {
        String[] array = {"banana", "maçã", "laranja", "uva", "abacaxi"};
        System.out.println("Array original: " + Arrays.toString(array));

        // Ordenação Merge Sort
        mergeSort(array, 0, array.length - 1);
        System.out.println("Ordenação Merge Sort: " + Arrays.toString(array));

        // Ordenação Quicksort
        String[] arrayQuicksort = {"banana", "maçã", "laranja", "uva", "abacaxi"};
        quicksort(arrayQuicksort, 0, arrayQuicksort.length - 1);
        System.out.println("Ordenação Quicksort: " + Arrays.toString(arrayQuicksort));

        // Busca Binária (necessário array ordenado)
        int resultadoBuscaBinaria = buscaBinaria(array, "laranja");
        System.out.println("Busca Binária: Palavra 'laranja' encontrada no índice " + resultadoBuscaBinaria);
    }
}
