//Desenvolva o Selection Sort e acompanhe cada iteração mostrando como a lista é organizada passo a passo.
//Analise o desempenho do Selection Sort em listas pequenas, médias e grandes.

public class SelectionSort {

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;

            // Encontrar o menor elemento no array não ordenado
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            // Trocar o menor elemento encontrado com o primeiro elemento não ordenado
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;

            // Mostrar o array após cada iteração
            System.out.print("Iteração " + (i + 1) + ": ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        
        System.out.println("Array Original:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        selectionSort(arr);

        System.out.println("Array Ordenado:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

//Análise de Desempenho

//Complexidade de Tempo: 
//O Selection Sort tem uma complexidade de tempo de O(n^2) no pior, médio e melhor caso. 
//Isso ocorre porque ele sempre percorre n^2 iterações, independentemente da ordem inicial dos elementos.

//Listas Pequenas:
//Para listas pequenas (e.g., 10 elementos), o Selection Sort pode ser suficientemente rápido. 
//A simplicidade do algoritmo o torna fácil de implementar e entender.

//Listas Médias:
//Para listas de tamanho médio (e.g., 100 a 1.000 elementos), o desempenho começa a ser impactado pela complexidade quadrática. 
//Algoritmos mais eficientes, como o Merge Sort ou o Quick Sort, são geralmente preferidos.

//Listas Grandes:
//Para listas grandes (e.g., milhares de elementos), o Selection Sort torna-se impraticável devido ao grande número de comparações e trocas necessárias. 
//Algoritmos de ordenação mais eficientes são recomendados para esses casos.