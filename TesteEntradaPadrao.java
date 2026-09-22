public class TesteEntradaPadrao {
    public static void main(String[] args) throws Exception {
        try (LivroInputStream entrada = new LivroInputStream(System.in)) {
            Livro[] livros = entrada.lerLivros();

            System.out.println("Livros lidos de System.in:");
            for (Livro l : livros) {
                System.out.println(l);
            }
        }
    }
}
