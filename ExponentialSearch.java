//Implemente o algoritmo Exponential Search para localizar um elemento em uma lista ordenada. 
//Explique como ele combina elementos do Jump Search e Binary Search.
//Analise o desempenho do Exponential Search em listas muito grandes e pequenas.

import java.util.Arrays;

public class ExponentialSearch {

    public static int exponentialSearch(int[] arr, int x) {
        int n = arr.length;
        
        // Caso base
        if (arr[0] == x) {
            return 0;
        }

        // Encontrar o intervalo para a busca binária
        int i = 1;
        while (i < n && arr[i] <= x) {
            i = i * 2;
        }

        // Realizar a busca binária no intervalo encontrado
        return Arrays.binarySearch(arr, i / 2, Math.min(i, n), x);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40, 60, 80};
        int x = 10;

        int result = exponentialSearch(arr, x);
        System.out.println("Elemento encontrado no índice: " + result);
        
        // Comparação de tempo de execução
        long startTime, endTime;

        // Teste com Exponential Search
        startTime = System.nanoTime();
        result = exponentialSearch(arr, x);
        endTime = System.nanoTime();
        System.out.println("Exponential Search levou " + (endTime - startTime) + " nanosegundos");

        // Teste com Binary Search
        startTime = System.nanoTime();
        result = Arrays.binarySearch(arr, x);
        endTime = System.nanoTime();
        System.out.println("Binary Search levou " + (endTime - startTime) + " nanosegundos");
    }
}

//Como ele combina Jump Search e Binary Search
//O Exponential Search utiliza um método semelhante ao Jump Search para encontrar o intervalo onde o elemento `x` pode estar. 
//Ele faz "saltos" exponenciais (multiplicando o índice por 2) para rapidamente identificar um intervalo.
//Após determinar o intervalo potencial onde `x` pode estar, o Exponential Search usa a Binary Search dentro desse intervalo para encontrar a posição exata do elemento.

//Análise de Desempenho
//O Exponential Search é eficiente em listas muito grandes porque reduz o número de elementos a serem verificados antes de realizar a Binary Search. 
//Em listas grandes, o tempo de busca é logarítmico, semelhante ao Binary Search, mas pode ser mais rápido em alguns casos devido à maneira como reduz o intervalo inicialmente.
//Em listas pequenas, o Exponential Search não apresenta grandes vantagens sobre a Binary Search, pois a vantagem do salto exponencial não é tão significativa quando o número total de elementos é pequeno.
