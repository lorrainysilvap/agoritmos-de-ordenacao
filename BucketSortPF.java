//Implemente o Bucket Sort para ordenar uma lista de números em ponto flutuante no intervalo [0, 1). 
//Explique como os "baldes" são preenchidos e ordenados.

import java.util.*;

public class BucketSortPF {

    public static void bucketSort(float[] arr) {
        int n = arr.length;
        
        // Criar n baldes vazios
        @SuppressWarnings("unchecked")
        List<Float>[] buckets = new List[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Colocar os elementos nos baldes correspondentes
        for (float num : arr) {
            int bucketIndex = (int) (num * n);
            buckets[bucketIndex].add(num);
        }

        // Ordenar cada balde e juntar todos os elementos
        int index = 0;
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
            for (float num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        float[] arr = {0.78f, 0.17f, 0.39f, 0.26f, 0.72f, 0.94f, 0.21f, 0.12f, 0.23f, 0.68f};

        System.out.println("Array Original:");
        for (float num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        bucketSort(arr);

        System.out.println("Array Ordenado:");
        for (float num : arr) {
            System.out.print(num + " ");
        }
    }
}

//Criação dos Baldes:

//Criamos n baldes (listas vazias) onde n é o tamanho do array original.
//Cada balde representa um intervalo no intervalo [0, 1).

//Distribuição dos Elementos:
//Para cada elemento num no array original, calculamos o índice do balde usando a fórmula bucketIndex = (int) (num * n).
//O elemento é então adicionado ao balde correspondente.

//Ordenação dos Baldes:
//Ordenamos cada balde individualmente usando, por exemplo, o método Collections.sort().

//Concatenação dos Baldes:
//Após ordenar os baldes individualmente, juntamos os elementos de todos os baldes de volta no array original.