public class InterpolationSearch {

    public static int interpolationSearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && x >= arr[low] && x <= arr[high]) {
            if (low == high) {
                if (arr[low] == x) return low;
                return -1;
            }

            int pos = low + (((high - low) / (arr[high] - arr[low])) * (x - arr[low]));

            if (arr[pos] == x)
                return pos;

            if (arr[pos] < x)
                low = pos + 1;
            else
                high = pos - 1;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arrUniforme = {10, 20, 30, 40, 50};
        int[] arrNaoUniforme = {5, 20, 21, 22, 35, 42, 50, 70};

        int x = 35;
        
        int resultUniforme = interpolationSearch(arrUniforme, x);
        System.out.println("Interpolation Search em arrUniforme: " + (resultUniforme != -1 ? "Elemento encontrado no índice " + resultUniforme : "Elemento não encontrado"));
        
        int resultNaoUniforme = interpolationSearch(arrNaoUniforme, x);
        System.out.println("Interpolation Search em arrNaoUniforme: " + (resultNaoUniforme != -1 ? "Elemento encontrado no índice " + resultNaoUniforme : "Elemento não encontrado"));

        // Comparação com Binary Search
        int resultBinaryUniforme = binarySearch(arrUniforme, x);
        System.out.println("Binary Search em arrUniforme: " + (resultBinaryUniforme != -1 ? "Elemento encontrado no índice " + resultBinaryUniforme : "Elemento não encontrado"));
        
        int resultBinaryNaoUniforme = binarySearch(arrNaoUniforme, x);
        System.out.println("Binary Search em arrNaoUniforme: " + (resultBinaryNaoUniforme != -1 ? "Elemento encontrado no índice " + resultBinaryNaoUniforme : "Elemento não encontrado"));
    }

    public static int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x)
                return mid;

            if (arr[mid] < x)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}

//Casos onde o Interpolation Search é mais eficiente

//1. Listas com distribuição uniforme:
//Quando os elementos da lista estão distribuídos de maneira uniforme, o Interpolation Search pode localizar o elemento com menos comparações do que o Binary Search.
//Por exemplo, em uma lista onde os elementos são incrementados de maneira constante (e.g., [10, 20, 30, 40, 50]), o Interpolation Search pode ser mais rápido.

//2. Listas grandes:
//Em listas muito grandes com distribuição uniforme, o Interpolation Search pode ser mais eficiente do que o Binary Search, pois pode pular seções maiores da lista em cada iteração.
