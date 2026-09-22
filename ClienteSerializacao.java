import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteSerializacao {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int porta = 5001;

        Socket socket = new Socket(host, porta);

        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

        Livro novoLivro = new Livro(10, "Grande Sertão: Veredas", "Guimarães Rosa", 1956, 45.00);

        Mensagem request = new Mensagem("REQUEST", "CADASTRAR", novoLivro, null, null);
        saida.writeObject(request);
        saida.flush();

        Mensagem reply = (Mensagem) entrada.readObject();
        System.out.println("Resposta do servidor: " + reply);

        socket.close();
    }
}
