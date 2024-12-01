//Use o Binary Search para procurar um livro específico por ISBN em uma lista ordenada de registros de biblioteca.

import java.util.Arrays;

public class BuscaLivroISBN {

    // Busca Binária para ISBN
    public static int buscaBinaria(String[] array, String isbn) {
        int esquerda = 0;
        int direita = array.length - 1;
        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            int comparacao = array[meio].compareTo(isbn);
            if (comparacao == 0) {
                return meio;  // ISBN encontrado
            } else if (comparacao < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return -1;  // Não encontrado
    }

    // Função principal
    public static void main(String[] args) {
        // Lista de registros de biblioteca ordenada por ISBN
        String[] registros = {
            "978-3-16-148410-0", "978-0-262-13472-9", "978-0-201-53082-7", 
            "978-0-596-52068-7", "978-1-491-92761-9"
        };
        System.out.println("Registros de Biblioteca: " + Arrays.toString(registros));
        
        // ISBN a ser procurado
        String isbnProcurado = "978-0-262-13472-9";
        
        // Realizar a busca binária
        int resultadoBusca = buscaBinaria(registros, isbnProcurado);
        if (resultadoBusca != -1) {
            System.out.println("Livro com ISBN '" + isbnProcurado + "' encontrado no índice " + resultadoBusca);
        } else {
            System.out.println("Livro com ISBN '" + isbnProcurado + "' não encontrado.");
        }
    }
}
