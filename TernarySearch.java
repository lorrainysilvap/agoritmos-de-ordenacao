//Desenvolva o algoritmo Ternary Search para localizar um elemento em uma lista ordenada. Compare seu desempenho com o Binary Search.
//Identifique situações em que o Ternary Search seria mais eficiente que o Binary Search.

public class TernarySearch {

    public static int ternarySearch(int[] arr, int l, int r, int x) {
        if (r >= l) {
            // Calcula os dois pontos médios
            int mid1 = l + (r - l) / 3;
            int mid2 = r - (r - l) / 3;

            // Verifica se o elemento está em algum dos pontos médios
            if (arr[mid1] == x) {
                return mid1;
            }
            if (arr[mid2] == x) {
                return mid2;
            }

            // Decide em qual dos três sub-arrays fazer a busca
            if (x < arr[mid1]) {
                return ternarySearch(arr, l, mid1 - 1, x);
            } else if (x > arr[mid2]) {
                return ternarySearch(arr, mid2 + 1, r, x);
            } else {
                return ternarySearch(arr, mid1 + 1, mid2 - 1, x);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int n = arr.length;
        int x = 5;

        int result = ternarySearch(arr, 0, n - 1, x);
        System.out.println("Elemento encontrado no índice: " + result);
    }
}

//Comparação de Desempenho

//Complexidade de Tempo:

//Binary Search: 
//Complexidade de tempo: \(O(\log_2 n)\).
//Divide a lista em duas partes e busca na metade relevante.
  
//Ternary Search: 
//Complexidade de tempo: \(O(\log_3 n)\).
//Divide a lista em três partes e busca no terço relevante.

//Embora \(O(\log_3 n)\) pareça mais eficiente que \(O(\log_2 n)\), 
//na prática, o Ternary Search faz mais comparações por iteração devido à divisão em três partes, o que pode torná-lo mais lento.

//Situações em que o Ternary Search é mais eficiente que o Binary Search

//Listas Muito Grandes:
//Para listas extremamente grandes, o Ternary Search pode reduzir o número de iterações necessárias, 
//embora o custo adicional por iteração devido ao maior número de comparações possa compensar isso.

//Hardware Específico:
//Em sistemas onde a divisão e comparação em três partes pode ser feita de forma mais eficiente devido a paralelismo ou otimizações específicas de hardware, 
//o Ternary Search pode ter vantagem.