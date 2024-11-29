//Implemente o Radix Sort para ordenar uma lista de números inteiros. 
//Teste-o com números de diferentes tamanhos (ex.: 2 dígitos, 5 dígitos, 10 dígitos).
//Explique como o algoritmo lida com bases diferentes (ex.: base 10 e base 2).

import java.util.Arrays;

public class RadixSort {

    // Função para obter o dígito máximo em arr[]
    static int getMax(int[] arr, int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // Função para fazer a contagem de classificação de acordo com o dígito representado por exp.
    static void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n]; // array de saída
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Armazenar a contagem de ocorrências nos count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Alterar count[i] para que count[i] contenha a posição real desse dígito na saída[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Construir o array de saída
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copiar o array de saída para arr[], de modo que arr[] agora contenha números ordenados de acordo com o dígito atual
        System.arraycopy(output, 0, arr, 0, n);
    }

    // Função principal para ordenar arr[] de tamanho n usando o Radix Sort
    static void radixSort(int[] arr, int n) {
        int m = getMax(arr, n);

        // Fazer a contagem de classificação para cada dígito. Em vez de passar o número do dígito, exp é passado. 
        //Exp é 10^i, onde i é o número atual do dígito
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        //dois dígitos
        //int[] arr = {45, 23, 12, 67, 34, 78, 90, 11};
        //cinco dígitos
        //int[] arr = {12345, 67890, 54321, 23456, 98765, 45678, 34567};
        //dez dígitos
        //int[] arr = {1234567890, 9876543210, 1230984567, 7654321098, 9081726354, 1092837465, 5647382910};
        int n = arr.length;

        System.out.println("Array Original:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();

        radixSort(arr, n);

        System.out.println("Array Ordenado:");
        for (int num : arr) System.out.print(num + " ");
    }
}

//O Radix Sort pode ser adaptado para diferentes bases (ex.: base 10 e base 2):

//Base 10 (Decimal)
//A implementação acima é para a base 10.
//Utiliza os dígitos de 0 a 9.
//Para cada iteração, organiza os números com base no dígito em uma posição específica, começando pelo menos significativo (unidades) e movendo-se para o mais significativo.

//Base 2 (Binária)
//Para ordenar em base 2, utilizamos apenas dois "dígitos" (0 e 1).
//A contagem é feita com base nos bits individuais.
