import java.io.FileInputStream;

public class TesteEntradaArquivo {
    public static void main(String[] args) throws Exception {
        Livro[] livros;
        try (FileInputStream fis = new FileInputStream("livros.dat");
             LivroInputStream entrada = new LivroInputStream(fis)) {
            livros = entrada.lerLivros();
        }

        System.out.println("Livros lidos do arquivo livros.dat:");
        for (Livro l : livros) {
            System.out.println(l);
        }
    }
}
