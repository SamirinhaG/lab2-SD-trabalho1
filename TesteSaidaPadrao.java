public class TesteSaidaPadrao {
    public static void main(String[] args) throws Exception {
        Livro[] livros = {
                new Livro(1, "Dom Casmurro", "Machado de Assis", 1899, 25.90),
                new Livro(2, "O Cortiço", "Aluísio Azevedo", 1890, 22.50)
        };

        System.err.println("Enviando " + livros.length + " livro(s) para System.out (bytes serializados):");

        try (LivroOutputStream saida = new LivroOutputStream(System.out, livros, livros.length)) {
            saida.enviarLivros();
        }
    }
}
