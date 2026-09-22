import java.io.FileOutputStream;

public class TesteArquivoSaida {
    public static void main(String[] args) throws Exception {
        Livro[] livros = {
                new Livro(1, "Dom Casmurro", "Machado de Assis", 1899, 25.90),
                new Livro(2, "O Cortiço", "Aluísio Azevedo", 1890, 22.50),
                new Livro(3, "Capitães da Areia", "Jorge Amado", 1937, 30.00)
        };

        FileOutputStream fos = new FileOutputStream("livros.dat");
        try (LivroOutputStream saida = new LivroOutputStream(fos, livros, livros.length)) {
            saida.enviarLivros();
        }
        fos.close();

        System.out.println("Livros gravados com sucesso em livros.dat");
    }
}
