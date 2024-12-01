//Implemente Bucket Sort para ordenar as notas de uma turma de alunos, classificadas entre 0 e 100. Em seguida, 
//utilize o Interpolation Search para encontrar um aluno com uma nota específica.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BODadosReais {

    // Implementação do Bucket Sort
    public static void bucketSort(int[] array, int maxNota) {
        int numeroBaldes = maxNota / 10 + 1;
        ArrayList<Integer>[] baldes = new ArrayList[numeroBaldes];

        // Inicializar baldes
        for (int i = 0; i < numeroBaldes; i++) {
            baldes[i] = new ArrayList<>();
        }

        // Distribuir os elementos nos baldes
        for (int num : array) {
            int indiceBalde = num / 10;
            baldes[indiceBalde].add(num);
        }

        // Ordenar elementos dentro de cada balde
        for (ArrayList<Integer> balde : baldes) {
            Collections.sort(balde);
        }

        // Concatenar os baldes ordenados
        int index = 0;
        for (ArrayList<Integer> balde : baldes) {
            for (int num : balde) {
                array[index++] = num;
            }
        }
    }

    // Implementação da Busca por Interpolação (Interpolation Search)
    public static int interpolationSearch(int[] array, int valorProcurado) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high && valorProcurado >= array[low] && valorProcurado <= array[high]) {
            if (low == high) {
                if (array[low] == valorProcurado) {
                    return low;
                }
                return -1;
            }

            // Fórmula da busca por interpolação
            int pos = low + ((valorProcurado - array[low]) * (high - low)) / (array[high] - array[low]);

            if (array[pos] == valorProcurado) {
                return pos;  // Valor encontrado
            }

            if (array[pos] < valorProcurado) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return -1;  // Valor não encontrado
    }

    // Função principal
    public static void main(String[] args) {
        int[] notas = {85, 45, 75, 90, 60, 55, 95, 70, 80, 50};
        System.out.println("Notas originais: " + Arrays.toString(notas));

        // Ordenação Bucket Sort
        bucketSort(notas, 100);
        System.out.println("Notas ordenadas com Bucket Sort: " + Arrays.toString(notas));

        // Busca por Interpolação
        int notaProcurada = 75;
        int resultadoBusca = interpolationSearch(notas, notaProcurada);
        if (resultadoBusca != -1) {
            System.out.println("Nota " + notaProcurada + " encontrada no índice " + resultadoBusca);
        } else {
            System.out.println("Nota " + notaProcurada + " não encontrada.");
        }
    }
}
