import java.net.Socket;

public class ClienteStreamTCP {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int porta = 5000;

        Socket socket = new Socket(host, porta);
        System.out.println("Conectado ao servidor " + host + ":" + porta);

        try (LivroInputStream entrada = new LivroInputStream(socket.getInputStream())) {
            Livro[] livros = entrada.lerLivros();

            System.out.println("Livros recebidos:");
            for (Livro l : livros) {
                System.out.println(l);
            }
        }
        socket.close();
    }
}
