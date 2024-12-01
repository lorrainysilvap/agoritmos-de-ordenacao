//Identifique quais algoritmos de ordenação da lista são estáveis e explique o que isso significa. Demonstre com exemplos.


import java.util.Arrays;

public class OrdenListas {

    // Ordenação por Inserção (Estável)
    public static void ordenacaoInsercao(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int chave = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = chave;  // Complexidade de Tempo: O(n^2) | Complexidade de Espaço: O(1)
        }
    }

    // Ordenação por Seleção (Instável)
    public static void ordenacaoSelecao(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }
            int temp = array[indiceMinimo];
            array[indiceMinimo] = array[i];
            array[i] = temp;  // Complexidade de Tempo: O(n^2) | Complexidade de Espaço: O(1)
        }
    }

    // Ordenação por Bolha (Estável)
    public static void ordenacaoBolha(int[] array) {
        boolean trocado;
        do {
            trocado = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    trocado = true;
                }
            }
        } while (trocado);  // Complexidade de Tempo: O(n^2) | Complexidade de Espaço: O(1)
    }

    // Merge Sort (Estável)
    public static void mergeSort(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergeSort(array, esquerda, meio);
            mergeSort(array, meio + 1, direita);
            merge(array, esquerda, meio, direita);
        }
    }

    public static void merge(int[] array, int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = array[esquerda + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[meio + 1 + j];
        }

        int i = 0, j = 0;
        int k = esquerda;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
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

    // Quicksort (Instável)
    public static void quicksort(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int pi = particionar(array, esquerda, direita);
            quicksort(array, esquerda, pi - 1);
            quicksort(array, pi + 1, direita);
        }
    }

    public static int particionar(int[] array, int esquerda, int direita) {
        int pivo = array[direita];
        int i = (esquerda - 1);
        for (int j = esquerda; j < direita; j++) {
            if (array[j] <= pivo) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[direita];
        array[direita] = temp;
        return i + 1;
    }

    // Função principal para demonstrar os algoritmos
    public static void main(String[] args) {
        int[] arrayOriginal = {5, 2, 9, 2, 7};

        // Ordenação por Inserção
        int[] arrayInsercao = arrayOriginal.clone();
        ordenacaoInsercao(arrayInsercao);
        System.out.println("Ordenação por Inserção (Estável): " + Arrays.toString(arrayInsercao));

        // Ordenação por Seleção
        int[] arraySelecao = arrayOriginal.clone();
        ordenacaoSelecao(arraySelecao);
        System.out.println("Ordenação por Seleção (Instável): " + Arrays.toString(arraySelecao));

        // Ordenação por Bolha
        int[] arrayBolha = arrayOriginal.clone();
        ordenacaoBolha(arrayBolha);
        System.out.println("Ordenação por Bolha (Estável): " + Arrays.toString(arrayBolha));

        // Merge Sort
        int[] arrayMergeSort = arrayOriginal.clone();
        mergeSort(arrayMergeSort, 0, arrayMergeSort.length - 1);
        System.out.println("Merge Sort (Estável): " + Arrays.toString(arrayMergeSort));

        // Quicksort
        int[] arrayQuicksort = arrayOriginal.clone();
        quicksort(arrayQuicksort, 0, arrayQuicksort.length - 1);
        System.out.println("Quicksort (Instável): " + Arrays.toString(arrayQuicksort));
    }
}
