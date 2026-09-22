import java.net.ServerSocket;
import java.net.Socket;

public class ServidorStreamTCP {
    public static void main(String[] args) throws Exception {
        int porta = 5000;
        ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Servidor de streams aguardando conexão na porta " + porta + "...");

        Socket cliente = servidor.accept();
        System.out.println("Cliente conectado: " + cliente.getInetAddress());

        Livro[] livros = {
                new Livro(1, "Dom Casmurro", "Machado de Assis", 1899, 25.90),
                new Livro(2, "O Cortiço", "Aluísio Azevedo", 1890, 22.50)
        };

        try (LivroOutputStream saida = new LivroOutputStream(cliente.getOutputStream(), livros, livros.length)) {
            saida.enviarLivros();
        }
        System.out.println("Livros enviados ao cliente.");

        cliente.close();
        servidor.close();
    }
}
