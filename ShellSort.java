//Implemente o Shell Sort com diferentes sequências de intervalo (ex.: Shell, Knuth, Hibbard). Compare os tempos de execução.
//Explique como a escolha da sequência de intervalos afeta a eficiência do algoritmo.

public class ShellSort {

    public static void shellSort(int[] arr, int[] gaps) {
        int n = arr.length;

        for (int gap : gaps) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static int[] shellGaps(int n) {
        // Sequência original de Shell: n/2, n/4, ..., 1
        int[] gaps = new int[(int) (Math.log(n) / Math.log(2))];
        for (int i = 0; i < gaps.length; i++) {
            gaps[i] = n / (int) Math.pow(2, i + 1);
        }
        return gaps;
    }

    public static int[] knuthGaps(int n) {
        // Sequência de Knuth: 1, 4, 13, 40, ..., (3^k - 1)/2
        int k = 1;
        int[] gaps = new int[(int) (Math.log(n) / Math.log(3))];
        int i = gaps.length - 1;
        while (k < n / 3) {
            gaps[i--] = k;
            k = 3 * k + 1;
        }
        return gaps;
    }

    public static int[] hibbardGaps(int n) {
        // Sequência de Hibbard: 1, 3, 7, 15, ..., 2^k - 1
        int k = 1;
        int[] gaps = new int[(int) (Math.log(n + 1) / Math.log(2))];
        for (int i = 0; i < gaps.length; i++) {
            gaps[i] = (int) Math.pow(2, i + 1) - 1;
        }
        return gaps;
    }

    public static void main(String[] args) {
        int[] arr = {34, 8, 64, 51, 32, 21};

        int[] shellGaps = shellGaps(arr.length);
        int[] knuthGaps = knuthGaps(arr.length);
        int[] hibbardGaps = hibbardGaps(arr.length);

        long startTime, endTime;

        int[] arrCopy = arr.clone();
        startTime = System.nanoTime();
        shellSort(arrCopy, shellGaps);
        endTime = System.nanoTime();
        System.out.println("Shell Sort com gaps de Shell levou " + (endTime - startTime) + " nanosegundos");

        arrCopy = arr.clone();
        startTime = System.nanoTime();
        shellSort(arrCopy, knuthGaps);
        endTime = System.nanoTime();
        System.out.println("Shell Sort com gaps de Knuth levou " + (endTime - startTime) + " nanosegundos");

        arrCopy = arr.clone();
        startTime = System.nanoTime();
        shellSort(arrCopy, hibbardGaps);
        endTime = System.nanoTime();
        System.out.println("Shell Sort com gaps de Hibbard levou " + (endTime - startTime) + " nanosegundos");
    }
}

//Como a Escolha da Sequência de Intervalos Afeta a Eficiência

//Distribuição dos Gaps:
//Shell: Grandes saltos inicialmente, reduzindo pela metade. Pode não ser eficiente em listas grandes.
//Knuth: Salta grandes inicialmente e reduz gradualmente, proporcionando uma abordagem balanceada.
//Hibbard: Usa saltos potenciais que melhoram a performance em casos específicos.

//Número de Comparações:
//A sequência de Knuth tende a produzir menos comparações e movimentações de elementos em muitas situações práticas, tornando-o geralmente mais eficiente.

//Complexidade de Tempo:
//Todas essas variações têm complexidade média de tempo entre O(n log^2 n) e O(n^3/2), dependendo da sequência de intervalos usada.
