//Desenvolva o algoritmo Jump Search e determine o tamanho ideal do "salto" para uma lista de tamanho.
//Compare o tempo de execução do Jump Search com o Binary Search em listas de diferentes tamanhos.

import java.util.Arrays;

public class JumpSearch {

    public static int jumpSearch(int[] arr, int x) {
        int n = arr.length;
        int step = (int)Math.floor(Math.sqrt(n)); // Tamanho do salto

        int prev = 0;
        while (arr[Math.min(step, n) - 1] < x) {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
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

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        int x = 55;

        int index = jumpSearch(arr, x);
        System.out.println("Número " + x + " está no índice " + index);

        // Comparação de tempo de execução
        long startTime, endTime;

        // Teste com Jump Search
        startTime = System.nanoTime();
        index = jumpSearch(arr, x);
        endTime = System.nanoTime();
        System.out.println("Jump Search levou " + (endTime - startTime) + " nanosegundos");

        // Teste com Binary Search
        startTime = System.nanoTime();
        index = Arrays.binarySearch(arr, x);
        endTime = System.nanoTime();
        System.out.println("Binary Search levou " + (endTime - startTime) + " nanosegundos");
    }
}

//Determinação do Tamanho Ideal do Salto
//O tamanho ideal do salto para uma lista de tamanho n é geralmente a raiz quadrada de n, ou seja, Math.sqrt(n). 
//Isso minimiza o número total de operações de comparação em média.

//Comparação de Tempo de Execução 
//O Jump Search pode ser mais eficiente do que o Binary Search em listas muito grandes, especialmente quando o elemento procurado está perto do início da lista. 
//O Binary Search, por outro lado, é geralmente mais consistente em termos de desempenho para listas ordenadas de qualquer tamanho e distribuição.