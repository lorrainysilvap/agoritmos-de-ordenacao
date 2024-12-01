//Analise a complexidade de tempo e espaço de cada algoritmo de busca e ordenação listados.

import java.util.Arrays;

public class ComplexTempo {
    
    // Busca Linear
    public static int buscaLinear(int[] array, int chave) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == chave) {
                return i;  // Complexidade de Tempo: O(n) | Complexidade de Espaço: O(1)
            }
        }
        return -1;  // Não encontrado
    }
    
    // Busca Binária
    public static int buscaBinaria(int[] array, int chave) {
        int esquerda = 0;
        int direita = array.length - 1;
        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            if (array[meio] == chave) {
                return meio;  // Complexidade de Tempo: O(log n) | Complexidade de Espaço: O(1)
            } else if (array[meio] < chave) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return -1;  // Não encontrado
    }
    
    // Ordenação por Inserção
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
    
    // Ordenação por Seleção
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
    
    // Ordenação por Bolha
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
    
    // Ordenação Merge Sort
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
    
    // Ordenação Quicksort
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

    // Função principal
    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11};
        System.out.println("Array original: " + Arrays.toString(array));
        
        // Busca Linear
        int resultadoBuscaLinear = buscaLinear(array, 22);
        System.out.println("Busca Linear: Elemento encontrado no índice " + resultadoBuscaLinear);
        
        // Busca Binária (necessário array ordenado)
        Arrays.sort(array);
        int resultadoBuscaBinaria = buscaBinaria(array, 22);
        System.out.println("Busca Binária: Elemento encontrado no índice " + resultadoBuscaBinaria);
        
        // Ordenação por Inserção
        int[] arrayInsercao = {64, 25, 12, 22, 11};
        ordenacaoInsercao(arrayInsercao);
        System.out.println("Ordenação por Inserção: " + Arrays.toString(arrayInsercao));
        
        // Ordenação por Seleção
        int[] arraySelecao = {64, 25, 12, 22, 11};
        ordenacaoSelecao(arraySelecao);
        System.out.println("Ordenação por Seleção: " + Arrays.toString(arraySelecao));
        
        // Ordenação por Bolha
        int[] arrayBolha = {64, 25, 12, 22, 11};
        ordenacaoBolha(arrayBolha);
        System.out.println("Ordenação por Bolha: " + Arrays.toString(arrayBolha));
        
        // Ordenação Merge Sort
        int[] arrayMergeSort = {64, 25, 12, 22, 11};
        mergeSort(arrayMergeSort, 0, arrayMergeSort.length - 1);
        System.out.println("Ordenação Merge Sort: " + Arrays.toString(arrayMergeSort));
        
        // Ordenação Quicksort
        int[] arrayQuicksort = {64, 25, 12, 22, 11};
        quicksort(arrayQuicksort, 0, arrayQuicksort.length - 1);
        System.out.println("Ordenação Quicksort: " + Arrays.toString(arrayQuicksort));
    }
}
