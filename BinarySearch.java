//Implemente o algoritmo Binary Search em uma lista ordenada e encontre o índice de um elemento dado.
//Explique por que a lista deve estar ordenada para que o Binary Search funcione corretamente. Forneça exemplos.

public class BinarySearch {
    
    // Função Binary Search
    public static int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            
            if (arr[mid] < x) {
                low = mid + 1;
            } else if (arr[mid] > x) {
                high = mid - 1;
            } else {
                return mid; // Elemento encontrado
            }
        }
        
        return -1; // Elemento não encontrado
    }
    
    public static void main(String[] args) {
        int[] listaOrdenada = {2, 3, 4, 10, 40};
        int elemento = 10;
        
        int resultado = binarySearch(listaOrdenada, elemento);
        
        if (resultado != -1) {
            System.out.println("Elemento está presente no índice " + resultado + ".");
        } else {
            System.out.println("Elemento não encontrado na lista.");
        }
    }
};

// Explicação
//algoritmo Binary Search funciona dividindo a lista em duas partes repetidamente até encontrar o elemento desejado ou confirmar que ele não está presente. 
//Ele compara o elemento alvo com o elemento do meio da lista:
//Se o elemento alvo for igual ao elemento do meio, o algoritmo retorna o índice do elemento.
//Se o elemento alvo for menor que o elemento do meio, o algoritmo ignora a metade direita da lista.
//Se o elemento alvo for maior que o elemento do meio, o algoritmo ignora a metade esquerda da lista.

//Exemplo
//Imagine que temos a lista ordenada [2, 3, 4, 10, 40] e queremos encontrar o índice do número `10`:
//1. O elemento do meio é `4`. `10` é maior que `4`, então ignoramos a metade esquerda `[2, 3, 4]`.
//2. Agora trabalhamos com a lista [10, 40]. O elemento do meio é `10`, que é o nosso alvo. O algoritmo retorna o índice `3`.
//Se a lista não estivesse ordenada, como [4, 2, 10, 3, 40], o Binary Search não funcionaria corretamente porque ele dependente da ordem dos elementos para decidir qual metade da lista ignorar.
